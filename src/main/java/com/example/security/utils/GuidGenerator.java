package com.example.security.utils;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

/**
 * @author Daiyu Chen
 */
public abstract class GuidGenerator {
    private static final TimeBasedGenerator timeBasedGenerator = Generators.timeBasedGenerator(EthernetAddress.constructMulticastAddress());

    public static String createGuid() {
        return timeBasedGenerator.generate().toString();
    }


    public static String cleanUuid() {
        return timeBasedGenerator.generate().toString().replaceAll("-", "");
    }
}
