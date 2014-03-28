/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shajeer.static_analysis.dex2class;

import com.shajeer.static_analysis.StaticAnalysis;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.swing.JOptionPane;

/**
 *
 * @author Shajeer
 */
public class ExtractJarFile {

    private static String destination = StaticAnalysis.MAIN_DIRECTORY+"\\uncompressedJar\\";

    public ExtractJarFile() {

    }
public static void main(String[] a) throws IOException{
    unzipJar("C:\\Users\\Shajeer\\AppData\\Local\\Temp\\android sandbox\\asd_dex2jar.jar");
}
    public static void unzipJar(String jarPath) throws IOException {

        File file = new File(jarPath);
        new File(destination).mkdir();
        JarFile jar = new JarFile(file);

        // fist get all directories,
        // then make those directory on the destination Path
        for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements();) {
            JarEntry entry = (JarEntry) enums.nextElement();

            String fileName = destination + File.separator + entry.getName();
            File f = new File(fileName);

            if (fileName.endsWith("/")) {
                f.mkdirs();
            }

        }

        //now create all files
        for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements();) {
            JarEntry entry = (JarEntry) enums.nextElement();

            String fileName = destination + File.separator + entry.getName();
            File f = new File(fileName);

            if (!fileName.endsWith("/")) {
                InputStream is = jar.getInputStream(entry);
                FileOutputStream fos = new FileOutputStream(f);

                // write contents of 'is' to 'fos'
                while (is.available() > 0) {
                    fos.write(is.read());
                }

                fos.close();
                is.close();
            }
        }
    }

}
