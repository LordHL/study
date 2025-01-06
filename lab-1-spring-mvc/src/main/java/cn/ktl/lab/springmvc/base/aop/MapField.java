package cn.ktl.lab.springmvc.base.aop;

/**
 * @Author lin ho
 * Des: TODO
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MapField {
    Class<? extends FieldMapper<?, ?>> mapperClass(); // 泛型支持任意输入/输出类型
}


