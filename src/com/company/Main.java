package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

   //    private static String extensionsOnLoadString = "hybris-cis-mock-subscription-web,entitlements-web,core,testweb,maintenanceweb,mediaweb,paymentstandard,commons,scripting,processing,impex,validation,catalog,europe1,advancedsavedquery,deliveryzone,platformservices,workflow,comments,oauth2,hac,npmancillary,embeddedserver,backoffice,virtualjdbc,cockpit,admincockpit,platformbackoffice,tomcatembeddedserver,reportcockpit,springintegrationlibs,productcockpit,solrserver,solrfacetsearch,ldap,basecommerce,payment,cms2,cms2lib,cmscockpit,webservicescommons,customerreview,voucher,promotions,commerceservices,commercefacades,ordermanagementfacade,commercesearch,subscriptionservices,subscriptionfacades,entitlementservices,entitlementfacades,commercewebservicescommons,personalizationservices,ticketsystem,cscockpit,customerticketingfacades,acceleratorservices,assistedserviceservices,hybrisacceleratorfulfilmentprocess,configurablebundleservices,configurablebundlefacades,acceleratorcms,acceleratorfacades,assistedservicefacades,acceleratorstorefrontcommons,addonsupport,subscriptionstorefront,liveeditaddon,orderselfserviceaddon,entitlementstorefront,assistedservicestorefront,customerticketingaddon,smarteditaddon,hybrisacceleratorcore,hybrisacceleratorfacades,hybrisacceleratorcockpits,b2ctelcoservices,b2ctelcofacades,b2ctelcocheckoutaddon,b2ctelcostorefront,b2bcommerce,b2bcommercefacades,b2bapprovalprocess,b2bapprovalprocessfacades,commerceorgaddon,buildscripts,velcomwsutils,velcomutils,velcomcore,velcominitialdata,velcomsampledata,velcomfulfilmentprocess,velcomfacades,velcomwsaddon,hybrisacceleratorws,hybrisacceleratorwstest,velcomwstest,velcomstorefrontaddon,velcomcockpits,velcomintegrations,hmc,voucherhmc,promotionshmc,subscriptionserviceshmc,ticketsystemhmc,customerreviewhmc,solrfacetsearchhmc,commercesearchhmc,b2bapprovalprocesshmc,b2bcommercehmc,basecommercehmc,acceleratorcmshmc,acceleratorserviceshmc,cms2hmc,hybrisacceleratorwshmc,platformhmc,commerceserviceshmc";
   private static String extensionsOnLoadString = "hybris-cis-mock-subscription-web,entitlements-web,core,testweb,maintenanceweb,mediaweb,paymentstandard,commons,scripting,processing,impex,validation,catalog,europe1,advancedsavedquery,deliveryzone,platformservices,workflow,comments,oauth2,hac,embeddedserver,virtualjdbc,tomcatembeddedserver,cockpit,reportcockpit,springintegrationlibs,admincockpit,productcockpit,solrserver,npmancillary,backoffice,platformbackoffice,solrfacetsearch,ticketsystembackoffice,ldap,basecommerce,payment,cms2,cms2lib,cmscockpit,webservicescommons,smartedit,basecommercebackoffice,customerreview,voucher,voucherbackoffice,promotions,commerceservices,velcomwsutils,entitlementservices,commercewebservicescommons,personalizationservices,commercefacades,entitlementfacades,commercesearch,ticketsystem,cscockpit,entitlementcockpits,acceleratorservices,hybrisacceleratorfulfilmentprocess,velcomutils,acceleratorcms,acceleratorfacades,acceleratorstorefrontcommons,addonsupport,cmsfacades,cmssmarteditwebservices,hybrisacceleratorcore,hybrisacceleratorfacades,hybrisacceleratorcockpits,subscriptionservices,subscriptionfacades,subscriptioncockpits,configurablebundleservices,configurablebundlefacades,configurablebundlecockpits,b2ctelcoservices,b2ctelcofacades,promotionsbackoffice,liveeditaddon,b2ctelcocheckoutaddon,b2ctelcostorefront,cmssmartedit,buildscripts,hmc,voucherhmc,promotionshmc,subscriptionserviceshmc,ticketsystemhmc,customerreviewhmc,solrfacetsearchhmc,commercesearchhmc,basecommercehmc,acceleratorcmshmc,acceleratorserviceshmc,cms2hmc,platformhmc,commerceserviceshmc,subscriptionbackoffice,solrfacetsearchbackoffice,commerceservicesbackoffice,customersupportbackoffice,commercesearchbackoffice,b2ctelcocockpits,velcomcore,velcominitialdata,velcomsampledata,velcomfulfilmentprocess,velcomintegrations,velcomcockpits,velcomfacades,velcomstorefrontaddon,velcomwsaddon,hybrisacceleratorws,hybrisacceleratorwstest,velcomwstest,hybrisacceleratorwshmc,hybrisacceleratorstorefront";

   private static List<String> extensionsList = Arrays.asList(extensionsOnLoadString.split(","));

   private static List<String> extensionInfoList = Arrays.asList(
         new String[] { "acceleratorcms", "addonsupport", "b2ctelcocheckoutaddon", "b2ctelcocockpits", "b2ctelcostorefront",
               "cissubscription", "commercesearchbackoffice", "commerceservicesbackoffice", "emsclient", "emsui",
               "entitlementstorefront", "mcc", "solrfacetsearchbackoffice", "solrserver", "subscriptionstorefront",
               "subscriptionbackoffice", "liveeditaddon", "b2bcommercebackoffice", "b2badmincockpit", "orderselfserviceaddon",
               "commerceorgaddon", "customersupportbackoffice", "customerticketingaddon", "rulebuilderbackoffice",
               "couponbackoffice", "droolsruleengineservices", "cmswebservices", "smarteditwebservices", "previewwebservices",
               "permissionswebservices", "smarteditaddon", "cmssmartedit", "assistedservicestorefront" });

   private static List<String> duplications = new ArrayList<>();
   private static List<String> addedExtensions = new ArrayList<>();

   private static String currentLocalExtensionsString =
         "hmc\n" + "acceleratorcms\n" + "addonsupport\n" + "b2ctelcocheckoutaddon\n" + "b2ctelcocockpits\n"
               + "b2ctelcostorefront\n" + "cissubscription\n" + "commercesearchbackoffice\n" + "commerceservicesbackoffice\n"
               + "emsclient\n" + "emsui\n" + "entitlementstorefront\n" + "mcc\n" + "solrfacetsearchbackoffice\n" + "solrserver\n"
               + "subscriptionstorefront\n" + "subscriptionbackoffice\n" + "liveeditaddon\n" + "b2bcommercebackoffice\n"
               + "b2badmincockpit\n" + "orderselfserviceaddon\n" + "commerceorgaddon\n" + "customersupportbackoffice\n"
               + "customerticketingaddon\n" + "rulebuilderbackoffice\n" + "couponbackoffice\n" + "droolsruleengineservices\n"
               + "couponfacades\n" + "cmswebservices\n" + "smarteditwebservices\n" + "previewwebservices\n"
               + "permissionswebservices\n" + "smarteditaddon\n" + "cmssmartedit\n" + "assistedservicestorefront\n"
               + "hybrisacceleratorcockpits\n" + "hybrisacceleratorcore\n" + "hybrisacceleratorfacades\n"
               + "hybrisacceleratorfulfilmentprocess\n" + "hybrisacceleratorstorefront\n" + "hybrisacceleratortest\n"
               + "hybrisacceleratorws\n" + "hybrisacceleratorwshmc\n" + "hybrisacceleratorwstest\n" + "velcomintegrations\n"
               + "velcomcockpits\n" + "velcomcore\n" + "velcomfacades\n" + "velcomfulfilmentprocess\n" + "velcomutils\n"
               + "velcomwsutils\n" + "velcominitialdata\n" + "velcomsampledata\n" + "velcomstorefrontaddon\n" + "velcomwsaddon\n"
               + "velcomwstest\n" + "buildscripts";
   private static List<String> currentLocalExtensions = Arrays.asList(currentLocalExtensionsString.split("\n"));



   public static void main(String[] args) throws Exception {

      //        printDuplicates();

      //        printAddedExtensions();

      //        Pattern pattern = Pattern.compile("_(\\d+)_(\\d+)_(\\d+)\\.csv");
      //
      //        final Matcher matcher = pattern.matcher("insert_subscriber_segments_1454347813_3_12.csv");
      //
      //        if (matcher.find()) {
      //            System.out.println(matcher.group(1) );
      //        }
      //				        dfkjughdfkjhgd("/home/mikhail/Downloads/generate/userSegments-1489746416255.csv");



//            				printImpexInitializationLog("/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/m3/hybris/bin/custom/buildscripts/resources/buildscripts/ant/111");
//      printImpexInitializationLog(
//            "/mnt/b72c5a51-4de0-4cd2-800b-a9e788068220/m4/hybris/bin/custom/buildscripts/resources/buildscripts/ant/junit.txt");

      //      splitBuildLogFiles("/home/mikhail/.IdeaIC2016.3/config/scratches/tickets/jgroups/diff/success/success.txt");
      //      splitBuildLogFiles("/home/mikhail/.IdeaIC2016.3/config/scratches/tickets/jgroups/diff/failed/failed.txt");



      //      printFailedMessageKinds("/home/mikhail/.IdeaIC2016.3/config/scratches/tickets/jgroups/diff/failed/failed.txt");


//      calcualteTimestamps();

//            String pathToFile = "/home/mikhail/.IdeaIC2016.3/config/scratches/tickets/integrationTests/testsExecutionTime/originalLog.txt";
//            printImpexFiles(pathToFile);
   }



   private static void calcualteTimestamps() throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream("/home/mikhail/.IdeaIC2016.3/config/scratches/tickets/integrationTests/testsExecutionTime/testsReport.txt")));

      List<Integer> ints = Arrays.asList(00,00,00,00,00,00,00,00,00,01,00,00,00,00,05,00,00,00,00,00,00,00,00,00,00,00,00,01,00,00,00,00,00,00,00,00,00);

long sum = 0;
      for (Integer anInt : ints) {
         sum   += anInt;
      }
      System.out.println(sum);
   }


   private static void printImpexFiles(final String pathToFile) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(pathToFile)));


      String line = null;
      while ((line = br.readLine()) != null) {
         if (line.contains("Finished") && line.contains("test class")) {
            System.out.print(line + " - ");
         }
         if (line.contains("Total run time: ")) {
            System.out.println(line);
         }
      }
   }

   private static void printFailedMessageKinds(final String pathToFile) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(pathToFile)));


      String line = null;
      while ((line = br.readLine()) != null) {
         if (line.contains("is not initialized yet - cannot send message")) {
            System.out.println(line);
         }

      }
   }

   private static void splitBuildLogFiles(final String wholeLog) throws Exception {
      Set<String> nodes = findNodes(wholeLog);
      System.out.println(nodes);

      for (String node : nodes) {
         if (!node.contains("/")) {
            printNodeLog(wholeLog, node);
         }
      }

   }

   private static Set<String> findNodes(final String wholeLog) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(wholeLog)));

      Set<String> result = new TreeSet<>();
      String line = null;
      while ((line = br.readLine()) != null) {
         if (line.contains("[") && line.contains("]")) {
            final int start = line.indexOf("[");
            final int end = line.indexOf("]");
            final String nodeName = line.substring(start, end + 1);
            result.add(nodeName);
         }

      }
      return result;
   }

   private static void printNodeLog(final String wholeLog, final String nodeName) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(
            new FileInputStream(wholeLog)));

      final FileWriter nodeFileWriter = new FileWriter(new File(wholeLog).getParent() + "/" + nodeName + ".txt");

      nodeFileWriter.write("============================================");
      nodeFileWriter.write("Log for " + nodeName + " node");
      nodeFileWriter.write("============================================");

      String line = null;
      while ((line = br.readLine()) != null) {

         if ((line.contains(nodeName))) {
            nodeFileWriter.write(line);
            nodeFileWriter.write("\n");
         }
      }

      nodeFileWriter.flush();
      nodeFileWriter.close();
   }





   //	private static void doTest()
   //	{
   //
   //		final HashSet<String> actualSegments = new HashSet<>(Arrays.asList("segment1", "segment3"));
   //		final HashSet<String> expectedSegments = new HashSet<>(Arrays.asList("segment1"));
   //
   //		System.out.println(expectedSegments.equals(actualSegments));
   //		System.out.println(actualSegments.equals(expectedSegments));
   //	}

   private static void dfkjughdfkjhgd(String pathname) throws IOException {
      StringBuilder sb = new StringBuilder();
      sb.append("INSERT Subscriber; uid[unique = true]; name; msisdn\n");

      //		final File[] files = new File("/home/mikhail/Downloads/generate/userProfiles-1489572908153.csv").listFiles();

      //		for (File file : files)
      //		{
      File file = new File(pathname);
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));
      String line = null;
      while ((line = br.readLine()) != null) {
         final String subscriberId = line.substring(0, line.indexOf(","));
         sb.append(";").append(subscriberId).append(";").append(subscriberId).append(";").append(subscriberId).append("\n");
      }
      //		}

      final String fileName = "/home/mikhail/Downloads/generate/users.impex";
      new File(fileName).getParentFile().mkdirs();
      FileWriter fw = new FileWriter(fileName);

      fw.write(sb.toString());
      fw.flush();
      fw.close();
   }

   private static void printAddedExtensions() {
      for (String currentLocalExtension : currentLocalExtensions) {
         if (!extensionsList.contains(currentLocalExtension)) {
            addedExtensions.add(currentLocalExtension);
         }
      }
      System.out.println("added extensions: ");
      for (String addedExtension : addedExtensions) {
         System.out.println(addedExtension);
      }
   }

   private static void printDuplicates() {
      for (String extension : extensionInfoList) {
         if (extensionsList.contains(extension)) {
            duplications.add(extension);
         }
      }
      for (String duplication : duplications) {

         System.out.println(duplication);
      }
   }
}
