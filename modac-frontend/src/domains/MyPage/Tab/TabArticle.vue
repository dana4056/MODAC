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
      <tr v-for="article in paginatedData" :key="article.seq">
        <td :class="$style.article_table_td">{{ article.seq + 1 }}</td>
        <td :class="$style.article_table_td">{{ article.registeredTime }}</td>
        <td :class="$style.article_table_td">
          <!-- {{ article.categoryName }} -->
          <span v-if="article.categoryName === '알고리즘'" :class="$style.article_table_td_category_algorithm">#알고리즘</span>
          <span v-if="article.categoryName === 'CS'" :class="$style.article_table_td_category_cs">#CS</span>
          <span v-if="article.categoryName === '개발'" :class="$style.article_table_td_category_programming">#개발</span>
          <span v-if="article.categoryName === '기획'" :class="$style.article_table_td_category_planning">#기획</span>
          <span v-if="article.categoryName === '기타'" :class="$style.article_table_td_category_ect">#기타</span>

        </td>
        <td :class="$style.article_table_title">
          <!-- 제목 누르면 해당 TIL를 볼 수 있는 화면 띄울 것 -->
          <!-- 눌렀을 때 모달을 띄울지, 탭 안에 띄울지, 새 창을 띄울지는 정해야 함 -->
          <a href="#"> {{ article.title }}</a>
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
    <div :class="$style.pagination_flex">
      <!-- <div v-for="item in paginatedData" :key="item.index"> {{ item.value }} </div> -->
      <button @click="backPage" :class="$style.pagination_prev" v-if="page != 1"> &lt;</button>
      <button
        :class="$style.pagination_number"
        v-for="item in Math.ceil(ArticleResponseList.length / perPage)"
        :key="item"
        @click="() => goToPage(item)"
      >
        {{ item }}
      </button>
      <button @click="nextPage" :class="$style.pagination_next" v-if="page != Math.ceil(ArticleResponseList.length / perPage)"> > </button>
    </div>
  </div>

</template>

<script setup>
import { ref, computed } from "vue";

const ArticleResponseList = [
  {
    seq: 0,
    title: "BFS에 대하여a;lsdlfk;ajskldfjlkasjdklfjaksldjklajsdklja;klsdj;klajskgljaksljdlfajsdlfjal;sjdgklajaskjdlf;ajsl;dfjlak;sjdfklja;sldjl;ajsl;jfa;lksjlasjd;lfajslkdfjal;sjdf;lajs;dlfj;alsjdf;lkajs;lfkjalskdf;ajsf",
    filepath: "String",
    registeredTime: "2023-01-23",
    publicType: "Integer",
    viewCount: 2,
    likeCount: 1,
    commentCount: 3,
    totalSecond: "String",
    user: {
        seq: "Integer",
        id: "String",
        nickname: "String",
        email: "String",
        password: "String",
        singleTheme: "String",
        totalSecond: "Integer",
        membership: {
            grade: "String"
        }
    },
    categoryName: "알고리즘"
  },
  {
    seq: 1,
    title: "운영체제에 대하여",
    filepath: "String",
    registeredTime: "2023-01-24",
    publicType: "Integer",
    viewCount: 6,
    likeCount: 5,
    commentCount: 1,
    totalSecond: "String",
    user: {
        seq: "Integer",
        id: "String",
        nickname: "String",
        email: "String",
        password: "String",
        singleTheme: "String",
        totalSecond: "Integer",
        membership: {
            grade: "String"
        }
    },
    categoryName: "CS"
  },
  {
    seq:2,
    title: "마이페이지 개발",
    filepath: "String",
    registeredTime: "2023-01-31",
    publicType: "Integer",
    viewCount: 5,
    likeCount: 3,
    commentCount: 0,
    totalSecond: "String",
    user: {
        seq: "Integer",
        id: "String",
        nickname: "String",
        email: "String",
        password: "String",
        singleTheme: "String",
        totalSecond: "Integer",
        membership: {
            grade: "String"
        }
    },
    categoryName: "개발"
  },
  {
    seq:3,
    title: "마이페이지 개발",
    filepath: "String",
    registeredTime: "2023-01-31",
    publicType: "Integer",
    viewCount: 5,
    likeCount: 3,
    commentCount: 0,
    totalSecond: "String",
    user: {
        seq: "Integer",
        id: "String",
        nickname: "String",
        email: "String",
        password: "String",
        singleTheme: "String",
        totalSecond: "Integer",
        membership: {
            grade: "String"
        }
    },
    categoryName: "기획"
  },
  {
    seq:4,
    title: "마이페이지 개발",
    filepath: "String",
    registeredTime: "2023-01-31",
    publicType: "Integer",
    viewCount: 5,
    likeCount: 3,
    commentCount: 0,
    totalSecond: "String",
    user: {
        seq: "Integer",
        id: "String",
        nickname: "String",
        email: "String",
        password: "String",
        singleTheme: "String",
        totalSecond: "Integer",
        membership: {
            grade: "String"
        }
    },
    categoryName: "기타"
  },
  {
    seq:5,
    title: "마이페이지 개발",
    filepath: "String",
    registeredTime: "2023-01-31",
    publicType: "Integer",
    viewCount: 5,
    likeCount: 3,
    commentCount: 0,
    totalSecond: "String",
    user: {
        seq: "Integer",
        id: "String",
        nickname: "String",
        email: "String",
        password: "String",
        singleTheme: "String",
        totalSecond: "Integer",
        membership: {
            grade: "String"
        }
    },
    categoryName: "개발"
  },
  {
    seq:6,
    title: "마이페이지 개발",
    filepath: "String",
    registeredTime: "2023-01-31",
    publicType: "Integer",
    viewCount: 5,
    likeCount: 3,
    commentCount: 0,
    totalSecond: "String",
    user: {
        seq: "Integer",
        id: "String",
        nickname: "String",
        email: "String",
        password: "String",
        singleTheme: "String",
        totalSecond: "Integer",
        membership: {
            grade: "String"
        }
    },
    categoryName: "개발"
  },
  {
    seq:7,
    title: "마이페이지 개발",
    filepath: "String",
    registeredTime: "2023-01-31",
    publicType: "Integer",
    viewCount: 5,
    likeCount: 3,
    commentCount: 0,
    totalSecond: "String",
    user: {
        seq: "Integer",
        id: "String",
        nickname: "String",
        email: "String",
        password: "String",
        singleTheme: "String",
        totalSecond: "Integer",
        membership: {
            grade: "String"
        }
    },
    categoryName: "개발"
  },
  {
    seq:8,
    title: "마이페이지 개발",
    filepath: "String",
    registeredTime: "2023-01-31",
    publicType: "Integer",
    viewCount: 5,
    likeCount: 3,
    commentCount: 0,
    totalSecond: "String",
    user: {
        seq: "Integer",
        id: "String",
        nickname: "String",
        email: "String",
        password: "String",
        singleTheme: "String",
        totalSecond: "Integer",
        membership: {
            grade: "String"
        }
    },
    categoryName: "개발"
  },
  {
    seq:9,
    title: "마이페이지 개발",
    filepath: "String",
    registeredTime: "2023-01-31",
    publicType: "Integer",
    viewCount: 5,
    likeCount: 3,
    commentCount: 0,
    totalSecond: "String",
    user: {
        seq: "Integer",
        id: "String",
        nickname: "String",
        email: "String",
        password: "String",
        singleTheme: "String",
        totalSecond: "Integer",
        membership: {
            grade: "String"
        }
    },
    categoryName: "개발"
  },
  {
    seq:10,
    title: "마이페이지 개발",
    filepath: "String",
    registeredTime: "2023-01-31",
    publicType: "Integer",
    viewCount: 5,
    likeCount: 3,
    commentCount: 0,
    totalSecond: "String",
    user: {
        seq: "Integer",
        id: "String",
        nickname: "String",
        email: "String",
        password: "String",
        singleTheme: "String",
        totalSecond: "Integer",
        membership: {
            grade: "String"
        }
    },
    categoryName: "개발"
  },
  {
    seq:11,
    title: "마이페이지 개발",
    filepath: "String",
    registeredTime: "2023-01-31",
    publicType: "Integer",
    viewCount: 5,
    likeCount: 3,
    commentCount: 0,
    totalSecond: "String",
    user: {
        seq: "Integer",
        id: "String",
        nickname: "String",
        email: "String",
        password: "String",
        singleTheme: "String",
        totalSecond: "Integer",
        membership: {
            grade: "String"
        }
    },
    categoryName: "개발"
  },
  {
    seq:12,
    title: "마이페이지 개발",
    filepath: "String",
    registeredTime: "2023-01-31",
    publicType: "Integer",
    viewCount: 5,
    likeCount: 3,
    commentCount: 0,
    totalSecond: "String",
    user: {
        seq: "Integer",
        id: "String",
        nickname: "String",
        email: "String",
        password: "String",
        singleTheme: "String",
        totalSecond: "Integer",
        membership: {
            grade: "String"
        }
    },
    categoryName: "개발"
  },
]

let page = ref(1);
const perPage = 10;
const data = ArticleResponseList;

const paginatedData = computed(() =>
  data.slice((page.value - 1) * perPage, page.value * perPage)
);

const nextPage = () => {
  if (page.value !== Math.ceil(data.length / perPage)) {
    page.value += 1;
  }
};

const backPage = () => {
  if (page.value !== 1) {
    page.value -= 1;
  }
};

const goToPage = (numPage) => {
  page.value = numPage;
};

</script>

<style lang="css" module>
  @import "./TabArticle.module.css";
</style>