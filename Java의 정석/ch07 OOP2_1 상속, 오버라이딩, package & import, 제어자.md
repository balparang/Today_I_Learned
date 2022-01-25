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
---
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

## ch7.3 Package & import

### 3.1 패키지(package)

- 서로 관련된 클래스의 묶음
- 클래스를 컴파일하면 클래스 파일(*.class)가 되는 것처럼, 패키지는 폴더를 의미함. 하위 패키지는 하위 폴더.
- 클래스의 실제 이름(full name)은 패키지를 포함. (java.lang.String)
    - rt.jar는 클래스들을 압축한 파일 (Java9 부터 rt.jar 삭제 → module개념으로 바뀜)

### 3.2 패키지의 선언

- 패키지는 소스파일의 첫 번째 문장으로 단 한 번만 선언
- 같은 소스 파일의 클래스들은 모두 같은 패키지에 속하게 된다.
- 패키지 선언이 없으면 이름없는(unnamed) 패키지에 속하게 된다. (이클립스IDE의 경우 default package)
    
    ### 클래스 패스(classpath)
    
    - 클래스 파일(*.class)의 위치를 알려주는 경로(path)
    

### 3.3 import문

- 클래스를 생성할 때 패키지 이름을 생략할 수 있다.
- import문의 역할 : 컴파일러에게 클래스가 속한 패키지를 알려준다.
- **자바의 기본 패키지, 즉 java.lang패키지의 클래스는 import하지 않고도 사용가능함.**
    - java.lang패키지의 대표적인 클래스 : String, Object, System, Thread, ...

💻 import문 추가 단축키 : `ctrl + alt + O` (IntelliJ : Optimize import)

```java
// import문을 추가하지 않은 코드
class ImportTest {
	java.util.Date today = new java.util.Date();
	// ...
}

// import문을 추가한 코드
import java.util.Date;

class ImportTest {
	Date today = new Date();
	// ...
}
```

### 3.4 import문의 선언

- import문을 선언하는 방법은 다음과 같다

```java
import 패키지명.클래스명;

	또는

import 패키지명.*; // 모든 클래스
```

- import문은 패키지 문과 클래스선언의 사이에 선언한다.
- import문은 컴파일 시에 처리되므로 프로그램의 성능에 영향 없음.

```java
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;

// 위의 3문장처럼 사용하거나 import java.util.*; 로 한 문장을 사용하는 것은 프로그램
// 의 성능에 영향 없음 

import java.util.*;
```

- 다음의 두 코드는 서로 의미가 다르다( ‘*’은 모든 ‘클래스’를 의미하는 것이지, 패키지는 포함하지 않는다.)

```java
import java.util.*; // java.util패키지의 모든 클래스
import java.text.*; // java.text패키지의 모든 클래스

// *은 모든 '클래스'를 의미하는 것이지, 패키지는 포함을 하지 않음 
import java.*; // java패키지의 모든 클래스(패키지는 포함 안 함)
```

- 이름이 같은 클래스가 속한 두 패키지를 import할 때는 클래스 앞에 패키지명을 붙여줘야한다. (어느 패키지의 클래스인지 컴파일러가 알 수가 없으므로)

```java
import java.sql.*; // java.sql.Date 
import java.util.*; // java.util.Date --> 패키지이름 동일!(Date)

public class ImportTest {
	public static void main(String[] args) {
		java.util.Date today = new java.util.Date(); // 클래스 앞에 패키지명을 직접 붙여준다.
	}
}

```

### 3.5 static import문

> ***static import문은 웬만해서는 사용하지마라. (꼭 필요할 때만 사용해라.)
코드가 줄어드는 장점은 있지만, 클래스이름을 사용하는 것이 코드의 의도가 더 명확하다.***
> 
- static멤버를 사용할 때 클래스 이름을 생략할 수 있게 해준다.

```java
import static java.lang.Integer.*; // Integer클래스의 모든 static멤버를 사용할 때 클래스이름 생략 가능
import static java.lang.Math.random; // Math.random()만 클래스이름 생략가능. import시 괄호 안붙임
import static java.lang.System.out; // System.out을 out만으로 참조 가능

// System.out.println(Math.random()); // 원래는 이렇게 작성해야할 문장을 
out.println(random()); // static import문을 사용하여 이렇게 클래스 이름을 생략가능함.  
```

```java
// static import문 예제
import static java.lang.System.out;
import static java.lang.Math.*; 

class Ex7_6 {
	public static void main(String[] args) {
		// System.out.println(Math.random());
		out.println(random());
		
		// System.out.println("Math.PI :" + Math.PI);
		out.println("Math.PI :" + PI); // 클래스 이름 Math 생략
	}
}
```

## ch7.4 제어자(modifier)

### 4.1 제어자란?

> 🚩 제어자(modifier) 
> 
> **접근 제어자** :  public, protected, (default), private
> 
> **그            외** : static, final, abstract, native, transient, synchronize, volatile
> 
- 클래스와 클래스의 멤버(멤버변수, 메서드)에 부가적인 의미 부여
- **접근 제어자**와 **그 외**의 것으로 나뉨
- ‘접근 제어자’는 4가지 중 하나만 선택해서 사용가능
    - (default) 접근 제어자는 아무것도 붙이지 않는 것을 의미
- 하나의 대상에 여러 제어자를 같이 사용 가능(접근 제어자는 하나만, 제일 먼저 작성)

### 4.2 static - 클래스의, 공통적인

- `static` 제어자는 클래스의 멤버(멤버변수, 메서드)에 붙일 수 있음. 그 대상이
    1. 멤버변수에 static을 붙이는 경우 
        - 모든 인스턴스에 공통적으로 사용되는 클래스 변수가 됨
        - 클래스 변수는 **인스턴스를 생성하지 않고도 사용가능**함
        - 클래스가 메모리에 로드될 때 생성됨
    2. 메서드에 static을 붙이는 경우
        - **인스턴스를 생성하지 않고도 호출이 가능**한 static메서드가 됨
        - static메서드 내에서는 인스턴스 멤버들을 직접 사용할 수는 없음 (static메서드를 호출하는 시점에 인스턴스가 생성되어있지 않을 수도 있기 때문)
        

### 4.3 final - 마지막의, 변경될 수 없는

- `final` 제어자가 사용될 수 있는 곳 : 클래스, 메서드, 멤버변수, 지역변수 (거의 모든 대상에 사용가능함)
- 대상에 따른 `final` 제어자의 의미, 대상이
    1. 클래스인 경우
        - 변경될 수 없는 클래스, 확장될 수 없는 클래스가 됨.
        → final로 지정된 클래스는 다른 클래스의 조상이 될 수 없다.
        - 상속 계층도의 말단 (마지막 자손)을 의미함.
    2. 메서드인 경우
        - 변경될 수 없는 메서드, final로 지정된 메서드는 오버라이딩을 통해 재정의 될 수 없음.
    3. 멤버변수 또는 지역변수인 경우
        - 변수 앞에 final이 붙으면, 값을 변경할 수 없는 상수가 됨.

```java
final class FinalTest { // 조상이 될 수 없는 클래스(확장 불가능한 클래스)
	final int MAX_SIZE = 10;

	final void getMaxSize() { // 오버라이딩할 수 없는 메서드(변경 불가) 
		final int LV = MAX_SIZE; // 상수
		return MAX_SiZE;
	}
}
```

- 대표적인 final 클래스 : String, Math

### 4.4 abstract - 추상의, 미완성의

- `abstract` 가 사용될 수 있는 곳 : 클래스, 메서드
- 대상에 따른 `abstract` 제어자의 의미, 대상이
    1. 클래스인 경우 : 클래스 내에 추상 메서드가 선언되어 있음을의미
    2. 메서드인 경우 : 선언부만 작성하고 구현부는 작성하지 않은 추상 메서드임을 알림 

```java
abstract class AbstractTest { // 추상 클래스(추상 메서드를 포함하는 클래스)
	abstract void move();  // 추상 메서드(구현부가 없는 메서드)
}

AbstractTest a = new AbstractTset(); // **에러**. 추상 클래스의 인스턴스 사용 불가
```

- 추상 메서드, 추상 클래스
    - 추상 메서드 : 선언부만 작성하지 않고 구현부를 작성하지 않은 미완성 메서드. 추상 메서드를 포함하는 클래스는 당연히 미완성 메서드가 된다.
    - 추상 클래스 : 추상 메서드를 포함한 클래스, **추상클래스를 상속받아서 완전한 클래스를 만들어야 객체를 생성할 수 있음**

### 4.5 접근 제어자(access modifier), 캡슐화

> 🚩 **접근 제어자**
> 
> **private**       : 같은 클래스 내에서만 접근 가능함.
> 
> **(default)**   : 같은 패키지 내에서만 접근 가능함
> 
> **protected** : 같은 패키지 내에서 & 다른 패키지의 자손 클래스에서 접근이 가능함
> 
> **public**       : 접근 제한이 전혀 없음, **하나의 소스파일에는 하나의 public만 사용가능**
                   ***public 클래스의 이름과 소스파일의 이름이 같아야함!!!***
> 

> ***소스파일을 컴파일 시에는 같은 소스에 있더라도 클래스파일이 개별로 생성**되니, 이를 이용해서 접근 제어자 범위의 개념을 익히는 연습을 할 것*
> 
- 접근제어자가 사용될 수 있는 곳 : 클래스, 멤버변수, 메서드, 생성자
- 접근 제한 범위 : `public` (접근제한없음) > `protected` (같은 패키지 + 다른 패키지의 자손)> `(default)` (같은 패키지) > `private` (같은 클래스)
- 대상에 따라 사용할 수 있는 접근 제어자
    
    
    | 대상 | 사용 가능한 접근제어자 |
    | --- | --- |
    | 클래스 | public, (default) |
    | 메서드 | public, protected, (default), private |
    | 멤버변수 | public, protected, (default), private |
    | 지역변수 | 없음 |

```java
package ch7_OOP2.pkg1;

public class MyParent {
    // class MyParent { // 접근 제어자 (default) 다른 패키지에서 사용 불가
    private int prv; // 같은 클래스 내 사용가능
    int dft; // 같은 패키지 내 사용가능
    protected int prt; // 같은 패키지 내 사용가능 & 다른 패키지의 자손 클래스
    public int pub; // 접근 제한 없음

    public void printMembers() { // 인스턴스 메서드
        System.out.println(prv); // OK
        System.out.println(dft); // OK
        System.out.println(prt); // OK
        System.out.println(pub); // OK
    }
}

class MyParentTest {
    public static void main(String[] args) {
        MyParent p = new MyParent();
        // System.out.println(p.prv); // 에러. 다른 클래스임
        System.out.println(p.dft); // OK. 같은 패키지(pkg)임
        System.out.println(p.prt); // OK. 같은 패키지(pkg)임
        System.out.println(p.pub); // OK. 제한 없음

    }
}
```

```java
package ch7_OOP2.pkg2;

// 클래스 앞에 패키지 이름 쓰기 싫으면 import해야한다.
import ch7_OOP2.pkg1.MyParent;

//class MyChild extends ch7_OOP2.pkg1.MyParent {
class MyChild extends MyParent {
    public void printMembers() { // 인스턴스 메서드
        // System.out.println(prv); // 에러. 다른 클래스
        // System.out.println(dft); // 에러. 다른 패키지
        System.out.println(prt); // OK. 다른 패키지의 자손 클래스임
        System.out.println(pub); // OK
    }
}
public class MyParentTest2 {
    public static void main(String[] args) {
        MyParent p = new MyParent();
        // System.out.println(p.prv); // 에러. 다른 클래스임
        // System.out.println(p.dft); // 에러. 다른 패키지
        // System.out.println(p.prt); // 에러. 다른패키지의 자손 아닌 클래스
        System.out.println(p.pub); // OK. 제한 없음

    }
}
```

### 캡슐화

// 추가 예정 

### 4.6 제어자(modifier)의 조합

// 추가예정