<!-- TOC -->
* [7.2 컴포지션 API 기본 구성 요소](#72-컴포지션-api-기본-구성-요소)
  * [7.2.2 템플릿 ref](#722-템플릿-ref)
    * [`ref()`로 생성한 반응형 데이터를 통한 DOM 참조](#ref로-생성한-반응형-데이터를-통한-dom-참조)
    * [컴포넌트 참조](#컴포넌트-참조)
* [7.2.3 computed 속성 활용](#723-computed-속성-활용)
    * [일반적인 사용법](#일반적인-사용법)
    * [수정 가능한 계산된 속성](#수정-가능한-계산된-속성)
* [7.2.4 함수 정의](#724-함수-정의-)
* [7.2.5 감시자 속성](#725-감시자-속성)
  * [watch()](#watch)
    * [사용법](#사용법)
    * [`ref()`로 생성한 반응형 데이터를 감시하는 경우](#ref로-생성한-반응형-데이터를-감시하는-경우)
    * [`reactive()`로 생성한 반응형 데이터를 감시하는 경우](#reactive로-생성한-반응형-데이터를-감시하는-경우)
  * [watchEffect()](#watcheffect)
    * [사용법](#사용법-1)
    * [예시](#예시)
  * [watchPostEffect()](#watchposteffect)
    * [예시](#예시-1)
  * [watch, watchEffect, watchPostEffect 를 언제 어떻게 사용할까?](#watch-watcheffect-watchposteffect-를-언제-어떻게-사용할까)
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

# 7.2.4 함수 정의 

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

# 7.2.5 감시자 속성

## watch()

### 사용법

```vue
<script setup>
import {watch} from 'vue'
  
watch(반응형_데이터, (newValue, oldValue) => {
  immediate: true, // 초기 렌더링 시 콜백 함수 호출 여부. 기본값은 false
  deep: true, // 중첩된 객체나 배열의 변경을 감시할지 설정. 기본값은 false
  flush: 'post' | 'sync', // 콜백 함수 실행 타이밍 결정. 
  once: false // true 지정 시 변경 사항을 한 번만 감시.
});
</script>
```

### `ref()`로 생성한 반응형 데이터를 감시하는 경우

- 반응형 변수 자체를 감시.
- 배열이나 객체의 특정 속성 값 변경은 감시하지 않음.
- 오직 반응형 변수의 값이 완전히 달라지는지만 감시한다.

```vue
<template>
  <h1>count 변수: {{ count }}</h1>
  <button @click="count++">count 변수 증가</button>
</template>

<script setup>
import {ref, watch} from 'vue';

// 감시를 이용하여 count 변수의 값이 증가하면 콘솔에 로그를 출력한다.
const count = ref(0);

watch(count, (newValue, oldValue) => {
  console.log(`count 변수가 ${oldValue}에서 ${newValue}(으)로 변경되었습니다.`);
})
</script>
```

- 위처럼 반응형 데이터인 `count`값을 변경하면 watch의 콜백 함수는 정상적으로 동작한다.

```vue
<template>
  <h1>count 변수: {{ state.count }}</h1>
  <button @click="state.count++">count 변수 증가</button>
</template>

<script setup>
import {ref, watch} from 'vue';

// 감시를 이용하여 count 변수의 값이 증가하면 콘솔에 로그를 출력한다.
const state = ref({
  count: 0,
});

watch(state, (currentValue, preValue) => {
      console.log(`count 변수가 ${preValue}에서 ${currentValue}(으)로 변경되었습니다.`);
    },
    {
      deep: true
    }
)
</script>
```

- ref로 선언한 객체나 배열 변경 감시할 때는 `deep: true` 옵션을 사용한다.
  - (책에서는 deep 옵션을 사용하지 않을 경우 콜백 함수 제대로 실행 안 된다고 하는데 deep 옵션 사용 안 해도 동작은 했다.)

### `reactive()`로 생성한 반응형 데이터를 감시하는 경우

- reactive로 선언한 반응형 데이터인 배열이나 객체는 내부 값이 변경되어도 watch 함수로 감지할 수 있다.

<br>

## watchEffect()

- 콜백 함수 내부에서 **참조하는 모든 반응형 데이터**를 감시. (옵션스 API에는 없는 기능)

### 사용법

```vue
<script setup>
import {ref, watchEffect} from 'vue';
watchEffect(() => {}, {flush: 'post'})  
</script>
```

- watchEffect()는 watch()와 다르게 `immediate`, `deep` 속성을 사용하지 않음.
  - 해당 속성이 기본으로 적용된 것처럼 동작하기 때문.
- 사용하는 속성은 콜백 함수 호출 시점을 결정할 때 적용하는 `flush` 속성 뿐이다.

### 예시

```vue
<template>
  <h1>ref: {{ count }}</h1>
  <button @click="count++">ref 증가</button>
  <h1>reactive: {{ state.count }}</h1>
  <button @click="state.count++">reactive 증가</button>
</template>

<script setup>
import {reactive, ref, watch, watchEffect} from 'vue';

const count = ref(0);
const state = reactive({
  count: 0,
});

/**
 * 아무 의미도 없이 console.log()로 출력해도,
 * 콜백 함수 내부에서 반응형 데이터를 사용한 것이 되어 감시 대상이 됨.
 */
watchEffect(() => {
  // count 또는 state.count 값이 변경되면 아래 로그가 모두 출력됨.
  // 여러 반응형 데이터를 사용하여 둘 중 하나만 변경되어도 실행됨.
  console.log(`ref: ${count.value}`);
  console.log(`reactive: ${state.count}`);
});
</script>
```

<br>

## watchPostEffect()

- `watchPostEffect()`?
  - `flush: 'post'` 옵션을 사용한 것처럼 DOM이 갱신되고 나서 콜백 함수가 실행된다.
- `flush: 'post'` 옵션을 사용하지 않으면 DOM이 갱신되기 전에 콜백 함수가 실행된다.

### 예시

```vue
<template>
  <div>
    <input v-model="message" placeholder="메시지를 입력하세요."/>
    <p ref="messageParagraph">{{ message }}</p>
  </div>
</template>

<script setup>
import {ref, watch, watchEffect, watchPostEffect} from 'vue';

const message = ref('');
const messageParagraph = ref(null);

watch(message, () => {
  if (message.value) {
    console.log(`watch: ${messageParagraph.value.innerText}`);
  }
});

watchEffect(() => {
  if (message.value) {
    console.log(`watchEffect: ${messageParagraph.value.innerText}`);
  }
});

watchPostEffect(() => {
  if (message.value) {
    console.log(`watchPostEffect: ${messageParagraph.value.innerText}`);
  }
});
</script>
```

![img.png](img.png)

## watch, watchEffect, watchPostEffect 를 언제 어떻게 사용할까?

1. `watch` - 명시적이고 세밀한 제어가 필요할 때

- 이전 값과 현재 값 비교가 필요한 경우
- 특정 조건에서만 실행해야 하는 경우
- 감시 시작 타이밍 제어가 필요한 경우 (immediate 옵션)

```js
watch(searchText, async (newText, oldText) => {
  if (newText !== oldText && newText.length > 2) {
    await searchAPI(newText);
  }
}, { immediate: true });
```

2. `watchEffect` - 반응형 데이터의 즉각적인 변화 감지가 필요할 때
- 콜백에서 사용되는 모든 반응형 데이터를 자동으로 감시하고 싶을 때
- API 호출이나 비동기 작업 처리할 때
- 초기 실행이 반드시 필요한 경우

```js
watchEffect(async () => {
  if (userId.value && token.value) {
    const data = await fetchUserData(userId.value);
    userData.value = data;
  }
});
```

3. `watchPostEffect` - DOM 업데이트 이후 작업이 필요할 때
- DOM 요소의 실제 크기나 위치를 계산해야 하는 경우
- 서드파티 UI 라이브러리와의 통합이 필요한 경우 (차트, 에디터 등)
- Vue의 가상 DOM 업데이트가 실제 DOM에 반영된 후 작업이 필요한 경우
```js
watchPostEffect(() => {
  if (chartRef.value && data.value) {
    // DOM 업데이트 후 차트 라이브러리 초기화/업데이트
    initChart(chartRef.value, data.value);
  }
});
```

성능 고려사항:

- `watch`: 가장 효율적 (필요할 때만 실행)
- `watchEffect`: 의존성이 있는 데이터가 변경될 때마다 실행
- `watchPostEffect`: DOM 업데이트를 기다려야 해서 약간의 지연 발생

Best Practice:

1. 기본적으로는 `watch` 사용을 우선 고려
2. 여러 반응형 데이터를 동시에 관찰해야 한다면 `watchEffect` 고려
3. DOM 조작이 필요한 경우에만 `watchPostEffect` 사용
