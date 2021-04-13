package DefaultAndStaticMethod;

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
