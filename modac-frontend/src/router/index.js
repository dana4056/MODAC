import { createRouter, createWebHistory } from "vue-router";
import TodoListView from "../views/TodoListView.vue";
import StudyRoomView from "../views/StudyRoomView.vue";
import TestView from "@/views/TestView.vue";

import GithubTest from "../views/GithubTest.vue";
import GithubTest2 from "../views/GithubTest2.vue";
import GithubTest3 from "../views/GithubTest3.vue";


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/todolist",
      name: "todolist",
      component: TodoListView,
    },
    {
      path: "/studyRoom",
      name: "studyRoom",
      component: StudyRoomView,
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
      path: "/commit/:user/:repo",
      name: "commit",
      component: GithubTest3,
    }
  ],
});

export default router;
