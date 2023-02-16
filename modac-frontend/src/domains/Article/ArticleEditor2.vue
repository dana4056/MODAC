<script setup>
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import "@toast-ui/editor/dist/theme/toastui-editor-dark.css";
import { ref, onMounted } from "vue";
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
    // theme: "dark",
    language: "ko-KR",
    autofocus: true,
  });
});

onMounted(() => {
  editor.value.addHook("change", () => {
    // change 될 때 마다 정보를 저장하기
  });
});
</script>

<template>
  <div id="editor"></div>
</template>

<style lang=""></style>
