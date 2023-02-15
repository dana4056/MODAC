import { reactive, ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";


export const useFeedStore = defineStore("feed", () => {
  let feeds = reactive([]);
  const userId = ref(null);
  const backendServerUrl = ref("localhost");
  let feedTotalArticleCnt = 0;
  let totalPageCnt = 0;
  // let originalArticles = [];
  
  
  // {feed_seq: 1, status: 1}, 
  // {feed_seq: 2, status: 2}, 
  // {feed_seq: 3, status: 2}, 
  // {feed_seq: 4, status: 2}, 
  // {feed_seq: 5, status: 2}, 
  // {feed_seq: 6, status: 2}, 
  // {feed_seq: 7, status: 2}
  
  const articles = ref([]);
  const article = ref();

  // const articles =  [
  //   {
  //     seq: 0,
  //     title: "프론트엔드 면접 질문 공부",
  //     filepath: "# Hello Markdown Editor! \nskdjflskdf\nsdfsdfsdf\n```\nconsole.log('hello world');\n```\n\nhello",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "면접"
  //   },
  //   {
  //     seq: 1,
  //     title: "웹 브라우저 동작 원리",
  //     filepath: "# 컨텐츠 내용~",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "CS"
  //   },
  //   {
  //     seq: 2,
  //     title: "[ React ] 정말 쉽다! 카카오 소셜 로그인 프론트에서 이해하고 구현하기",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "개발"
  //   },
  //   {
  //     seq: 3,
  //     title: "Velog 비슷한 마크다운 게시판 만들기",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "개발"
  //   },
  //   {
  //     seq: 4,
  //     title: "[알고리즘/Swift] DFS & BFS 알고리즘",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "알고리즘"
  //   },
  //   {
  //     seq: 5,
  //     title: "[Java] JVM",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "CS"
  //   },
  //   {
  //     seq: 6,
  //     title: "마라톤 같았던 나의 2022년 회고",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "공통"
  //   },
  //   {
  //     seq: 7,
  //     title: "selenium 기본 사용법",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "개발"
  //   },
  //   {
  //     seq: 8,
  //     title: "[JAVA] 정렬 및 비교",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "알고리즘"
  //   },
  //   {
  //     seq: 9,
  //     title: "백준 2563번 색종이",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "알고리즘"
  //   },
  //   {
  //     seq: 10,
  //     title: "SWEA 1204번 최빈수 구하기",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "알고리즘"
  //   },
  //   {
  //     seq: 11,
  //     title: "백준 2750번 수 정렬하기",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "알고리즘"
  //   },
  //   {
  //     seq: 12,
  //     title: "API 통신 컴포넌트 설계와 SOLID 원칙",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "CS"
  //   },
  //   {
  //     seq: 13,
  //     title: "[Chart.js] Vue + Chart.js 라이브러리 적용",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "개발"
  //   },
  //   {
  //     seq: 14,
  //     title: "[Chart.js] Vue + Chart.js 라이브러리 적용",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "개발"
  //   },
  //   {
  //     seq: 15,
  //     title: "[Chart.js] Vue + Chart.js 라이브러리 적용",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "개발"
  //   },
  //   {
  //     seq: 16,
  //     title: "[Chart.js] Vue + Chart.js 라이브러리 적용",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "개발"
  //   },
  //   {
  //     seq: 17,
  //     title: "[Chart.js] Vue + Chart.js 라이브러리 적용",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "개발"
  //   },
  //   {
  //     seq: 18,
  //     title: "[Chart.js] Vue + Chart.js 라이브러리 적용",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "개발"
  //   },
  //   {
  //     seq: 19,
  //     title: "[Chart.js] Vue + Chart.js 라이브러리 적용",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "개발"
  //   },
  //   {
  //     seq: 20,
  //     title: "[Chart.js] Vue + Chart.js 라이브러리 적용",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token"
  //     },
  //     categoryName: "개발"
  //   },
  //   {
  //     seq: 21,
  //     title: "[Chart.js] Vue + Chart.js 라이브러리 적용",
  //     filepath: "filepath",
  //     registeredTime: "2023-01-29",
  //     publicType: 1,
  //     viewCount: 20,
  //     likeCount: 2,
  //     commentCount: 1,
  //     totalSecond: 264892,
  //     user: {
  //       seq: 0,
  //       id: "kimssafy",
  //       nickname: "김싸피",
  //       email: "kim@ssafy.com",
  //       catSkin: 1,
  //       singleTheme: "기본",
  //       totalSecond: 312313615646,
  //       membershipLevel: "BRONZE_LV1",
  //       point: 25,
  //       maxPoint: 50,
  //       token: "token",
  //     },
  //     categoryName: "개발",
  //   },
  // ]
  const getFeeds = function() {
    
    feedTotalArticleCnt= articles.length;
    totalPageCnt = feedTotalArticleCnt / 24;
    
    articles.forEach((article) => {
      feeds.push(article)
    })

    const set = new Set(articles);
    const uniqueArr = [...set];
    feeds = uniqueArr;
  }

    // const filterFeeds = originalTodos.filter((elem) => {
    //   console.log(elem)
    //   return elem.status === 2
    // })

    // const newFeeds = filterFeeds.map((elem) => {
    //   elem.public_type = 0
    //   elem.isSubmit = false
    //   return elem
    // })
    
    // console.log('NEW TODOS', newFeeds)
    // newFeeds.forEach((newTodo) => {
    //   feeds.push(newTodo)
    // })

    // const set = new Set(feeds);
    // const uniqueArr = [...set];
    // feeds = uniqueArr;
    
    
    // // 게시글 전체 조회
    const getFeedList = () => {
      console.log('get request 실행');
      console.log('게시글 전체 조회');
      axios({
        method: "get",
        url: `${backendServerUrl.value}/article/list`,
        params: {
          users_seq: userId,
        },
      })
        .then((res) => {
          console.log('게시글 전체 조회 성공');
          console.log('res.data: ', res.data);
          articles.value = res.data;
        })
        .catch((err) => {
          console.log('게시글 전체 조회 실패')
          console.log('에러: ',err);
        });
    };
    return {
      feeds,
      getFeedList,
      articles,
      article,
      getFeeds,
      feedTotalArticleCnt,
      totalPageCnt,
    };
  }
);
