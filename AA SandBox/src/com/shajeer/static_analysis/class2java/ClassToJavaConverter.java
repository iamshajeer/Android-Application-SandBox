/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shajeer.static_analysis.class2java;

import com.strobel.decompiler.Decompiler;
import com.strobel.decompiler.PlainTextOutput;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author Shajeer
 */
public class ClassToJavaConverter {

    public static void main(String a[]) {
        try {
            Decompiler decom = new Decompiler();
            PlainTextOutput pl = new PlainTextOutput(new OutputStreamWriter(new FileOutputStream("D:\\asd.txt"), "UTF-8"));
            decom.decompile("D:\\BuildConfig.class", pl);
            
            //System.out.println(pl.toString());
//            decom.decompile("D:\\BuildConfig.class",new PlainTextOutput(new OutputStreamWriter(new FileOutputStream("D:\\asd.txt"), "UTF-8")));
/*        com.strobel.decompiler.Decompiler.decompile(
             "D:\\BuildConfig.class",
             new com.strobel.decompiler.PlainTextOutput(
             //new java.io.OutputStreamWriter(System.out)
                        
             )
             );*/
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
