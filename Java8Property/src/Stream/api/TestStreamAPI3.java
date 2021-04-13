package Stream.api;

import Lamdba.comparator.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestStreamAPI3 {
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
     * 映射
     * map--接收Lambda，将元素转换成其它形式或提取信息，接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     * flatMap--接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */

    @Test
    public void test1(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream()
                .map(e -> e.toUpperCase())
                .forEach(System.out::println);
        System.out.println("------------------------------------");
        empList.stream().map(Employee::getName).distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test2(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        List<Object> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.addAll(list);
        System.out.println(list1);
    }

    //使用flatmap进行流中的数据遍历
    @Test
    public void test3(){
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        Stream<Stream<Character>> stream = list.stream()
                .map(TestStreamAPI3::filterCharacter);
        stream.forEach(sm -> {
            sm.forEach(System.out::println);
        });
        System.out.println("-------------------------------");
        list.stream()
                .flatMap(TestStreamAPI3::filterCharacter).forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        ArrayList<Character> list = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }

}
