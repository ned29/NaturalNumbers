package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.powermock.reflect.Whitebox.invokeMethod;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Application.class)
public class ApplicationTest {

    private static final String PHONE_NUMBER_1 = "2 10 6 9 30 6 6 4";
    private static final String PHONE_NUMBER_2 = "2 10 69 30 6 6 4";
    private static final String PHONE_NUMBER_3 = "0 0 30 69 700 24 1 3 50 2";
    private static final String PHONE_NUMBER_INVALID_1 = "0030 69 700 24 1 3 50 2";
    private static final String PHONE_NUMBER_INVALID_2 = " dfd 0030 69 700 24 1 3 50 2";

    private Application applicationTest = new Application();

    @Test
    public void testAmbiguitiesSuccess() throws Exception {
        Set<String> result = new HashSet<>();
        invokeMethod(applicationTest, "calculateAmbiguities", "", PHONE_NUMBER_1.split(" "), result);
        assertEquals(2, result.size());
        result = new HashSet<>();
        invokeMethod(applicationTest, "calculateAmbiguities", "", PHONE_NUMBER_2.split(" "), result);
        assertEquals(4, result.size());
        result = new HashSet<>();
        invokeMethod(applicationTest, "calculateAmbiguities", "", PHONE_NUMBER_3.split(" "), result);
        assertEquals(8, result.size());
    }

    @Test
    public void testCheckNumber() throws Exception {
        assertTrue(invokeMethod(applicationTest, "checkNumber", (Object) PHONE_NUMBER_1.split(" ")));
        assertTrue(invokeMethod(applicationTest, "checkNumber", (Object) PHONE_NUMBER_2.split(" ")));
        assertTrue(invokeMethod(applicationTest, "checkNumber", (Object) PHONE_NUMBER_2.split(" ")));
        assertFalse(invokeMethod(applicationTest, "checkNumber", (Object) PHONE_NUMBER_INVALID_1.split(" ")));
        assertFalse(invokeMethod(applicationTest, "checkNumber", (Object) PHONE_NUMBER_INVALID_2.split(" ")));
    }
}