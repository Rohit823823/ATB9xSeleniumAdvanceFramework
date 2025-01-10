package com.therohitsahu.tests.sample;
import io.qameta.allure.Description;
import junit.framework.Assert;
import org.testng.annotations.Test;

public class TestSample {

    @Test
    public void testSample0() {
        Assert.assertTrue(true);
    }

    @Description("Sample TC-1")
    @Test
    public void testSample1() {
        Assert.assertTrue(true);
    }
}
