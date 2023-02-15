<script setup>
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import "@toast-ui/editor/dist/theme/toastui-editor-dark.css";
// import { ref, onMounted, toRefs, computed } from "vue";
// import { useArticleStore } from "@/stores/article";
// import todoAPI from "@/api/todo";

// import "@toast-ui/editor/dist/theme/toastui-editor-dark.css";
import { ref, onMounted, toRefs, computed } from "vue";
import { useArticleStore } from "@/stores/article";
import todoAPI from "@/api/todo";

const editor = ref(null);
const templateValue = ref("");

const magic = async () => {
  const response = await todoAPI.findTodo(2);
  templateValue.value = response.templateContent;
};

onMounted(async () => {
  await magic();
  editor.value = new Editor({
    el: document.querySelector("#editor"),
    height: "100%",
    width: "25%",
    initialValue: templateValue.value,
    initialEditType: "markdown",
    theme: "dark",
    language: "ko-KR",
    autofocus: true,
  });
});

// const editor = ref(null);

// const templateValue = ref("");

// const magic = async () => {
//   const response = await todoAPI.findTodo(2);
//   console.log(1);
//   return response.templateContent;
// };

// console.log(2);

// onMounted(async () => {
//   console.log(3);
//   templateValue.value = await magic();
//   editor.value = new Editor({
//     el: document.querySelector("#editor"),
//     height: "100%",
//     width: "25%",
//     initialValue: templateValue,
//     initialEditType: "markdown",
//     theme: "dark",
//     language: "ko-KR",
//     autofocus: true,
//   });
// });

const setTemplate = (template) => {
  // const contentTag = document.getElementsByClassName(
  //   "toastui-editor-md-heading toastui-editor-md-heading1"
  // );
  const contentTag = document.querySelector(".ProseMirror");
  contentTag.innerText = template;
  // if (editor.value !== null) {
  //   editor.value.setMarkdown("this is template content", true);
  // }

  // editor.value.setMarkdown(template, true);
};

// onMounted(() => {
//   editor.value.addHook("change", () => {
//     articleContent.value = editor.value.getMarkdown();
//   });
// });

const templateExample = templateValue;
</script>

<template>
  <div id="editor"></div>
  <button @click="setTemplate(templateExample)">저장</button>
  <button @click="magic">마크다운 템플릿 불러오고 저장하기</button>
</template>

<style lang=""></style>
