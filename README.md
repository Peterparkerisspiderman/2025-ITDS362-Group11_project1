ITDS362 Project 1:(Unit Testing) 
Group 11 สิรวิชญ์ เอี่ยมสอาด
---

## ส่วนที่ 1: การทดสอบแบบ Interface-based

### ฟังก์ชันที่ใช้ทดสอบ
```python
def get_index_of(string, letter):
    # คืนค่าตำแหน่งของอักษร (letter) ตัวแรกที่พบในสตริง (string)
    # หากไม่พบให้คืนค่า -1
```

---

### ขั้นตอนที่ 1: วิเคราะห์โดเมนข้อมูลเข้า (Model Input Domain)

| รายการ | รายละเอียด |
|--------|-------------|
| **ฟังก์ชันที่ทดสอบได้** | `get_index_of()` |
| **พารามิเตอร์** | `string`, `letter` |
| **ชนิดข้อมูลผลลัพธ์ (Return Type)** | `int` |
| **ค่าผลลัพธ์ที่คาดหวัง** | คืนค่าดัชนีของอักษรที่พบครั้งแรก, ถ้าไม่พบให้คืน `-1` |
| **พฤติกรรมพิเศษ (Exceptional Behavior)** | กรณีสตริงว่าง หรือข้อมูลไม่ถูกชนิด |

---

### ขั้นตอนที่ 2: สร้างคุณลักษณะของข้อมูล (Develop Characteristics)

| ลักษณะ | b1 (จริง) | b2 (เท็จ) |
|---------|-------------|-------------|
| C1 = string เป็นค่าว่าง | `""` | `"testing"` |
| C2 = letter เป็นค่าว่าง | `""` | `"t"` |

---

### ขั้นตอนที่ 3: รวมคุณลักษณะเพื่อสร้างชุดทดสอบ (Combine Partitions)

| Test ID | (C1, C2) | string | letter | ผลลัพธ์ที่คาดหวัง |
|----------|-----------|---------|---------|----------------|
| T1 | (True, True) | `""` | `""` | -1 |
| T2 | (True, False) | `""` | `"t"` | -1 |
| T3 | (False, True) | `"testing"` | `""` | -1 |
| T4 | (False, False) | `"testing"` | `"t"` | 0 |

**จำนวนชุดทดสอบทั้งหมด:** 4  
✅ ครอบคลุมทุกกรณีของการแบ่งโดเมนข้อมูล (Complete & Disjoint)

---

## ส่วนที่ 2: การทดสอบแบบ Functionality-based

### ฟังก์ชันที่ใช้ทดสอบ
```python
def get_index_of(string, letter):
    # คืนค่าตำแหน่งของอักษรตัวแรกที่พบใน string,
    # ถ้าไม่พบให้คืนค่า -1
```

---

### ขั้นตอนที่ 1: ระบุโดเมนข้อมูลเข้า
| รายการ | รายละเอียด |
|--------|-------------|
| **พารามิเตอร์** | `string`, `letter` |
| **ผลลัพธ์** | ดัชนีของอักษรตัวแรก หรือ `-1` หากไม่พบ |
| **พฤติกรรมพิเศษ** | ไม่มี เมื่อข้อมูลถูกชนิด |

---

### ขั้นตอนที่ 2: สร้างคุณลักษณะ (Characteristics)

| ลักษณะ | คำอธิบาย |
|---------|-----------|
| C1 | จำนวนครั้งที่ `letter` ปรากฏใน `string` |
| C2 | `letter` ปรากฏตัวแรกใน `string` หรือไม่ |

| Characteristic | b1 | b2 | b3 |
|----------------|----|----|----|
| C1 = จำนวนครั้งที่พบ | 0 | 1 | >1 |
| C2 = letter อยู่ตัวแรก | True | False | — |

**ค่าทดสอบที่เป็นไปได้ (Possible Values):**
| ตัวอย่าง | รายละเอียด |
|-----------|-------------|
| `"software engineering", ""` | ไม่มีอักษร |
| `"software engineering", "s"` | พบ 1 ครั้ง |
| `"software engineering", "t"` | พบ 1 ครั้งแต่ไม่ใช่ตัวแรก |
| `"software testing", "s"` | พบหลายครั้ง (>1) และเป็นตัวแรก |
| `"software engineering", "n"` | พบหลายครั้ง (>1) และไม่ใช่ตัวแรก |

---

### ขั้นตอนที่ 3: รวมโดเมนข้อมูลและสร้างชุดทดสอบ (Combine + Derive)

| Test ID | (C1, C2) | string | letter | ผลลัพธ์ที่คาดหวัง |
|----------|-----------|---------|---------|----------------|
| T1 | (0, False) | `"software engineering"` | `""` | -1 |
| T2 | (1, True) | `"software engineering"` | `"s"` | 0 |
| T3 | (1, False) | `"software engineering"` | `"t"` | 3 |
| T4 | (>1, True) | `"software testing"` | `"s"` | 0 |
| T5 | (>1, False) | `"software engineering"` | `"n"` | 10 |

**จำนวนชุดทดสอบทั้งหมด:** 5  
✅ ครอบคลุมทุกพฤติกรรมของฟังก์ชัน

---

## ส่วนที่ 3: การทดสอบในโปรเจกต์โอเพนซอร์ส (JSON.simple)

### รายละเอียดของโปรเจกต์
- **ชื่อโปรเจกต์:** [JSON.simple (Google Code Archive)](https://github.com/fangyidong/json-simple)  
- **เวอร์ชันที่ใช้ทดสอบ:** 1.1.1  
- **เครื่องมือ Build:** Apache Maven 3.9.11  
- **Framework ที่ใช้ทดสอบ:** JUnit 5  

---

### ชุดทดสอบที่พัฒนาเพิ่มเติม
| ชื่อไฟล์ | รายละเอียด |
|-----------|-------------|
| **JSONObjectPutToJSONStringTest.java** | ทดสอบการทำงานของเมธอด `put()` และ `toJSONString()` เพื่อยืนยันว่าโครงสร้าง JSON ถูกต้องและเรียงลำดับ key-value ตามที่คาดไว้ |
| **JSONValueParseTest.java** | ทดสอบเมธอด `JSONValue.parse()` ในกรณี JSON ที่ถูกต้อง, ไม่ถูกต้อง และข้อมูล `null` |

---

### ผลการรันทดสอบ
ใช้คำสั่ง:
```bash
mvn test
```

ผลลัพธ์:
```
[INFO] Tests run: 32, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

✅ **การทดสอบทั้งหมดผ่านสมบูรณ์**

---

## ส่วนที่ 4: วิธีการรันทดสอบ

### รันผ่าน Command Line (Maven)
```bash
cd json-simple
mvn test
```

### รันผ่าน IntelliJ IDEA
1. เปิดโปรเจกต์ `json-simple-full-for-submission`
2. คลิกขวาที่โฟลเดอร์ `test` → **Run 'All Tests'**
3. ตรวจสอบผลว่าเป็นสีเขียวทั้งหมด ✅

---

## อ้างอิง
- [Apache Maven Official Site](https://maven.apache.org/)  
- [JSON.simple GitHub Repository](https://github.com/fangyidong/json-simple)  
- เอกสารประกอบการสอนรายวิชา **ITDS362 – Software Testing and Quality Assurance**  
  มหาวิทยาลัยมหิดล  

---


