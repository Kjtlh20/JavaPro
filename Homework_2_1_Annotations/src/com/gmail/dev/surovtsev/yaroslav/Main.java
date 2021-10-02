/* 1. Создать аннотацию, которая принимает параметры для метода. Написать код, который вызовет метод,
 * помеченный этой аннотацией, и передаст параметры аннотации в вызываемый метод.
 * @Test(a=2, b=5)
 * public void test(int a, int b) {...}
 */
package com.gmail.dev.surovtsev.yaroslav;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        final Class<?> cls = Main.class;
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test annotationTest = method.getAnnotation(Test.class);
                try {
                    Constructor<?> ctr = cls.getConstructor();
                    Main main = (Main) ctr.newInstance();
                    method.invoke(main, annotationTest.a(), annotationTest.b());
                } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test(a = 2, b = 5)
    public void test(int a, int b) {
        System.out.println(a + " + " + b + " = " + (a + b));
    }
}

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface Test {
    int a();

    int b();
}
