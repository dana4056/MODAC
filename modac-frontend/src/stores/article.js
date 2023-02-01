import { useTodoStore } from "./todo";
import { reactive, ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";


export const useArticleStore = defineStore("article", () => {
  const articles = reactive([]);
  const userId = ref(null);
  const publicType = ref(0)
  const todoStore = useTodoStore()
  const backendServerUrl = ref("localhost");
  const selectList = [
    { name: "전체 공개", value: "0"},
    { name: "내 피드에만 업로드", value: "1" },
    { name: "피드에 업로드하지 않기", value: "2" },
    ]
  // 이걸로 데이터를 조작하여 원하는 데이터 형식으로 나는 axios 요청을 보낼 거임
  // const originalTodos = [{todos_seq: 1, status: 1}, {todos_seq: 1, status: 2}]
  // const originalTodos = todostore.todos

  // 1. filter 사용해서 각 객체의 status가 2(완료)인 것인 새로운 배열 만들기
  // const filterTodos = originalTodos.filter((elem) => { 
  //     console.log(elem)
  //     return elem.status === 2
  // })
  // const filterTodos = todostore.todos.filter(todo => todo.status === 2);
  
  
  // 2. 내가 추가로 넣고 싶은 데이터를 넣기 (초기값)
  // isSubmit : TIL 작성 완료 여부 / public_type : 공개 범위 설정
  
  // const newTodos = filterTodos.map((elem) => {
  //   elem.isSubmit = false
  //   elem.public_type = 0
  //   return elem
  // })
  
  const getArticles = function(){
    const originalTodos = [{todos_seq: 1, status: 1}, {todos_seq: 2, status: 2}, {todos_seq: 3, status: 2}, {todos_seq: 2, status: 2}, {todos_seq: 3, status: 2}, {todos_seq: 2, status: 2}, {todos_seq: 3, status: 2}, {todos_seq: 2, status: 2}, {todos_seq: 3, status: 2}, {todos_seq: 2, status: 2}, {todos_seq: 3, status: 2}, {todos_seq: 2, status: 2}, {todos_seq: 3, status: 2}, {todos_seq: 2, status: 2}, {todos_seq: 3, status: 2}, {todos_seq: 2, status: 2}, {todos_seq: 3, status: 2}, {todos_seq: 2, status: 2}, {todos_seq: 3, status: 2}, {todos_seq: 2, status: 2}, {todos_seq: 3, status: 2}, {todos_seq: 2, status: 2}, {todos_seq: 3, status: 2}, {todos_seq: 2, status: 2}, {todos_seq: 3, status: 2}]

    const filterTodos = originalTodos.filter((elem) => { 
      console.log(elem)
      return elem.status === 2
    })

    const newTodos = filterTodos.map((elem) => {
      elem.public_type = 0
      elem.isSubmit = false
      return elem
    })
    console.log('NEW TODOS', newTodos)
    newTodos.forEach((newTodo) => {
      articles.push(newTodo)
    })
  }
  
  
  
  // // 게시글 전체 조회
  const getArticleList = () => {
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
  // // 게시글 작성
  // const createArticleListItem = () => {
  //   console.log("post request 실행");
  //   console.log('게시물 생성 요청')
  //   axios({
  //     methods: "post",
  //     url: `${backendServerUrl.value}/article`,
  //     data: {
  //       users_seq: "int",
  //       todos_seq: "int",
  //       public_type: "int",
  //       filepath: "String"
  //     },
  //   })
  //     .then((res) => {
  //       console.log('게시물 생성 성공')
  //       console.log('res.data: ', res.data);
  //       articles.push(res.data);
  //     })
  //     .catch((err) => {
  //       console.log('게시물 생성 실패');
  //       console.log('에러: ',err);
  //     });
  // };

  // // 게시물 조회
  // const getArticleListItem = (articleId) => {
  //   console.log("get request 실행");
  //   console.log('게시물 한개 조회용');
  //   axios({
  //     methods: "get",
  //     url: `${backendServerUrl.value}/article`,
  //     params: {
  //       seq: articleId,
  //     }
  //   })
  //     .then((res) => {
  //       console.log('게시물 조회 성공')
  //       console.log('res.data: ', res.data);
  //     })
  //     .catch((err) => {
  //       console.log('게시물 조회 실패');
  //       console.log('에러: ',err);
  //     });
  // };
  
  // // 게시글 삭제
  // const deleteArticleListItem = (articleId) => {
  //   console.log("delete request 실행");
  //   axios({
  //     methods: "delete",
  //     url: `${backendServerUrl.value}/todo`,
  //     data: {
  //       seq: articleId
  //     },
  //   })
  //     .then((res) => {
  //       console.log('게시물 삭제 성공')
  //       console.log('res.data: ', res.data);
  //       articles.delete(res.data);
  //     })
  //     .catch((err) => {
  //       console.log('게시물 삭제 실패');
  //       console.log('에러: ',err);
  //     });
  // };

  // Create 
  const createArticleListItem = (todo, submitData) => {
    console.log("post request 실행");
    console.log('게시물 생성 요청')
    axios({
          methods: "post",
          url: `${backendServerUrl.value}/article`,
          data: {
            users_seq: userId,
            todos_seq: todo.Id,
            public_type: submitData.public_type,
            filepath: submitData.filepath
          }
    })
      .then((res) => {
        console.log('게시물 생성 성공')
        console.log('res.data: ', res.data);
        articles.delete(res.data);
      })
      .catch((err) => {
        console.log('게시물 생성 실패');
        console.log('에러: ',err);
      });
    }
  
  const deleteArticleItem = () => {
    console.log('DELETE')
  }

  const changePublicStatus = () => {

  }

  const completeSubmit = () => {

  }

  return {
    // 공통
    articles,
    getArticleList,
    // ArticleForm
    publicType,
    selectList,
    // ArticleList
    createArticleListItem,
    deleteArticleItem,
    getArticles,
    changePublicStatus,
    completeSubmit,

    // ArticleListItem

    // ArticleForm
  };
});
