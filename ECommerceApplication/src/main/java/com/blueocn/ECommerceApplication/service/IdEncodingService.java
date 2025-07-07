package com.blueocn.ECommerceApplication.service;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IdEncodingService {
    private final Hashids hashids;

    public IdEncodingService(@Value("${security.id.salt}") String salt) {
        this.hashids = new Hashids(salt, 8);
    }

    public String encode(Long id) {
        return hashids.encode(id);
    }

    public Long decode(String encoded) {
        long[] decoded = hashids.decode(encoded);
        return decoded.length > 0 ? decoded[0] : null;
    }
}

