package com.alexmail.cronClient;



import com.alexmail.cronClient.GUI.CronGUI;

import javax.swing.*;

public class Cron {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            CronGUI gui = new CronGUI();
            gui.setVisible(true);
        });
    }
}