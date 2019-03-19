package com.alexmail.cronClient.GUI.Listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OpenFileButtonListener implements ActionListener {

    private static Logger LOGGER = LogManager.getLogger(OpenFileButtonListener.class.getSimpleName());
    private JTextArea cronArea;
    private JTextArea humanArea;

    public OpenFileButtonListener(JTextArea cronArea, JTextArea humanArea) {
        this.cronArea = cronArea;
        this.humanArea = humanArea;
    }

    /**
     * adds text from a file to input fields
     * */
    @Override
    public void actionPerformed(ActionEvent e) {

        String inputString = "";
        JFileChooser fileChooser = new JFileChooser();
        File rootDir = new File("C:\\Users\\Rane\\IdeaProjects\\Cron\\CronClient\\src\\main\\resources");
        fileChooser.setCurrentDirectory(rootDir);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int retVal = fileChooser.showOpenDialog(null);
        if (retVal == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getPath();
            Path absolutePath = Paths.get(path).toAbsolutePath();
            Charset charset = Charset.forName("UTF-8");
            try {
                List<String> lines = Files.readAllLines(absolutePath, charset);
                inputString = lines.get(0);
            } catch (IOException e1) {
                LOGGER.error(e1);
            }
            cronArea.setText(null);
            humanArea.setText(null);
            if(inputString.matches("\\d+")) {
                cronArea.setText(inputString);
            }else{
                humanArea.setText(inputString);
            }
        }
        LOGGER.info("Text from file has been read");
    }
}