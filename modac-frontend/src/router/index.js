import { createRouter, createWebHistory } from "vue-router";
import TodoListView from "../views/TodoListView.vue";
import StudyRoomView from "../views/StudyRoomView.vue";
import ArticleView from "../views/ArticleView.vue";

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
      path: "/article",
      name: "article",
      component: ArticleView,
    },
  ],
});

export default router;
