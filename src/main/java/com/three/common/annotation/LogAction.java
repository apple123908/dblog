package com.three.common.annotation;

import java.lang.annotation.*;
import java.util.Map;

/**
 * 申明一个注解用户
 * Created by three on 2017/9/2.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAction {

    String name();

}
