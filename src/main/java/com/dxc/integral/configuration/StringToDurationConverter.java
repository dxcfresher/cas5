package com.dxc.integral.configuration;

import java.time.Duration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
final  class StringToDurationConverter implements Converter<String, Duration> {

	@Override
    public Duration convert(String source) {
        return Duration.parse(source);
    }

}
