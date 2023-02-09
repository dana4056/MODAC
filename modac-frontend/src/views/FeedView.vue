<script setup>
import FeedList from '@/domains/Feed/FeedList.vue'
// import FeedViewer from '@/domains/Feed/FeedViewer.vue';

</script>

<template>

  <div :class="$style.article_container_above">
    <div :class="$style.flex_row_div">
      <div :class="$style.flex_row">
        <h1 :class="$style.page_title">TIL 피드</h1>
      </div>
    </div>

    <div :class="$style.flex_row_div_edit">

        <div :class="$style.article_container_below">
          <div :class="$style.article_list">
            <FeedList />
          </div>
          <!-- <div :class="$style.editor">
            <FeedViewer/>
          </div> -->
        </div>

    </div>


<!-- 
    <div :class="$style.feed_container_below">
      <div :class="$style.feed_list">
        <FeedList/>
      </div>
      <div :class="$style.feed_viewer">
        <FeedViewer/>
      </div>
    </div> -->





    
  </div>

</template>

<style lang="css" module>
@import "FeedView.module.css";
</style>