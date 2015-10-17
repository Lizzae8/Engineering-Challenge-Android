package com.holmusk.restapi;

import java.io.IOException;

import retrofit.Converter;

/**
 * Created by gmsdvt on 10/12/15.
 */
public class ToStringConverter implements Converter<String, String>{

    @Override
    public String convert(String value) throws IOException {
        return value;
    }
}
