<template>
  <FeedListItem
      :class="$style.feed_item"
      v-for="feed in computedFeedList"
      :key="feed.seq"
      :feed="feed"
      @click="openFeedModal(feed.seq)"
      />

  <Teleport to="body">
    <Modal
      :closeModal="closeFeedModal"
      v-if="feedModalState"
    >
      <div class="w-full flex justify-end">
        <button
        @click="closeFeedModal('cancle')"
        id="cancle"
        :class="$style.add_room_button_exit">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
            <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
          </svg>
        </button>
      </div>

      <OverflowDiv :class="$style.modal_div">
        <!-- <div :class="$style.modal_div"> -->
        
          <div :class="$style.modal_item">
            <div :class="$style.modal_header">
              <div :class="$style.modal_title_img">
                <h1 :class="$style.modal_title" id="title">
                  {{ feedArticle.title }}
                </h1>
                <div class="flex justify-between flex-wrap" style="background-image: linear-gradient( rgba(0, 0, 0, 0), rgba(0, 0, 0, 1) )">
                  <div :class="$style.modal_title_info_left">
                    <div class="text-gray-400">
                    By
                    </div>
                    <div>
                    {{ feedArticle.user.nickname }}
                    </div>

                  </div>
                  <div :class="$style.modal_title_info_right">
                    <div :class="$style.modal_icon">
                      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-4 h-4">
                        <path d="M10 12.5a2.5 2.5 0 100-5 2.5 2.5 0 000 5z" />
                        <path fill-rule="evenodd" d="M.664 10.59a1.651 1.651 0 010-1.186A10.004 10.004 0 0110 3c4.257 0 7.893 2.66 9.336 6.41.147.381.146.804 0 1.186A10.004 10.004 0 0110 17c-4.257 0-7.893-2.66-9.336-6.41zM14 10a4 4 0 11-8 0 4 4 0 018 0z" clip-rule="evenodd" />
                      </svg>
                      {{ feedArticle.viewCount }}
                    </div>
                    <div :class="$style.section_divider">|</div>
                    <div :class="$style.modal_icon">
                      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-4 h-4">
                        <path d="M9.653 16.915l-.005-.003-.019-.01a20.759 20.759 0 01-1.162-.682 22.045 22.045 0 01-2.582-1.9C4.045 12.733 2 10.352 2 7.5a4.5 4.5 0 018-2.828A4.5 4.5 0 0118 7.5c0 2.852-2.044 5.233-3.885 6.82a22.049 22.049 0 01-3.744 2.582l-.019.01-.005.003h-.002a.739.739 0 01-.69.001l-.002-.001z" />
                      </svg>
                      {{ feedArticle.likeCount }}
                    </div>
                    <div :class="$style.section_divider">|</div>
                    <div :class="$style.modal_icon">
                      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-4 h-4">
                        <path fill-rule="evenodd" d="M10 2c-2.236 0-4.43.18-6.57.524C1.993 2.755 1 4.014 1 5.426v5.148c0 1.413.993 2.67 2.43 2.902 1.168.188 2.352.327 3.55.414.28.02.521.18.642.413l1.713 3.293a.75.75 0 001.33 0l1.713-3.293a.783.783 0 01.642-.413 41.102 41.102 0 003.55-.414c1.437-.231 2.43-1.49 2.43-2.902V5.426c0-1.413-.993-2.67-2.43-2.902A41.289 41.289 0 0010 2zM6.75 6a.75.75 0 000 1.5h6.5a.75.75 0 000-1.5h-6.5zm0 2.5a.75.75 0 000 1.5h3.5a.75.75 0 000-1.5h-3.5z" clip-rule="evenodd" />
                      </svg>
                      {{ feedArticle.commentCount }}
                    </div>
                    <div :class="$style.section_divider">|</div>
                    <div>
                      {{ feedArticle.registeredTime.substr(0,10) + " " + feedArticle.registeredTime.substr(11,18) }}
                    </div>
                    <div :class="$style.section_divider">|</div>
                    <div>
                      <span v-if="feedArticle.categoryName === '알고리즘'" :class="$style.article_table_td_category_algorithm">#알고리즘</span>
                      <span v-if="feedArticle.categoryName === 'CS'" :class="$style.article_table_td_category_cs">#CS</span>
                      <span v-if="feedArticle.categoryName === '개발'" :class="$style.article_table_td_category_programming">#개발</span>
                      <span v-if="feedArticle.categoryName === '면접'" :class="$style.article_table_td_category_planning">#면접</span>
                      <span v-if="feedArticle.categoryName === '공통'" :class="$style.article_table_td_category_ect">#공통</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <FeedViewer :feedModalSeq="feedModalSeq"></FeedViewer>

          <FeedFooter :feedModalSeq="feedModalSeq" :commentList="commentList" :commentCount="feedArticle.commentCount" :updateFeedModal="updateFeedModal"></FeedFooter>

          <div class="fixed bottom-16 right-[23%] bg-white border shadow-xl rounded-full p-3">
            <a href="#title">
              <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 6.75L12 3m0 0l3.75 3.75M12 3v18" />
              </svg>
            </a>
          </div>
        <!-- </div> -->
      </OverflowDiv>
    </Modal>
  </Teleport>
</template>

<script setup>
import FeedListItem from "@/domains/Feed/FeedListItem.vue"
import FeedViewer from "@/domains/Feed/FeedViewer.vue"
import FeedFooter from "@/domains/Feed/FeedFooter.vue"
import OverflowDiv from "@/components/OverflowDiv.vue"

import { computed, ref, onMounted } from "vue"
import { useUserStore } from "@/stores/user.js";
import { useFeedStore } from "@/stores/feed";
import { useCommentStore } from "@/stores/comment.js";
import { storeToRefs } from "pinia";
import userAPI from "@/api/user.js";
import articleAPI from "@/api/article.js";
import commentAPI from "@/api/comment.js";
import Modal from "@/components/Modal.vue";

const userStore = useUserStore();
const feedStore = useFeedStore();
const commentStore = useCommentStore();

const { loginUser } = storeToRefs(userStore);
const { articles } = storeToRefs(feedStore);
const { article } = storeToRefs(feedStore);
const { comments } = storeToRefs(commentStore);
const isFollowing = ref(false);

const payload = {
  usersSeq : userStore.loginUser.seq,
  offset : 1, 
  limit: 100,
}
articleAPI.findArticleByFollowing(payload);

let feeds = articles.value.articleInfoList;
let feedArticle = article.value;
let commentList = comments.value;

onMounted(() => {
  feeds = articles.value.articleInfoList;
  feedArticle = article.value;
  commentList = comments.value;
})
// console.log("commentList", commentList);

// store에서 불러 와야 할 아이들
const computedFeedList = computed(() => {
  return articles.value.articleInfoList;
})


const feedModalState = ref(false);
let feedModalSeq = ref(null);

const closeFeedModal = (element) => {
  const backdropElement = ref();
  const cancleElement = ref();

  backdropElement.value = document.querySelector("#backdrop");
  cancleElement.value = document.querySelector("#cancle")

  if (backdropElement.value === event.target 
      || cancleElement.value === event.target) {
      feedModalState.value = false;
    }
  if (element === "cancle") {
    feedModalState.value = false;
  }
};

const openFeedModal = async (seq) => {
  // 여기서 seq를 이용해서 하나의 게시글을 조회하는 걸로 바꿔서 구현
  // console.log("1111article", article);

  await articleAPI.updateViewCount(seq); // 2
  await articleAPI.findArticle(seq); // 2
  feedArticle = article;
  // console.log("3333feedArticle.value", feedArticle.value.viewCount);

  // feedArticle = article.value;

  feedModalSeq.value = seq;
  // feedModalSeq.value = seq;
  feedModalState.value = true;
  feeds = articles.value.articleInfoList;

  commentList = comments;
  await commentAPI.findCommentList(feedModalSeq.value);

  commentList = comments;


  console.log("from", loginUser.value.seq);
  console.log("to", feedArticle.value.user.seq);
  const payload = {
    fromSeq : loginUser.value.seq,
    toSeq : feedArticle.value.user.seq
  }
  await userAPI.isFollowing(payload)
  
};

const updateFeedModal = async (seq) => {
  // 여기서 seq를 이용해서 하나의 게시글을 조회하는 걸로 바꿔서 구현
  // console.log("1111article", article);

  // await articleAPI.updateViewCount(seq); // 2
  await articleAPI.findArticle(seq); // 2
  feedArticle = article;
  console.log("3333feedArticle.value", feedArticle.value.viewCount);

  // feedArticle = article.value;

  feedModalSeq.value = seq;
  // feedModalSeq.value = seq;
  feedModalState.value = true;
  feeds = articles.value.articleInfoList;

  commentList = comments;
  await commentAPI.findCommentList(feedModalSeq.value);

  commentList = comments;
  
  console.log("commentList", commentList);
};

</script>

<style lang="css" module>
@import "./FeedList.module.css";

</style>