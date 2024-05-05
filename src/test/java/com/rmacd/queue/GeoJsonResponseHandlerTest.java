package com.rmacd.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GeoJsonResponseHandlerTest {

    @Test
    public void genTest01_index01() {
        assertEquals("HHA", GeoJsonResponseHandler.getDerivedType(1, "123/HHA/12"));
        assertEquals("HHA", GeoJsonResponseHandler.getDerivedType(1, "/HHA/12"));
        assertNull(GeoJsonResponseHandler.getDerivedType(1, "123/HHA2/12"));
        assertNull(GeoJsonResponseHandler.getDerivedType(1, "HHA/12"));
        assertNull(GeoJsonResponseHandler.getDerivedType(1, null));
        assertNull(GeoJsonResponseHandler.getDerivedType(1, "//"));
        assertNull(GeoJsonResponseHandler.getDerivedType(1, "ABC123"));
    }

    @Test
    public void genTest01_index02() {
        assertEquals("HHA", GeoJsonResponseHandler.getDerivedType(2, "123/ABC/HHA"));
        assertEquals("HHA", GeoJsonResponseHandler.getDerivedType(2, "/12/HHA"));
        assertNull(GeoJsonResponseHandler.getDerivedType(2, "123/2024/HHA2"));
        assertNull(GeoJsonResponseHandler.getDerivedType(2, "HHA/HHA"));
        assertNull(GeoJsonResponseHandler.getDerivedType(2, null));
        assertNull(GeoJsonResponseHandler.getDerivedType(2, "//"));
        assertNull(GeoJsonResponseHandler.getDerivedType(2, "ABC123"));
    }

}