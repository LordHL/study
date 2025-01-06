package cn.ktl.lab.springmvc.base.aop;

/**
 * @Author lin ho
 * Des: TODO
 */
public interface FieldMapper<T, R> {
    R map(T sourceValue);
}


