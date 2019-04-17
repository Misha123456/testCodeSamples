package com;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Mikhail_Asadchy (EPAM)
 */
public class Main {


   public static void main(String[] args) throws Exception {

   }

   public static String mai2n(String[] args) throws Exception {
      System.out.println("=" + String.valueOf("") + "=");
      return "";
   }

   private static void getUpdateStatementsFromMysqlLog(final String mysqlLog) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(mysqlLog)));
      String line = null;

      while ((line = br.readLine()) != null) {
         final String startPattern = "UPDATE usergroups";
         if (line.contains(startPattern) && line.contains("8796093120517")) {
            System.out.println(line);
         }
      }
   }


   private static void findDuplicatePksInSyncOrderLog(final String initLog) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(initLog)));
      String line = null;

      Set<Long> uniquePks = new HashSet<>();
      Set<Long> duplicatedPks = new HashSet<>();
      while ((line = br.readLine()) != null) {
         final String startPattern = "PK: ";
         if (line.contains(startPattern)) {
            final int startIndex = line.indexOf(startPattern);
            String pkString = line.substring(startIndex + startPattern.length());
            if (pkString.contains("[") && pkString.contains("]")) {
               pkString = pkString.replace("[", "").replace("]", "");
            }
            final long pk = Long.parseLong(pkString);
            registerPk(uniquePks, duplicatedPks, pk);
         }

      }

      for (Long duplicatedPk : duplicatedPks) {
         System.out.println(duplicatedPk);
      }
   }

   private static void printSyncProcess(final String initLog) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(initLog)));
      String line = null;

      while ((line = br.readLine()) != null) {
         String startString1 = "Starting coping of item: ";
         if (line.contains(startString1)) {
            System.out.println("Start " + line.substring(line.indexOf(startString1) + startString1.length()));
         }

         final String startString = "Could not copy item - ";
         final String endString = "] synchronizing";
         if (line.contains(startString) && line.contains(endString)) {
            final int beginIndex = line.indexOf(startString) + startString.length();
            final int endIndex = line.indexOf(endString) +1;
            System.out.println("Failed " + line.substring(beginIndex, endIndex));
         }

      }
   }



   private static void printDuplicatedPKs(final String syncScript) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(syncScript)));
      String line = null;

      Set<Long> uniqueItems = new HashSet<>();
      Set<Long> duplicatedItems = new HashSet<>();
      while ((line = br.readLine()) != null) {
         final String[] csvValues = line.split(";");

         for (String value : csvValues) {
            if (!value.isEmpty()) {
               long pk;
               try {
                  pk = Long.parseLong(value);
                  registerPk(uniqueItems, duplicatedItems, pk);
               }
               catch (NumberFormatException e) {
                  if (value.contains("->")) {
                     final String[] pks = value.split("->");
                     for (String pkString : pks) {
                        pk = Long.parseLong(pkString);
                        registerPk(uniqueItems, duplicatedItems, pk);
                     }
                  }
               }
            }

         }

      }

      System.out.println("unique items:");
      System.out.println(uniqueItems);
      System.out.println("duplicated items:");
      System.out.println(duplicatedItems);

   }

   private static void registerPk(final Set<Long> uniqueItems, final Set<Long> duplicatedItems, final long pk) {
      final boolean added = uniqueItems.add(pk);
      if (!added) {
         duplicatedItems.add(pk);
      }
   }

   private static void printImpexInitializationLog(final String initializationLog) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(initializationLog)));
      String line = null;

      Pattern p = Pattern.compile(".+item type is \\[.+\\]\\)\\.");

      Set<String> failedTypes = new HashSet<>();
      while ((line = br.readLine()) != null) {
         Matcher m = p.matcher(line);

         if (m.matches()) {
            final String startString = "item type is [";
            final int start = line.indexOf(startString);
            final int end = line.indexOf("]).");
            final String failedType = line.substring(start + startString.length(), end);
            failedTypes.add(failedType);
         }
      }

      final ArrayList<String> failedTypesSortedList = new ArrayList<>(failedTypes);
      Collections.sort(failedTypesSortedList, new Comparator<String>() {
         @Override
         public int compare(final String o1, final String o2) {
            return o1.compareTo(o2);
         }
      });

      System.out.println("Unique failed types:");
      System.out.println(failedTypesSortedList);

   }

}
