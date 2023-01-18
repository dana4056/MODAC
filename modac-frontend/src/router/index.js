import { createRouter, createWebHistory } from "vue-router";
import TodoListView from "../views/TodoListView.vue";
import StudyRoomView from "../views/StudyRoomView.vue";

import GithubTest from "../views/GithubTest.vue";
import GithubTest2 from "../views/GithubTest2.vue";


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
    }
  ],
});

export default router;
