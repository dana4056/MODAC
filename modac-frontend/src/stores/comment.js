import { reactive, ref } from "vue";
import { defineStore } from "pinia";
import axios from "axios";


export const useCommentStore = defineStore("comment", () => {
  let comments = reactive([]);
  const userId = ref(null);
  const backendServerUrl = ref("localhost");
  let commentTotalCnt = 0;

  const testCommentList = [
    {
      seq: 0,
      content: "우와 유용해요!",
      registeredTime: "2023-02-09",
      user: {
        seq: 0,
        id: "kimssafy",
        nickname: "김싸피",
        email: "kim@ssafy.com",
        catSkin: 1,
        singleTheme: "기본",
        totalSecond: 312313615646,
        membershipLevel: "BRONZE_LV1",
        point: 25,
        maxPoint: 50,
        token: "token"
      },
    },
    {
      seq: 1,
      content: "잘 보고 갑니다.",
      registeredTime: "2023-02-09",
      user: {
        seq: 0,
        id: "kimssafy",
        nickname: "이싸피",
        email: "kim@ssafy.com",
        catSkin: 1,
        singleTheme: "기본",
        totalSecond: 312313615646,
        membershipLevel: "BRONZE_LV1",
        point: 25,
        maxPoint: 50,
        token: "token"
      },
    },
    {
      seq: 2,
      content: "많이 도움되었습니다. :)",
      registeredTime: "2023-02-09",
      user: {
        seq: 0,
        id: "kimssafy",
        nickname: "박싸피",
        email: "kim@ssafy.com",
        catSkin: 1,
        singleTheme: "기본",
        totalSecond: 312313615646,
        membershipLevel: "BRONZE_LV1",
        point: 25,
        maxPoint: 50,
        token: "token"
      },
    },
  ]
  
  const getComments = (feedSeq) => {
    
    // 여기다가 feedSeq 이용해서 그 게시글의 전체 댓글 리스트 불러오는 코드 작성
    // comments에 testCommentList 대신 해당 리스트 삽입
    // commentTotalCnt에 댓글수 저장
    
    comments = testCommentList;
    commentTotalCnt = testCommentList.length;
    
    // console.log(comments);
    // console.log("commentTotalCnt", commentTotalCnt);

    
    // testCommentList.forEach((comment) => {
    //   comments.push(comment)
    // })

    
    // console.log("comments", comments);

    return comments;
    // const set = new Set(articles);
    // const uniqueArr = [...set];
    // feeds = uniqueArr;
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
    // const getFeedList = () => {
    //   console.log('get request 실행');
    //   console.log('게시글 전체 조회');
    //   axios({
    //     method: "get",
    //     url: `${backendServerUrl.value}/article/list`,
    //     params: {
    //       users_seq: userId,
    //     },
    //   })
    //     .then((res) => {
    //       console.log('게시글 전체 조회 성공');
    //       console.log('res.data: ', res.data);
    //       articles.value = res.data;
    //     })
    //     .catch((err) => {
    //       console.log('게시글 전체 조회 실패')
    //       console.log('에러: ',err);
    //     });
    // };
    return {
      comments,
      commentTotalCnt,
      getComments,
    };
  }
);
