import { reactive, ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";

export const useTodoStore = defineStore("article", () => {
  const articles = reactive([]);
  const userId = ref(null);
  const backendServerUrl = ref("localhost");

  // 백이랑 연결 되면 삭제 예정
  const addArticleItem = (inputarticleItem) => {
    console.log(inputarticleItem);
    articles.push(inputarticleItem);
  };
  // 

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

  const createArticleListItem = () => {
    console.log("post request 실행");
    axios({
      methods: "post",
      url: `${backendServerUrl.value}/todo`,
      data: {},
    });
  };

  return {
    articles,
    addArticleItem,
    getArticleList,
    createArticleListItem,
  };
});
