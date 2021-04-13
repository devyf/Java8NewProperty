package Parallel;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinTest {
    /**
     * ForkJoin框架
     */
    @Test
    public void testForkJoin(){
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        RecursiveTask<Long> task = new ForkJoinCalculate(0, 1000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());
    }

    /**
     * 普通for
     */
    @Test
    public void testFori(){
        Instant start = Instant.now();
        long sum = 0L;
        for (long i = 0; i < 1000000000L; i++) {
            sum += i;
        }
        //System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());
    }

    /**
     * java8并行流：实际得出在大数据1亿以上的数据量求和时，java8 parallel性能最高
     */
    public void testParallel(){
        Instant start = Instant.now();
        LongStream.rangeClosed(0, 1000000000L)
                .parallel()
                .reduce(0, Long::sum); //计算求和
        Instant end = Instant.now();
        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());

    }


}
