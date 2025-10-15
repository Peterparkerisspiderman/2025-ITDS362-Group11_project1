/* Copyright (C) 2025 Sirawich Eamsaard - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the Apache-2.0 license.
 */

package org.json.simple;

import org.junit.Test;
import static org.junit.Assert.*;

public class JSONValueParseTest {

    @Test
    public void testParseObject() {
        Object o = JSONValue.parse("{\"a\":1}");
        assertNotNull("parse should return non-null for valid object", o);
        assertTrue("result should be JSONObject", o instanceof JSONObject);
        JSONObject jo = (JSONObject) o;
        assertEquals("value for key 'a' should be 1 (Long)", 1L, jo.get("a"));
    }

    @Test
    public void testParseArray() {
        Object o = JSONValue.parse("[1,2,3]");
        assertNotNull("parse should return non-null for valid array", o);
        assertTrue("result should be JSONArray", o instanceof JSONArray);
        JSONArray ja = (JSONArray) o;
        assertEquals(3, ja.size());
        assertEquals(1L, ja.get(0));
        assertEquals(2L, ja.get(1));
        assertEquals(3L, ja.get(2));
    }

    @Test
    public void testParsePrimitives() {
        assertEquals("hello", JSONValue.parse("\"hello\""));
        assertEquals(123L, JSONValue.parse("123"));
        assertEquals(Boolean.TRUE, JSONValue.parse("true"));
        assertNull(JSONValue.parse("null"));
    }

    @Test
    public void testParseMalformedReturnsNull() {
        Object o = JSONValue.parse("{ not a json ");
        assertNull("malformed JSON should produce null for JSONValue.parse", o);
    }
}
