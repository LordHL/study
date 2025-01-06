package cn.ktl.lab.springmvc.base.aop;

/**
 * @Author lin ho
 * Des: TODO
 */

import cn.hutool.extra.spring.SpringUtil;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
public class FieldMappingAspect {

    @AfterReturning(pointcut = "@annotation(cn.ktl.lab.springmvc.base.aop.CountryMapping)", returning = "result")
    public void mapFields(Object result) throws IllegalAccessException {
        if (result == null) {
            return;
        }

        if (result instanceof Iterable<?>) {
            for (Object item : (Iterable<?>) result) {
                processMapping(item);
            }
        } else {
            processMapping(result);
        }
    }

    private void processMapping(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            MapField annotation = field.getAnnotation(MapField.class);
            if (annotation != null) {
                field.setAccessible(true);
                Object sourceValue = field.get(obj); // 获取字段的原始值

                // 动态获取映射器实例
                Class<? extends FieldMapper<?, ?>> mapperClass = annotation.mapperClass();
                FieldMapper<Object, Object> mapper = getFieldMapper(mapperClass);

                // 调用泛型映射器进行转换
                Object mappedValue = mapper.map(sourceValue);
                field.set(obj, mappedValue); // 设置转换后的值
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T, R> FieldMapper<T, R> getFieldMapper(Class<? extends FieldMapper<?, ?>> mapperClass) {
        return (FieldMapper<T, R>) SpringUtil.getBean(mapperClass);
    }
}


