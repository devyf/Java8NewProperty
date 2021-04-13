package Annotation;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 *  可重复注解与注解类型
 */
public class TestAnnotation {

    @Test
    public void test1() throws NoSuchMethodException {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method m1 = clazz.getMethod("show");
        MyAnnotations[] annotations = m1.getAnnotationsByType(MyAnnotations.class);
        for (MyAnnotations annotation : annotations) {
            System.out.println(annotation.value());
        }
    }


    @MyAnnotation("hello")
    @MyAnnotation("world")
    public void show(@MyAnnotation("abc") String str){

    }
}
