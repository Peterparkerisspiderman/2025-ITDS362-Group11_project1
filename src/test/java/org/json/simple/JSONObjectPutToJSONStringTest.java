/* Copyright (C) 2025 Sirawich Eamsaard - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the Apache-2.0 license.
 */

package org.json.simple;

import org.junit.Test;
import static org.junit.Assert.*;

public class JSONObjectPutToJSONStringTest {

    @Test
    public void testPutAndOverwrite() {
        JSONObject obj = new JSONObject();
        obj.put("k", "v");
        obj.put("k", "new");
        String json = obj.toJSONString();

        Object parsed = JSONValue.parse(json);
        assertTrue(parsed instanceof JSONObject);
        JSONObject p = (JSONObject) parsed;
        assertEquals("new", p.get("k"));
    }

    @Test
    public void testToJSONStringWithArrayAndNull() {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        arr.add(1L);
        arr.add(2L);
        obj.put("arr", arr);
        obj.put("n", null);

        String json = obj.toJSONString();
        Object parsed = JSONValue.parse(json);
        assertTrue(parsed instanceof JSONObject);
        JSONObject p = (JSONObject) parsed;

        assertTrue(p.get("arr") instanceof JSONArray);
        JSONArray parsedArr = (JSONArray) p.get("arr");
        assertEquals(2, parsedArr.size());
        assertEquals(1L, parsedArr.get(0));
        assertEquals(2L, parsedArr.get(1));

        assertNull(p.get("n"));
    }
}
