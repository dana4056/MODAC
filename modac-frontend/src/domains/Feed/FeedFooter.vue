<template>

  <div :class="$style.comment_view">
    <div class="flex justify-end">
      <LikeButton></LikeButton>
    </div>
    <hr class="mt-5">
    <div>
      <div class="flex font-semibold text-xl items-center gap-2 bg-gray-100 py-6 px-4 mb-5">
        {{ feedStore.feeds[feedModalSeq].user.nickname }}
        <div :class="$style.comment_membership" class="h-fit">
          {{ feedStore.feeds[feedModalSeq].user.membershipLevel }}
        </div>
        <FollowButton class="text-sm rounded-lg py-1"></FollowButton>
      </div>
    </div>
    <div>댓글 <span class="font-semibold text-red-600"> {{ commentTotalCnt }}</span>개</div>

    <div :class="$style.comment_add">
      <textarea placeholder="댓글을 작성하세요" 
                :class="$style.comment_textarea" 
                ref="textarea"
                v-model="comment"
                @input="resizeTextarea"></textarea>
      <button type="button" 
              :class="$style.add_button"
              @click="comment_add"
              >
          댓글 작성하기 →
      </button>
    </div>

    <!-- 댓글 목록 -->
    <div v-for="comment in commentList" :key="comment.seq"
        :class="$style.comment_list">

      <div :class="$style.comment_div">
        <div :class="$style.comment_user">

          <div :class="$style.comment_nickname">
            {{comment.user.nickname}}
          </div>

          <div :class="$style.comment_membership">
            {{ comment.user.membershipLevel }}
          </div>

          <div :class="$style.comment_time">
            {{comment.registeredTime}}
          </div>
          
        </div>

        <div :class="$style.comment_content">
          {{comment.content}}
        </div>
      </div>
    
    </div>
  </div>

</template>

<script setup>

import { useCommentStore } from "../../stores/comment.js";
import { useFeedStore } from "../../stores/feed.js";
import { ref, onMounted } from "vue"
import LikeButton from "./LikeButton.vue";
import FollowButton from "@/components/FollowButton.vue";

const store = useCommentStore();
const feedStore = useFeedStore();

const props = defineProps({
  feedModalSeq : Number,
});

let commentList = store.getComments(props.feedModalSeq);
let commentTotalCnt = commentList.length;

// console.log("commentList", commentList);
// console.log("commentTotalCnt", commentTotalCnt);

const textarea = ref(null);
const comment = ref("");
const resizeTextarea = () => {
  textarea.value.style.height = 'auto'
  textarea.value.style.height = `${textarea.value.scrollHeight}px`
}

const comment_add = () => {
  // comment.value를 댓글 작성 시 보내면 됨!
  console.log(comment.value);

  // 댓글 작성 후 commentList = store.getComments(props.feedModalSeq); 를 한 번 더 해서
  // 추가된 댓글까지 보이게 업데이트 해주면 될듯?
}

onMounted(() => {
  resizeTextarea()
})

</script>

<style lang="css" module>
@import "FeedFooter.module.css";
</style>