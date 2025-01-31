<!-- TOC -->
* [7.2 컴포지션 API 기본 구성 요소](#72-컴포지션-api-기본-구성-요소)
  * [7.2.2 템플릿 ref](#722-템플릿-ref)
    * [`ref()`로 생성한 반응형 데이터를 통한 DOM 참조](#ref로-생성한-반응형-데이터를-통한-dom-참조)
    * [컴포넌트 참조](#컴포넌트-참조)
* [7.2.3 computed 속성 활용](#723-computed-속성-활용)
    * [일반적인 사용법](#일반적인-사용법)
    * [수정 가능한 계산된 속성](#수정-가능한-계산된-속성)
<!-- TOC -->

# 7.2 컴포지션 API 기본 구성 요소

## 7.2.2 템플릿 ref

### `ref()`로 생성한 반응형 데이터를 통한 DOM 참조

```vue
<template>
  <h1 ref="inputRef">Hello, Ref!</h1>
</template>

<script setup>
  import {ref, reactive} from 'vue'

  const inputRef = ref(null);
  setTimeout(() => {
  console.log(inputRef.value); // h1 태그에 해당하는 DOM객체
  console.log(inputRef.value.innerText); // DOM 객체의 속성, 메서드 모두 사용 가능.
}, 1000);
</script>
```

- `ref="inputRef`: 이 부분을 `템플릿 ref`라고 한다.
  - 컴포넌트가 마운트됐을 때, 변수 `inputRef`에 h1 태그에 해당하는 DOM 객체가 할당된다.
  - 따라서 변수 `inputRef`를 통해 DOM 조작이 가능해진다.
- 템플릿 ref?
  - Vue에서 템플릿 내부의 DOM 요소나 컴포넌트 인스턴스에 직접 접근하기 위해 사용하는 특수한 속성.

### 컴포넌트 참조

```vue
<template>
  <h1>{{ number }}</h1>
  <h1>{{ doubleNumber }}</h1>
</template>

<script setup>
  import { ref, computed } from 'vue'

  const number = ref(5);
  const doubleNumber = computed(() => number.value * 2);

  // defineExpose() 함수로 노출한 값만 부모 컴포넌트에서 접근 가능
  defineExpose({
    number,
    doubleNumber
  });
</script>
```
```vue
<template>
  <RefEx2Child ref="componentRef"></RefEx2Child>
</template>

<script setup>
import {ref} from 'vue'
import RefEx2Child from "@/components/RefEx2Child.vue";

const componentRef = ref(null);

setTimeout(() => {
  console.log(componentRef.value.number); // RefEx2Child 컴포넌트 인스턴스에 접근
  console.log(componentRef.value.doubleNumber); // RefEx2Child 컴포넌트 인스턴스에 접근
}, 1000);
</script>
```

- ref 속성으로 다른 컴포넌트 인스턴스에 접근 가능하다.

# 7.2.3 computed 속성 활용

### 일반적인 사용법

```vue
<template>
  <h1>{{ refDoubleCount }}</h1>
  <h1>{{ reactiveDoubleCount }}</h1>
</template>

<script setup>
import {ref, reactive, computed} from 'vue'

const refCount = ref(1);
const reactiveCount = reactive({count: 2});

// ref() 데이터 활용 시 value 속성 사용
const refDoubleCount = computed(() => refCount.value * 2);

// reactive() 데이터 활용 시에는 value 속성 불필요`
const reactiveDoubleCount = computed(() => reactiveCount.count * 2);

/**
 * computed() 데이터 출력 시에는 value 사용
 */
console.log(refDoubleCount.value);
console.log(reactiveDoubleCount.value);
</script>
```

### 수정 가능한 계산된 속성

```vue
<template>
  <h1>{{ refDoubleCount }}</h1>
  <h1>{{ reactiveDoubleCount }}</h1>
</template>

<script setup>
import {ref, reactive, computed} from 'vue'

const refCount = ref(1);
const reactiveCount = reactive({count: 2});

const refDoubleCount = computed(() => refCount.value * 2);
const reactiveDoubleCount = computed(() => reactiveCount.count * 2);

setTimeout(() => {
  refDoubleCount.value = 20; // computed 변수는 재할당 불가해서 콘솔에 경고 표시 뜬다.
}, 2000);
</script>
```

- 원래 computed 변수는 재할당 불가능하다.
  - 그래서 재할당 시 콘솔에 경고 표시 뜬다.(`[Vue warn] Write operation failed: computed value is readonly`)

> 👨🏻‍🏫 수코딩의 조언
> - computed() 함수를 set(), get()으로 정의해서 사용할 경우
>   - ref(), reactive() 데이터를 직접 수정하는 방식으로 사용할 수 있긴한데 권장하지 않는다.
> - _**computed는 그냥 원래 목적대로 읽기 전용으로 사용하라.**_

## 7.2.4 함수 정의 

```vue
<template>
  <h1>총합: {{ numberSum }}</h1>
  <h1>ref: {{ count }}</h1>
  <h1>reactive: {{ state.count }}</h1>
  <button @click="expressFunc">ref 증가</button>
  <button @click="arrowFunc">reactive 증가</button>
</template>

<script setup>
import {ref, reactive, computed} from "vue";

/**
 * 반응형 데이터 정의
 */
const count = ref(0);
const state = reactive({
  count: 0,
  message: "Hello, Vue 3!"
});

const numberSum = computed(() => count.value + state.count);

/**
 * 함수 표현식
 */
const expressFunc = function () {
  count.value++;
};
/**
  * 화살표 함수
  */
const arrowFunc = () => {
  state.count++;
};
</script>
```

- 함수 정의는 함수 표현식 또는 화살표 함수로 정의 가능
