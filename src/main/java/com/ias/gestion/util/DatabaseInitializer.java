package com.ias.gestion.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class DatabaseInitializer {

    public static void initialize() {
        try (Connection conn = DbConnection.getConnection()) {
            // Verificar si la tabla funcionarios ya existe (si existe, no re-inicializamos)
            try (Statement checkStmt = conn.createStatement()) {
                checkStmt.executeQuery("SELECT 1 FROM funcionarios LIMIT 1");
                return; // Ya existe
            } catch (Exception e) {
                // No existe, procedemos a crearla
            }

            executeScript(conn, "/sql/schema.sql");
            executeScript(conn, "/sql/data.sql");
            System.out.println("Base de datos inicializada correctamente.");
        } catch (Exception e) {
            System.err.println("Error inicializando base de datos: " + e.getMessage());
        }
    }

    private static void executeScript(Connection conn, String path) throws Exception {
        try (InputStream is = DatabaseInitializer.class.getResourceAsStream(path)) {
            if (is == null) throw new Exception("No se encontró el archivo: " + path);
            
            String script = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                    .lines().collect(Collectors.joining("\n"));
            
            try (Statement stmt = conn.createStatement()) {
                // Dividir por ; para ejecutar comandos individuales
                for (String sql : script.split(";")) {
                    if (!sql.trim().isEmpty()) {
                        stmt.execute(sql.trim());
                    }
                }
            }
        }
    }
}
