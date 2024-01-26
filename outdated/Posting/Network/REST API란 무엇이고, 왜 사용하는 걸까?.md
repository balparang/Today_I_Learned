> ### REST API란 무엇이고, 왜 사용하는 걸까?

<!-- TOC -->
* [들어가며](#)
* [REST API란 무엇인가](#rest-api-)
  * [REST API? REST'ful' API?](#rest-api-restful--api)
* [REST란 무엇인가](#rest-)
  * [REST 원칙](#rest-)
    * [균일한 인터페이스(Uniform Interface)](#---uniform-interface-)
    * [클라이언트 - 서버 분리(Client - Server Separation)](#------client---server-separation-)
    * [계층형 시스템(Layered System)](#---layered-system-)
    * [무상태(Stateless)](#--stateless-)
    * [캐시 가능성(Cacheable)](#---cacheable-)
    * [온디맨드 코드(Code-on-demand)](#---code-on-demand-)
  * [REST 규약을 반드시 지켜서 개발해야할까](#rest----)
* [마치며](#)
    * [추가적으로 공부해 볼 것](#---)
    * [Reference](#reference)
<!-- TOC -->


<img width="906" alt="image" src="https://github.com/haero77/Today-I-Learned/assets/65555299/58f9f8cc-aecf-440c-a8e9-bd9d3ff974a4">

_([이미지 출처](https://meetup.nhncloud.com/posts/92))_

<br>

> 🎯GOAL
> - REST API가 무엇이고 왜 사용하는지 이해합니다.
> - REST가 무엇인지 이해합니다.
> - REST가 갖는 제약 조건을 전부 지켜서 개발해야하는지 고민해봅니다.

<br>

# 들어가며 

토이 프로젝트를 해오면서, `REST API`를 설계하기 위해 어떤 HTTP 메서드를 써야하고, URI는 어떻게 설계할지 등을 고민해왔습니다. 그러나 정작 `REST`란 뭔지, `RESTful` 하다는 것이 무엇인지 등 그 근본적인 내용에 대해서는 명확히 답을 하기 어려웠습니다. 그 물음에 답하고자, 본 포스팅에서는 **REST API란 무엇이고**, **왜 많이 사용하는지**, **REST API를 구현하기 위한 원칙을 반드시 지켜야하는 것인지**를 살펴봅니다.

<br>

# REST API란 무엇인가

`REST API`란,  _'REST 아키텍처 스타일을 따르는 API'_ 를 말합니다. HTTP 요청에 어떤 `자원`이 필요한지 `URI`를 통해 나타내고, 어떤 `행위`를 할 것인지 `HTTP 메서드`로 명세합니다. 이렇게 함으로써 **클라이언트가 어떤 작업을 원하는지, `요청 형식`만으로 추론이 가능**해집니다. 

예를 들어,

- `GET /api/members/1`

와 같은 API를 설계했다고 한다면, REST API를 경험한 개발자는 이 API가 특정 회원의 정보를 조회하는 것임을 쉽게 알아차릴 수 있습니다. 즉, 일정한 제약(=REST 아키텍처)에 따라 API를 설계했으므로 **여러 개발자간에 협업이 가능**해지는 것이죠.

<br>

## REST API? REST'ful' API?

용어 `REST API`와 `RESTful API`는 서로 많이 혼용되어 사용됩니다. 엄밀히 말하면, REST 아키텍처의 제약 조건을 모두 만족해야만 `REST API`라고 할 수 있습니다. 그러나, 현실적으로 제약을 모두 지키기는 어려우므로 'REST 스러운', 'REST 스타일의' 라는 뉘앙스로 `RESTful API`라는 용어가 사용되며, 이를 편하게 `REST API`라고도 부르는 것이죠. 추가적으로, `RESTful API`를 이용하여 구현한 웹 애플리케이션을 `RESTful 웹 서비스`라고합니다. 본 포스팅에서는 REST 제약을 모두 지키는 API를 `REST API`, 일부 제약을 지키는 API를 `RESTful API`로 구분해서 사용하겠습니다. 


<br>

# REST란 무엇인가

앞서 `REST` 아키텍처를 따르는 `API`를 `REST API`라고 했습니다. 여기서 말하는 `REST`란, `REpresentational State Transfer`의 약자로, ['사용할 웹 리소스와, 해당 리소스의 접근 방법을 정의하는 소프트웨어 아키텍처'](https://www.baeldung.com/cs/rest-architecture)를 의미합니다. 클라이언트는 `HTTP URI`를 통해 사용할 리소스를 지정하고, `HTTP Method`를 통해 어떻게 접근할지를 나타냅니다. 서버는 요청에 대해 리소스의 상태(State)를 표현(Represent)하여 클라이언트에게 전송(Transfer)합니다.

REST 아키텍처를 사용하면 `균일한 인터페이스`, `무상태`, `캐시 가능성` 등을 통해 효율적이며, 확장성있고 유연한 서비스 개발이 가능해집니다.

## REST 원칙

`REST`의 원칙으로는 아래 6가지가 있습니다.

1. 균일한 인터페이스(Uniform Interface)
2. 클라이언트-서버 분리(Client - Server Separation)
3. 무상태(Stateless)
4. 캐시 가능성(Cacheable)
5. 계층형 시스템(Layered System)
6. 온디맨드 코드(Code-on-demand)

`REST`는 `HTTP`를 효율적으로 잘 사용하기 위한 방법론입니다. 따라서 HTTP만 잘 지켜도, `클라이언트-서버 분리`, `무상태`, `계층형 시스템`, `캐시 가능성`을 자연스럽게 만족합니다.

<br>

### 균일한 인터페이스(Uniform Interface)

`균일한 인터페이스`란, 서버가 일관된 형식으로 정보를 응답하는 것을 의미합니다. 즉 HTTP 표준 프로토콜만 지키면 특정 언어나 기술에 종속되지 않고 여러 플랫폼에서 API 사용이 가능합니다. 균일한 인터페이스에는 4가지 제약조건이 있습니다. 

1. Identification of resources
   - 서버로의 요청은 리소스 식별자를 포함해야함을 의미
2. Manipulation of resources through representations
   - 클라이언트가 리소스를 통해 작업을 하기 위해 서버는 충분한 정보를 담아 응답해야함을 의미
3. Self-descriptive message
   - API에 대한 각각의 요청에는, 서버가 요청을 수행하는 데 필요한 정보들이 모두 담겨있어야합니다.  
4. Hypermedia as the engine of application state(HATEOAS)
   - 애플리케이션의 상태가 Hyperlink를 통해 전이되어야함을 의미

이 중 `Hypermedia as the engine of application state(HATEOAS)`에 대해 좀 더 살펴보겠습니다. '애플리케이션의 상태가 Hyperlink를 통해 전이'라, 말이 어렵습니다. 

예를 들어보겠습니다. (예시는 [여기](https://joomn11.tistory.com/26)를 참고했습니다.)

여기, 회원의 정보를 조회하여 다음과 같은 응답을 받았다고 합니다.

```json
{
  "id": 1,
  "name": "haero77"
}
```

전형적인 응답 예시입니다. `HATEOAS`를 적용한 응답은 다음과 같이 생겼습니다.

```json
{
  "id": 1,
  "name": "haero77",
  "links": [
    {
      "title": "걷기",
      "href": "http://localhost:8080/api/members/1/walk"
    },
    {
      "title": "코딩하기",
      "href": "http://localhost:8080/api/members/1/coding"
    }
  ]
}
```

 **하이퍼링크를 통해**, 회원 id가 1인 'haero77'이라는 친구의 **상태를 전이**(변경)할 수 있게되었습니다. 즉 요청한 리소스의 상태를 나타냄과 함께, 해당 리소스의 상태를 어떻게 변경할 수 있을지 그 방법을 하이퍼링크로 포함하여 응답하는 것이 `HATEOS`라고 볼 수 있습니다.

<br>

### 클라이언트-서버 분리(Client-Server Separation)

`클라이언트-서버 분리`란 클라이언트와 서버가 독립적으로 동작함을 의미합니다.
서버는 REST API를 제공하고, 클라이언트는 사용자 컨텍스트(세션, 로그인 정보 등)를 각각 관리함으로써 서로에 대한 의존성이 줄어듭니다. 

<br>

### 무상태(Stateless)

`Stateless`란, 서버가 API를 사용하는 클라이언트에 대해 그 어떤 정보도 저장하지 않아야함을 의미합니다. 이 덕분에 클라이언트는 순서에 상관없이 리소스를 요청할 수 있으며 각각의 요청은 서로 분리됩니다. 따라서 각 요청은 요청을 수행하기 위한 모든 정보가 포함되어야합니다.   

<br>

### 캐시 가능성(Cacheable)

`캐시 가능성`이란, 서버가 응답하는 데이터에 해당 데이터를 캐싱 가능한지에 대한 여부를 포함해야함을 의미합니다. 지속적으로 업데이트될 필요가 없는 데이터를 캐싱해두면 같은 요청에 대해 해당 요청을 서버로 보내지 않으므로, 서버의 성능을 향상시킬 수 있습니다. 

<br>

### 계층형 시스템(Layered System)

`계층형 시스템`이란, 요청을 보내는 클라이언트와 요청에 대한 응답을 하는 서버 사이에 여러 대의 다른 서버가 있을 수 있음을 의미합니다. 중간에 놓인 계층(서버)은 요청 수행에 필요한 보안이나 캐싱 등을 담당할 수 있습니다. 단, 이런 계층은 클라이언트에게 노출되지 않습니다.

<br>

### 온디맨드 코드(Code-on-demand)

`온디맨드 코드` 원칙은 선택사항입니다. 클라이언트가 서버로부터 스크립트(코드)를 받으면 이를 실행시킬 수 있어야함을 의미합니다. Javascript 등이 이에 해당합니다. 

<br>

## REST 원칙을 반드시 지켜서 개발해야할까

그렇다면 REST 아키텍처를 완벽히 따라서, `REST API`를 개발해야할까요?
REST의 창시자는 이와 관련하여 다음과 같이 언급했습니다.  

> _"REST emphasizes evolvability to sustain an uncontrollable system. **If you think you have control over the system or aren’t interested in evolvability, don’t waste your time arguing about REST.**" - Roy T. Fielding_

볼드 처리된 부분을 번역하면 이렇습니다.
 
> _"시스템 전체를 통제할 수 있다고 생각하거나, 진화에 관심이 없다면, REST에 대해 따지느라 시간을 낭비하지마라."_

여기서 '시스템 전체를 통제할 수 있다'는 것은 혼자서 클라이언트와 서버를 모두 개발하는 경우를 예로 들 수 있겠습니다. 이런 경우 REST 원칙을 따질 필요 없이 자신만의 API를 만들면 되겠죠. 그러나 **다수의 경우 여러 명이서 개발하고, 서비스가 계속 발전(진화)해 나가기를 원합니다.** 그래서 트레이드 오프를 고려하여 `HATEOS` 등 그 세부적인 원칙을 제외하고 REST를 최대한 따르는 `RESTful API`를 많이들 사용하는 것 같습니다.

'REST 원칙을 반드시 지켜서 개발해야하는가?'에 대한 제 생각은 이렇습니다. 세부적인 원칙을 따지기보다는, Best Practice를 참고해가며 API 성격에 맞는 URI 설계 방법이나 HTTP 메서드에 대한 이해도를 높이면 자연스레 REST가 갖는 철학을 좇게되는 것이라고 생각합니다. 핵심은 다수의 개발자가 이해하기 쉬운 `RESTful API`를 만듦으로써 원할한 소통을 가능케 하는 것이니까요.

<br>

# 마치며 

본문을 요약하자면,

- `REST API`란 REST 아키텍처를 따르는 API
- `REST`란 필요한 자원을 지정하는 것과 그 접근 방식에 대한 제약  

이고, 핵심은 '일정한 제약 조건을 통해 다수의 개발자와 협업가능한 API를 만드는 것' 정도로 요약할 수 있겠습니다. 더 좋은 RESTful API를 만들기 위해서는 어떻게 해야할지, 시간 될 때 해당 내용 역시 포스팅 해봐야겠습니다.

_마침_

<br>

### 추가적으로 공부해 볼 것 

- 좋은 RESTful API 설계 
- HTTP Method
  - `PUT`, `PATCH`, 멱등성

<br>

### Reference

- [RESTful API란 무엇인가요? - AWS](https://aws.amazon.com/ko/what-is/restful-api/)

- [REST API 제대로 알고 사용하기 - NHN](https://meetup.nhncloud.com/posts/92)
- [그런 REST API로 괜찮은가 - Naver D2](https://www.youtube.com/watch?v=RP_f5dMoHFc&t=1s)
- [What is REST — A Simple Explanation for Beginners, Part 1: Introduction - Shif Ben Avraham](https://medium.com/extend/what-is-rest-a-simple-explanation-for-beginners-part-1-introduction-b4a072f8740f)
- [RESTful API란 ? - 이동규(씨유)님](https://brainbackdoor.tistory.com/53)
- [The REST Architecture - Baeldung](https://www.baeldung.com/cs/rest-architecture)



- [REST API가 뭔가요? - 얄코](https://www.youtube.com/watch?v=iOueE9AXDQQ)
- [면접 단골 질문! API, REST API가 뭔가요? (개발 필수지식)](https://www.youtube.com/watch?v=C7yhysF_wAg)
- [[10분 테코톡] 정의 REST API](https://www.youtube.com/watch?v=Nxi8Ur89Akw)
- [It is okay to use POST - Roy T. Fielding(REST 창시자)](https://roy.gbiv.com/untangled/2009/it-is-okay-to-use-post)
- [What is a REST API? - Red Hat](https://www.redhat.com/en/topics/api/what-is-a-rest-api)
- https://overcome-the-limits.tistory.com/38#rest%EB%A5%BC-%EA%B5%AC%EC%84%B1%ED%95%98%EB%8A%94-%EC%8A%A4%ED%83%80%EC%9D%BC
- [[Hateoas] Hateoas(헤이티오스) 란 ( + REST API란 )](https://joomn11.tistory.com/26)