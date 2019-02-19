package CornTranslator;

import javax.swing.*;

public class Cron {
    final static String EVERY_PERIOD_OF_TIME = "*";

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            CornGUI gui = new CornGUI();
            gui.setVisible(true);
        });
    }
}