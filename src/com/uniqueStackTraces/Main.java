package com.uniqueStackTraces;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Mikhail_Asadchy (EPAM)
 */
public class Main {

   public static void main(String[] args) throws Exception {
//getUniqueStackTraces("/home/mikhail/.IdeaIC2016.3/config/scratches/temp/16threadsSyncLog.txt", "ItemSyncTimestamp.pk");
//getUniqueStackTraces("/home/mikhail/.IdeaIC2016.3/config/scratches/temp/16threads.txt", "offering:installmentPriceOnVariant2");
getUniqueStackTraces("/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/tempFolder/test.txt", "[", "stackTrace - ");
   }

   private static void getUniqueStackTraces(final String file, final String pattern, final String stackTraceStartSubstring) throws Exception
   {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(file)));
      String line = null;
         final List<String> matchedLines = new ArrayList<>();
      while ((line = br.readLine()) != null)
      {
         if (line.contains(pattern))
         {
            matchedLines.add(line);
         }
      }

      Set<String> uniqueStackTraces = new HashSet<>();
      for (String matchedLine : matchedLines) {
         final String lineStackTrace = matchedLine.substring(matchedLine.indexOf(stackTraceStartSubstring));
         uniqueStackTraces.add(lineStackTrace);
      }

      printProcessedStackTraces(uniqueStackTraces);
   }

   private static void printProcessedStackTraces(final Set<String> uniqueStackTraces) {
      for (String uniqueStackTrace : uniqueStackTraces) {
         System.out.println(uniqueStackTrace.replace(",", "\r\n"));
      }
   }

}
