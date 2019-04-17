package com.mergeFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Mikhail_Asadchy (EPAM)
 */
public class Main {

   public static void main(String[] args) {

      File dumpFolder = new File("/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/m1/dump");

      File dataFolder = new File(dumpFolder + "/data");
      File schemaFolder = new File(dumpFolder + "/schema");
      File mergedFile = new File(dumpFolder + "/dump.txt");

      List<File> files = new ArrayList<>();
      Collections.addAll(files, schemaFolder.listFiles());
      Collections.addAll(files, dataFolder.listFiles());

      mergeFiles(files, mergedFile);
   }

   public static void mergeFiles(List<File> files, File mergedFile) {

      FileWriter fstream = null;
      BufferedWriter out = null;
      try {
         fstream = new FileWriter(mergedFile, true);
         out = new BufferedWriter(fstream);
      } catch (IOException e1) {
         e1.printStackTrace();
      }

      for (File f : files) {
         System.out.println("merging: " + f.getName());
         FileInputStream fis;
         try {
            fis = new FileInputStream(f);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            String aLine;
            while ((aLine = in.readLine()) != null) {
               out.write(aLine);
               out.newLine();
            }

            in.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

      try {
         out.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

}
