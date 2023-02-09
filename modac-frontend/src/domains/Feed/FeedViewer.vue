<template>
  <div :class="$style.viewer">
    <div id="viewer"></div>
  </div>
</template>

<script setup>
import { useFeedStore } from "../../stores/feed";
import { onMounted } from "vue"

import Viewer from '@toast-ui/editor/dist/toastui-editor-viewer';
import '@toast-ui/editor/dist/toastui-editor-viewer.css';

const store = useFeedStore();

const props = defineProps({
  feedModalSeq : Number,
});

onMounted(() => {
  // viewerSelector = document.querySelector("#viewer");
  let viewer = new Viewer({
    el: document.querySelector("#viewer"),
    height: "100%",
    initialValue: store.feeds[props.feedModalSeq].filepath, // Editor에 처음으로 적혀있는 글
  });
})


</script>

<style lang="css" module>
@import "FeedViewer.module.css";
</style>