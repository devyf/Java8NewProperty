package Lamdba.corefunction;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8内置的四大核心函数式接口
 * Consumer<T>：消费型接口：void accept(T t);
 * Supplier<T>：供给型接口：T get();
 * Function<T, R>：函数型接口：R apply(T t);
 * Predicate<T>：断言型接口：boolean test(T t);
 */
public class CoreFuction {
    //Consumer<T>：消费型接口测试
    @Test
    public void testConsumer(){
        consume(1000.56, m -> System.out.println("每月吃饭，消费" + m  + "元"));
    }
    public void consume(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }

    //Supplier<T>：供给型接口测试
    @Test
    public void testSupplier(){
        List<Integer> list = getNumberList(10, () -> (int)(Math.random() * 100));
        list.forEach(System.out::println);
    }
    public List<Integer> getNumberList(Integer num, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer res = supplier.get();
            list.add(res);
        }
        return list;
    }

    //Function<T, R>：函数型接口
    @Test
    public void testFunction(){
        String handler = strHandler("\t\n  B站资源好又多！   ", str -> str.trim());
        String strHandler1 = strHandler("B站资源好又多！", str -> str.substring(2, 4));
        System.out.println(handler);
        System.out.println(strHandler1);
    }
    public String strHandler(String str, Function<String, String> function){
        return function.apply(str);
    }

    //Predicate<T>：断言型接口
    @Test
    public void testPredicate(){
        List<String> stringList = Arrays.asList("ded2d2d2", "枫夜dd22d", "shangguigu", "de", "枫夜Alex", "ddff");
        List<String> res = predicateRes(stringList, t -> t.length() > 3 && t.startsWith("枫夜"));
        res.forEach(System.out::println);
    }

    public List<String> predicateRes(List<String> list, Predicate<String> predicate){
        List<String> arrayList = new ArrayList<>();
        for (String item : list) {
            if(predicate.test(item)){
                arrayList.add(item);
            }
        }
        return arrayList;
    }
}
