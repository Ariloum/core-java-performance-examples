package com.google.code.java.core.sizeof;

import org.junit.Test;

import java.util.BitSet;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicReference;

import static junit.framework.Assert.assertEquals;

public class SizeofUtilTest {
    @Test
    public void testAverageBytes() throws Exception {
        assertEquals(4.0, new SizeofUtil() {
            int[] array;

            @Override
            protected int create() {
                array = new int[1024];
                return array.length;
            }
        }.averageBytes(), 0.02);

        assertEquals(1.0 / 8, new SizeofUtil() {
            BitSet bits;

            @Override
            protected int create() {
                bits = new BitSet(1024 * 1024);
                return bits.size();
            }
        }.averageBytes(), 1e-4);
    }

    @Test
    public void testHeaderSize() {
        final int factor = 1;
        System.out.printf("The average size of an Integer is %.1f bytes%n", new SizeofUtil() {
            Integer obj = null;

            @Override
            protected int create() {
                obj = new Integer(1);
                return 1;
            }
        }.averageBytes());
        System.out.printf("The average size of a Long is %.1f bytes%n", new SizeofUtil() {
            Long obj = null;

            @Override
            protected int create() {
                obj = new Long(1);
                return 1;
            }
        }.averageBytes());
        System.out.printf("The average size of an AtomicReference is %.1f bytes%n", new SizeofUtil() {
            AtomicReference obj = null;

            @Override
            protected int create() {
                obj = new AtomicReference();
                return 1;
            }
        }.averageBytes());
        System.out.printf("The average size of a Calendar is %.1f bytes%n", new SizeofUtil() {
            Calendar obj = null;

            @Override
            protected int create() {
                obj = Calendar.getInstance();
                return 1;
            }
        }.averageBytes());
        System.out.printf("The average size of an Exception is %.1f bytes%n", new SizeofUtil() {
            Exception obj = null;

            @Override
            protected int create() {
                obj = new Exception("" + System.currentTimeMillis());
                return 1;
            }
        }.averageBytes());
        System.out.printf("The average size of a bit in a BitSet is %.3f bytes%n", new SizeofUtil() {
            BitSet obj = null;

            @Override
            protected int create() {
                obj = new BitSet(128 * 1024);
                return obj.size();
            }
        }.averageBytes());
    }
}
