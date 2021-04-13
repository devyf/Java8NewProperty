package Date.TimeZone;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

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
