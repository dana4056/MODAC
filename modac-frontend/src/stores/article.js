import { reactive, ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

export const useTodoStore = defineStore("article", () => {
  const articles = reactive([]);
  const userId = ref(null);
  const backendServerUrl = ref("localhost");

 
  // 게시글 조회
  const getArticleList = () => {
    console.log(2);
    axios({
      method: "get",
      url: `${backendServerUrl.value}/article/list`,
      params: {
        users_seq: userId,
      },
    })
      .then((res) => {
        console.log(res.data);
        articles.value = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  };
  // 게시글 작성
  const createArticleListItem = () => {
    console.log("post request 실행");
    axios({
      methods: "post",
      url: `${backendServerUrl.value}/article`,
      data: {
        users_seq:"int",
        todos_seq:"int",
        public_type:"int",
        filepath: "String"
      },
    });
  };

  // 게시글 삭제
  const deleteArticleListItem = () => {
    console.log("delete request 실행");
    axios({
      methods: "delete",
      url: `${backendServerUrl.value}/todo`,
      data: {},
    });
  };
  // 게시글 수정
  const changeArticleListItem = () => {
    console.log("put request 실행");
    axios({
      methods: "put",
      url: `${backendServerUrl.value}/todo`,
      data: {},
    });
  };

  // 백이랑 연결 되면 삭제 예정
  const addArticleItem = (inputarticleItem) => {
    console.log(inputarticleItem);
    articles.push(inputarticleItem);
  };
  // 


  return {
    articles,
    addArticleItem,
    getArticleList,
    createArticleListItem,
  };
});
