<script setup>
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import "@toast-ui/editor/dist/theme/toastui-editor-dark.css";
import { ref, onMounted, computed } from "vue";
import { useArticleStore } from "../../stores/article";

const editor = ref(null);
onMounted(() => {
  editor.value = new Editor({
    el: document.querySelector("#editor"),
    height: "100%",
    width: "25%",
    initialValue: initialValue.value,
    initialEditType: "markdown",
    theme: "dark",
    language: "ko-KR",
    autofocus: true,
  });
});

const articleStore = useArticleStore();

const editorContent = ref("");
const getMarkdownText = () => {
  editorContent.value = editor.value.getMarkdown();
};
onMounted(() => {
  editor.value.addHook("change", () => {
    editorContent.value = editor.value.getMarkdown();

    articleStore.tempArticle = editorContent.value;
  });
  editor.value.addHook("focus", () => {
    setTemplete();
    getMarkdownText();
    articleStore.tempArticle = editorContent.value;
  });
  editor.value.addHook("blur", () => {
    getMarkdownText();
    articleStore.tempArticle = editorContent.value;
  });
});

const setTemplete = () => {
  if (
    editorTemplete[0].innerText !==
    "# " + articleStore.articles[articleStore.selectedState]?.title
  )
    editorTemplete[0].innerText =
      "# " + articleStore.articles[articleStore.selectedState]?.title;
};

const initialValue = computed(() => {
  return "# " + articleStore.articles[articleStore.selectedState]?.title;
});

var editorTemplete = document.getElementsByClassName(
  "toastui-editor-md-heading toastui-editor-md-heading1"
);
</script>

<template>
  <div id="editor"></div>
</template>

<style lang=""></style>
