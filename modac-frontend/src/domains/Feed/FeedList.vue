<template>
  <div>
    <h1 class="my_color my_size">나는 피드 아이템</h1>
    <FeedListItem
        :class="$style.feed_item"
        v-for="(feed, index) in computedFeedList"
        :key="index"
        :feed="feed"
        />
  </div>
</template>

<script setup>
import FeedListItem from "./FeedListItem.vue"
import { useFeedStore } from "../../stores/feed";
import { computed } from "vue"
const store = useFeedStore()



// store에서 불러 와야 할 아이들
const computedFeedList = computed(() => {
  return store.feeds
})

// 화면 생성 시 실행되어야 할 함수
store.getFeeds()

</script>

<style lang="css" module>
@import "./FeedList.module.css"

</style>
