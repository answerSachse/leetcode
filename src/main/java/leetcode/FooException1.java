package leetcode;

import java.lang.reflect.Method;

public class FooException1 {
//  private static int tryBlock;
//  private static int catchBlock;
//  private static int finallyBlock  = 0;
//  private static int methodExit;
//
//  public static void test() {
//    for (int i = 0; i < 100; i++) {
//      try {
//        tryBlock = 0;
//        if (i < 50) {
//          System.out.println("---continue");
//          continue;
//        } else if (i < 80) {
//          System.out.println("---break");
//          break;
//        } else {
//          System.out.println("---return");
//          return;
//        }
//      } catch (Exception e) {
//        System.out.println("---catch");
//        catchBlock = 1;
//      } finally {
//        System.out.println("---finally");
//        System.out.println("finallyBlock:" + ++finallyBlock);
//      }
//    }
//    methodExit = 3;
//  }



  public static void target(int i){

  }

  public static void polluteProfile() throws Exception {
    Method method1 = FooException1.class.getMethod("target", int.class);
    Method method2 = FooException1.class.getMethod("target", int.class);
//    for (int i = 0; i < 2000; i++) {
//      method1.invoke(null, 0);
//      method2.invoke(null, 0);
//    }
    System.out.println(method1 == method2);
  }

  public static void main(String[] args) throws Exception {
    polluteProfile();
  }

}