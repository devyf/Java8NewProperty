package Stream.api;

import Lamdba.comparator.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 中间操作：
 * 规约：reduce
 *
 */
public class TestStreamAPI6 {
    List<Employee> empList = Arrays.asList(
            new Employee("张三", 38, 8888.88, Employee.Status.BUSY),
            new Employee("李四", 20, 6666.66, Employee.Status.BUSY),
            new Employee("王五", 18, 2222.22, Employee.Status.FREE),
            new Employee("赵六", 8, 666.66, Employee.Status.VOCATION),
            new Employee("田七", 77, 5555.55, Employee.Status.BUSY),
            new Employee("田七", 77, 5555.55, Employee.Status.VOCATION),
            new Employee("田七", 77, 5555.55, Employee.Status.BUSY)
    );

    @Test
    public void test3(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);  //从标识位0开始，标识集合中至少有一个值
        System.out.println(sum);
        //求员工工资总和
        Optional<Double> optional = empList.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        Double allSalary = optional.get();
        System.out.println(allSalary);
    }

    /**
     * 收集
     * collect--将流转换成为其它形式，接收一个Collector接口的实现，用于给Stream中元素汇总的方法
     */
    @Test
    public void test4(){
        List<String> list = empList.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("---------------------");
        Set<String> set = empList.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("----------------------");
        HashSet<String> hashSet = empList.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
    }

    @Test
    public void test5(){
        //总数
        Long count = empList.stream()
                .collect(Collectors.counting());
        System.out.println(count);
        System.out.println("--------------------------");
        //平均值
        Double average = empList.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(average);
        System.out.println("---------------------------");
        //总和
        Double sum = empList.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);
        //最大值
        Optional<Employee> optional = empList.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        Employee employee = optional.get();
        System.out.println(employee);
        //最小值
        Optional<Double> optional1 = empList.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compareTo));
        System.out.println(optional.get());
    }

    //分组
    @Test
    public void test6(){
        Map<Employee.Status, List<Employee>> map = empList.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }

    //多级分组
    @Test
    public void test7(){
        Map<Employee.Status, Map<String, List<Employee>>> map = empList.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if (e.getAge() <= 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(map);
    }

    //分区：true/false为两个区
    @Test
    public void test8(){
        Map<Boolean, List<Employee>> map = empList.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));
        System.out.println(map);
    }

    //summarizing汇总聚合函数求值
    @Test
    public void test9(){
        DoubleSummaryStatistics dss = empList.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getMax());
        System.out.println(dss.getMin());
        System.out.println(dss.getAverage());
        System.out.println(dss.getCount());
    }

    //连接字符串操作
    @Test
    public void test10(){
        String joinStr = empList.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",", "***", "***"));
        System.out.println(joinStr);
    }
}
