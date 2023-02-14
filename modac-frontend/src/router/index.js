import { createRouter, createWebHistory } from "vue-router";
import UserLogin from "../domains/User/UserLogin.vue";
import UserSignUp from "../domains/User/UserSignUp.vue";
import UserFindId from "../domains/User/UserFindId.vue";
import UserFindPassword from "../domains/User/UserFindPassword.vue";
import RoomView from "@/views/RoomView.vue";
import ArticleView from "../views/ArticleView.vue";
import TestView from "@/views/TestView.vue";
import GithubTest from "../views/GithubTest.vue";
import GithubTest2 from "../views/GithubTest2.vue";
import GithubTest2_2 from "../views/GithubTest2-2.vue";
import GithubTest3 from "../views/GithubTest3.vue";
import GithubTest4 from "../views/GithubTest4.vue";
import ApiTest from "../views/ApiTestView.vue";
import ChatList from "../domains/Room/Chat/ChatList.vue";
import MyPageView from "../views/MyPageView.vue";
import FeedView from "@/views/FeedView.vue";
import ServerError from '@/domains/Error/ServerError.vue'
import PageNotFound from '@/domains/Error/PageNotFound.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/login",
      name: "login",
      component: UserLogin,
    },
    {
      path: "/test",
      name: "test",
      component: TestView,
    },
    {
      path: "/signup",
      name: "UserSignUp",
      component: UserSignUp,
    },
    {
      path: "/findid",
      name: "UserFindId",
      component: UserFindId,
    },
    {
      path: "/findpass",
      name: "UserFindPassword",
      component: UserFindPassword,
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
      path: "/before",
      name: "before",
      component: GithubTest,
    },
    {
      path: "/after",
      name: "after",
      component: GithubTest2,
    },
    {
      path: "/createRepo/:user",
      name: "createRepo",
      component: GithubTest2_2,
    },
    {
      path: "/commit/:user/:repo",
      name: "commit",
      component: GithubTest3,
    },
    {
      path: "/repo/:user/:repo",
      name: "repo",
      component: GithubTest4,
    },
    {
      path: "/ApiTest",
      name: "api",
      component: ApiTest,
    },
    {
      path: "/myPage",
      name: "myPage",
      component: MyPageView,
    },
    {
      path: '/serverError',
      name: 'ServerError',
      component: ServerError
    },
    {
        path: '*',
        name: 'PageNotFound',
        component: PageNotFound
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
