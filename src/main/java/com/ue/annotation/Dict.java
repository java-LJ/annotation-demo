package com.ue.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 专门用于数据字典中的数字转汉字的自定义注解
 * @author LiJun
 * @Date 2019/11/27
 * @Time 16:08
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dict {

    /**
     * 方法描述：数据dataSource
     * @return 返回类型： String
     */
    String dictDataSource();

    /**
     * 方法描述：这是返回后Put到josn中的文本key值
     * @return 返回类型： String
     */
    String dictText() default "";
}