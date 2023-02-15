<template>

  <div :class="$style.comment_view">
    <div class="flex justify-end">
      <LikeButton></LikeButton>
    </div>
    <hr class="mt-5">
    <div>
      <div class="flex font-semibold text-xl items-center gap-2 bg-gray-100 py-6 px-4 mb-5">
        <!-- {{ article.value.user.nickname }} -->
        {{ userNickname }}
        <div :class="$style.comment_membership" class="h-fit">
          <!-- {{ article.value.user.membershipLevel }} -->
          {{ userMembership }}
        </div>
        <FollowButton class="text-sm rounded-lg py-1"></FollowButton>
      </div>
    </div>
    <div>댓글 <span class="font-semibold text-red-600"> {{ commentListCount }}</span>개</div>

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
    <div :class="$style.comment_list_div">
      <div v-for="comment in commentList" :key="comment.seq"
          :class="$style.comment_list">

        <div :class="$style.comment_div">
          <div :class="$style.comment_user">

            <div :class="$style.comment_nickname">
              {{ comment.user.nickname }}
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
  </div>

</template>

<script setup>

import { useFeedStore } from "@/stores/feed.js";
import { ref, onMounted, computed } from "vue"
import LikeButton from "./LikeButton.vue";
import FollowButton from "@/components/FollowButton.vue";
import { useCommentStore } from "@/stores/comment.js";
import commentAPI from "@/api/comment.js";
import { storeToRefs } from "pinia";
import { useUserStore } from "@/stores/user.js";

const commentStore = useCommentStore();
const feedStore = useFeedStore();
const userStore = useUserStore();
const { comments } = storeToRefs(commentStore);
const { article } = storeToRefs(feedStore);
let feedArticle = article.value;

const props = defineProps({
  feedModalSeq : Number,
  commentList : Object,
  commentCount : Number,
  updateFeedModal :Function,
});

let commentList = comments;

const commentListCount = computed(() => {
  return commentList.value.length;
})

const userNickname = article.value.user.nickname;
const userMembership = article.value.user.membershipLevel;

const textarea = ref(null);
const comment = ref("");
const resizeTextarea = () => {
  textarea.value.style.height = 'auto'
  textarea.value.style.height = `${textarea.value.scrollHeight}px`
}

const comment_add = async () => {
  // comment.value를 댓글 작성 시 보내면 됨!
  const payload = {
    articlesSeq : props.feedModalSeq,
    usersSeq : userStore.loginUser.seq,
    content : comment.value
  }

  commentAPI.postComment(payload);
  comment.value = "";

  await props.updateFeedModal();
  commentList = comments;
  feedArticle.commentCount = feedArticle.commentCount + 1;
  console.log("feedArticle.commentCount 업뎃됐니?!?!?", feedArticle.commentCount)
  
  console.log(commentList);
}

onMounted(() => {
  resizeTextarea()
})

</script>

<style lang="css" module>
@import "FeedFooter.module.css";
</style>