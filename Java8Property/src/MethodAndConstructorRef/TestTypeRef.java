package MethodAndConstructorRef;

import org.junit.Test;

import java.util.function.Function;

/**
 * 数组引用：
 *  语法格式：
 *  Type::new
 */
public class TestTypeRef {
    @Test
    public void test(){
        Function<Integer, String[]> function = x -> new String[x];
        String[] apply = function.apply(10);
        System.out.println(apply.length);
        //上面形式等价于
        Function<Integer, String[]> function1 = String[]::new;
        String[] apply1 = function1.apply(10);
        System.out.println(apply1.length);
    }
}
