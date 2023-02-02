import { reactive, ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";


export const useFeedStore = defineStore("feed", () => {
  const feeds = reactive([]);
  const userId = ref(null);
  const backendServerUrl = ref("localhost");

  
  const getFeeds = function(){
    const originalTodos = [{feed_seq: 1, status: 1}, {feed_seq: 2, status: 2}, {feed_seq: 3, status: 2}, {feed_seq: 2, status: 2}, {feed_seq: 3, status: 2}, {feed_seq: 2, status: 2}, {feed_seq: 3, status: 2}]

    const filterFeeds = originalTodos.filter((elem) => { 
      console.log(elem)
      return elem.status === 2
    })

    const newFeeds = filterFeeds.map((elem) => {
      elem.public_type = 0
      elem.isSubmit = false
      return elem
    })
    console.log('NEW TODOS', newFeeds)
    newFeeds.forEach((newTodo) => {
      feeds.push(newTodo)
    })
  }
  
  
  
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
    getFeeds,
  };
});
