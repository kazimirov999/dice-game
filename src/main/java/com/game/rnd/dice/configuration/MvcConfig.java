package com.game.rnd.dice.configuration;

import com.game.rnd.dice.type.Status;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToStatusConverter());
    }

    private class StringToStatusConverter implements Converter<String, Status> {
        @Override
        public Status convert(String s) {
            return Status.fromString(s);
        }
    }
}
