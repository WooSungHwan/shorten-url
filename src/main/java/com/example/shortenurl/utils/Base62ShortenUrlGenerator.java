package com.example.shortenurl.utils;

import org.springframework.stereotype.Component;

@Component
public class Base62ShortenUrlGenerator implements ShortenUrlGenerator {

    @Override
    public String generate(String shortenUrlKey) {
        return null;
    }

    public static String encoding(long param) {
        StringBuffer sb = new StringBuffer();
        while(param > 0) {
            sb.append(BASE62.charAt((int) (param % 62)));
            param /= 62;
        }
        return sb.toString();
    }

    public static long decoding(String param) {
        long sum = 0;
        long power = 1;
        for (int i = 0; i < param.length(); i++) {
            sum += BASE62.indexOf(param.charAt(i)) * power;
            power *= 62;
        }
        return sum;
    }

}
