package com.pattern;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

   public static void main(String[] args) {
      String line = "https://fqa1-www.velcom.by:7443/MAG-%D1%86%D0%B2%D0%B5%D1%82-%D0%BC%D0%BE%D0%B4%D0%B5%D0%BB%D0%B8/Lenovo-Tab-3-Business-32GB/p/17.1008392/pc/NEW_CONTRACT/ONE_TIME";
      String line1 = "https://fqa1-www.velcom.by:7443/MAG-%D1%86%D0%B2%D0%B5%D1%82-%D0%BC%D0%BE%D0%B4%D0%B5%D0%BB%D0%B8/Lenovo-Tab-3-Business-32GB/p/17.1008392/pc";
      Pattern p = Pattern.compile("^.+\\/p\\/.+\\/pc(\\/.+)?$");
      final List<String> strings = Arrays.asList(line, line1);
      for (String string : strings) {
         Matcher m = p.matcher(string);
         System.out.println(m.matches());
      }

   }

}
