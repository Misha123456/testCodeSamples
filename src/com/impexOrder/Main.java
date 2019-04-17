package com.impexOrder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Mikhail_Asadchy (EPAM)
 */
public class Main {

   public static void main(String[] args) throws Exception {
      printImpexInitializationLog("/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/tempFolder/initLog.txt");
   }

   private static void printImpexInitializationLog(final String initializationLog) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(initializationLog)));
      String line = null;

      Pattern p = Pattern.compile(".+\\.impex.+");

      while ((line = br.readLine()) != null) {
         Matcher m = p.matcher(line);

         if (line.contains(".impex")) {
            System.out.println(line);
         }
      }
   }

}
