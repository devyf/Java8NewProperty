package Lamdba.comparator;

import org.junit.Test;

import java.util.*;

public class Demo1 {
    List<Employee> empList = Arrays.asList(
            new Employee("张三", 38, 8888.88),
            new Employee("李四", 20, 6666.66),
            new Employee("王五", 18, 2222.22),
            new Employee("赵六", 8, 666.66),
            new Employee("田七", 77, 4444.44)
    );

    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp){
        ArrayList<Employee> emps = new ArrayList<>();
        for (Employee employee : list) {
            if(mp.test(employee)){
                emps.add(employee);
            }
        }
        return emps;
    }

    //原来的匿名内部类
    @Test
    public void test1(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    @Test
    public void test2(){
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    //需求：获取当前公司中员工年龄大于35的员工信息
    @Test
    public void test3(){
        List<Employee> filterList = filterEmployees(empList);
        System.out.println(filterList);
    }
    public List<Employee> filterEmployees(List<Employee> employees){
        ArrayList<Employee> list = new ArrayList<>();
        for (Employee employee : employees) {
            if(employee.getAge() >= 35){
                list.add(employee);
            }
        }
        return list;
    }

    //需求：获取公司中工资大于5000的员工信息
    @Test
    public void test4(){
        List<Employee> employees = filterEmployees2(empList);
        System.out.println(employees);
    }
    public List<Employee> filterEmployees2(List<Employee> employees){
        ArrayList<Employee> list = new ArrayList<>();
        for (Employee employee : employees) {
            if(employee.getSalary() >= 5000){
                list.add(employee);
            }
        }
        return list;
    }

    //需求：按照下面的方式一一优化代码
    @Test
    public void test5(){
        //优化方式一：策略设计模式（每一个方法实现一个类），推荐：*
        //按年龄过滤
        List<Employee> employees = filterEmployee(empList, new FilterEmployeeByAge());
        System.out.println(employees);
        //按工资过滤
        List<Employee> employees1 = filterEmployee(empList, new FilterEmployeeBySalary());
        System.out.println(employees1);

        System.out.println("---------------------------------");
        //优化方式二：匿名内部类方式，推荐：**
        List<Employee> filterAgesEmp = filterEmployee(empList, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() >= 35;
            }
        });
        System.out.println(filterAgesEmp);

        List<Employee> filterSalaryEmp = filterEmployee(empList, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() >= 5000;
            }
        });
        System.out.println(filterSalaryEmp);
        System.out.println("---------------------------------");
        //优化方式三：Lamda表达式，推荐：***
        List<Employee> ageLambda = filterEmployee(empList, employee -> employee.getAge() >= 35);
        ageLambda.forEach(System.out::println);
        List<Employee> salaryLambda = filterEmployee(empList, employee -> employee.getSalary() >= 5000);
        salaryLambda.forEach(System.out::println);
        System.out.println("---------------------------------");
        //优化方式四：Stream API流式处理，推荐：****（假设上面代码都没有）
        empList.stream()
                .filter(employee -> employee.getAge() >=35)
                .limit(2)
                .forEach(System.out::println);

        empList.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

}
