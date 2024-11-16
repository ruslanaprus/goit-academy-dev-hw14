package com.example.goit_academy_dev_hw14.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class IdGenerator {
    private  final AtomicLong idGenerator = new AtomicLong(0);

    public long generateId(){
        return idGenerator.incrementAndGet();
    }
}
