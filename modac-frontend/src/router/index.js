import { createRouter, createWebHistory } from "vue-router";
import UserLogin from "../domains/User/UserLogin.vue";
import UserSignUp from "../domains/User/UserSignUp.vue";
import UserFindId from "../domains/User/UserFindId.vue";
import UserFindPassword from "../domains/User/UserFindPassword.vue";
import UserChangePassword from "../domains/User/UserChangePassword.vue";
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
import ServerError from "@/domains/Error/ServerError.vue";
import PageNotFound from "@/domains/Error/PageNotFound.vue";
import { useUserStore } from "@/stores/user.js";
import { ref } from "vue";
import { storeToRefs } from "pinia";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "login",
      component: UserLogin,
      beforeEnter(to, from, next) {
        const userStore = useUserStore();
        const { loginUser } = storeToRefs(userStore);
        if (loginUser.value) {
          next({ name: "room" });
        } else {
          next();
        }
      },
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
      beforeEnter(to, from, next) {
        const userStore = useUserStore();
        const { loginUser } = storeToRefs(userStore);
        if (loginUser.value) {
          next({ name: "room" });
        } else {
          next();
        }
      },
    },
    {
      path: "/findid",
      name: "UserFindId",
      component: UserFindId,
      beforeEnter(to, from, next) {
        const userStore = useUserStore();
        const { loginUser } = storeToRefs(userStore);
        if (loginUser.value) {
          next({ name: "room" });
        } else {
          next();
        }
      },
    },
    {
      path: "/findpass",
      name: "UserFindPassword",
      component: UserFindPassword,
      beforeEnter(to, from, next) {
        const userStore = useUserStore();
        const { loginUser } = storeToRefs(userStore);
        if (loginUser.value) {
          next({ name: "room" });
        } else {
          next();
        }
      },
    },
    {
      path: "/changepw",
      name: "UserChangePassword",
      component: UserChangePassword,
      beforeEnter(to, from, next) {
        const userStore = useUserStore();
        const { loginUser } = storeToRefs(userStore);
        if (loginUser.value) {
          next({ name: "room" });
        } else {
          next();
        }
      },
    },
    {
      path: "/room",
      name: "room",
      component: RoomView,
      beforeEnter(to, from, next) {
        const userStore = useUserStore();
        const { loginUser } = storeToRefs(userStore);
        if (!loginUser.value) {
          next({ name: "login" });
        } else {
          next();
        }
      },
    },
    {
      path: "/article",
      name: "article",
      component: ArticleView,
      children: [
        {
          path: "/before",
          name: "before",
          component: GithubTest,
        },
      ],
      beforeEnter(to, from, next) {
        const userStore = useUserStore();
        const { loginUser } = storeToRefs(userStore);
        if (!loginUser.value) {
          next({ name: "login" });
        } else {
          next();
        }
      },
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
      path: "/feed",
      name: "feed",
      component: FeedView,
      beforeEnter(to, from, next) {
        const userStore = useUserStore();
        const { loginUser } = storeToRefs(userStore);
        if (!loginUser.value) {
          next({ name: "login" });
        } else {
          next();
        }
      },
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
      beforeEnter(to, from, next) {
        const userStore = useUserStore();
        const { loginUser } = storeToRefs(userStore);
        if (!loginUser.value) {
          next({ name: "login" });
        } else {
          next();
        }
      },
    },
    {
      path: "/serverError",
      name: "ServerError",
      component: ServerError,
    },
    // {
    //     path: '*',
    //     name: 'PageNotFound',
    //     component: PageNotFound
    // },
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
