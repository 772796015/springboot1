package com.example.demo.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";//参数列表，此处会在LogAspect方法中log.value()调用获取 注解@Log(value="-------------------自定义注解实现了!!!------------")的值
}
