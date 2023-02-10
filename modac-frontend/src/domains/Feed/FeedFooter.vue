<template>

  <div :class="$style.commentAdd">
    댓글 작성란

  </div>
      <br>
  <div :class="$style.commentView">
    <!-- 댓글 목록 -->
    <div> {{ commentTotalCnt }} 개의 댓글</div>
    <br>
    <div v-for="comment in commentList" :key="comment.seq">
      <!-- <div> {{comment}} </div> -->
      
      <div :class="$style.comment_div">
        <div :class="$style.comment_user">
          <div>{{comment.user.nickname}}</div>
          <div>{{comment.user.membershipLevel}}</div>
          <div>{{comment.registeredTime}}</div>
        </div>
        <div>{{comment.content}}</div>
      </div>
      <br>
    
    </div>
  </div>

  
</template>

<script setup>

import { useCommentStore } from "../../stores/comment.js";
// import { computed, onMounted } from "vue"

const store = useCommentStore();

const props = defineProps({
  feedModalSeq : Number,
});

let commentList = store.getComments(props.feedModalSeq);
let commentTotalCnt = commentList.length;

console.log("commentList", commentList);
console.log("commentTotalCnt", commentTotalCnt);


// const computedCommentList = computed(() => {
//   return store.comments;
// })
// console.log("store.comments", store.comments);
// console.log("store.commentTotalCnt", store.commentTotalCnt);
// console.log("computedCommentList", computedCommentList.value);
// console.log("computedCommentList.value.length", computedCommentList.value.length);

// onMounted(() => {
// })

</script>

<style lang="css" module>
@import "FeedFooter.module.css";
</style>