package com.example;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class MySeriveTest {

    private MyService arrange;

    @Before
    public void setUp() {
        System.out.println("Setup Method");
        arrange = new MyService();
    }

    @After
    public void tearDown() {
        System.out.println("Teardown Method");
        arrange = null;
    }

    @Test
    public void testAdd() {

        // Arrange
        int a = 5;
        int b = 3;

        // Act
        int result = arrange.add(a, b);

        // Assert
        assertEquals(8, result);
    }
}