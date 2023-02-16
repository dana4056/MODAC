<script setup>
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import "@toast-ui/editor/dist/theme/toastui-editor-dark.css";
import todoAPI from "@/api/todo";
import { useArticleStore } from "../../stores/article";
import { ref, toRef, onMounted, defineProps, watch } from "vue";

const props = defineProps({
  articleItem: Object,
});
const articleItem = toRef(props, "articleItem");
const articleStore = useArticleStore();
const selectedArticleItemSeq = toRef(articleStore, "selectedArticleItemSeq");
const activeEditor = toRef(articleStore, "activeEditor");

const editor = ref(null);
const templateValue = ref("");

const magic = async () => {
  const response = await todoAPI.findTodo(articleItem.value.seq);
  // console.log("templateContent", response.templateContent);
  templateValue.value = response.templateContent;
};

onMounted(async () => {
  await magic();
  editor.value = new Editor({
    el: document.getElementById("editor_" + articleItem.value.seq),
    height: "550px",
    width: "25%",
    initialValue: templateValue.value,
    initialEditType: "markdown",
    // theme: "dark",
    language: "ko-KR",
    autofocus: true,
  });
});

watch(selectedArticleItemSeq, () => {
  if (
    editor.value !== null &&
    selectedArticleItemSeq.value === articleItem.value.seq
  ) {
    activeEditor.value = editor.value;
  }
});

// onMounted(() => {
//   editor.value.addHook("change", () => {
//     console.log("changed");
//     // change 될 때 마다 정보를 저장하기
//   });
// });

// const printContent = () => {
//   console.log(editor.value.getMarkdown());
// };
</script>

<template>
  <div>
    <div
      v-show="selectedArticleItemSeq === articleItem.seq"
      :id="`editor_${articleItem.seq}`"
    ></div>
  </div>
  <!-- <button @click="printContent">print content</button> -->
</template>

<style lang=""></style>
