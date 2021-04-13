## Java8新特性

### 1.Lambda表达式

Java8中引入了一个新的操作符“->”，该操作符称为箭头操作符或Lambda操作符，箭头操作符将Lambda表达式拆分成两部分。

左侧：Lambda表达式的参数列表；

右侧：Lambda表达式中所需执行的功能，即Lambda方法体。

Lambda表达式的语法格式及演示代码：

```java
/**
 *  Lambda表达式支持函数式接口的编程方式
 *  语法格式一：无参数、无返回值
 *  () -> System.out.println("Hello Lambda!");
 *  语法格式二：有一个参数无返回值
 *  (x) -> System.out.println(x);
 *  语法格式三：若只有一个参数，小括号可以省略不写
 *  x -> System.out.println(x);
 *  语法格式四：有两个以上的参数，有返回值，并且Lambda体中有多条语句
 *  Comparator<Integer> comparator = (x, y) -> {
 *     return Integer.compare(x, y);
 *  };
 *  语法格式五：若Lambda体中只有一条语句，return和大括号都可以省略不写
 *  语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器可以通过上下文推断出数据类型，即“类型推断”
 *
 *  总结：
 *  1.左右遇一括号省，左侧推断类型省
 *  2.Lambda表达式需要“函数式接口”的支持
 *  函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用注解@FunctionalInterface修饰（检查接口是否是函数式接口）
 */
public class TestLambda {
    @Test
    public void test1(){
        final int num = 0; //jdk1.7以前，必须是final

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Lambda!" + num);
            }
        };
        r1.run();

        System.out.println("--------------------------");
        Runnable r2 = () -> System.out.println("Hello Lambda!" + num);
        r2.run();
    }

    @Test
    public void test2(){
        Consumer<String> con = x -> System.out.println(x);
        con.accept("这是单个参数的Lambda表达式");
    }

    @Test
    public void test3(){
        Comparator<Integer> comparator = (x, y) -> {
            return Integer.compare(x, y);
        };
    }

    @Test
    public void test4(){
        Comparator<Integer> comparator = (Integer x, Integer y) -> Integer.compare(x, y);
    }

    @Test
    public void test5(){
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
    }

    @Test
    public void test6(){
        Integer res1 = operation(20, x -> x * 20);
        Integer res2 = operation(30, y -> y + 60);
        System.out.println(res1);
        System.out.println(res2);
    }

    public Integer operation(Integer num, MyFunction function){
        return function.getValue(num);
    }

}
```



### 2.函数式接口

函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。可以使用注解@FunctionalInterface修饰（检查接口是否是函数式接口）

#### 2.1.常用的java内置函数式接口

```java
/**
 * Java8内置的四大核心函数式接口
 * Consumer<T>：消费型接口：void accept(T t);
 * Supplier<T>：供给型接口：T get();
 * Function<T, R>：函数型接口：R apply(T t);
 * Predicate<T>：断言型接口：boolean test(T t);
 */
public class CoreFuction {
    //Consumer接口测试
    @Test
    public void testConsumer(){
        consume(10000, m -> System.out.println("每月还贷款，消费" + m  + "元"));
    }
    public void consume(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }

    //供给型接口
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

    //函数型接口
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

    //断言型接口
    @Test
    public void testPredicate(){
        List<String> stringList = Arrays.asList("ded2d2d2", "dd22d", "shangguigu", "de", "枫夜Alex", "ddff");
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
```





### 3.方法引用与构造器引用

#### 3.1.方法引用

```java
/**
 * 方法引用：若Lamda体中的内容有方法已经实现了，我们可以使用“方法引用”
 * (可以理解为方法引用是Lambda表达式的另一种表现形式)
 * 主要有三种语法格式：
 * 对象::实例方法名
 * 类::静态方法名
 * 类::实例方法名
 * ！！！注意：
 * ①Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的参数列表和返回值类型保持一致
 * ②若Lambda体中参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数值时，可以使用ClassName::method
 */
public class TestMethodRef {
    //对象::实例方法名
    @Test
    public void test1(){
        PrintStream ps = System.out;
        Consumer<String> con = x -> ps.println(x);
        //上面等价于
        Consumer<String> ps1 = ps::println;  //而ps又等价于System.out
        //最终等价于
        Consumer<String> ps2 = System.out::println;
    }

    //对象::实例方法名-2
    public void test11(){
        Employee employee = new Employee();
        Supplier<String> supplier = () -> employee.getName();  // T get();
        String name = supplier.get();
        System.out.println(name);
        //上面等价于
        Supplier<String> supplier1 = employee::getName;
        String name1 = supplier.get();
        System.out.println(name1);
    }

    //类::静态方法名
    @Test
    public void test2(){
        //当调用Comparator Lambda方法体进行实现时，发现已经有Integer实现了这个方法，那么就可以直接调用
        //而Integer的compare方法同时也是静态方法，所以可以直接使用类名::方法名调用
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        //上面等价于：
        Comparator<Integer> comparator1 = Integer::compare;
    }

    //类::实例方法名
    public void test3(){
        BiPredicate<String, String> bp = (s1, s2) -> s1.equals(s2);

        BiPredicate<String, String> bp2 = String::equals;
    }
}
```



#### 3.2.构造器引用

```java
/**
 *  构造器引用：
 *  语法格式：
 *  ClassName::new
 *  注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
 */
public class TestConstructorRef {
    @Test
    public void test(){
        Supplier<Employee> supplier = () -> new Employee();
        //上面形式等价于
        Supplier<Employee> supplier1 = Employee::new;
        //注意：Employee::new自动调用无参构造器创建对象，取决于Supplier中接口方法是否有参
        Employee employee = supplier1.get();  //Employee{name='null', age=null, salary=0.0, status=null}
        System.out.println(employee);
    }

    @Test
    public void test1(){
        BiFunction<String, Integer, Employee> bif = (p1, p2) -> new Employee();
        //上面形式等价于
        BiFunction<String, Integer, Employee> bif1 = Employee::new;  //需要Employee有对应的构造方法
        Employee employee = bif1.apply("余杭", 23);
        System.out.println(employee);
    }
}
```



#### 3.3.数组引用

```java
/**
 * 数组引用：
 *  语法格式：
 *  Type::new
 */
public class TestTypeRef {
    @Test
    public void test(){
        Function<Integer, String[]> function = x -> new String[x];
        String[] apply = function.apply(10);
        System.out.println(apply.length);
        //上面形式等价于
        Function<Integer, String[]> function1 = String[]::new;
        String[] apply1 = function1.apply(10);
        System.out.println(apply1.length);
    }
}
```





### 4.Stream API

Java8中有两大最为重要的改变。一个是Lambda表达式，另一个则是Stream API（java.util.stream.*）。

Stream是Java8中处理集合（数组）的关键抽象概念，利用Stream流式处理可以对集合执行非常复杂的查找、过滤和映射数据等操作。相当于数据库的SQL执行数据库查询。还可以使用Stream API来并行执行操作。



注意：

- Stream自己不会存储元素；
- Stream不会改变源对象。相反，它们会返回一个持有结果的新Stream。
- Stream操作是延迟执行的。这意味者它们会等到需要结果的时候才执行。

#### 4.1.创建Stream流的方式

```java
  //1.可以通过Collection系列集合提供的stream()或parallelStream()创建
  List<String> list = new ArrayList<>();
  Stream<String> stream = list.stream();
  //2.通过Arrays中的静态方法stream()获取数组
  Employee[] employees = new Employee[10];
  Stream<Employee> stream1 = Arrays.stream(employees);
  //3.通过Stream类中的静态方法0f()
  Stream<String> stream2 = Stream.of("aa", "bb", "cc");
  //4.创建无限流
  //迭代
  Stream<Integer> stream3 = Stream.iterate(0, x -> x + 2);
  stream3.limit(10).forEach(System.out::println);
  //5.使用Strem.generate生成
  Stream<Double> stream4 = Stream.generate(() -> Math.random());
  stream4.limit(5).forEach(System.out::println);
```



#### 4.2.Stream的中间操作

多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理！

而在终止操作时一次性全部处理，称为“惰性求值”。

这部分是Java8流式操作集合数组进行查询、过滤、限制、分组、排序、映射功能操作的核心，代码比较多，具体请查询Github上代码。



#### 4.3.并行流与顺序流

并行流就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流。

Java8中将并行进行了优化，Stream API可以声明性地通过parallel()与sequential()在并行流与顺序流之间进行切换。



> 了解Fork/Join框架

![image-20210412230003227](C:\Users\huang\AppData\Roaming\Typora\typora-user-images\image-20210412230003227.png)

```java
/**
 * 大数据计算
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private static final long serialVersionUID = 134656970987L;

    private long start;
    private long end;

    public ForkJoinCalculate(long start, long end){
        this.start = start;
        this.end = end;
    }

    private static final long THRESHOLD = 10000;  //拆分临界值

    @Override
    protected Long compute() {
        long sum = 0;
        long length = end -start;
        if(length <= THRESHOLD){
            for (long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        }else {
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();  //拆分子任务，同时压入线程队列
            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();
            //合并任务
            return left.join() + right.join();
        }
    }
}
```





#### 4.4.Optional类

Optional<T>类（java.util.Optional）是一个容器类，代表一个值存在或不存在，原来用null表示一个值不存在，现在Optional可以更好地表达这个概念，并且可以避免空指针异常。

常用方法：

- Optional.of(T t)：创建一个Optional实例
- Optional.empty()：创建一个空的Optional实例
- Optional.ofNullable(T t)：若t不为null，创建Optional实例，否则创建空实例
- isPresent()：判断是否包含值
- orElse(T t)：如果调用对象包含值，返回该值，否则返回t
- orElseGet(Supplier s)：如果调用对象包含值，返回该值，否则返回s获取的值
- map(Function f)：如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
- flatMap(Function mapper)：与map类似，要求返回值必须是Optional

Optional类在实际使用中可以避免大量重复的if/else循环语句，达到美化代码的目的：

```java
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
```



### 5.接口中的默认方法与静态方法

接口默认方法的***“类优先”***原则：

若一个接口中定义了一个默认方法，而另外一个父类或接口中又定义了一个同名的方法时。

- 优先选择父类的方法。如果一个父类提供了具体实现，那么接口中具有相同名称和参数的默认方法会被忽略。
- 接口冲突。如果一个父接口提供一个默认方法，而另一个父接口也提供了一个具有相同名称和参数列表的方法（不管是否是默认方法），都必须覆盖该方法来解决冲突。

```java
public interface MyFunction {
    //默认方法：可以实现默认方法
    default String getName(){
        return "Interface1 getName方法";
    }
    //允许接口中有static静态实现方法
    public static void show(){
        System.out.println("MyFunction接口中的静态方法！");
    }
}
```



### 6.新时间日期API

替代了原来的Date、SimpleDateFormat等线程不安全的类。

新的日期时间API采用了java.time包下的chrono、format、temporal、zone等类，是不可变的线程安全的类。



#### 6.1.本地时间与时间戳

LocalDate、LocalTime、LocalDateTime类的实例是不可变的对象，分别表示使用ISO-8601日历系统的日期、时间、日期和时间。它们提供了简单的日期或时间，并不包含当前的时间信息。也不包含与时区相关的信息。

```java
//LocalDate  LocalTime  LocalDateTime：可供人识别的时间日期格式
@Test
public void test1(){
    LocalDateTime dateTime = LocalDateTime.now();
    System.out.println(dateTime);

    LocalDateTime dateTime1 = LocalDateTime.of(2020, 7, 12, 6, 10);
    System.out.println(dateTime1);

    LocalDateTime plusDays = dateTime1.plusDays(3);
    System.out.println(plusDays);

    LocalDateTime minusDays = dateTime1.minusDays(3);
    System.out.println(minusDays);
    System.out.println(dateTime1.getYear());
    System.out.println(dateTime1.getMonthValue());
    System.out.println(dateTime1.getDayOfMonth());
    System.out.println(dateTime1.getHour());
    System.out.println(dateTime1.getMinute());
    System.out.println(dateTime1.getSecond());
}

//Instant：时间戳（以Unix元年：1970年1月1日00:00:00 到给定的时间之间的毫秒值）
@Test
public void test2(){
    Instant ins1 = Instant.now();  //默认获取UTC时区
    System.out.println(ins1);
    System.out.println(ins1.toEpochMilli());  //获取毫秒值
    OffsetDateTime dateTime = ins1.atOffset(ZoneOffset.ofHours(8));  //获取UTC时区带偏移量的时间
    System.out.println(dateTime);
    Instant epochSecond = Instant.ofEpochSecond(60);//相较于元年做时间加减运算
    System.out.println(epochSecond);
}

//Duration：计算两个“时间”之间的间隔
@Test
public void test3(){
    Instant ins1 = Instant.now();
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    Instant ins2 = Instant.now();
    Duration duration = Duration.between(ins1, ins2);
    System.out.println(duration.toMillis());
    System.out.println("--------------------------------");
    LocalDateTime ldt1 = LocalDateTime.now();
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    LocalDateTime ldt2 = LocalDateTime.now();
    Duration duration2 = Duration.between(ldt1, ldt2);
    System.out.println(duration2.toMillis());
}

//Period：计算两个“日期”之间的间隔
@Test
public void test4(){
    LocalDate ld1 = LocalDate.of(2015, 10, 23);
    LocalDate ld2 = LocalDate.now();

    Period period = Period.between(ld1, ld2);
    System.out.println(period);

    System.out.println(period.getYears());
    System.out.println(period.getMonths());
    System.out.println(period.getDays());
}
```



#### 6.2.日期的操纵

- TemporalAdjuster：时间校正器。有时我们可能需要获取例如：将日期调整到“下个周日”等操作。
- TemporalAdjusters：该类通过静态方法提供了大量的常用TemporalAdjuster的实现。

```java
//TemporalAdjuster：时间校正器
//TemporalAdjusters：集成了TemporalAdjuster，内部增加了多种方法
@Test
public void test5(){
    LocalDateTime ldt = LocalDateTime.now();
    System.out.println(ldt);

    LocalDateTime ldt2 = ldt.withDayOfMonth(10);
    System.out.println(ldt2);

    //将时间自动调整到下周日
    LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
    System.out.println(ldt3);

    //自定义：下一个工作日
    LocalDateTime workDate = ldt.with(lt -> {
    LocalDateTime ldt4 = (LocalDateTime) lt;
    if (ldt4.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
    return ldt4.plusDays(3);
    } else if (ldt4.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
    return ldt4.plusDays(2);
    } else {
    return ldt4.plusDays(1);
    }
    });
    System.out.println(workDate);
}
```

#### 6.3.时区的处理

Java8中加入了对时区的支持，带时区的时间为分别为：

ZonedDate、ZonedTime、ZonedDateTime

其中每个时区都对应着ID，地区ID都为“{区域}/{城市}”的格式，例如：Asia/Shanghai等。



ZoneId：该类中包含了所有的时区信息：

getAvailableZoneIds()：可以获取所有时区时区信息。

of(id)：用指定的时区信息获取ZoneId对象。

```java
/**
 * 获取不同时区信息
 */
public class TestZoneTime {
    //获取所有可用时区集合
    @Test
    public void test1(){
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out::println);
    }

    @Test
    public void test2(){
        LocalDateTime time1 = LocalDateTime.now(ZoneId.of("Asia/Pontianak"));
        System.out.println(time1);

        LocalDateTime time2 = LocalDateTime.now(ZoneId.of("Asia/Pontianak"));
        ZonedDateTime zoneShanghai = time2.atZone(ZoneId.of("Asia/Shanghai"));  //2021-04-13T13:11:28.149+08:00[Asia/Shanghai]
        System.out.println(zoneShanghai);
    }
}
```



### 7.其它新特性

#### 7.1.重复注解与类型注解

Java8对注解处理提供了两点改进：可重复的注解及可用于类型的注解。

可重复注解：可以在指定的方法或者是类上面添加相同的注解。	

![image-20210413141417818](C:\Users\huang\AppData\Roaming\Typora\typora-user-images\image-20210413141417818.png)