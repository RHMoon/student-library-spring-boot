package com.devland.studentlibraryspringboot.utils;

import org.modelmapper.ModelMapper;

public interface Convertable {
    default <T> T convertTo(ModelMapper modelMapper, Class<T> targetClass) {
        return modelMapper.map(this, targetClass);
    }
}
