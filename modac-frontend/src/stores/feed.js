import { reactive, ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";


export const useFeedStore = defineStore("feed", () => {
  let feeds = reactive([]);
  const userId = ref(null);
  const backendServerUrl = ref("localhost");
  let feedTotalArticleCnt = 0;
  let totalPageCnt = 0;
  
  const articles = ref([]);
  const article = ref();
  const userArticles = ref([]);


  const getFeeds = function() {
    
    feedTotalArticleCnt= articles.value.length;
    totalPageCnt = feedTotalArticleCnt / 24;
    
    articles.value.forEach((article) => {
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
      userArticles,
      getFeeds,
      feedTotalArticleCnt,
      totalPageCnt,
    };
  },
  {
    persist: true,
  }


);
