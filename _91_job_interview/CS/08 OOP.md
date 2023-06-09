# 객체 지향 프로그래밍 

<details>
    <summary><b>✅ 객체 지향 프로그래밍이란? Vs. 절차지향 프로그래밍?</b></summary>

### 객체 지향 프로그래밍

- 프로그램을 단순히 데이터와 처리 방법으로 나누는 것이 아니라, 수많은 객체 단위로 나누고, 객체들의 상호작용으로 프로그래밍하는 방식
- `데이터`와 `프로시저`를 객체 단위로 묶음
- 장점
  - 코드 유지보수, 재사용이 쉽다
- 단점
  - 설계 시 많은 노력 요구
    - 현실 세계와 컴퓨터 세계가 닮지 않은 부분이 있기 때문에, 객체와 그들의 상호작용을 정의하기가 어려움
    - 모든 것들을 객체로 객체지향적인 사고가 필요
  - 필요한 메모리 양이 많다.
    - 모든 것을 객체로 생성하기 때문

### 절차 지향 프로그래밍

- 각 절차마다, 데이터를 공유하는 방식의 프로그래밍
- 순차적인 처리 구조를 지향
- 장점
  - 속도가 빠르다.
- 단점
  - 순서가 바뀌면 

</details>

<details>
    <summary><b>✅ 객체 Vs 클래스 Vs. 인스턴스?</b></summary>

- 객체 
  - 속성과 행동을 가진 식별되는 존재
  - OOP에서 프로그램의 가장 작은 논리적 단위 

- 클래스 
  - 객체의 공통되는 속성과 행동을 추출하여 일반화한 것이 클래스

- 인스턴스 
  - new 키워드를 이용하여 힙 메모리에 객체를 직접 할당하여 사용할 때 인스턴스라고 함.  


</details>


<details>
    <summary><b>✅ 객체 지향이 중요한 이유? 관련 경험?</b></summary>

- 객체 지향이 중요한 이유는 **결국 사람이 읽기 편하게 하기 위함** 
  - 예를 들어서 단순히 필드값을 가져와서 부등호를 이용하여 비교하거나 하는 것은 컴퓨터가 읽기 편합니다.
  - 그런데 필드값을 가지고 비교하는 작업을 메서드로 만들어 의미있는 이름을 붙임으로써 이 작업이 어떤 작업인지 다른 사람도 이해하게 할 수 있음.

<br>

- **다형성을 이용하여 에러를 해결한 경험**
  - Open API 사용 중 한도 초과로 제대로 응답이 오질 않았고, 이 경우에 `500`으로 응답을 내려주고 있었음. 👉 핫픽스 상황 발생 
  - 인터페이스를 이용하여 추상화 해둔 덕분에 로컬 DB에 저장되어있던 데이터를 응답하는 구현체로 바꿔낌으로써 코드를 한 줄만 변경함으로써 핫픽스 대응

</details>

<br>

---

# 캡상추다

<details>
    <summary><b>✅ 객체지향 4대 특성?</b></summary>

- 캡슐화
  - `데이터`와 `프로시저`를 메서드로 묶는 것
  - 데이터에 대한 접근, 수정은 데이터를 갖는 객체만 가능 
  
- 상속
  - 슈퍼 클래스의 기능을 확장하는 것
  
- 추상화
  - 공통되는 속성과 행동을 추출해서 일반화하는 과정

- 다형성
  - 다형성이란 여러(poly) 모습(morph)을 갖는 것을 의미한다.
  - 객체 지향에서는 한 객체가 여러 타입을 갖는 것을 말한다.
    - 한 객체가 여러 타입의 기능을 제공할 수 있다.
    - 타입 상속으로 다형성을 구현한다.
      - 하위 타입은 상위 타입도 된다.

</details>


<details>
    <summary><b>✅ 인터페이스와 추상클래스를 비교해주세요.</b></summary> 


- 공통점
  - 인스턴스를 생성할 수 없음
  - 상속한 클래스가 메서드를 구현

- 차이점
  - 추상 클래스
    - 필드와 구현부가 있는 메서드를 가질 수 있음. 
    - 상속을 위한 부모 클래스로 활용하기 위한 클래스로써, 클래스 간의 연관관계를 구축하는 것에 초점.
  - 인터페이스 
    - 필드와 구현부가 있는 메서드를 가질 수 없음
    - 구현한 객체가 같은 동작을 한다는 것을 보장하기 위해 사용된다는 점에서 사용 목적에 대한 차이

> 👉 추상 클래스와 인터페이스는 인스턴스를 생성할 수 없고, 상속한 클래스가 메서드를 구현해야한다는 공통점이 있습니다. 둘의 차이점으로는 추상 클래스는 필드와 일반 메서드를 가질 수 있으나 인터페이스는 가질 수 없다는 구조적인 차이가 있습니다. 또, 추상 클래스는 상속을 위한 부모 클래스로 활용하기 위한 클래스로써, 클래스 간의 연관관계를 구축하는 것에 초점을 둡니다. 그러나 인터페이스는 구현한 객체가 같은 동작을 한다는 것을 보장하기 위해 사용된다는 점에서 사용 목적에 대한 차이가 있습니다.

---

### 추상 클래스

- 개념
  - abstract 키워드로 선언된 클래스
- 문법
  - `abstract` 으로 선언된 메서드가 하나라도 있으면 그 클래스는 클래스는 반드시 `abstract class` 으로 선언되어야함
  - 추상 메서드가 0개여도 된다.
- 목적
  - 인터페이스와 달리 _**클래스 간의 연관관계를 구축**_ 하는 것에 초점을 둔다.
  - 상속을 위한 부모 클래스로 활용하기 위한 클래스

### 인터페이스

- 개념
  - 추상 메서드와 상수만을 포함하고, interface 키워드를 사용하여 선언
- 문법
  - 내부의 모든 메서드는 `public abstract` (추상 메서드)
    - 추상 메서드 말고 `static`, `default`, `private` 을 붙여 구체적인 메서드를 가질 수 있음
  - 내부의 모든 필드는 `public static final` 상수
  - 클래스에 다중 구현 지원 / **_인터페이스 끼리는 다중 상속 지원_**
- 목적
  - 클래스와 별도로 **_구현 객체가 같은 동작을 한다는 것을 보장하기 위해 사용하는 것에 초점을 둔다._**

### 👉 추상 클래스와 인터페이스 비교

- 공통점
  - 추상 클래스와 인터페이스는 인스턴스 생성 불가
  - 상속한 클래스가 메서드를 구현하도록 책임 위임
- 차이점
  - 구조적 차이
    - 추상 클래스는 추상 메서드 뿐만 아니라 필드, 메서드 선언 가능하지만 인터페이스는 상수와 추상 메서드만 선언 가능 (Java8 부터는 )
  - 목적의 차이
    - 추상 클래스는 관련성이 높은 클래스 간의 코드 재사용과 확장이 목적(연관관계 구축)
    - 인터페이스는 관련성이 없는 클래스들의 기능이 같은 동작을 한다는 것을 보장하는 것이 목적

**추상 클래스와 인터페이스 예시**

<img width="749" alt="image" src="https://user-images.githubusercontent.com/65555299/229523228-33992943-65a7-46e0-bb48-7d8f7d8204d9.png">

(사진 출처: [인파님 블로그](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4-vs-%EC%B6%94%EC%83%81%ED%81%B4%EB%9E%98%EC%8A%A4-%EC%B0%A8%EC%9D%B4%EC%A0%90-%EC%99%84%EB%B2%BD-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0))



※ Ref

- [인터페이스 vs 추상 클래스 차이점 완벽 이해하기](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4-vs-%EC%B6%94%EC%83%81%ED%81%B4%EB%9E%98%EC%8A%A4-%EC%B0%A8%EC%9D%B4%EC%A0%90-%EC%99%84%EB%B2%BD-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0)
- http://alecture.blogspot.com/2011/05/abstract-class-interface.html

</details>

<details>
    <summary><b>✅ 클래스는 왜 다중 상속을 허용하지 않을까요?</b></summary>

- 자식 클래스가 **어느 부모 클래스의 메서드를 사용해야할지 결정할 수 없는 메서드 충돌 문제**가 발생
- 부모 클래스들이 자신의 부모 클래스의 메서드를 각각 오버라이딩하여 구현한다고 했을 때, 자식 클래스 입장에서는 어느 부모 클래스의 메서드를 사용할지 결정할 수가 없는 상태
- 따라서 자바에서는 원천적으로 하나의 클래스만 상속하도록 강제

> 👉
> 자식 클래스가 어느 부모 클래스의 메서드를 사용해야할지 결정할 수 없는 메서드 충돌 문제가 발생하기 때문입니다. 부모 클래스들이 자신의 부모 클래스의 메서드를 각각 오버라이딩하여 구현한다고 했을 때, 자식 클래스 입장에서는 어느 부모 클래스의 메서드를 사용할지 결정할 수가 없는 상태가 됩니다. 따라서 자바에서는 원천적으로 하나의 클래스만 상속하도록 강제하고 있습니다.

---

<img width="616" alt="image" src="https://user-images.githubusercontent.com/65555299/229527757-3359f00e-c10c-4e7c-a1ec-b113e28e5dc8.png">

**Q. 인터페이스는 다중 상속이 가능한 이유?**
- 인터페이스는 실제로 구현은 하지 않고 메서드에 대한 정의만 하며, 메서드에 대한 실제 구현은 인터페이스를 구현한 객체가 하기 때문.

※ Ref

- [JAVA-다중상속을-허용하지-않는-이유는-뭘까](https://selfish-developer.com/entry/JAVA-%EB%8B%A4%EC%A4%91%EC%83%81%EC%86%8D%EC%9D%84-%ED%97%88%EC%9A%A9%ED%95%98%EC%A7%80-%EC%95%8A%EB%8A%94-%EC%9D%B4%EC%9C%A0%EB%8A%94-%EB%AD%98%EA%B9%8C)


</details>


<br>

---

# SOLID

<details>
    <summary><b>✅ SOLID 원칙?</b></summary>

- SRP(Single Responsibility Principle)
  - 객체는 단 하나의 책임만 가져야 한다.
  - 어떤 클래스를 변경해야 하는 이유는 오직 하나 뿐이어야한다. 
  
- OCP(Open Closed Principle)
  - 확장에는 열려 있어야 하고, 변경에는 닫혀 있어야 한다.
  - **기능을 변경하거나 확장할 수 있으면서 그 기능을 사용하는 코드는 수정하지 않는다.**
  - 예를 들어 같은 인터페이스를 구현하는 클래스를 직접 주입하지 않고, DI에 의해 수행 
    - Open API를 사용할지, DB를 사용할지 라는 의미에서의 기능은 변경
  - 설정 파일로 관리하여, 프로덕션 코드를 변경하지 않고 원하는 객체를 주입 가능

- LSP(리스코프 치환 원칙)
  - "상위 타입의 객체를 하위 타입의 객체로 치환해도 상위 타입을 사용하는 프로그램은 정상적으로 동작해야 한다."

- ISP(Interface Segregation Principle, 인터페이스 분리 원칙)
  - 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
  - 인터페이스 하나가 다양한 책임을 가질 경우, 
    - 인터페이스를 변경 시 구현 클래스에 영향이 크다. 
  - **커다란 인터페이스를 여러 개로 분리하여 인터페이스 변경사항이 있어도 영향 최소화** 

- DIP(Dependency Inversion Principle)
  - 프로그래머는 ***"추상화에 의존해야지, 구체화에 의존하면 안된다."*** 라는 원칙
    - `의존성 주입`은 DIP를 따르는 방법 중 하나다.
  - 지키지 않을 경우 변경이 어려워짐

--- 

# SRP(Single Responsibility Principle): 단일 책임 원칙

> _"어떤 클래스를 변경해야 하는 이유는 오직 하나뿐이어야 한다." - 로버트 C. 마틴_

- **_"한 클래스는 하나의 책임만 가져야 한다."_** 를 의미하는 규칙
  - 여기서 책임은 하나의 기능이라고 보면 된다.
- 하나의 책임이라는 것은 모호하다.
  - 책임은 클 수도 있고, 작을 수도 있다.
  - 책임은 문맥과 상황에 따라 다르다.
- 책임의 기준은 `변경`이다. 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것이다.
- 예) UI 변경, 객체의 생성과 사용을 분리

## 단일 책임 원칙을 지키지 않았을 때의 문제점

```java
public class DataViewer {
    public void display() {
        String data = loadHtml();
        updateGui(data);
    }
    
    public String loadHtml() {
        HttpClient client = new HttpClient();
        client.connect(url);
        return client.getResponse();
    }
    
    private void updateGui(String data) {
        GuiData guiModel = parseDataToGuiData(data);
        tableUI.changeData(guiModel);
    }
    
    private GuiData parseDataToGuiData(String data) {
       ...// 파싱 처리 코드
    }
    ...// 기타 필드 등 다른 코드
}
```

- 위 코드는 HTTP 프로토콜을 이용해 데이터를 읽어와 화면에 보여주는 기능을 한다.
- HTTP 클라이언트만 사용한다면 상관이 없지만 소켓 프로그래밍 등으로 읽어오는 데이터가 `String -> byte[]` 로 변경되면 코드가 연쇄적으로 변경되어야한다.

```java
public class DataViewer {
    public void display() {
        byte[] data = loadHtml();
        updateGui(data);
    }
    
    public byte[] loadHtml() { // 리턴타입 변경
        SocketClient client = new SocketClient();
        client.connect(server, port);
        return client.read;
    }
    
    private void updateGui(byte[] data) { // 파라미터 타입 변경
        GuiData guiModel = parseDataToGuiData(data);
        tableUI.changeData(guiModel);
    }
    
    private GuiData parseDataToGuiData(byte[] data) { // 파라미터 타입 변경
       ...// 파싱 처리 코드
    }
    ...// 기타 필드 등 다른 코드
}
```

- 데이터를 제공하는 서버만 달라졌는데, 연쇄적으로 코드가 수정되었다.
- 책임의 개수가 많아질수록 한 책임의 기능 변화가 다른 책임에 주는 영향이 비례해서 증가하기 때문
- 코드를 절차 지향적으로 변하게 하여 유지 보수를 엉망으로 만든다.

👉 GUI를 보여주는 책임을 담당하는 객체와 데이터를 읽는 책임을 담당하는 객체, 그리고 데이터 자체를 추상화한 객체 3가지를 이용하여 책임을 분리해야한다. (**한 클래스가 하나의 책임을 갖도록 분리한다.**)

- SRP를 지키지 않으면 **_클래스를 재사용하기 어려워진다._**
- `DataViewer` 클래스는 데이터를 읽어오기 위한 클래스이고, HTTP 연동을 위해 HttpClient 패키지를 사용하고, 화면에 데이터를 보여주기 위해 GuiComp라는 패키지를 사용한다고 가정해보자

## 책임은 무엇일까

### 정리

SRP를 지키지 않으면

1.
2. 클래스 재사용을 어렵게 한다.

이를 해결하기 위해서,

<br>
<br>

**※ Reference**

- https://steady-coding.tistory.com/370

# OCP

"확장에는 열려 있어야 하고, 변경에는 닫혀 있어야 한다."

"기능을 변경하거나 확장할 수 있으면서 그 기능을 사용하는 코드는 수정하지 않는다."

- MemberService 클라이언트가 구현 객체를 직접 선택
  - `MemberRepository m = new MemoryMemberRepository();`
  - `MemberRepository m = new JDBCMemberRepository();`
- **_분명 다형성을 사용했지만, 구현 객체를 변경하려면 클라이언트 코드를 변경해야한다._** 👉 객체를 생성하고, 연관관계를 맺어주는 별도의 조립, 설정자를 통해서 해결한다.(스프링 컨테이너가 DI를 통해 해준다.)

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fdqs36A%2FbtqZpfr81Sw%2FGK2ht1F6Ch87rK0FIAmIoK%2Fimg.png)

JDBC 를 사용할 때를 예로 들자. 클라이언트가 데이터베이스를 오라클에서 MySQL로 변경하더라도 Connection을 설정하는 부분 이외에는 수정할 필요가 없다. Connection 부분을 설정 파일로 분리해두면 클라이언트 코드는 단 한 줄도 변경할 필요가 없다.

## 개방 폐쇄 원칙을 지키는 방법

- `다형성`을 활용한다.
- 인터페이스를 이용하여 `역할`과 `구현`을 분리한다.

## OCP를 지키지 않았을 때의 문제점

추상화와 다형성이 제대로 지켜지지 않은 경우 OCP를 어기게 된다.

### 1. 다운 캐스팅을 한다.

```java

// Missile, Player, Enemy extends Character

public void drawCharacter(Character character) {
  if(character instanceof Missile) {  // 타입 확인
    Missile missile = (Missile) character; // 타입 다운 캐스팅
    missile.drawSpecific();

  } else {
    character.draw();

  }

}
```

# LSP 리스코프 치환 원칙

- "상위 타입의 객체를 하위 타입의 객체로 치환해도 상위 타입을 사용하는 프로그램은 정상적으로 동작해야 한다."
  - 특정 메소드가 상위 타입을 파라미터로 사용한다고 할 때, 그 타입의 하위 타입을 인자로 사용했을 때도 문제 없이 정상적으로 동작해야한다.
- 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다는 것, 다형성을 지원하기 위한 원칙, 인터페이스를 구현한 구현체는 믿고 사용하려면, 이 원칙이 필요하다.
- 단순히 컴파일에 성공하는 것을 넘어서는 이야기

## 리스코프 치환 원칙이 지켜지지 않았을 때의 문제

### 직사각형-정사각형 문제

```java
public class Rectangle {
	
    private int width;
    private int height;

    public void setWidth(final int width) {
        this.width = width;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
```

사각형을 상속하는 정사각형 클래스를 다음과 같이 정의할 수 있다.

```java
public class Square extends Rectangle {

    @Override
    public void setWidth(final int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(final int height) {
        super.setWidth(height);
        super.setHeight(height);
    }
}
```


# ISP

- 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다
- 자동차 인터페이스 -> 운전 인터페이스, 정비 인터페이스로 분리
- 사용자 클라이언트 -> 운전자 클라이언트, 정비사 클라이언트로 분리
- 분리하면 정비 인터페이스 자체가 변해도 운전자 클라이언트에 영향을 주지 않는다.
- 인터페이스가 명확해지고, 대체 가능성이 높아진다.

# DIP

- 프로그래머는 ***"추상화에 의존해야지, 구체화에 의존하면 안된다."*** 라는 원칙
  - `의존성 주입`은 DIP를 따르는 방법 중 하나다.
- 쉽게 말하면, `구현 클래스가 아니라 인터페이스에 의존해야한다`라는 것.
- 앞에서 이야기한 역할(Role)에 의존하게 해야 한다는 것과 같다.
  - 객체 세상도 클라이언트가 인터페이스에 의존해야 유연하게 구현체를 변경할 수 있다.
  - 구현체에 의존하게 되면 변경이 아주 어려워진다.

</details>

<details>
    <summary><b>✅ SOLID 원칙 중 적용 시켜본 것 있는지</b></summary>

### SRP 
  - MemberService 객체를 C, R, U, D 를 할 때마다 매 번 변경됨.
  - 문제점
    - 코드의 길이가 길어지고, 
    - 어느 메서드를 사용해야하는지 찾기도 힘들고, 
    - 같은 기능을 또 정의하는 문제점들이 생김
  - CreateService, UpdateService 등으로 `책임`을 분리
    - 어떤 작업을 할 때 어느 객체를 써야하는지 명확함
    - 중복되는 기능을 만들지 않음 
    
- DIP 
  - 다형성을 이용
  - 구현체가 아닌 추상체에 의존하도록 함

</details>

<br>

---