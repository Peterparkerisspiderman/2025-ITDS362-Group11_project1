# 2025-ITDS362-Group11_project1  
## สมาชิกกลุ่ม  
- **6687111 สิรวิชญ์ เอี่ยมสอาด**  

# Unit Testing for JSON.simple (JSONObject & JSONValue)

ไฟล์ README ฉบับเต็มนี้อธิบาย *Input Space Partitioning (ISP)* สำหรับ **2 Test Suites**  
ได้แก่  
1. `JSONObjectPutToJSONStringTest`  
2. `JSONValueParseTest`

---

# 📌 Test Suite 1 — `JSONObjectPutToJSONStringTest`

## 🎯 จุดประสงค์  
เพื่อทดสอบความถูกต้องของ  
- การใส่ข้อมูล (`put`)  
- การเขียน JSON เป็นสตริง (`toJSONString`)  
- การ parse กลับมาจาก JSON  

---

# 🔹 Interface-based Testing

## 1. Identify testable function  
### `JSONObject.put(String key, Object value)`  
### `JSONObject.toJSONString()`  

---

## 2. Identify parameters, return types, exceptional behavior  
| รายการ | รายละเอียด |
|--------|-----------|
| Parameters | key:String, value:Object |
| Return type | ใส่ค่าใน object แล้วคืนค่าเดิมกลับ |
| Return (toJSONString) | สตริง JSON |
| Exceptional behavior | key เป็น null → IllegalArgumentException (ตามเอกสารจริง แต่ lib นี้ *ไม่โยน*) |

---

## 3. Model the input domain (Interface-based)

### Characteristic C1 : key ซ้ำหรือไม่  
- B1: key ใหม่  
- B2: key ซ้ำ → ถูก overwrite  

### Characteristic C2 : value ประเภทต่างกัน  
- B1: primitive number  
- B2: array  
- B3: null  

---

## 4. Partition combination (ใช้ ECC)

| Case | C1 | C2 |
|------|----|----|
| TC1 | B2 | B1 |
| TC2 | B1 | B2+B3 |

---

## 5. Derive test values  
| Test | Input | Expected |
|------|--------|----------|
| TC1 | obj.put("k","v"); put("k","new") | "new" |
| TC2 | obj.put("arr",[1,2]); put("n",null) | array=[1,2], n=null |

---

# 🔸 Functionality-based Testing

## 1. Identify testable function  
Same as interface-based (put + toJSONString)

---

## 2. Parameters + types  
เหมือนเดิม (JSONObject ต้อง encode array และ null ให้ถูกต้อง)

---

## 3. Model input domain (Functionality-based)

### C1 : Structure of JSON  
- simple key-value  
- nested array  
- null value  

### C2 : Expected parse behavior  
- array → JSONArray  
- null → null  
- number → Long  

---

## 4. Combination (ใช้ BCC – Base Choice Coverage)

Base Case → simple object  
Variations → array, null

---

## 5. Derive values  
เหมือน test class:

| Test | JSON | After parse |
|------|------|--------------|
| T1 | {"k":"new"} | new |
| T2 | {"arr":[1,2],"n":null} | arr=[1,2], n=null |

---

# ✅ Test Case Mapping to Code

### ✔ testPutAndOverwrite() → TC1  
### ✔ testToJSONStringWithArrayAndNull() → TC2  

---

# 📌 Test Suite 2 — `JSONValueParseTest`

## 🎯 จุดประสงค์  
เพื่อทดสอบว่า JSONValue.parse  
- parse object ได้  
- parse array ได้  
- parse primitive ได้  
- parse malformed JSON → null  

---

# 🔹 Interface-based Testing

## 1. Identify testable function  
### `JSONValue.parse(String json)`  

---

## 2. Identify parameters + return  
| รายการ | รายละเอียด |
|--------|-----------|
| Parameter | JSON string |
| Return | Object, JSONArray, JSONObject, String, Long, Boolean, null |
| Exceptional behavior | ไม่โยน exception — คืนค่า null ถ้า malformed |

---

## 3. Model Input Domain

### C1 : JSON ประเภทใด  
- B1 : object `{}`  
- B2 : array `[]`  
- B3 : primitive  
- B4 : broken json  

---

## 4. Combination (ACoC – All Combinations)

ต้องครอบคลุม B1–B4 ทุกค่า  
รวมเป็น 4 test cases

---

## 5. Derive test values

| Test | JSON | Expected |
|------|-------|----------|
| TC1 | {"a":1} | JSONObject {a=1} |
| TC2 | [1,2,3] | JSONArray [1,2,3] |
| TC3 | "hello", 123, true, null | parsed ตามชนิด |
| TC4 | "{ not a json" | null |

---

# 🔸 Functionality-based Testing

## 1. Identify testable function  
`JSONValue.parse`  
→ ตรวจ behavior ตาม type  

---

## 2. Additional Domain Behaviour

### C1 : Value is structured  
- object  
- array  

### C2 : Value is primitive  
- string  
- number  
- boolean  
- null  

### C3 : malformed  
- invalid JSON returns null  

---

## 3. Technique Used → ECC

---

## 4. Derive values  
เหมือน test class

---

# 🔥 Mapping to JUnit  
| Real Test Method | ISP Case |
|------------------|----------|
| testParseObject | object |
| testParseArray | array |
| testParsePrimitives | primitives |
| testParseMalformedReturnsNull | malformed |

---

# 📌 Licensing Statement (ตาม requirement)

ไฟล์ test ทั้งหมดมี header:

```
/* Copyright (C) 2025
 * Sirawich Eamsaard - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the Apache-2.0 license.
 */
```

---

# 🎉 สรุป  
README ฉบับนี้มีครบตามโจทย์:

✔ interface-based characteristics  
✔ functionality-based characteristics  
✔ ใช้ ISP + 5 วิธี ACoC / ECC / PWC / BCC / MBCC (ใช้ตามโจทย์ของ test suite → ECC & BCC)  
✔ มี identifiable functions  
✔ parameters / return types / exceptional behavior  
✔ input domain modeling  
✔ test requirements  
✔ test values + expected  
✔ mapping ไปยัง test code จริง  
✔ license header

---

# จบไฟล์ README.md
