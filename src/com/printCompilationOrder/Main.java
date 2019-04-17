package com.printCompilationOrder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Mikhail_Asadchy (EPAM)
 */
public class Main {
   public static void main(String[] args) throws Exception {
      findInFile("/home/mikhail/.IdeaIC2016.3/config/scratches/tickets/migration-6.4.0.3-TO-6.7.0.0/antAllWithBuildOrder.txt");
   }

   private static void findInFile(final String file) throws Exception
   {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(file)));
      String line = null;
      while ((line = br.readLine()) != null)
      {
         if (line.contains("writing")) {
            System.out.println(line);
         }
      }
   }
}
