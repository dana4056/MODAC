<script setup>
import TodoItemContent from "./TodoItemContent.vue";
import TodoListItemCategoryTag from "./TodoListItemCategoryTag.vue";
import TodoListItemTimer from "./TodoListItemTimer.vue";
import PlayPauseButton from "./PlayPauseButton.vue";
import UpdateButton from "./UpdateButton.vue";
import DeleteButton from "./DeleteButton.vue";
import TodoListItemUpdateForm from "./TodoListItemUpdateForm.vue";
import todoAPI from "../../../api/todo";
import { ref, defineProps, toRefs, watch } from "vue";
import { useTodoStore } from "../../../stores/todo";

const props = defineProps({
  todo: Object,
});
const { seq, status, title, totalSecond, categoriesName } = toRefs(props.todo);

const openState = ref(false);
const toggleOpenState = () => {
  openState.value = !openState.value;
};

const handleCheckTodoItem = () => {
  todoAPI.updateTodo(
    seq.value,
    categoriesName.value,
    title.value,
    status.value,
    totalSecond.value
  );
};

const inputCheckBoxValue = status.value === 2 ? ref(true) : ref(false);
watch(inputCheckBoxValue, () => {
  if (inputCheckBoxValue.value === true) {
    status.value = 2;
  } else {
    status.value = 0;
  }
  handleCheckTodoItem();
});

const isOpenUpdateFormState = ref(false);
const toggleUpdateFormState = () => {
  isOpenUpdateFormState.value = !isOpenUpdateFormState.value;
};

const todoStore = useTodoStore();

const handleUpdateTodoItem = (title, categoriesName) => {
  const responseDate = todoAPI.updateTodo(
    seq.value,
    categoriesName,
    title,
    status.value,
    totalSecond.value
  );

  todoStore.updateTodoItem(responseDate.seq, responseDate);
};

const deleteTodoItem = () => {
  todoAPI.deleteTodo(props.todo.seq);
  todoStore.deleteTodoItem(props.todo.seq);
};
</script>

<template>
  <div :class="$style.todo_item_wrapper">
    <input
      type="checkbox"
      :class="$style.checkbox"
      v-model="inputCheckBoxValue"
    />
    <div :class="$style.flex_wrapper">
      <TodoItemContent
        :toggleOpenState="toggleOpenState"
        :openState="openState"
        :status="status"
        :title="title"
      />
      <div class="flex items-center justify-between">
        <div class="flex">
          <TodoListItemCategoryTag :categoriesName="categoriesName" />
          <TodoListItemTimer :time="totalSecond" :status="status" />
        </div>

        <div class="flex gap-2" :id="`buttons_group_${props.todo.seq}`">
          <PlayPauseButton :status="props.todo.status" />
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
