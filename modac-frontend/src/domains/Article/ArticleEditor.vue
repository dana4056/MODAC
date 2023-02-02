<script setup>
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";
// import "@toast-ui/editor/dist/theme/toastui-editor-dark.css"; // 다크테마 적용하기

import { ref, onMounted } from "vue";

// created에서 editor 변수 생성
// mounted에서 editor에 Editor 인스턴스를 할당
const editor = ref(null);
onMounted(() => {
  editor.value = new Editor({
    // options
    el: document.querySelector("#editor"),
    height: "100%",
    width: "50%",
    initialValue: `# Hello Markdown Editor!`, // Editor에 처음으로 적혀있는 글
    initialEditType: "markdown",
    // theme: "dark", // 다크테마 적용하기
    language: "ko-KR",
  });
});

// Editor 내부 데이터를 가져오기
// 1안. Editor 의 데이터가 변할 때 마다 데이터를 저장? => 성능 이슈 가능성
// 2안. 다른 Article로 넘어간다면, 현재 가지고 있는 데이터가 저장되지 않음을 고지함. => 사용성이 이상함
// (결론) 일단 1안으로 구현
const editorContent = ref("");
const getMarkdown = () => {
  editorContent.value = editor.value.getMarkdown();
};
onMounted(() => {
  editor.value.addHook("change", () => {
    // addHook 함수는 Editor에 대해서 Event가 발생했을 때 실행시킬 함수를 등록한다.
    // addHook 함수의 첫번째 인자를 "blur"로 설정한다면 괜찮을지 체크(다른 Article을 선택했을 때, Article을 제출했을 때 => 과연 blur가 잘 작동할 것인가? 확인 필요
    editorContent.value = editor.value.getMarkdown();
  });
});
</script>

<template>
  <div id="editor"></div>
  <!-- <button @click="getMarkdown">그냥 이벤트 호출해주는 버튼</button> -->
</template>

<style lang=""></style>
