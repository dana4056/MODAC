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
        <div v-if="myUserSeq == userSeq" class="w-full flex justify-end text-red-500">
          <button @click="article_delete(props.feedModalSeq)" class="flex text-sm items-start bg-transparent">
            <div class="h-full flex items-center">게시글 삭제</div>
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
              <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
            </svg>
          </button>
        </div>
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
            
            
            <div v-if="myUserSeq == userSeq" class="w-full flex justify-end">
              <button
              @click="comment_delete(comment.seq)" class="bg-transparent">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                  <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
                </svg>
              </button>
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
import articleAPI from "@/api/article.js";
import LikeButton from "./LikeButton.vue";
import FollowButton from "@/components/FollowButton.vue";
import Message from "vue-m-message";

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
  feedModalState : Boolean
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
    Message.info("댓글을 입력해주세요", {
      closable: true,
    });
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

const comment_delete = async (seq) => {
  await commentAPI.deleteComment(seq, props.feedModalSeq);
  commentList = comments;
}

const article_delete = (articleSeq) => {
  articleAPI.deleteArticle(articleSeq);
  // props.feedModalState.value = false;
}

onMounted(() => {
  resizeTextarea()
})


</script>

<style lang="css" module>
@import "FeedFooter.module.css";
</style>