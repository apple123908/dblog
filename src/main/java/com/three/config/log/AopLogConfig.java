package com.three.config.log;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 设置aopBean
 * Created by three on 2017/9/2.
 */
@Configuration
@ComponentScan(basePackages="com.three.*")
@EnableAspectJAutoProxy
public class AopLogConfig {
}
