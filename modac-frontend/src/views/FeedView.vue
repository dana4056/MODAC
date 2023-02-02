<script setup>
import FeedList from '@/domains/Feed/FeedList.vue'
import FeedViewer from '@/domains/Feed/FeedViewer.vue';
</script>

<template>
  <div :class="$style.feed_container">
    <div :class="$style.feed_container_above">
      <div :class="$style.title">
        <h1>TIL 피드</h1>
      </div>
    </div>

    <div :class="$style.feed_container_below">
      <div :class="$style.feed_list">
        <FeedList/>
      </div>
      <div :class="$style.feed_viewer">
        <FeedViewer/>
      </div>
    </div>
  </div>
</template>

<style lang="css" module>
@import "FeedView.module.css"
</style>