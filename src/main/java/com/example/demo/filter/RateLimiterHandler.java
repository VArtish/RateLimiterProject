package com.example.demo.filter;

import com.example.demo.exception.RateLimiterException;
import io.github.bucket4j.Bucket;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RateLimiterHandler implements HandlerInterceptor {
    private Bucket bucket;

    public RateLimiterHandler(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!bucket.tryConsume(100)) {
            throw new RateLimiterException("Requests per minute exceeded");
        }

        return true;
    }
}
