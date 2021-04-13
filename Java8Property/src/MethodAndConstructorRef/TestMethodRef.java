package MethodAndConstructorRef;

import Lamdba.comparator.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 方法引用：若Lamda体中的内容有方法已经实现了，我们可以使用“方法引用”
 * (可以理解为方法引用是Lambda表达式的另一种表现形式)
 * 主要有三种语法格式：
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 * ！！！注意：
 * ①Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的参数列表和返回值类型保持一致
 * ②若Lambda体中参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数值时，可以使用ClassName::method
 */
public class TestMethodRef {
    //对象::实例方法名
    @Test
    public void test1(){
        PrintStream ps = System.out;
        Consumer<String> con = x -> ps.println(x);
        //上面等价于
        Consumer<String> ps1 = ps::println;  //而ps又等价于System.out
        //最终等价于
        Consumer<String> ps2 = System.out::println;
    }

    //对象::实例方法名-2
    public void test11(){
        Employee employee = new Employee();
        Supplier<String> supplier = () -> employee.getName();  // T get();
        String name = supplier.get();
        System.out.println(name);
        //上面等价于
        Supplier<String> supplier1 = employee::getName;
        String name1 = supplier.get();
        System.out.println(name1);
    }

    //类::静态方法名
    @Test
    public void test2(){
        //当调用Comparator Lambda方法体进行实现时，发现已经有Integer实现了这个方法，那么就可以直接调用
        //而Integer的compare方法同时也是静态方法，所以可以直接使用类名::方法名调用
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        //上面等价于：
        Comparator<Integer> comparator1 = Integer::compare;
    }

    //类::实例方法名
    public void test3(){
        BiPredicate<String, String> bp = (s1, s2) -> s1.equals(s2);

        BiPredicate<String, String> bp2 = String::equals;
    }
}
