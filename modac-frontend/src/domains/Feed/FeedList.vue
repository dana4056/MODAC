<template>
  <FeedListItem
      :class="$style.feed_item"
      v-for="feed in computedFeedList"
      :key="feed.seq"
      :feed="feed"
      />
</template>

<script setup>
import FeedListItem from "./FeedListItem.vue"
import { useFeedStore } from "../../stores/feed";
import { computed } from "vue"
const store = useFeedStore()

// 화면 생성 시 실행되어야 할 함수
store.getFeeds()

// store에서 불러 와야 할 아이들
const computedFeedList = computed(() => {
  // console.log("store.feeds", store.feeds);
  return store.feeds
})


</script>

<style lang="css" module>
@import "./FeedList.module.css";

</style>
