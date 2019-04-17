package com;

import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * Created by Mikhail_Asadchy (EPAM)
 */
public class FairLocking {


   public static final boolean FAIR = false;
   private static final int NUM_THREADS = 3;

   private static volatile int expectedIndex = 0;

   public static void main(String[] args) throws InterruptedException {
      ReentrantReadWriteLock.WriteLock lock = new ReentrantReadWriteLock(FAIR).writeLock();

      // we grab the lock to start to make sure the threads don't start until we're ready
      lock.lock();
      System.out.println("main thread locked the lock object");

      for (int i = 0; i < NUM_THREADS; i++) {
         new Thread(new ExampleRunnable(i, lock)).start();

         // a cheap way to make sure that runnable 0 requests the first lock
         // before runnable 1
         System.out.println("wait");
         Thread.sleep(1000);
      }

      // let the threads go
      lock.unlock();
      System.out.println("main thread unlocked the lock object");
   }

   private static class ExampleRunnable implements Runnable {
      private final int index;
      private final ReentrantReadWriteLock.WriteLock writeLock;

      public ExampleRunnable(int index, ReentrantReadWriteLock.WriteLock writeLock) {
         this.index = index;
         this.writeLock = writeLock;
      }

      public void run() {
         while (true) {
            writeLock.lock();
            System.out.println("slave thread " + index + " locked the lock object");
            try {
               // this sleep is a cheap way to make sure the previous thread loops
               // around before another thread grabs the lock, does its work,
               // loops around and requests the lock again ahead of it.
               Thread.sleep(10);
            }
            catch (InterruptedException e) {
               //ignored
            }
            if (index != expectedIndex) {
               System.out.printf("Unexpected thread obtained lock! " +
                     "Expected: %d Actual: %d%n", expectedIndex, index);
               System.exit(0);
            }

            expectedIndex = (expectedIndex + 1) % NUM_THREADS;
            writeLock.unlock();
            System.out.println("slave thread " + index + " unlocked the lock object");
         }
      }
   }
}

