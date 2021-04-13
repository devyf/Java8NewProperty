package Date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class TestSimpleDateFormat {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Callable<Date> callable = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                //return sdf.parse("20200413");
                return DateFormatThreadLocal.convert("2020-04-13");
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<Date>> list = new ArrayList<>();
        //创建10个线程并行去执行解析任务
        for (int i = 0; i < 10; i++) {
            Future<Date> submit = pool.submit(callable);
            list.add(submit);
        }

//        list.stream()
//                .forEach(dateFuture -> {
//                    try {
//                        System.out.println(dateFuture.get());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                });
        for (Future<Date> future : list) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
