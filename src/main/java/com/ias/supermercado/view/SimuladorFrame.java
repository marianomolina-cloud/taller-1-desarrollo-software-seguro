package com.ias.supermercado.view;

import com.ias.supermercado.logic.Cajera;
import com.ias.supermercado.model.Cliente;
import com.ias.supermercado.model.Producto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimuladorFrame extends JFrame {
    private JTextArea logArea;
    private JButton startButton;
    private JLabel totalTimeLabel;

    public SimuladorFrame() {
        setTitle("Simulador de Supermercado - Desarrollo Seguro");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 128, 185));
        JLabel titleLabel = new JLabel("Simulación de Cobro en Supermercado");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Log Area
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        logArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalTimeLabel = new JLabel("Tiempo total: 0s");
        totalTimeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        startButton = new JButton("Iniciar Simulación");
        startButton.setBackground(new Color(46, 204, 113));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        startButton.addActionListener(e -> startSimulation());

        bottomPanel.add(totalTimeLabel);
        bottomPanel.add(Box.createHorizontalStrut(20));
        bottomPanel.add(startButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private void startSimulation() {
        logArea.setText("");
        startButton.setEnabled(false);
        
        long initialTime = System.currentTimeMillis();
        
        // Mock data
        Cliente cliente1 = new Cliente("Juan");
        cliente1.addProducto(new Producto("Leche", 3500, 2));
        cliente1.addProducto(new Producto("Pan", 1500, 1));
        cliente1.addProducto(new Producto("Huevos", 12000, 3));

        Cliente cliente2 = new Cliente("Maria");
        cliente2.addProducto(new Producto("Arroz", 5000, 2));
        cliente2.addProducto(new Producto("Aceite", 18000, 4));
        
        Cliente cliente3 = new Cliente("Pedro");
        cliente3.addProducto(new Producto("Carne", 25000, 5));
        cliente3.addProducto(new Producto("Frutas", 8000, 2));

        Cajera cajera1 = new Cajera("Cajera 1", cliente1, initialTime, this::appendLog);
        Cajera cajera2 = new Cajera("Cajera 2", cliente2, initialTime, this::appendLog);
        Cajera cajera3 = new Cajera("Cajera 3", cliente3, initialTime, this::appendLog);

        cajera1.start();
        cajera2.start();
        cajera3.start();

        new Thread(() -> {
            try {
                cajera1.join();
                cajera2.join();
                cajera3.join();
                
                long endTime = System.currentTimeMillis();
                long totalTime = (endTime - initialTime) / 1000;
                
                SwingUtilities.invokeLater(() -> {
                    appendLog("\n========================================");
                    appendLog("SIMULACIÓN FINALIZADA");
                    appendLog("Tiempo total de procesamiento: " + totalTime + " seg");
                    appendLog("========================================\n");
                    totalTimeLabel.setText("Tiempo total: " + totalTime + "s");
                    startButton.setEnabled(true);
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void appendLog(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }
}
