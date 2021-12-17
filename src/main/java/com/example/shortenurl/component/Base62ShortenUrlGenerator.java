package com.example.shortenurl.component;

import com.example.shortenurl.data.ShortenPath;
import org.springframework.stereotype.Component;

@Component
public class Base62ShortenUrlGenerator implements ShortenUrlGenerator {

    @Override
    public String generate(long id) {
        return String.format("%s/s/%s", REDIRECT_URL, encode(id));
    }

    @Override
    public Long parse(ShortenPath shortenPath) {
        final String path = shortenPath.getPath();
        String key = path.substring(path.lastIndexOf("/") + 1);
        return decode(key);
    }

    private String encode(long id) {
        StringBuffer sb = new StringBuffer();
        while(id > 0) {
            sb.append(BASE62.charAt((int) (id % 62)));
            id /= 62;
        }
        return sb.toString();
    }

    private long decode(String encoded) {
        long sum = 0;
        long power = 1;
        for (int i = 0; i < encoded.length(); i++) {
            sum += BASE62.indexOf(encoded.charAt(i)) * power;
            power *= 62;
        }
        return sum;
    }

}
