<template lang="">
  <div :class="$style.article_flex">
    <table :class="$style.article_table">
      <!-- 헤더 부분 -->
      <tr>
        <th :class="$style.article_table_th" width="60px">글번호</th>
        <th :class="$style.article_table_th" width="100px">작성일자</th>
        <th :class="$style.article_table_th" width="100px">카테고리</th>
        <th :class="$style.article_table_th">제목</th>
        <th :class="$style.article_table_th" width="60px">좋아요</th>
        <th :class="$style.article_table_th" width="60px">조회수</th>
        <th :class="$style.article_table_th" width="60px">댓글</th>
      </tr>

      <!-- 표 내용 부분 -->
      <!-- <tr v-for="(article, index) in paginatedData" :key="article.seq" :index="index"> -->
      <tr v-for="(article, index) in myArticles" :key="article.seq" :index="index">
        <td :class="$style.article_table_td">{{ index + 1 }}</td>
        <td :class="$style.article_table_td">{{ article.registeredTime.substr(0,10) }}</td>
        <td :class="$style.article_table_td">
          <span v-if="article.categoryName === '알고리즘'" :class="$style.article_table_td_category_algorithm">#알고리즘</span>
          <span v-if="article.categoryName === 'CS'" :class="$style.article_table_td_category_cs">#CS</span>
          <span v-if="article.categoryName === '개발'" :class="$style.article_table_td_category_programming">#개발</span>
          <span v-if="article.categoryName === '면접'" :class="$style.article_table_td_category_planning">#면접</span>
          <span v-if="article.categoryName === '공통'" :class="$style.article_table_td_category_ect">#공통</span>

        </td>
        <td @click="openFeedModal(article.seq)" :class="$style.article_table_title">
          <span> {{ article.title }}</span>
        </td>
        <td :class="$style.article_table_td">
          <div :class="$style.article_table_with_icon">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-4 h-4">
              <path d="M9.653 16.915l-.005-.003-.019-.01a20.759 20.759 0 01-1.162-.682 22.045 22.045 0 01-2.582-1.9C4.045 12.733 2 10.352 2 7.5a4.5 4.5 0 018-2.828A4.5 4.5 0 0118 7.5c0 2.852-2.044 5.233-3.885 6.82a22.049 22.049 0 01-3.744 2.582l-.019.01-.005.003h-.002a.739.739 0 01-.69.001l-.002-.001z" />
            </svg>
            {{ article.likeCount }}
          </div>
        </td>
        <td :class="$style.article_table_td">
          <div :class="$style.article_table_with_icon">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-4 h-4">
              <path d="M10 12.5a2.5 2.5 0 100-5 2.5 2.5 0 000 5z" />
              <path fill-rule="evenodd" d="M.664 10.59a1.651 1.651 0 010-1.186A10.004 10.004 0 0110 3c4.257 0 7.893 2.66 9.336 6.41.147.381.146.804 0 1.186A10.004 10.004 0 0110 17c-4.257 0-7.893-2.66-9.336-6.41zM14 10a4 4 0 11-8 0 4 4 0 018 0z" clip-rule="evenodd" />
            </svg>
            {{ article.viewCount }}
          </div>
        </td>
        <td :class="$style.article_table_td">
          <div :class="$style.article_table_with_icon">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-4 h-4">
              <path fill-rule="evenodd" d="M10 2c-2.236 0-4.43.18-6.57.524C1.993 2.755 1 4.014 1 5.426v5.148c0 1.413.993 2.67 2.43 2.902 1.168.188 2.352.327 3.55.414.28.02.521.18.642.413l1.713 3.293a.75.75 0 001.33 0l1.713-3.293a.783.783 0 01.642-.413 41.102 41.102 0 003.55-.414c1.437-.231 2.43-1.49 2.43-2.902V5.426c0-1.413-.993-2.67-2.43-2.902A41.289 41.289 0 0010 2zM6.75 6a.75.75 0 000 1.5h6.5a.75.75 0 000-1.5h-6.5zm0 2.5a.75.75 0 000 1.5h3.5a.75.75 0 000-1.5h-3.5z" clip-rule="evenodd" />
            </svg>
            {{ article.commentCount }}
          </div>
        </td>
      </tr>

    </table>

    <!-- 페이지네이션 -->
    <!-- <div :class="$style.pagination_flex">
      <button @click="backPage" :class="$style.pagination_prev" v-if="page != 1"> &lt;</button>
      <button
        :class="$style.pagination_number"
        v-for="item in Math.ceil(data.length / perPage)"
        :key="item"
        @click="() => goToPage(item)"
      >
        {{ item }}
      </button>
      <button @click="nextPage" :class="$style.pagination_next" v-if="page != Math.ceil(ArticleResponseList.length / perPage)"> > </button>
    </div> -->



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
                  {{ myArticle.title }}
                </h1>
                <div class="flex justify-between flex-wrap" style="background-image: linear-gradient( rgba(0, 0, 0, 0), rgba(0, 0, 0, 1) )">
                  <div :class="$style.modal_title_info_left">
                    <div class="text-gray-400">
                    By
                    </div>
                    <div>
                    {{ myArticle.user.nickname }}
                    </div>

                  </div>
                  <div :class="$style.modal_title_info_right">
                    <div :class="$style.modal_icon">
                      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-4 h-4">
                        <path d="M10 12.5a2.5 2.5 0 100-5 2.5 2.5 0 000 5z" />
                        <path fill-rule="evenodd" d="M.664 10.59a1.651 1.651 0 010-1.186A10.004 10.004 0 0110 3c4.257 0 7.893 2.66 9.336 6.41.147.381.146.804 0 1.186A10.004 10.004 0 0110 17c-4.257 0-7.893-2.66-9.336-6.41zM14 10a4 4 0 11-8 0 4 4 0 018 0z" clip-rule="evenodd" />
                      </svg>
                      {{ myArticle.viewCount }}
                    </div>
                    <div :class="$style.section_divider">|</div>
                    <div :class="$style.modal_icon">
                      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-4 h-4">
                        <path d="M9.653 16.915l-.005-.003-.019-.01a20.759 20.759 0 01-1.162-.682 22.045 22.045 0 01-2.582-1.9C4.045 12.733 2 10.352 2 7.5a4.5 4.5 0 018-2.828A4.5 4.5 0 0118 7.5c0 2.852-2.044 5.233-3.885 6.82a22.049 22.049 0 01-3.744 2.582l-.019.01-.005.003h-.002a.739.739 0 01-.69.001l-.002-.001z" />
                      </svg>
                      {{ myArticle.likeCount }}
                    </div>
                    <div :class="$style.section_divider">|</div>
                    <div :class="$style.modal_icon">
                      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-4 h-4">
                        <path fill-rule="evenodd" d="M10 2c-2.236 0-4.43.18-6.57.524C1.993 2.755 1 4.014 1 5.426v5.148c0 1.413.993 2.67 2.43 2.902 1.168.188 2.352.327 3.55.414.28.02.521.18.642.413l1.713 3.293a.75.75 0 001.33 0l1.713-3.293a.783.783 0 01.642-.413 41.102 41.102 0 003.55-.414c1.437-.231 2.43-1.49 2.43-2.902V5.426c0-1.413-.993-2.67-2.43-2.902A41.289 41.289 0 0010 2zM6.75 6a.75.75 0 000 1.5h6.5a.75.75 0 000-1.5h-6.5zm0 2.5a.75.75 0 000 1.5h3.5a.75.75 0 000-1.5h-3.5z" clip-rule="evenodd" />
                      </svg>
                      {{ myArticle.commentCount }}
                    </div>
                    <div :class="$style.section_divider">|</div>
                    <div>
                      {{ myArticle.registeredTime.substr(0,10) + " " + myArticle.registeredTime.substr(11,18) }}
                    </div>
                    <div :class="$style.section_divider">|</div>
                    <div>
                      <span v-if="myArticle.categoryName === '알고리즘'" :class="$style.article_table_td_category_algorithm">#알고리즘</span>
                      <span v-if="myArticle.categoryName === 'CS'" :class="$style.article_table_td_category_cs">#CS</span>
                      <span v-if="myArticle.categoryName === '개발'" :class="$style.article_table_td_category_programming">#개발</span>
                      <span v-if="myArticle.categoryName === '면접'" :class="$style.article_table_td_category_planning">#면접</span>
                      <span v-if="myArticle.categoryName === '공통'" :class="$style.article_table_td_category_ect">#공통</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <FeedViewer :feedModalSeq="feedModalSeq"></FeedViewer>

          <FeedFooter :feedModalSeq="feedModalSeq" :commentList="commentList" :commentCount="myArticle.commentCount" :updateFeedModal="updateFeedModal"></FeedFooter>

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
</div>

</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import { useUserStore } from '@/stores/user';
import { useStatsStore } from '@/stores/statistics';
import { useFeedStore } from "@/stores/feed";
import { useCommentStore } from "@/stores/comment.js";
import { storeToRefs } from "pinia"
import articleAPI from "@/api/article.js";
import commentAPI from "@/api/comment.js";
import FeedViewer from "@/domains/Feed/FeedViewer.vue"
import FeedFooter from "@/domains/Feed/FeedFooter.vue"
import OverflowDiv from "@/components/OverflowDiv.vue"
import Modal from "@/components/Modal.vue";

const userStore = useUserStore();
const statsStore = useStatsStore();
const feedStore = useFeedStore();
const commentStore = useCommentStore();
const { userArticles } = storeToRefs(feedStore);
const { article } = storeToRefs(feedStore);
const { comments } = storeToRefs(commentStore);

const { loginUser } = storeToRefs(userStore);

statsStore.api.getStatistics(loginUser.value.seq);

const payload = {
  usersSeq : userStore.loginUser.seq,
  offset : 1, 
  limit: 20,
}

articleAPI.findArticleByUsersSeq(payload);

// store에서 불러 와야 할 아이들
const myArticles = computed(() => {
  // articleAPI.findArticleByUsersSeq(payload);
  console.log("userArticles.value.articleInfoList", userArticles.value.articleInfoList);
  return userArticles.value.articleInfoList;
})

// let myArticles = userArticles.value.articleInfoList;
let myArticle = article.value;
let commentList = comments.value;

// console.table(myArticles.value);
// console.log(myArticles);

// onMounted(() => {
  // myArticles.value = userArticles.value.articleInfoList;
  // myArticle = article.value;
  // commentList = comments.value;
// })

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
  await articleAPI.updateViewCount(seq); // 2
  await articleAPI.findArticle(seq); // 2
  myArticle = article;

  feedModalSeq.value = seq;
  feedModalState.value = true;
  myArticles.value = userArticles.value.articleInfoList;

  commentList = comments;
  await commentAPI.findCommentList(feedModalSeq.value);

  commentList = comments;
};

const updateFeedModal = async (seq) => {
  await articleAPI.findArticle(seq); // 2
  myArticle = article;
  console.log("3333myArticle.value", myArticle.value.viewCount);

  feedModalSeq.value = seq;
  feedModalState.value = true;
  myArticles.value = userArticles.value.articleInfoList;

  commentList = comments;
  await commentAPI.findCommentList(feedModalSeq.value);

  commentList = comments;
  console.log("commentList", commentList);
};

// const ArticleResponseList = ref([]);
// const articleResponseList = computedFeedList;

// let page = ref(1);
// const perPage = 10;
// const data = articleResponseList.value;

// const paginatedData = computed(() =>
//   data.slice((page.value - 1) * perPage, page.value * perPage)
// );

// const nextPage = () => {
//   if (page.value !== Math.ceil(data.length / perPage)) {
//     page.value += 1;
//   }
// };

// const backPage = () => {
//   if (page.value !== 1) {
//     page.value -= 1;
//   }
// };

// const goToPage = (numPage) => {
//   page.value = numPage;
// };

</script>

<style lang="css" module>
  @import "./TabArticle.module.css";
</style>