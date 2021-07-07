package Stream.api;

import Lamdba.comparator.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestStreamAPI4 {
    List<Employee> empList = Arrays.asList(
            new Employee("张三", 38, 8888.88),
            new Employee("李四", 20, 6666.66),
            new Employee("王五", 18, 2222.22),
            new Employee("赵六", 8, 666.66),
            new Employee("田七", 77, 5555.55),
            new Employee("田七", 77, 3333.33),
            new Employee("田七", 77, 4444.44)
    );
    /**
     * 中间操作：
     * 排序
     * sorted()--自然排序（Comparable）
     * sorted(Comparator)--定制排序（Comparator）
     */
    @Test
    public void test1(){
        List<String> list = Arrays.asList("aaa", "ccc", "ddd", "bbb", "eee");
        list.stream()
            .sorted()  //自然排序
            .forEach(System.out::println);
        System.out.println("---------------------------");
        empList.stream()  //定制排序：先按年龄排序，年龄相同按照薪资排序
                .sorted((e1, e2) -> {
                    if(e1.getAge() != e1.getAge())
                        return e1.getAge().compareTo(e2.getAge());
                    else
                        return Double.compare(e1.getSalary(), e2.getSalary());
                })
                .forEach(System.out::println);
    }

}
