package Lamdba.lambda.practice;

import Lamdba.comparator.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PracticeDemo {
    List<Employee> empList = Arrays.asList(
            new Employee("张三", 38, 8888.88),
            new Employee("李四", 20, 6666.66),
            new Employee("王五", 18, 2222.22),
            new Employee("赵六", 8, 666.66),
            new Employee("田七", 77, 4444.44)
    );

    //需求1：通过Collection.sort()，定制排序比较两个Employee（先按年龄比，年龄相同按姓名比）
    @Test
    public void sorted(){
        Collections.sort(empList, (e1, e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else {
                return e2.getAge().compareTo(e1.getAge());
            }
        });
        empList.forEach(System.out::println);
    }

    //需求2：使用ICaculateFuction接口计算两个long型参数的和，再计算两个long型参数的积
    @Test
    public void getVal(){
        Object val1 = cacluteVal(10L, 20L, (x, y) -> x + y);
        Object val2 = cacluteVal(10L, 20L, (x, y) -> x * y);
        System.out.println(val1);
        System.out.println(val2);
    }

    public Object cacluteVal(long num, long num2, ICaculateFuction<Long, Long> fuction){
        return fuction.getCaculResult(num, num2);
    }
}
