package Date.localdatetime;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestLocalDateTime2 {
    //日期时间格式化：DateTimeFormatter
    //把当前日期转换成指定格式的日期
    @Test
    public void test1(){
        //DateTimeFormatter内置的日期格式化
        DateTimeFormatter dateTime = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        String format = ldt.format(dateTime);
        System.out.println(format);
        System.out.println("-----------------------------");

        //自定义日期格式转换
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format1 = dtf2.format(ldt);
        System.out.println(format1);
    }

    //把指定字符串格式化为日期
    @Test
    public void test2(){
        String str1="2018-07-05 12:24:12";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(str1, dtf);
        System.out.println(parse);
    }
}
