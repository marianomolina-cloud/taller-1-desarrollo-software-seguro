package com.ias.supermercado;

import com.ias.supermercado.view.SimuladorFrame;
import javax.swing.SwingUtilities;

public class MainSupermercado {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimuladorFrame frame = new SimuladorFrame();
            frame.setVisible(true);
        });
    }
}
