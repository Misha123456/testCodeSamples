package com.company;

/**
 * Created by Mikhail_Asadchy on 26.4.17.
 */
public class Point {

   private int a;

   public Point(final int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }

   public void setA(final int a) {
      this.a = a;
   }

   @Override
   public boolean equals(final Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      Point point = (Point) o;

      return a == point.a;
   }

   @Override
   public int hashCode() {
      return a;
   }
}
