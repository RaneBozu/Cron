package com.alexmail.cron;

import com.alexmail.cron.Client.CronGUI;

import javax.swing.*;

public class Cron {
    public final static String EVERY_PERIOD_OF_TIME = "*";

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            CronGUI gui = new CronGUI();
            gui.setVisible(true);
        });
    }
}