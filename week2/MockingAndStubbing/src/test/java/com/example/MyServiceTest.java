package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MyServiceTest {

    @Test
    public void testExternalApi() {

        // Create Mock Object
        ExternalApi mockApi = mock(ExternalApi.class);

        // Stub Method
        when(mockApi.getData()).thenReturn("Mock Data");

        // Test Service
        MyService service = new MyService(mockApi);

        String result = service.fetchData();

        // Verify Result
        assertEquals("Mock Data", result);
    }
}