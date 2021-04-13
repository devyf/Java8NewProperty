package Parallel;

import java.util.concurrent.RecursiveTask;

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
