package Stream.api;

import Lamdba.comparator.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *  一、Stream的三个操作步骤：
 *  1.创建Stream
 *  2.中间操作
 *  3.终止操作（终端操作）
 */
public class TestStreamAPI {
    @Test
    public void test1(){
        //1.可以通过Collection系列集合提供的stream()或parallelStream()创建
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        //2.通过Arrays中的静态方法stream()获取数组
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);
        //3.通过Stream类中的静态方法0f()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");
        //4.创建无限流
        //迭代：如果不限制次数，就是无限迭代
        Stream<Integer> stream3 = Stream.iterate(0, x -> x + 2);
        stream3.limit(10).forEach(System.out::println);
        //5.使用Strem.generate生成
        Stream<Double> stream4 = Stream.generate(() -> Math.random());
        stream4.limit(5).forEach(System.out::println);
    }
}
