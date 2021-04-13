package Date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class TestDateTimeFormatter {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //使用线程安全的类进行转换
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Callable<LocalDate> callable = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("2018-04-23", dtf);
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> list = new ArrayList<>();
        //创建10个线程并行去执行解析任务
        for (int i = 0; i < 10; i++) {
            Future<LocalDate> submit = pool.submit(callable);
            list.add(submit);
        }

        for (Future<LocalDate> future : list) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
