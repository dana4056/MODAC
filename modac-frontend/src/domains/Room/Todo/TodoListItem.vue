<script setup>
import TodoItemContent from "./TodoItemContent.vue";
import TodoListItemCategoryTag from "./TodoListItemCategoryTag.vue";
import TodoListItemTimer from "./TodoListItemTimer.vue";
import PlayPauseButton from "./PlayPauseButton.vue";
import UpdateButton from "./UpdateButton.vue";
import DeleteButton from "./DeleteButton.vue";
import TodoListItemUpdateForm from "./TodoListItemUpdateForm.vue";
import todoAPI from "../../../api/todo";
import { ref, defineProps, toRefs } from "vue";
import { useTodoStore } from "../../../stores/todo";

const props = defineProps({
  todo: Object,
});

const todo = toRefs(props.todo);

const { seq, status, title, totalSecond } = toRefs(props.todo);

const todoStore = useTodoStore();

const openState = ref(false);
const toggleOpenState = () => {
  openState.value = !openState.value;
};

const isOpenUpdateFormState = ref(false);
const toggleUpdateFormState = () => {
  isOpenUpdateFormState.value = !isOpenUpdateFormState.value;
};

const handleUpdateTodoItem = (title, categoriesName) => {
  // console.log(title, categoriesName);
  todoAPI.updateTodo(
    seq.value,
    categoriesName,
    title,
    status.value,
    totalSecond.value
  );
  console.log(1);
  todoStore.updateTodoItem(seq.value, {
    title,
    categoriesName,
    status,
    totalSecond,
    seq,
  });
  console.log(title);
};

const deleteTodoItem = () => {
  todoAPI.deleteTodo(props.todo.seq);
  todoStore.deleteTodoItem(props.todo.seq);
};
</script>

<template>
  <div :class="$style.todo_item_wrapper">
    <input type="checkbox" :class="$style.checkbox" />
    <div :class="$style.flex_wrapper">
      <TodoItemContent
        @click="toggleOpenState"
        :openState="openState"
        :status="status"
        :title="title"
      />
      <div class="flex items-center justify-between">
        <div class="flex">
          <TodoListItemCategoryTag :categoriesName="todo.categoriesName" />
          <TodoListItemTimer :time="totalSecond" :status="status" />
        </div>

        <div class="flex gap-2" :id="`buttons_group_${props.todo.seq}`">
          <PlayPauseButton
            :playPauseTodoItem="playPauseTodoItem"
            :status="props.todo.status"
          />
          <UpdateButton @click="toggleUpdateFormState" />
          <DeleteButton @click="deleteTodoItem" />
        </div>
      </div>
      <TodoListItemUpdateForm
        :isOpenUpdateFormState="isOpenUpdateFormState"
        :handleUpdateTodoItem="handleUpdateTodoItem"
        :toggleUpdateFormState="toggleUpdateFormState"
      />
    </div>
  </div>
</template>

<style lang="css" module>
@import "TodoListItem.module.css";
</style>
