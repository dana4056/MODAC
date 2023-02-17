<template>

  <div :class="$style.comment_view">
    <div class="flex justify-end">
      <LikeButton></LikeButton>
    </div>
    <hr class="mt-5">
    <div>
      <div class="flex font-semibold text-xl items-center gap-2 bg-gray-100 py-6 px-4 mb-5">
        {{ userNickname }}
        <div :class="$style.comment_membership" class="h-fit">
          {{ userMembership }}
        </div>
        <FollowButton v-if="myUserSeq != userSeq" class="text-sm rounded-lg py-1">
        </FollowButton>
      </div>
    </div>
    <div>댓글 <span class="font-semibold text-red-600"> {{ commentListCount }}</span>개</div>

    <div :class="$style.comment_add">
      <textarea placeholder="댓글을 작성하세요" 
                :class="$style.comment_textarea" 
                ref="textarea"
                v-model="comment"
                required
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

import { ref, onMounted, computed } from "vue"
import { useFeedStore } from "@/stores/feed.js";
import { useCommentStore } from "@/stores/comment.js";
import { useUserStore } from "@/stores/user.js";
import { storeToRefs } from "pinia";

import commentAPI from "@/api/comment.js";
import LikeButton from "./LikeButton.vue";
import FollowButton from "@/components/FollowButton.vue";

const commentStore = useCommentStore();
const feedStore = useFeedStore();
const userStore = useUserStore();
const { comments } = storeToRefs(commentStore);
const { article } = storeToRefs(feedStore);
const { loginUser } = storeToRefs(userStore);
// const { isFollowing } = storeToRefs(userStore);
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
const userSeq = article.value.user.seq;
const myUserSeq = loginUser.value.seq;

const textarea = ref(null);
const comment = ref("");
const resizeTextarea = () => {
  textarea.value.style.height = 'auto'
  textarea.value.style.height = `${textarea.value.scrollHeight}px`
}

const comment_add = async () => {
  if (comment.value === "") {
    alert("댓글을 입력해주세요.")
  }
  else {
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
  }
}

onMounted(() => {
  resizeTextarea()
})


</script>

<style lang="css" module>
@import "FeedFooter.module.css";
</style>