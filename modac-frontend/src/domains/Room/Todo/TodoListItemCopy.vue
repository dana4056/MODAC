<template lang="">
  <div
    style="
      display: flex;
      align-items: flex-start;
      padding: 13px 15px;
      gap: 5px;
      border-radius: 5px;
    "
    :class="$style.nonClick"
  >
    <!-- :class="completedStyle"> -->
    <input
      type="checkbox"
      v-model="updateTodoItem.completed"
      @click="clickCompleteTodoItem"
      class="form-check-input h-4 w-4 outline-0 rounded-sm bg-white checked:bg-blue-600 checked:border-blue-600 focus:outline-none transition duration-200 align-top bg-no-repeat bg-center bg-contain float-left cursor-pointer"
    />

    <div
      style="width: 90%; flex-direction: column"
      class="gap-2 flex"
      :id="`todo_contents_${props.todo.seq}`"
    >
      <!-- Todo가 한 줄을 넘어가면 말줄임표(...)으로 표시되도록 함 -->
      <div
        v-if="ellipsisState && props.todo.status == 2"
        class="text-ellipsis whitespace-nowrap overflow-hidden line-through"
        @click="showFullTodo"
      >
        {{ props.todo.title }}
      </div>
      <div
        v-if="ellipsisState && props.todo.status != 2"
        class="text-ellipsis whitespace-nowrap overflow-hidden no-underline"
        @click="showFullTodo"
      >
        {{ props.todo.title }}
      </div>

      <!-- 전체를 보고 싶다면 해당 Todo 영역을 클릭 -->
      <div
        v-if="!ellipsisState"
        @click="showFullTodo"
        class="break-all whitespace-normal h-full"
      >
        {{ props.todo.title }}
      </div>

      <div class="flex items-center justify-between">
        <div class="flex">
          <TodoListItemCategoryTag :categoriesName="todo.categoriesName" />
          <TodoListItemTimer :time="time" :status="todo.status" />
        </div>

        <div class="flex gap-2" :id="`buttons_group_${props.todo.seq}`">
          <PlayPauseButton
            :playPauseTodoItem="playPauseTodoItem"
            :status="props.todo.status"
          />
          <UpdateButton :toggleUpdateForm="toggleUpdateForm" />
          <DeleteButton :deleteTodoItem="deleteTodoItem" />
        </div>
      </div>
      <TodoListItemUpdateForm
        :toggleUpdateForm="toggleUpdateForm"
        :updateState="updateState"
        :updateTodo="updateTodo"
      />
    </div>
  </div>
</template>

<script setup>
import TodoListItemCategoryTag from "./TodoListItemCategoryTag.vue";
import TodoListItemTimer from "./TodoListItemTimer.vue";
import PlayPauseButton from "./PlayPauseButton.vue";
import UpdateButton from "./UpdateButton.vue";
import DeleteButton from "./DeleteButton.vue";
import TodoListItemUpdateForm from "./TodoListItemUpdateForm.vue";
import { useTodoStore } from "../../../stores/todo";
import { ref } from "vue";
const store = useTodoStore();
let msTime = ref(0);
let time = ref("00:00:00");

const props = defineProps({
  todo: Object,
  nowPlay: Number,
});

const emits = defineEmits(["nowPlay"]);

const nowPlayUpdate = (seq) => {
  emits("nowPlay", seq);
};

let updateTodoItem = {
  seq: props.todo.seq,
  title: props.todo.title,
  categoriesName: props.todo.categoriesName,
  status: props.todo.status,
  totalSecond: String(msTime.value),
};

// status 변화 시
// 0 -> 1 : 멈춤 -> 재생 [재생 버튼 클릭 시]
// 0 -> 2 : 멈춤 -> 할일 완료 [완료 버튼 클릭 시]
// 1 -> 0 : 재생 -> 멈춤 [멈춤 버튼 클릭 시]
// 1 -> 2 : 재생 -> 멈춤 & 할일 완료 [재생 중에 완료 버튼 클릭 시]
// 2 -> 0 : 완료 -> 완료 해제 [완료 버튼 해제 시]
// 2 -> 1 : 완료 -> 재생 & 완료 해제 [완료 중에 재생 버튼 클릭 시]

// completed = 할일 완료 여부
// const completed = ref(false);

// const completed = "completed";
// const nonCompleted = "nonCompleted";
// let completedStyle = "$style." + nonCompleted;

const clickCompleteTodoItem = () => {
  // 할일 완료하기
  if (!updateTodoItem.completed) {
    updateTodoItem.completed = true;
    updateTodoItem.status = 2;
    stop();

    let select = document.getElementById(`todo_` + props.todo.seq);
    select.style.background = "rgb(116, 116, 116, 0.9)";

    select = document.getElementById(`buttons_group_` + props.todo.seq);
    select.style.display = "none";

    select = document.getElementById(`todo_contents_` + props.todo.seq);
    select.style.flexDirection = "row";
    select.style.justifyContent = "space-between";
  }
  // 할일 완료 해제하기
  else {
    updateTodoItem.completed = false;
    updateTodoItem.status = 0;

    let select = document.getElementById(`todo_` + props.todo.seq);
    select.style.background = "rgb(236, 236, 236, 0.9)";

    select = document.getElementById(`buttons_group_` + props.todo.seq);
    select.style.display = "flex";

    select = document.getElementById(`todo_contents_` + props.todo.seq);
    select.style.flexDirection = "column";
    select.style.justifyContent = "";
  }
  updateTodo();
};

// 타이머 재생 / 멈춤 버튼
const playPauseTodoItem = () => {
  // 멈춰있는 상태 -> 재생 (0 -> 1)
  if (updateTodoItem.status == 0) {
    updateTodoItem.status = 1;
    start();
  }

  // 할일 완료 상태 -> 완료 해제 & 재생 (2 -> 1)
  else if (updateTodoItem.status == 2) {
    updateTodoItem.completed = false;
    updateTodoItem.status = 1;
    start();
  } else if (updateTodoItem.status == 1) {
    // 재생되고 있는 상태 -> 멈춤 (1 -> 0)
    if (updateTodoItem.completed == false) {
      updateTodoItem.status = 0;
    }
    // 재생되고 있는 상태 -> 완료 체크 (1 -> 2)
    else if (updateTodoItem.completed == true) {
      updateTodoItem.completed = true;
      updateTodoItem.status = 2;
    }
    stop();
  }
  updateTodo();
};

const deleteTodoItem = () => {
  store.deleteTodoItem(props.todo);
};

let updateState = ref(false);
const toggleUpdateForm = () => {
  updateState.value = !updateState.value;
};

const updateTodo = () => {
  store.updateTodoItem({
    seq: Number(updateTodoItem.seq),
    users_seq: Number(updateTodoItem.users_seq),
    categories_seq: Number(updateTodoItem.categories_seq),
    title: updateTodoItem.title,
    status: updateTodoItem.status,
    total_second: String(msTime.value),
    index: store.todos.indexOf(props.todo),
  });

  if (updateState.value == true) {
    toggleUpdateForm();
  }
};

// 말줄임표로 열고 닫기
let ellipsisState = ref(true);
const showFullTodo = () => {
  ellipsisState.value = !ellipsisState.value;
};

// timer test

var timeBegan = null,
  timeStopped = null,
  stoppedDuration = 0,
  started = null,
  running = false;

const start = () => {
  if (running) return;

  if (timeBegan === null) {
    reset();
    timeBegan = new Date();
  }

  if (timeStopped !== null) {
    stoppedDuration += new Date() - timeStopped;
  }

  started = setInterval(clockRunning, 10);
  running = true;

  nowPlayUpdate(updateTodoItem.seq);

  const select = document.getElementById(`todo_` + props.todo.seq);
  // select.className = "$style.click";
  select.style.background = "rgb(175, 219, 255, 0.9)";
};

const stop = () => {
  running = false;
  timeStopped = new Date();
  clearInterval(started);

  nowPlayUpdate(-1);

  const select = document.getElementById(`todo_` + props.todo.seq);
  // select.className = "$style.nonClick";
  select.style.background = "rgb(236, 236, 236, 0.9)";
};

// 일단은 넣어두지 않은 기능
const reset = () => {
  running = false;
  clearInterval(started);
  stoppedDuration = 0;
  timeBegan = null;
  timeStopped = null;
  time.value = "00:00:00";
  // time.value = "00:00:00.000";
};

const clockRunning = () => {
  var currentTime = new Date(),
    timeElapsed = new Date(currentTime - timeBegan - stoppedDuration),
    hour = timeElapsed.getUTCHours(),
    min = timeElapsed.getUTCMinutes(),
    sec = timeElapsed.getUTCSeconds();
  // , ms = timeElapsed.getUTCMilliseconds()
  msTime.value = timeElapsed.getTime();

  time.value =
    zeroPrefix(hour, 2) + ":" + zeroPrefix(min, 2) + ":" + zeroPrefix(sec, 2);
  // + "." + zeroPrefix(ms, 3)
};

const zeroPrefix = (num, digit) => {
  var zero = "";
  for (var i = 0; i < digit; i++) {
    zero += "0";
  }
  return (zero + num).slice(-digit);
};

// const nonClick = document.querySelectorAll(".non-click");

// function handleClick(event) {
//   // div에서 모든 "click" 클래스 제거
//   nonClick.forEach((e) => {
//     e.classList.remove("click");
//   });
//   // 클릭한 div만 "click"클래스 추가
//   event.target.classList.add("click");
// }

// nonClick.forEach((e) => {
//   e.addEventListener("click", handleClick);
// });

// const select = document.querySelector(`props.todo.seq`);
// function handleTitleClick() {
//     select.className = "click";
// }
// select.addEventListener("click", handleTitleClick)

// const select = document.querySelector(`todo_` + props.todo.seq);
// const activeTodoPlay = () => {
//   select.className = "click";
// }

defineExpose({
  stop,
});
</script>

<style module>
.nonClick {
  background: rgba(236, 236, 236, 0.9);
}

.click {
  background: rgb(175, 219, 255, 0.9);
}

/* .completed, .completed > .title-text {
  text-decoration-line: line-through;
  background: rgb(116, 116, 116, 0.9);
}

.nonCompleted, .nonCompleted > .title-text {
  text-decoration-line: none;
  background: rgba(236, 236, 236, 0.9);
} */
</style>
