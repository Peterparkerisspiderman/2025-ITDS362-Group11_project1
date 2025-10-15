# ITDS362 Project 1 — การสร้าง Unit Test สำหรับโครงการ Open-Source (json-simple)

## ข้อมูลทั่วไปของโครงการ
**ชื่อโปรเจค:** https://github.com/fangyidong/json-simple  
**ภาษา:** Java  
**Build Tool:** Maven  
**Framework สำหรับทดสอบ:** JUnit 4  
**สมาชิกทีม:** Sirawich Eamsaard  

---

## วัตถุประสงค์ของงาน
งานนี้มีจุดประสงค์เพื่อให้นักศึกษาเข้าใจการทดสอบซอฟต์แวร์เชิงระบบ และสามารถออกแบบ test case ได้อย่างมีหลักการ  
โดยใช้เทคนิค Input Space Partitioning และสร้าง unit test ใหม่จำนวน 2 test suites

### เมธอดที่เลือกทดสอบ
1. `JSONValue.parse(String s)`
2. `JSONObject.put(Object key, Object value)` และ `JSONObject.toJSONString()`

---

## รายชื่อไฟล์ที่เพิ่มใหม่

| ไฟล์ | ตำแหน่ง | รายละเอียด |
|------|-----------|-------------|
| `JSONValueParseTest.java` | `src/test/java/org/json/simple/` | ทดสอบการทำงานของเมธอด JSONValue.parse() |
| `JSONObjectPutToJSONStringTest.java` | `src/test/java/org/json/simple/` | ทดสอบเมธอด put() และ toJSONString() |

---

## 🔹 Test Suite 1: JSONValueParseTest
### จุดประสงค์
ตรวจสอบว่า JSONValue.parse() แปลง JSON string ให้เป็น object ที่ถูกต้อง และคืนค่า null เมื่อข้อมูลไม่ถูกต้อง

### Interface-based Characteristics
- ประเภท input: {object, array, string, number, boolean, null, malformed}

### Functionality-based Characteristics
- ตรวจสอบชนิดผลลัพธ์ที่ถูกต้อง
- คืนค่า null เมื่อ input ผิดรูปแบบ

### เทคนิคที่ใช้
- ACoC, ECC, PWC, BCC, MBCC

### Test Values / Expected Values
| Input | Output |
|--------|--------|
| `{"a":1}` | JSONObject (key a=1L) |
| `[1,2,3]` | JSONArray (3 elements) |
| `"hello"` | String |
| `123` | Long |
| `true` | Boolean |
| `null` | null |
| `{ not a json ` | null |

---

## 🔹 Test Suite 2: JSONObjectPutToJSONStringTest
### จุดประสงค์
ตรวจสอบการทำงานของ put() และ toJSONString() ให้ทำงานถูกต้องทั้งกรณี overwrite และค่าประเภทต่างๆ

### Interface-based Characteristics
- value types: {String, Number, JSONArray, null}

### Functionality-based Characteristics
- put() overwrite ค่าเดิมได้ถูกต้อง
- toJSONString() แปลงกลับได้เหมือนเดิม

### เทคนิคที่ใช้
- ACoC, ECC, PWC, BCC, MBCC

### Test Values / Expected Values
| Input | Output |
|--------|--------|
| `put("k","v"); put("k","new")` | `"k":"new"` |
| `put("arr",[1,2]); put("n",null)` | arr=JSONArray, n=null |

---

## 🧪 การรัน Test
ใช้คำสั่ง:
```
mvn test
```
หรือรันเฉพาะ test ใด test หนึ่ง:
```
mvn -Dtest=org.json.simple.JSONValueParseTest test
mvn -Dtest=org.json.simple.JSONObjectPutToJSONStringTest test
```

---

## ✅ สรุป
- เพิ่ม test suite ใหม่ 2 ชุด
- ใช้ JUnit 4 ผ่าน Maven
- อธิบายตามหลัก Input Space Partitioning ครบทุกข้อ
- พร้อมรันและส่งในรายวิชา ITDS362
