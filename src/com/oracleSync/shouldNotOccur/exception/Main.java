package com.oracleSync.shouldNotOccur.exception;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Mikhail_Asadchy (EPAM)
 */
public class Main {
   public static void main(String[] args) throws IOException {
      printOjectOccurencies("/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/m2/hybris/bin/custom/buildscripts/resources/buildscripts/ant/customInitModifications.txt");
   }

   private static void printOjectOccurencies(final String file) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(file)));
      String line = null;

      Set<Long> shouldRetryPks = new HashSet<>();
      Set<Long> exceptionOccurPks = new HashSet<>();

            String pattern = "item pk (\\d+) ";
      final Pattern p = Pattern.compile(pattern);
      while ((line = br.readLine()) != null) {
         if (line.contains("item pk")) {
            final Matcher matcher = p.matcher(line);
            final boolean found = matcher.find();
            if (found) {
               final String pk = matcher.group(1);
               final long pkLong = Long.parseLong(pk);
               if (line.contains("should retry")) {
                  shouldRetryPks.add(pkLong);
               } else {
                  exceptionOccurPks.add(pkLong);
               }
            }
         }
      }

      for (Long exceptionOccurPk : exceptionOccurPks) {
         if (!shouldRetryPks.contains(exceptionOccurPk)) {
            System.out.println("Should not be retried pk: " + exceptionOccurPk);
         }
      }
   }

}
