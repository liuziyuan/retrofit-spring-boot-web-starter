package io.github.liuziyuan.retrofit.spring.boot.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootConfig {
    @Bean
    @ConditionalOnMissingBean
    public CustomRetrofitBuilderExtension webCustomConfig() {
        return new CustomRetrofitBuilderExtension();
    }
}
