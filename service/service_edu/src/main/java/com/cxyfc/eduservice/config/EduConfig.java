package com.cxyfc.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author HASEE
 * @create 2020-03-10 23:05
 */
@Configuration
@MapperScan("com.cxyfc.eduservice.mapper")
public class EduConfig {
}
