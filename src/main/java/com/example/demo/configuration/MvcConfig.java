package com.example.demo.configuration;

import com.example.demo.filter.RateLimiterHandler;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RateLimiterHandler(bucket()));
    }

    @Bean
    public Bucket bucket() {
        Refill refill = Refill.intervally(2, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(2, refill);
        Bucket bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();

        return bucket;
    }
}