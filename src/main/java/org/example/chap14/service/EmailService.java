package org.example.chap14.service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailService {

    public void sendEmail(
            String to,
            String firstName)
            throws MessagingException {


        String from = System.getenv("SMTP_FROM");
        String username = System.getenv("SMTP_USERNAME");
        String password = System.getenv("SMTP_PASSWORD");

        Properties properties = new Properties();

        properties.put(
                "mail.smtp.host",
                "smtp-relay.brevo.com"
        );

        properties.put(
                "mail.smtp.port",
                "587"
        );

        properties.put(
                "mail.smtp.auth",
                "true"
        );

        properties.put(
                "mail.smtp.starttls.enable",
                "true"
        );

        Session session =
                Session.getInstance(
                        properties,
                        new Authenticator() {

                            @Override
                            protected PasswordAuthentication
                            getPasswordAuthentication() {

                                return new PasswordAuthentication(
                                        username,
                                        password
                                );
                            }
                        }
                );

        Message message =
                new MimeMessage(session);

        message.setFrom(
                new InternetAddress(from)
        );

        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)
        );

        message.setSubject(
                "Welcome to our email list"
        );

        String body =
                "Dear " + firstName + ",\n\n"
                        + "Thanks for joining our email list. "
                        + "We'll make sure to send you "
                        + "announcements about new products "
                        + "and promotions.\n\n"
                        + "Have a great day and thanks again!\n\n"
                        + "Kelly Slivkoff\n"
                        + "Mike Murach & Associates";

        message.setText(body);

        Transport.send(message);
    }
}