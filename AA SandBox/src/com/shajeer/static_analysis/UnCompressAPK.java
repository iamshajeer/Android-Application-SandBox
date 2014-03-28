/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shajeer.static_analysis;

import com.shajeer.static_analysis.xmlparser.main.AXMLPrinter;
import com.shajeer.static_analysis.xmlparser.v1.XmlPullParserException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

/**
 *
 * @author Shajeer
 */
public class UnCompressAPK {
    //progress bar ui components

    static JFrame frameForProgress;
    File unCompressFile, ourApk;
    int progressValue;
    Container content;
    JProgressBar progressBar;
    Border border;
    JLabel currentProgress;
    Timer timer;

    public UnCompressAPK(File apkToUnCompress, File apkFile) throws XmlPullParserException {
        unCompressFile = apkToUnCompress;
        ourApk = apkFile;
        //frameForProgress = new JFrame("Unzipping APK file");
        //content = frameForProgress.getContentPane();
        //progressBar = new JProgressBar();
        //border = BorderFactory.createTitledBorder("Writing in to directory...");
        //currentProgress = new JLabel();
        //progressValue = 0;
        timer = new Timer();

        String outputFolder = StaticAnalysis.UNZIPPED_DIRECTORY;

        if (!unZipIt(unCompressFile.getAbsolutePath(), outputFolder)) {
            JOptionPane.showMessageDialog(null, "Error in uncompressing APK File please try another file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean unZipIt(String zipFile, String outputFolder) throws XmlPullParserException {
        //initialisning progressbar ui

        byte[] buffer = new byte[1024];

        try {

            //create output directory is not exists
          /*  File folder = new File("D:/othrz/Works/aa sandbox/uncompressed");
             if (!folder.exists()) {
             folder.mkdir();
             }*/
            //get the zip file content
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            while (ze != null) {
                // initProgressUI();
//                progressValue += 1;

                String fileName = ze.getName();
                final File newFile = new File(outputFolder + File.separator + fileName);
                //System.out.println("file unzip : " + newFile.getAbsoluteFile());

                //create all non exists folders
                //else you will hit FileNotFoundException for compressed folder
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                ze = zis.getNextEntry();

                //call to show progress bar
              /*  timer.schedule(new TimerTask() {
                 @Override
                 public void run() {
                 progressValue += 50;
                 //showProgress(progressValue, "file unzip : " + newFile.getAbsoluteFile());
                 }
                 }, 0, 10);*/
            }
            if (ze == null) {
                //timer.cancel();
                //showProgress(100, "file unzip : " + newFile.getAbsoluteFile());
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(content, "APK file successfully uncompressed", "Done", JOptionPane.INFORMATION_MESSAGE);
                //this.frameForProgress.setVisible(false);
            }
            if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to start static analysis ?", "Start ?", JOptionPane.OK_CANCEL_OPTION)) {
                String apkFile = ourApk.getAbsolutePath();
                File file = new File(System.getProperty("java.io.tmpdir") + "\\android sandbox\\Manifest.xml");
                //System.out.println(ManifestScanner.getManifestXMLFromAPK(apkFile));
                //System.out.println("apk file path="+ourApk.getAbsolutePath());
                BufferedWriter output = new BufferedWriter(new FileWriter(file));
                output.write(AXMLPrinter.getManifestXMLFromAPK(ourApk.getAbsolutePath()));
                
                
                
                //option for viewing TREE
                
               // if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(null, "Manifest File created do you want to see ?", "AndroidMainfest.xml", JOptionPane.OK_CANCEL_OPTION)) {
                    //TO DO display Manifest description
                //}

                output.close();

            }
            zis.closeEntry();
            zis.close();
            return true;

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void showProgress(int progressValue, String progress) {
        progressBar.setValue(progressValue);
        progressBar.setStringPainted(true);
        progressBar.setBorder(border);
        content.add(progressBar, BorderLayout.NORTH);
        currentProgress.setText(progress);
        content.add(currentProgress, BorderLayout.AFTER_LAST_LINE);
        frameForProgress.setSize(600, 100);
        frameForProgress.setVisible(true);
    }
}
