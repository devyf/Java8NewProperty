package Stream.api;

import Lamdba.comparator.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *  中间操作：终止操作
 */
public class TestStreamAPI5 {
    List<Employee> empList = Arrays.asList(
            new Employee("张三", 38, 8888.88, Employee.Status.BUSY),
            new Employee("李四", 20, 6666.66, Employee.Status.BUSY),
            new Employee("王五", 18, 2222.22, Employee.Status.FREE),
            new Employee("赵六", 8, 666.66, Employee.Status.VOCATION),
            new Employee("田七", 77, 5555.55, Employee.Status.BUSY),
            new Employee("田七", 77, 5555.55, Employee.Status.VOCATION),
            new Employee("田七", 77, 5555.55, Employee.Status.BUSY)
    );

    /**
     * 查找与匹配
     * allMatch--检查是否匹配所有元素
     * anyMatch--检查是否至少匹配一个元素
     * noneMatch--检查是否没有匹配所有元素
     * findFirst--返回第一个元素
     * findAny--返回任意一个满足条件的元素
     * count--返回流中元素的总个数
     * max--返回流中最大值
     * min--返回流中最小值
     *
     */
    @Test
    public void test1(){
        boolean match = empList.stream()
                .allMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(match);

        boolean match2 = empList.stream()
                .anyMatch(e -> e.getStatus().equals(Employee.Status.FREE));
        System.out.println(match2);

        boolean match3 = empList.stream()
                .noneMatch(e -> e.getStatus().equals(Employee.Status.FREE));
        System.out.println(match3);
    }

    @Test
    public void test2(){
        Optional<Employee> optional = empList.stream()
                .sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        Employee employee = optional.get();
        System.out.println(employee);

        System.out.println("----------------------------");
        Optional<Employee> optiona2 = empList.parallelStream()  //获取一个并行流
                .filter(e -> e.getStatus().equals(Employee.Status.VOCATION))
                .findAny();
        Employee employee2 = optiona2.get();
        System.out.println(employee2);
    }

    @Test
    public void test3(){
        long count = empList.stream()
                .filter(e -> e.getName().equals("王五"))
                .count();
        System.out.println(count);
        //返回年龄最大的那个人
        Optional<Employee> optional = empList.stream().max((e1, e2) -> e1.getAge().compareTo(e2.getAge()));
        Employee employee = optional.get();
        System.out.println(employee);
        //返回工资最低的工资数
        Optional<Double> min = empList.stream()
                .map(Employee::getSalary)
                .min(Double::compareTo);
        System.out.println(min.get());
    }
}
