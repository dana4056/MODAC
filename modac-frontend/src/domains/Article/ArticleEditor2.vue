<script>
import Editor from "@toast-ui/editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import "@toast-ui/editor/dist/toastui-editor-viewer.css";
import { ref, onMounted } from "vue";
import todoAPI from "@/api/todo";

export default {
  setup() {
    const editor = ref(null);
    const templateValue = ref("");

    onMounted(async () => {
      editor.value = new Editor({
        el: document.querySelector("#editor"),
        height: "100%",
        initialEditType: "markdown",
        previewStyle: "vertical",
        language: "ko-KR",
        events: {
          change() {
            templateValue.value = editor.value.getMarkdown();
          },
        },
      });
      const response = await todoAPI.findTodo(2);
      editor.value.setMarkdown(response.templateContent, true);
    });

    return {
      editor,
      templateValue,
    };
  },
};
</script>

<template>
  <div id="editor"></div>
</template>

<style lang="css" module>
/* @import "ArticleEditor2.module.css"; */
</style>
