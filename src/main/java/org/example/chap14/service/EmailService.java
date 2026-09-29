package org.example.chap14.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EmailService {

    public void sendEmail(String to, String firstName)
            throws IOException, InterruptedException {

        String apiKey = System.getenv("BREVO_API_KEY");
        String from = System.getenv("SMTP_FROM");

        String body =
                "{"
                        + "\"sender\":{"
                        + "\"email\":\"" + escapeJson(from) + "\""
                        + "},"
                        + "\"to\":[{"
                        + "\"email\":\"" + escapeJson(to) + "\""
                        + "}],"
                        + "\"subject\":\"Welcome to our email list\","
                        + "\"textContent\":\""
                        + escapeJson(
                        "Dear " + firstName + ",\n\n"
                                + "Thanks for joining our email list. "
                                + "We'll make sure to send you "
                                + "announcements about new products "
                                + "and promotions.\n\n"
                                + "Have a great day and thanks again!\n\n"
                                + "Kelly Slivkoff\n"
                                + "Mike Murach & Associates"
                )
                        + "\""
                        + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.brevo.com/v3/smtp/email"))
                .header("accept", "application/json")
                .header("api-key", apiKey)
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        long start = System.currentTimeMillis();

        HttpResponse<String> response =
                client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        long end = System.currentTimeMillis();

        System.out.println(
                "BREVO API TIME: " + (end - start) + " ms"
        );

        System.out.println(
                "BREVO STATUS: " + response.statusCode()
        );

        System.out.println(
                "BREVO RESPONSE: " + response.body()
        );

        if (response.statusCode() < 200
                || response.statusCode() >= 300) {

            throw new IOException(
                    "Brevo API error: HTTP "
                            + response.statusCode()
                            + " - "
                            + response.body()
            );
        }
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}