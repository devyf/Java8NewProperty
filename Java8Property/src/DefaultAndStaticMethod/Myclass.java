package DefaultAndStaticMethod;

import org.junit.Test;

public class Myclass /*extends ParentClass*/ implements MyFunction, MyFunction2{
    //类优先原则：如果父类和接口中同时声明了相同的方法名，会优先调用父类中的方法名
    @Test
    public void test1(){
        Myclass myclass = new Myclass();
        String name = myclass.getName();
        System.out.println(name);
    }

    @Override
    public String getName() {
        return "Myclass";
    }
}
