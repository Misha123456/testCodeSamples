package com.findCreateTableDuplications;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
//      final String sqlStatement = "/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/m3/HYBRISCOMM6700P_0-80003492/hybris/temp/hybris/init_junit_schema.sql";
      final String sqlStatement = "/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/installers/hybris/6.7/unpacked/HYBRISCOMM6700P_0-80003492/hybris/temp/hybris/init_junit_schema.sql";
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(sqlStatement)));
      String line = null;

      Pattern p = Pattern.compile("CREATE TABLE (.+)");

      Set<String> tables = new HashSet<>();
      Set<String> duplicatedTables = new HashSet<>();
      while ((line = br.readLine()) != null) {
         if (line.contains("CREATE TABLE")) {
            final Matcher matcher = p.matcher(line);
            if (matcher.find()) {
               final String table = matcher.group(1);
               final boolean add = tables.add(table);
               if (!add) {
                  duplicatedTables.add(table);
               }
            }
         }
      }

      for (String duplicatedTable : duplicatedTables) {
         System.out.println(duplicatedTable);
      }
   }

}
