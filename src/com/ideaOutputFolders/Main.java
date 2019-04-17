package com.ideaOutputFolders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by Mikhail_Asadchy (EPAM)
 */
public class Main {

   public static void main(String[] args) throws IOException {
      changeOutputFolders();
   }

   private static void changeOutputFolders() throws IOException {
//      changeOutputFolderIdea("/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/m1/hybris/idea-module-files");
//      changeOutputFolderIdea("/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/m2/hybris/idea-module-files");
//      changeOutputFolderIdea("/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/m3/hybris/idea-module-files");
//      changeOutputFolderIdea("/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/m4/hybris/idea-module-files");
      changeOutputFolderIdea("/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/m5/hybris/idea-module-files");
//      changeOutputFolderIdea("/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/m6/hybris/idea-module-files");
//      changeOutputFolderIdea("/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/m7/hybris/idea-module-files");
//      changeOutputFolderIdea("/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/mig/hybris/idea-module-files");
   }

   private static void changeOutputFolderIdea(String ideaModulesFilesPath) throws IOException {
      final File ideaModulesDirectory = new File(ideaModulesFilesPath);
      if (ideaModulesDirectory.exists()) {
         final File[] files = ideaModulesDirectory.listFiles(File::isFile);
         for (File file : files) {
            if (file.getAbsolutePath().endsWith(".iml")) {
               changeOutputFolders(file);
            }
         }
      }
   }

   private static void changeOutputFolders(File file) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(file.getAbsolutePath())));
      String line = null;
      StringBuilder sb = new StringBuilder();
      try {
         while ((line = br.readLine()) != null) {
            String replacedLine = line;
            if (((line.contains("<output") || line.contains("<output-test")) && line.contains("/classes"))) {
               replacedLine = line.replace("/classes", "/eclipsebin");
            }
            sb.append(replacedLine).append("\n");
         }
      }
      catch (IOException e) {
         e.printStackTrace();
      }
      finally {
         br.close();
      }

      FileWriter fw = new FileWriter(file);
      fw.write(sb.toString());
      fw.close();
   }

}
