package Lamdba.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 *  Lambda表达式支持函数式接口的编程方式
 *  语法格式一：无参数、无返回值
 *  () -> System.out.println("Hello Lambda!");
 *  语法格式二：有一个参数无返回值
 *  (x) -> System.out.println(x);
 *  语法格式三：若只有一个参数，小括号可以省略不写
 *  x -> System.out.println(x);
 *  语法格式四：有两个以上的参数，有返回值，并且Lambda体中有多条语句
 *  Comparator<Integer> comparator = (x, y) -> {
 *     return Integer.compare(x, y);
 *  };
 *  语法格式五：若Lambda体中只有一条语句，return和大括号都可以省略不写
 *  Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
 *  语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器可以通过上下文推断出数据类型，即“类型推断”
 *  (Integer x, Integer y) -> Integer.compare(x, y);
 *  总结：
 *  1.左右遇一括号省，左侧推断类型省
 *  2.Lambda表达式需要“函数式接口”的支持
 *  函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用注解@FunctionalInterface修饰（检查接口是否是函数式接口）
 */
public class TestLambda {
    @Test
    public void test1(){
        final int num = 0; //jdk1.7以前，必须是final

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Lambda!" + num);
            }
        };
        r1.run();

        System.out.println("--------------------------");
        Runnable r2 = () -> System.out.println("Hello Lambda!" + num);
        r2.run();
    }

    @Test
    public void test2(){
        Consumer<String> con = x -> System.out.println(x);
        con.accept("这是单个参数的Lambda表达式");
    }

    @Test
    public void test3(){
        Comparator<Integer> comparator = (x, y) -> {
            return Integer.compare(x, y);
        };
    }

    @Test
    public void test4(){
        Comparator<Integer> comparator = (Integer x, Integer y) -> Integer.compare(x, y);
    }

    @Test
    public void test5(){
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
    }

    @Test
    public void test6(){
        Integer res1 = operation(20, x -> x * 20);
        Integer res2 = operation(30, y -> y + 60);
        System.out.println(res1);
        System.out.println(res2);
    }

    public Integer operation(Integer num, MyFunction function){
        return function.getValue(num);
    }

}
