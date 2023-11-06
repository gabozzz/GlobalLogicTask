package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        // Ruta al archivo de la base de datos H2
        String h2DatabasePath = "db/";

        File h2DatabaseDirectory = new File(h2DatabasePath);
        if (h2DatabaseDirectory.exists()) {
            deleteDirectory(h2DatabaseDirectory);
        }
        SpringApplication.run(TestApplication.class, args);
    }

    private static void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }

}
