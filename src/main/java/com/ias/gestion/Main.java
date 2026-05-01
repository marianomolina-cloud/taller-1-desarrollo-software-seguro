package com.ias.gestion;

import com.ias.gestion.util.DatabaseInitializer;
import com.ias.gestion.view.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Establecer Look and Feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Inicializar DB
        DatabaseInitializer.initialize();

        // Lanzar GUI
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
