/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shajeer.static_analysis;

import com.shajeer.static_analysis.dex2class.DexToClassConverter;
import com.shajeer.static_analysis.dex2class.ExtractJarFile;
import com.shajeer.static_analysis.xmlparser.v1.XmlPullParserException;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;


/**
 *
 * @author Shajeer
 */
public class StaticAnalysis {

    File apkToUncompress;
    File destFolder = new File(System.getProperty("java.io.tmpdir") + "\\android sandbox\\");
    File unzippedFolder = new File(destFolder.getAbsolutePath() + "\\uncompressed\\");
    public static String MAIN_DIRECTORY;
    public static String UNZIPPED_DIRECTORY;

    public StaticAnalysis(File apk) throws XmlPullParserException, IOException {

        destFolder.mkdirs();
        MAIN_DIRECTORY = destFolder.getAbsolutePath();

        //creating location for unzipping apk
        unzippedFolder.mkdirs();
        UNZIPPED_DIRECTORY = unzippedFolder.getAbsolutePath();

        apkToUncompress = apk;

        //call method for extracting apk

        extractApk(apkToUncompress);
        
        DexToClassConverter d = new DexToClassConverter(apkToUncompress.getAbsolutePath());
        
        new ManifestViewer();
        //ExtractJarFile.unzipJar(FilenameUtils.removeExtension(apkToUncompress.getAbsolutePath())+"_dex2jar");
        //converting dex file to .class files
        //convertDexToClass(dexFile);

    }

    public void extractApk(File apkFile) throws XmlPullParserException {

        //copying to another location
        if(copyToAnotherLocation(apkFile)){
           
        }
        File zipedAPK = renameFile(new File(UNZIPPED_DIRECTORY + "\\" + apkFile.getName()));
       
        UnCompressAPK uncompressApk = new UnCompressAPK(zipedAPK, apkToUncompress);

    }

    //method for moving file from og loc to temp location
    public boolean copyToAnotherLocation(File apkFile) {
        try {
            //copiying orgnal uploaded file to another location
            FileUtils.copyFile(apkFile, new File(UNZIPPED_DIRECTORY + "\\" + apkFile.getName()));
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }


    }

    public File renameFile(File oldFile) {
//        File newFile = new File("D:/othrz/Works/aa sandbox/newApk.zip");
        File newFile = new File(UNZIPPED_DIRECTORY + "\\" + oldFile.getName() + ".zip");

        if (oldFile.renameTo(newFile)) {

            return newFile;

        }
        return null;
    }
}
