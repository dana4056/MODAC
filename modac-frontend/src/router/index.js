import { createRouter, createWebHistory } from "vue-router";
import UserLogin from "../domains/User/UserLogin.vue";
import UserSignUp from "../domains/User/UserSignUp.vue";
import TodoListView from "../views/TodoListView.vue";
import RoomView from "@/views/RoomView.vue";
import ArticleView from "../views/ArticleView.vue";
// import TestView from "@/views/TestView.vue";
// import GithubTest from "../views/GithubTest.vue";
// import GithubTest2 from "../views/GithubTest2.vue";
// import GithubTest2_2 from "../views/GithubTest2-2.vue";
// import GithubTest3 from "../views/GithubTest3.vue";
// import GithubTest4 from "../views/GithubTest4.vue";
import MyPageView from "../views/MyPageView.vue";
import FeedView from "@/views/FeedView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/login",
      name: "login",
      component: UserLogin,
    },
    {
      path: "/signup",
      name: "UserSignUp",
      component: UserSignUp,
    },
    {
      path: "/todolist",
      name: "todolist",
      component: TodoListView,
    },
    {
      path: "/room",
      name: "room",
      component: RoomView,
    },
    {
      path: "/article",
      name: "article",
      component: ArticleView,
    },
    {
      path: "/feed",
      name: "feed",
      component: FeedView,
    },
    {
      path: "/myPage",
      name: "myPage",
      component: MyPageView,
    },
    // {
    //   path: "/before",
    //   name: "before",
    //   component: GithubTest,
    // },
    // {
    //   path: "/after",
    //   name: "after",
    //   component: GithubTest2,
    // },
    // {
    //   path: "/createRepo/:user",
    //   name: "createRepo",
    //   component: GithubTest2_2,
    // },
    // {
    //   path: "/commit/:user/:repo",
    //   name: "commit",
    //   component: GithubTest3,
    // },
    // {
    //   path: "/repo/:user/:repo",
    //   name: "repo",
    //   component: GithubTest4,
    // },
  ],
});

export default router;
