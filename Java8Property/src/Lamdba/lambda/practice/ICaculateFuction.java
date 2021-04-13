package Lamdba.lambda.practice;

/**
 *
 * @param <T> 运算类型
 * @param <R> 返回值类型
 */
public interface ICaculateFuction<T,R> {
    R getCaculResult(T t1, T t2);
}
