package com.example.demo.configuration;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CardNumberMaskingPatternLayout extends PatternLayout {
    private String patternsProperty;

    public String getPatternsProperty() {
        return patternsProperty;
    }

    public void setPatternsProperty(String patternsProperty) {
        this.patternsProperty = patternsProperty;
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        String message = super.doLayout(event);

        if (patternsProperty != null) {
            String[] patterns = patternsProperty.split("\\|");
            for (int i = 0; i < patterns.length; i++) {
                Pattern pattern = Pattern.compile(patterns[i]);

                Matcher matcher = pattern.matcher(message);
                if (matcher.find()) {
                    message = matcher.replaceAll("****");
                }
            }
        }

        return message;
    }
}