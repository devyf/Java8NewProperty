package OptionalDemo;

import Lamdba.comparator.Employee;
import org.junit.Test;

import java.util.Optional;

/*
    Java8中Optional的方法：
    - Optional.of(T t)：创建一个Optional实例
    - Optional.empty()：创建一个空的Optional实例
    - Optional.ofNullable(T t)：若t不为null，创建Optional实例，否则创建空实例
    - isPresent()：判断是否包含值
    - orElse(T t)：如果调用对象包含值，返回该值，否则返回t
    - orElseGet(Supplier s)：如果调用对象包含值，返回该值，否则返回s获取的值
    - map(Function f)：如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
    - flatMap(Function mapper)：与map类似，要求返回值必须是Optional*/
public class TestOptional {
    //测试1：of快速创建一个Optional实例，使用of可以快速定位null发生的位置
    @Test
    public void test1() {
        Optional<Employee> optional = Optional.of(new Employee());
        Employee employee = optional.get();
        System.out.println(employee);
        //如果of中传入null，则直接抛出异常
        Optional<Object> optional1 = Optional.of(null);
        System.out.println(optional1.get());
    }

    @Test
    public void test2() {
        //允许构建一个空实例，获取时抛出异常：java.util.NoSuchElementException: No value present
        Optional<Employee> empty = Optional.empty();
        System.out.println(empty.get());
    }

    @Test
    public void test3() {
        //允许构建一个空实例，获取时抛出异常：java.util.NoSuchElementException: No value present
        //当传入new Employee时，返回一个对象，是of与empty的综合
        Optional<Employee> optional = Optional.ofNullable(null);  //java.util.NoSuchElementException: No value present
        //如果有值不为null才获取
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
    }

    @Test
    public void test4() {
        //有值，则返回值，没值使用默认的
        Optional<Employee> optional = Optional.ofNullable(new Employee());
        Employee employee = optional.orElse(new Employee("张三", 23, 2499.5));
        System.out.println(employee);
    }

    @Test
    public void test5() {
        //有值，则返回值，没值使用默认的
        Optional<Employee> optional = Optional.ofNullable(new Employee("张三", 28));
        Optional<String> optional1 = optional.map(e -> e.getName());
        System.out.println(optional1.get());

        Optional<String> optional2 = optional.flatMap(e -> Optional.ofNullable(e.getName()));
        System.out.println(optional2.get());
    }

    //实际运用场景：设置默认值预防空指针输入
    @Test
    public void test6() {
        Man man1 = null;
        Man man2 = new Man();
        Man man3 = new Man(new Lady("朝旭"));
        //使用原始的if/else重复判空方法，会增加代码圈复杂度
        String nullMan = getManLadyName(man1);
        System.out.println(nullMan);
        String noLadyMan = getManLadyName(man2);
        System.out.println(noLadyMan);
        String ladyMan = getManLadyName(man3);
        System.out.println(ladyMan);
    }

    //原始项目low b的代码，可读性强，易替代性高
    public String getManLadyName(Man man) {
        if (man != null) {
            Lady lady = man.getLady();
            if (lady != null) {
                System.out.println(lady.getName());
                return lady.getName();
            }
        }
        return "朝旭";
    }


    //实际运用场景：设置默认值预防空指针输入
    @Test
    public void test7() {
        NewMan man1 = null;
        NewMan man2 = new NewMan(Optional.ofNullable(new Lady()));
        NewMan man3 = new NewMan(Optional.ofNullable(new Lady("朝旭")));
        String nullMan = getOpManLadyName(man1);
        System.out.println(nullMan);
        String noLadyMan = getOpManLadyName(man2);
        System.out.println(noLadyMan);
        String ladyMan = getOpManLadyName(man3);
        System.out.println(ladyMan);
    }

    /***
     * 使用Optional：减少圈复杂度，让初级看不懂，增加内卷度
     * @param man
     * @return
     */
    public String getOpManLadyName(NewMan man) {
        return  Optional.ofNullable(man)
                .orElse(new NewMan())
                .getLady()
                .orElse(new Lady("朝旭"))
                .getName();
    }

    //简化if/else
    public String getUpperName(User user){
        if (user != null) {
            String userName = user.getUserName();
            if (userName != null) {
                return userName.toUpperCase();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public String getOptionalUpName(User user){
        return Optional.ofNullable(user)
                .map(User::getUserName)
                .map(String::toUpperCase)
                .orElse(null).toString();
    }
}
