<!-- TOC -->
* [7.2 컴포지션 API 기본 구성 요소](#72-컴포지션-api-기본-구성-요소)
  * [7.2.2 템플릿 ref](#722-템플릿-ref)
    * [`ref()`로 생성한 반응형 데이터를 통한 DOM 참조](#ref로-생성한-반응형-데이터를-통한-dom-참조)
    * [컴포넌트 참조](#컴포넌트-참조)
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
