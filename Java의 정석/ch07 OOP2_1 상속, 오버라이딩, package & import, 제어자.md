### 7.7 오버라이딩(Overriding)

- 상속받은 조상의 메서드를 **자신에 맞게 변경**하는 것
- **선언부 변경 불가**, 내용만 변경 가능

```java
// 오버라이딩 예제 
class MyPoint3 {
    int x;
    int y;

    String getLocation() {
        return "x: " + x + ", y:" + y;
    }
}

class MyPoint3D extends MyPoint3 { // MyPoint3 클래스 상속 받음
    int z;

    String getLocation() { // 오버라이딩. 선언부는 변경 불가. 내용물은 변경 가능
        return "x: " + x + ", y:" + y + ", z: " + z;
    }
}
```

```java
// Object클래스 오버라이딩
class MyPoint3 {
    int x;
    int y;

    MyPoint3(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() { // Object클래스의 toString메서드 오버라이딩
        return "x: " + x + ", y: " + y;
    }
}

public class OverrideTest {
    public static void main(String[] args) {
        MyPoint3 p = new MyPoint3(3, 4);

        // 아래의 두 문장은 동일하게 수행됨 
        System.out.println(p); // x: 3, y: 4
        System.out.println(p.toString()); // x: 3, y: 4
    }
}
```

### 7.8 오버라이딩 조건

> **🚩 오버라이딩의 조건 3가지**
> 
> 1. 선언부가 조상 클래스의 메서드와 일치해야한다.
> 2. 접근 제어자를 조상클래스의 메서드보다 좁은 범위로 변경할 수 없다.
> 3. 예외는 조상 클래스의 메서드보다 많이 선언할 수 없다.

```java
class Parent {
	void parentMethod() throws IOException, SQLException { // 예외 2개 선언 
		// ...
	}
}

class Child extend Parent {
	void parentMethod() throws IOException { // 예외 1개 선언 
		// ...
	}
}
```

### 7.9 오버로딩 vs. 오버라이딩

- 오버로딩(overloading) : 기존에 없는 새로운 메서드를 정의하는 것 (new)
- 오버라이딩(overriding) : 상속받은 메서드의 내용을 변경하는 것(change, modify)

```java
class Parent {
	void parentMethod() {}
}

class Chid extends Parent { // 상속
	void parentMethod() {} // 오버라이딩
	void parentMethod(int i) {} // 오버로딩

	void childMethod() {} // 새로운 메서드 정의
	void childMethod(int i) {} // 오버로딩
	void childMethod() {} // 중복정의, **컴파일 에러**
}
```

### 7.10 참조변수 super

- 객체 자신을 가리키는 참조변수. 인스턴스 메서드(생성자) 내에만 존재 **(static 메서드에서 사용 불가능)**
- 조상의 멤버를 자신의 멤버와 구별할 때 사용

## 7.11 super() - 조상클래스의 생성자

### super() - 조상클래스의 생성자

- 조상의 생성자를 호출할 때 사용(`super()` 자체가 조상의 생성자를 호출함)
- **조상의 멤버는 조상의 생성자를 호출해서 초기화**

```java
// 자손의 생성자는 자기가 선언한 것만 초기화 해야함 
class Point {
	int x, y;

	Point(int x, int y) {
		this.x = x; // iv초기화
		this.y = y; // iv초기화
	}
}

class Point3D extends Point {
	int z; 
	
	Point3D(int x, int y, int z) { // 자손의 생성자가 조상의 멤버를 초기화(지양할 것)
		this.x = x; // 조상의 멤버를 초기화 
		this.y = y; // 조상의 멤버를 초기화
		this.z = z; // 자손의 생성자는 자기가 선언한 것만 초기화 해야함 
	}
}

// 아래 코드처럼 바꾼다.
Point3D(int x, int y, int z) {
	super(x, y); // 조상클래스의 생성자 Point(int x, int y)를 호출
	this.z = z; // 자신의 멤버를 초기화
}
```

### 조상의 생성자, 자손의 생성자

- 상속받을 때, 생성자랑 초기화 블럭은 상속이 안된다. → 자손 클래스에서 조상의 멤버도 초기화를 해줄 필요가 있음. 이 때 `super()` 를 사용함.
- 자손의 생성자는 **자기가 선언한 것만 초기화** 해주어야함. (조상의 멤버는 조상의 생성자를 호출 `super()` 해서 초기화)

### 💯💯💯 모든 생성자는 첫 줄에 다른 생성자를 반드시 호출해야 한다!!!

- Objet클래스를 제외한 **모든 클래스의 생성자 첫 줄에 생성자, `this()` 또는 `super()` ,를 호출해야한다!!!.**
- 생성자 첫 줄에 생성자(`this()`, `super()`) 를 호출 하지 않으면 컴파일러가 *super();* 를 생성자의 첫 줄에 삽입한다.

> ***클래스를 작성할 때 기본 생성자를 필수로 작성하라!***
> 

```java
// 생성자 첫 줄에 다른 생성자를 반드시 호출해야하는 이유.java
package ch7_OOP2;

class Point {
    int x;
    int y;

    // 생성자가 있으므로 컴파일러가 기본생성자 (Point() { }) 를 자동으로 추가해주지 않는다.
    Point(int x, int y) {
        // 컴파일시 컴파일러가 'super();' 자동추가. (생성자의 첫줄에 다른 생성자를 호출하지 않았기 때문)
        this.x = x;
        this.y = y;
    }

    String getLocation() {
        return "x: " + x + ", y: " + y;
    }
}

class Point3D extends Point {
    int z;

    Point3D(int x, int y, int z) {
        // 1. 컴파일시 컴파일러가 'super();'를 자동으로 추가.
        // 2. super()는 조상 클래스 Point의 기본 생성자 Point() 호출
        // 3. Point()를 호출 했더니 Point()가 없다 -> 컴파일 에러 발생
        // 에러발생 이유 : Point클래스에서 생성자가 있으므로 컴파일러가 기본생성자 Point() { } 를 자동으로 추가해주지 않았음
        // 에러방지를 위해 'super(x, y);' (// 조상의 생성자인 Point(int x, int y)를 호출)를 작성한다.
        this.x = x; // -> super.x로 변경권장
        this.y = y; // -> super.y로 변경권장
        this.z = z;
    }
    String getLocation() { // 오버라이딩
        return "x: " + x + ", y: " + y + ", z:" + z;
    }
}

```