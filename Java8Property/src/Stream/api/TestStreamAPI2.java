package Stream.api;

import Lamdba.comparator.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 *  一、Stream的三个操作步骤：
 *  1.创建Stream
 *  2.中间操作
 *  3.终止操作（终端操作）
 */
public class TestStreamAPI2 {
    List<Employee> empList = Arrays.asList(
            new Employee("张三", 38, 8888.88),
            new Employee("李四", 20, 6666.66),
            new Employee("王五", 18, 2222.22),
            new Employee("赵六", 8, 666.66),
            new Employee("田七", 77, 5555.55),
            new Employee("田七", 77, 5555.55),
            new Employee("田七", 77, 5555.55)
    );

    //中间操作
    /**
     * 筛选与切片
     * filter--接收Lambda，从流中排除某些元素
     * limit--截断流，使其元素不超过给定的数量
     * skip(n)--跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流，与limit(n)互补
     * distinct--筛选，通过流所生成元素的hashCode()和equals()去除重复元素
     */


    //内部迭代：迭代操作由Stream API完成
    @Test
    public void test1(){
        //中间操作，不会执行任何输出
        Stream<Employee> stream = empList.stream().filter(emp -> {
            System.out.println("Stream API的中间操作！");
            return emp.getAge() >= 35;
        });
        //终止操作：才会一次性执行全部内容，即“惰性求值”
        stream.forEach(System.out::println);
    }

    //外部迭代：使用迭代器完成
    @Test
    public void test2(){
        Iterator<Employee> iterator = empList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    //limit(n)：截断流，使元素不超过给定数量；同时有短路的功能，只要找到对应的结果，后续操作就不再执行
    @Test
    public void test3(){
        empList.stream()
                .filter(emp ->{
                    System.out.println("短路！");
                    return emp.getSalary() > 5000; })
                .limit(2)
                .forEach(System.out::println);
    }

    //skip(n)：跳过前n个元素
    @Test
    public void test4(){
        empList.stream()
                .filter(emp ->{
                    return emp.getSalary() > 5000; })
                .skip(2)  //跳过前面两个合适的结果
                .forEach(System.out::println);
    }

    //distinct：筛选去重，根据对象的hashcode和equals进行去重，需要重写hashcode与equals
    @Test
    public void test5(){
        empList.stream()
                .filter(emp ->{
                    return emp.getSalary() > 5000; })
                .skip(2)  //跳过前面两个合适的结果
                .distinct()
                .forEach(System.out::println);
    }

}
