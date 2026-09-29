package org.example.chap14.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class JPAUtil {

    private static final EntityManagerFactory emf;

    static {
        Map<String, Object> properties = new HashMap<>();

        String dbUrl = System.getenv("DB_URL");
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        if (dbUrl != null && !dbUrl.isBlank()) {
            properties.put("jakarta.persistence.jdbc.url", dbUrl);
            properties.put("jakarta.persistence.jdbc.user", dbUsername);
            properties.put("jakarta.persistence.jdbc.password", dbPassword);
        }

        emf = Persistence.createEntityManagerFactory("ch14PU", properties);
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }
}