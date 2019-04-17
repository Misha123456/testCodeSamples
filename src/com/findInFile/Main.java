package com.findInFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
      findInFile("/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/m1/hybris/bin/custom/buildscripts/resources/buildscripts/ant/1.xml", "component=\"([a-z\\-]+)\"");
   }

   private static void findInFile(final String file, final String pattern) throws Exception
   {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(file)));
      String line = null;
         final Set<String> matchedLines = new HashSet<>();
         Pattern p = Pattern.compile(pattern);
      while ((line = br.readLine()) != null)
      {
         final Matcher matcher = p.matcher(line);
         if (matcher.find())
         {
            matchedLines.add(matcher.group(1));
         }
      }

      for (String matchedLine : matchedLines) {
         System.out.println(matchedLine);
      }
   }

}
