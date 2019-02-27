package com.alexmail.cron;

import javax.swing.*;

public class Cron {
    final static String EVERY_PERIOD_OF_TIME = "*";

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            CronGUI gui = new CronGUI();
            gui.setVisible(true);
        });
    }
}