package jvm.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.IntStream;

/**
 * @author hefuhai
 */
public class InvokeTest {



  public static void main(String[] args) throws Throwable {
    reflection();
    methodHandle();

    IntStream.of(1,2,3).map(i->i+2).map(i->i+5);

  }


  /**
   * 反射方式
   */
  public static void reflection()
    throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method bar = Test.class.getMethod("target", int.class);
    bar.invoke(null, 0);
  }


  /**
   * 方法句柄
   */
  public static void methodHandle() throws Throwable {
    MethodHandles.Lookup lookup = MethodHandles.lookup();
    MethodType methodType = MethodType.methodType(void.class, int.class);
    MethodHandle bar = lookup.findStatic(Test.class, "target", methodType);
    bar.invokeExact( 1);
  }

}