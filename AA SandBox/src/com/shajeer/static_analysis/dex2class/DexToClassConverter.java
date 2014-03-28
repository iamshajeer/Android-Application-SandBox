/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shajeer.static_analysis.dex2class;

import com.googlecode.dex2jar.v3.Main;
import com.shajeer.static_analysis.ManifestViewer;
import com.shajeer.static_analysis.StaticAnalysis;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

/**
 *
 * @author Shajeer
 */
public class DexToClassConverter extends Thread {

    String pathOfDexFile;
    int progressValue;
    Container content;
    JProgressBar progressBar;
    Border border;
    JLabel currentProgress;
    JFrame frameForProgress;
    Timer timer;
    int tickesForHidingProgressBar = 0;

    public DexToClassConverter(String path) throws IOException {

        frameForProgress = new JFrame("Unzipping APK file");
        content = frameForProgress.getContentPane();
        progressBar = new JProgressBar();
        border = BorderFactory.createTitledBorder("Writing in to directory...");
        currentProgress = new JLabel();
        progressValue = 0;
        pathOfDexFile = path;
        File dexFile = new File(pathOfDexFile);
        initUI(0, "Conversion started");
        timer = new Timer();
        this.start();

    }

    public boolean dexToClass(String dexFile) {
        try {
            File dexFiles = new File(dexFile);
            //using dex2jar converter
            Main.doFile(dexFiles);
            

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void run() {
        frameForProgress.setVisible(false);
        initUI(50, "Conversion is on progress");
        if (!dexToClass(pathOfDexFile)) {
            JOptionPane.showMessageDialog(null, "Error converting dex to jar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        frameForProgress.setVisible(false);
        initUI(100, "Completed");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tickesForHidingProgressBar += 1;
                if (tickesForHidingProgressBar == 3) {
                    frameForProgress.setVisible(false);
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Conversion completed", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // ExtractJarFile.unzipJar(pathOfDexFile);
                    timer.cancel();
                    
                   

                }
            }
        }, 0, 1000);
        try {
            /*File jarFile = new File(StaticAnalysis.MAIN_DIRECTORY + "asd_dex2jar.jar");
            System.out.println(jarFile.getAbsolutePath());
            String outputFile = StaticAnalysis.MAIN_DIRECTORY + "\\class\\";*/
            
            
            
        } catch (Exception ex) {
            ex.getMessage();
        }

    }

    public void initUI(int progressValue, String progress) {
        //centralisng progress frame
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frameForProgress.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frameForProgress.getHeight()) / 2);
        frameForProgress.setLocation(x, y);

        progressBar.setValue(progressValue);
        progressBar.setStringPainted(true);
        progressBar.setBorder(border);
        content.add(progressBar, BorderLayout.NORTH);
        content.add(currentProgress, BorderLayout.AFTER_LAST_LINE);
        currentProgress.setText(progress);
        frameForProgress.setSize(600, 100);
        //Type typeOfFrame = new 
        frameForProgress.setResizable(false);
        //frameForProgress.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        frameForProgress.setVisible(true);
    }
    //method for extracting jar to files

    public void extractJarFile(String myJarFile) throws IOException {
        ZipFile file = new ZipFile(myJarFile);
        if (file != null) {
            Enumeration<? extends ZipEntry> entries = file.entries();
            if (entries != null) {
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    System.out.println(entry);

                }
            }
        }
    }
}
