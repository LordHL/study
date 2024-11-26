package cn.ktl.lab.springmvc.utils;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.cglib.beans.BeanCopier;

import java.beans.PropertyDescriptor;
import java.text.MessageFormat;
import java.util.List;

/**
 * @Author lin ho
 * Des: 实体转换
 */
public class BeanConvertUtils {

    /**
     * 批量实体转换
     *
     * @param resourceObj
     * @param targetClass
     * @param <T>
     * @param <R>
     * @return
     */
    public final static <T, R> List<T> listConvert(List<R> resourceObj, Class<T> targetClass) {
        if (resourceObj != null && !resourceObj.isEmpty()) {
            List<T> tList = Lists.newArrayList();
            for (R r : resourceObj) {
                T t = baseConvert(r, targetClass);
                tList.add(t);
            }
            return tList;
        }
        return resourceObj == null ? null : Lists.newArrayList();
    }

    /**
     * 单一实体转换
     *
     * @param resourceObj
     * @param targetClazz
     * @param <T>
     * @param <R>
     * @return
     */
    public final static <T, R> T baseConvert(R resourceObj, Class<T> targetClazz) {
        if (resourceObj == null) {
            return null;
        }
        BeanCopier beanCopier = BeanCopier.create(resourceObj.getClass(), targetClazz, false);
        T targetObject = null;
        try {
            targetObject = targetClazz.newInstance();
        } catch (Exception e) {

        }
        if (targetObject == null) {
            throw new RuntimeException(MessageFormat.format("构造{0}失败，BeanCopier无法完成", targetClazz));
        }
        beanCopier.copy(resourceObj, targetObject, null);
        return targetObject;
    }


    /**
     * 更新目标对象中的字段，仅拷贝源对象中非空的字段
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void updateNonNullFields(Object source, Object target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("源对象和目标对象不能为空");
        }

        // 获取源对象和目标对象的属性描述器
        BeanWrapper srcWrapper = new BeanWrapperImpl(source);
        BeanWrapper tgtWrapper = new BeanWrapperImpl(target);

        // 遍历源对象的属性描述器
        for (PropertyDescriptor pd : srcWrapper.getPropertyDescriptors()) {
            String propertyName = pd.getName();
            // 跳过 class 属性
            if ("class".equals(propertyName)) {
                continue;
            }

            // 获取源对象的字段值
            Object value = srcWrapper.getPropertyValue(propertyName);

            // 如果字段值不为空，且目标对象可写，则进行更新
            if (value != null && tgtWrapper.isWritableProperty(propertyName)) {
                tgtWrapper.setPropertyValue(propertyName, value);
            }
        }
    }

}
