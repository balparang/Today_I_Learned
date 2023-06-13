# Java Version

<details>
    <summary><b>Java 8 Vs. Java 11 Vs. Java 17?</b></summary>

- Java 8
    - 람다
    - 스트림
    - 옵셔널
    - **default GC Parallel GC**
- Java 11
    - **default GC G1 GC**
    - `String::strip()`
- Java 17
    - Java 14
        - Record Class
    - Java 15
        - `String.formatted()`
        - `Multiline String`

</details>

---

<br>

## Java 8

<details>
    <summary><b>자바 8에 추가된 기능들에 대해 간단히 설명해주세요.</b></summary> 

> 👉 Java8 의 변경점으로 대표적인 것들은
> <br> 메서드를 하나의 식으로 표현하는 익명함수인 람다 표현식,
> <br> 추상메서드를 하나만 갖는 함수형 인터페이스,
> <br> 컬렉션 데이터 타입의 데이터를 내부 반복을 통해 정렬, 필터링 등이 가능한 스트림,
> <br> null이 올 수 있는 값을 감싸는 래퍼클래스인 옵셔널,
> <br> 기존 소스 코드와의 하위 호환성을 위한 인터페이스의 디폴트 메서드 등이 있습니다.

### Java8에 추가된 기능들

- Lambda 표현식
- 함수형 인터페이스
- Stream
- Optional
- 인터페이스의 default 메서드
- 날짜 관련 클래스들 추가
- 병렬 배열 정렬
- StringJoiner 추가

</details>

<details>
    <summary><b>Stream에 대해 설명해주세요.</b></summary>

> 👉  스트림 API는 배열이나 컬렉션처럼 ***데이터 그룹을 간단하고 효율적으로 처리할 목적***으로 JDK 8부터 지원하는 API 입니다. 스트림은 ***원본 데이터를 변경하지 않고 데이터 가공***이 가능하며, 배열이나 컬렉션 데이터와 달리 ***작업 후 스트림 데이터가 자동으로 소멸***된다는 특징이 있습니다. 마지막으로 스트림은 ***중간 연산이 최종 연산이 수행될 때가 되서야 수행***된다는 지연 연산의 특징을 갖고 있습니다.


### Stream

- Java8 에 추가된 API
- 컬렉션 타입의 데이터를 Stream 메서드로 내부 반복을 통해 정렬, 필터링이 가능
- 특징
    - _**parallel 메서드 제공을 통해 병렬 처리가 가능**_
    - **원본 데이터를 변경하지 않음(Immutable)**
        - 원본 데이터로부터 읽기만 할 뿐, 원본 데이터 자체를 변경하지 않는다.
    - 작업을 내부 반복으로 처리하므로 불필요한 코드를 줄일 수 있습니다.
    - 최종 연산 이후 stream이 닫히므로 **일회용**이다.
        - **배열과 컬렉션 데이터는 메모리에 저장되지만 스트림 데이터는 작업 후 자동으로 소멸됨**
- 구조
    - `Stream 생성`
    - `중간연산`: 데이터를 가공하는 과정
        - 필터링: filter, ditinct
        - 변환: map, flatMap
        - 제한: limit, skit
        - 정렬: sorted
        - 연산결과확인: peek
    - `최종연산`: Stream 안의 데이터를 모아 반환하는 역할을 한다.
        - 출력: forEach
        - 소모: reduce
        - 검색: findFirst, findAny
        - 검사: anyMatch, allMatch, noneMatch
        - 통계: count, min, max
        - 연산: sum, savage
        - 수집: collect

### ParallelStream

> 추가 예정

</details>

<details>
    <summary><b>Stream과 반복문 for문의 성능차이가 있을까요?</b></summary>

> 👉 일반적으로 for문이 stream에 비해 빠릅니다. for문은 오래된 기술로, JIT 컴파일러에 의해 최적화가 잘되어있습니다. 또한 stream은 for문에 비해 오버헤드가 발생하기 때문에, for문이 성능상 더 좋습니다. 그럼에도 불구하고, 저는 stream이 가독성이 더 좋아 개발 시에는 stream을 주로 사용하는 편입니다.

### Stream vs for 반복문

- _**for문이 stream 보다 빠르다.**_
    - for문은 40년동안 JIT 컴파일러에 의해 최적화 되어있다.
    - stream은 for문에 비해 오버헤드 발생
        - stream 사용을 위해서는 boxing이 필요하지만, for문은 인덱스에 바로 접근하여 오버헤드 발생하지 않음


- _**그래도 stream을 사용하는 이유?**_
    - stream은 for문에 비해 **_가독성이 좋다._**

※ Ref
- [Java Lambda Expression과 성능](https://brunch.co.kr/@heracul/3)
- [Java Stream API는 왜 for-loop보다 느릴까?](https://sigridjin.medium.com/java-stream-api%EB%8A%94-%EC%99%9C-for-loop%EB%B3%B4%EB%8B%A4-%EB%8A%90%EB%A6%B4%EA%B9%8C-50dec4b9974b)

</details>

<details>
    <summary><b>Stream에서 사용할 수 있는 함수형 인터페이스에 대해 설명해주세요.</b></summary>

> 👉 함수형 인터페이스란 추상 메서드를 하나만 갖는 인터페이스를 의미합니다. 익명 클래스 또는 람다식으로 인스턴스 생성이 가능하며, 스트림에서는 주로 함수형 인터페이스 `Consumer<T>, Function<T, R>, Predicate<T>, Supplier<T>`을 람다식으로 선언해서 사용합니다.

**※ 함수형 인터페이스**

- 정의
    - 추상 메서드를 딱 하나만 갖는 인터페이스
    - `@FunctionalInterface` 어노테이션을 갖는 인터페이스
- 예시
  ```java
  @FunctionalInterface
  public interface RunSomething { // 추상메서드가 단 1개만 있으므로 함수형 인터페이스 맞다.
      void doIt();
  
      static void printName(){
          System.out.println("catsbi");
      }
      
      default void printAge(){
          System.out.println("33");
      }
  }
  
  // 함수형 인터페이스는 익명 내부 클래스로 구현해서 사용 가능
  RunSomething runSomething = new RunSomething() {

			@Override
			public void doIt() {
				System.out.println("do something");
			}
		};

  runSomething.doIt();
  
  // 익명 내부 클래스 to 람다식
  RunSomething runSomething = () -> System.out.println("do something");
  ```

</details>

<details>
    <summary><b>Lambda에 대해 설명해주세요.</b></summary> 

> 👉 람다식이란 함수를 하나의 식으로 표현한 것으로, 함수형 인터페이스의 인스턴스를 선언할 때 사용가능합니다. 따라서, 변수처럼 취급이 가능하고 인자로 전달 가능한 특징이 있습니다. 기존에 익명 클래스로 구현하던 코드를 줄일 수 있고, 개발자의 의도가 명확히 드러난다는 점에서 가독성을 높일 수 있습니다. 단, 익명함수로써 사용된 람다식은 재사용이 불가능하다는 단점 등이 있습니다. 개인적으로는 스트림 API를 활용시 자주 사용하고 있습니다.

</details>

<details>
    <summary><b>익명 클래스(Anonymous Inner Class)와 Lambda의 차이점을 알고계신가요?</b></summary> 

> 👉 익명 클래스와 람다식의 가장 큰 차이점은 추상 메서드가 여러 개인 인터페이스를 구현할 수 있는가에 대한 여부입니다. 우선 익명 클래스는 추상 클래스 또는 추상 메서드가 여러 개인 인터페이스를 상속하여 인스턴스를 생성할 수 있습니다. 반면 람다식은 추상 메서드가 하나인 인터페이스, 즉 함수형 인터페이스만 구현이 가능합니다. 또한 익명 클래스의 this 는 클래스의 인스턴스 자기 자신을 가리키지만, 람다식의 this는 선언된 클래스를 가리킨다는 차이점 등도 있습니다.
>

※ 익명 클래스

코드 내부에 이름이 존재하지 않는 클래스

</details>

<details>
    <summary><b>람다식에서, 외부 변수를 사용할 때 final 키워드를 붙여서 사용하는 이유가 무엇일까요? final을 안 붙여도 되지 않을까요?</b></summary>

> 👉
>  만약 참조하고자 하는 지역 변수가 final 혹은 effectively final 이 아니라면, 멀티쓰레드 환경에서 람다 캡쳐링 시 외부 지역 변수가 복사될 때, 전달되는 값이 항상 최신 값임을 보장할 수 없습니다. 따라서 외부 지역 변수는 final 혹은 effectively-final 이어야 합니다. 추가로 외부 변수 중 인스턴스 변수나 클래스 변수는 쓰레드간 공유가 가능하기 때문에 final 키워드를 사용하지 않아도 참조 시 최신값임을 보장할 수 있습니다.

- **_‘람다식에서 참조하는 외부 지역 변수는 final 혹은 effectively final 이어야한다.’_**
    - 람다식에서 사용되는 외부 지역 변수는 복사본이다.
- 외부 변수에는 인스턴스 변수, 클래스 변수, 지역 변수가 있고 이 중 인스턴스 변수나 클래스 변수는 final 이 아니어도 사용가능하다.

### 람다식에서 사용되는 외부 지역변수는 복사본이다.

람다식에서 사용되는 외부 지역변수가 복사본인 이유
- 지역 변수는 스택 영역에 생성되고, 선언된 block 이 끝나면 stack 에서 제거된다.
    - 메서드 내에서 지역변수를 참조하는 람다식(람다 캡쳐링)이 있는 경우, 추후에 다시 사용할 수도 있으므로 참조했던 지역변수를 복사해야한다. (복사한 지역변수는 스레드의 스택에 저장)
- 지역 변수를 관리하는 쓰레드와 람다식이 실행되는 쓰레드가 다를 수 잇다.

**※ Effectively final**
- 초기화 된 이후 값이 한 번도 변경되지 않은 상태

```java
public String composeNames(List<Book> books) {
	String seperator = ","; // 람다식의 매개변수가 아니다. -> 자유 변수

	return books.stream()
            .map(book-> book.getAuthor())
            .collect(Collectors.joining(seperator)); // Variable used in lambda expression should be final or effectively final 에러 발
	
	separator = ":"; // final 키워드가 없더라도 final 처럼 쓰지 않으면 문제가 된다.
}
```

※ Ref

- https://vagabond95.me/posts/lambda-with-final/
- https://steady-coding.tistory.com/306

</details>


<details>
    <summary><b>Optional에 대해 설명해주세요. Optional을 사용하면 무슨 이점이 있을까요?</b></summary> 

> 👉 옵셔널은 null이 올 수 있는 값을 감싸는 Wrapper 클래스입니다. 이 덕분에 Optional 타입의 객체를 사용하게 되면 그 자체만으로 다른 개발자 역시 아! 이 안의 value가 null일 수 있어! 라고 명시적으로 변수에 대한 null 가능성을 표현할 수 있습니다. 또 null 체크를 하지 않아도 되는 등의 이점이 있습니다. 마지막으로 NullPointerException 이 발생할 수 있는 값을 직접 다루지 않아도 된다는 장점이 있습니다.

</details>

<details>
    <summary><b>Optional을 사용하면서 주의해야할 점이 있을까요?</b></summary>

> 👉 Optional은 설계된 의도, 즉 메서드의 리턴타입으로만 사용하는 것이 좋고 이외의 경우는 사용을 지양하는 편이 좋습니다. 예를 들어 메서드의 파라미터로 사용된 경우를 생각해보겠습니다. 파라미터로 전달된 옵셔널 객체의 값을 사용하기 위해서 먼저 파라미터 자체가 null인지 검사하고, null이 아니라면 isPresent() 등 value가 존재하는지를 확인하는 과정을 거쳐야만 합니다. 이는 Optional을 사용하기 전에 단순히 null 체크만 해주는 것보다도 코드가 복잡해져버립니다. 또한 Optional 자체가 래퍼클래스 이다보니 남용하면 그만큼 메모리와 실제 객체에 접근하는 시간이 많이 소요될 것입니다. 따라서 설계된 의도인 반환타입으로만 사용할 것을 지키는 것이 좋다고 생각합니다.

</details>