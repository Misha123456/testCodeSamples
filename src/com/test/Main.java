package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Mikhail_Asadchy (EPAM)
 */
public class Main {

   private static final Set<String> usedComponents = new HashSet<>(
         Arrays.asList("VelcomNewsAndCampaignsCarouselComponent", "VelcomNewsAndCampaignsComponent", "VelcomBannerComponent",
               "VelcomWidgetComponent", "VelcomAbstractHidableComponent", "VelcomEShopBannerComponent",
               "VelcomEShopBannerItemComponent", "VelcomCampaignOffersComponent", "VelcomShopBannerComponent",
               "VelcomBrandsCarouselComponent", "VelcomCatalogueHotMenuItemComponent", "VelcomHotMenuItemComponent",
               "VelcomCatalogueHotMenuComponent", "VelcomHotMenuComponent"));

   public static void main(String[] args) throws Exception {
      findInFile("/home/mikhail/Downloads/localizations.properties", "component.");
   }

   private static void findInFile(final String file, final String pattern) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(file)));
      String line = null;
      final Set<String> matchedLines = new HashSet<>();

      while ((line = br.readLine()) != null) {
         if (line.contains(pattern) && !line.contains(".description")) {
            final String[] keyValue = line.split("=");
            if (keyValue.length == 1) {
               final String key = keyValue[0];
               for (String usedComponent : usedComponents) {
                  if (key.contains(usedComponent.toLowerCase())) {
                     matchedLines.add(line);
                  }
               }
            }
         }
      }

      final ArrayList<String> sortedList = new ArrayList<>(matchedLines);
      sortedList.sort(new Comparator<String>() {
         @Override
         public int compare(final String o1, final String o2) {
            return o1.compareTo(o2);
         }
      });

      for (String matchedLine : sortedList) {
         System.out.println(matchedLine);
      }
   }

}
