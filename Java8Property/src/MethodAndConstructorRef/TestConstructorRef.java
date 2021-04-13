package MethodAndConstructorRef;

import Lamdba.comparator.Employee;
import org.junit.Test;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 *  构造器引用：
 *  语法格式：
 *  ClassName::new
 *  注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
 */
public class TestConstructorRef {
    @Test
    public void test(){
        Supplier<Employee> supplier = () -> new Employee();
        //上面形式等价于
        Supplier<Employee> supplier1 = Employee::new;
        //注意：Employee::new自动调用无参构造器创建对象，取决于Supplier中接口方法是否有参
        Employee employee = supplier1.get();  //Employee{name='null', age=null, salary=0.0, status=null}
        System.out.println(employee);
    }

    @Test
    public void test1(){
        BiFunction<String, Integer, Employee> bif = (p1, p2) -> new Employee();
        //上面形式等价于
        BiFunction<String, Integer, Employee> bif1 = Employee::new;  //需要Employee有对应的构造方法
        Employee employee = bif1.apply("余杭", 23);
        System.out.println(employee);
    }
}
