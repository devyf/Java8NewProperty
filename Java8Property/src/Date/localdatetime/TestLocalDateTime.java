package Date.localdatetime;

import org.junit.Test;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

public class TestLocalDateTime {
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
}
