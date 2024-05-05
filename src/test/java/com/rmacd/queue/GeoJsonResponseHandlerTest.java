package com.rmacd.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoJsonResponseHandlerTest {

    @Test
    public void genTest01() {
        assertEquals("HHA", GeoJsonResponseHandler.getDerivedType("123/HHA/12"));
        assertEquals("HHA", GeoJsonResponseHandler.getDerivedType("/HHA/12"));
        assertNull(GeoJsonResponseHandler.getDerivedType("123/HHA2/12"));
        assertNull(GeoJsonResponseHandler.getDerivedType("HHA/12"));
        assertNull(GeoJsonResponseHandler.getDerivedType(null));
        assertNull(GeoJsonResponseHandler.getDerivedType("//"));
        assertNull(GeoJsonResponseHandler.getDerivedType("ABC123"));
    }

}