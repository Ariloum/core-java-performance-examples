package com.google.code.java.core.primitives;

import java.util.HashMap;
import java.util.Map;

public class WrapperMain {
    public static void main(String... args) throws InterruptedException {
        Map<Integer, Integer> counters = new HashMap<Integer, Integer>();
        while (true) {
            performTest(counters);
            Thread.sleep(100);
        }
    }

    private static void performTest(Map<Integer, Integer> counters) {
        counters.clear();
        long start = System.nanoTime();
        int runs = 1000 * 1000;
        for (Integer i = 0; i < runs; i++) {
            Integer x = i % 1000;
            Integer y = i / 1000;
            Integer times = x * y;
            Integer count = counters.get(times);
            if (count == null)
                counters.put(times, 1);
            else
                counters.put(times, count + 1);
        }
        long time = System.nanoTime() - start;
        System.out.printf("Took %,d ns per loop%n", time / runs);
    }
}
