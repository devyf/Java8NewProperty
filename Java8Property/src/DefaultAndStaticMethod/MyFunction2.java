package DefaultAndStaticMethod;

public interface MyFunction2 {
    //默认方法：可以实现默认方法
    default String getName(){
        return "Interface2 getName方法";
    }

    //允许接口中有static静态实现方法
    public static void show(){
        System.out.println("MyFunction2接口中的静态方法！");
    }
}
