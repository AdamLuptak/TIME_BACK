package com.example.adam.timemanagerultimate.test;

import android.test.InstrumentationTestCase;

/**
 * Created by adam on 15.3.2016.
 */
public class ExampleTest extends InstrumentationTestCase {
    public void test() throws Exception {
        final int expected = 2;
        final int reality = 5;
        assertEquals(expected, reality);
    }
}