<script setup>
import TodoListItemCategoryTag from "./TodoListItemCategoryTag.vue";
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
  users_seq: props.todo.users_seq,
  title: props.todo.title,
  categories_seq: props.todo.categories_seq,
  status: props.todo.status,
  total_second: String(msTime.value),
  index: store.todos.indexOf(props.todo),
  completed: false,
  time: time,
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
    // select.style.filter = "blur(5px)";

    // select = document.getElementById("title_text");
    // select.style.textDecoration = "line-through";

    // completedStyle = "$style." + completed;
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

    // select.style.filter = "blur(0px)";

    // select = document.getElementById("title_text");
    // select = document.querySelector("")
    // select.style.textDecoration = "none";

    // completedStyle = "$style." + nonCompleted;
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
const openUpdateTodo = () => {
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
    openUpdateTodo();
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

defineExpose({
  stop,
});
</script>

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
          <TodoListItemCategoryTag
            :categories_seq="props.todo.categories_seq"
          />

          <!-- 타이머 -->
          <div id="clock">
            <span
              v-if="props.todo.status == 0"
              class="bg-indigo-100 text-indigo-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-indigo-400 border border-indigo-400"
              >{{ time }}</span
            >
            <span
              v-if="props.todo.status == 1"
              class="bg-red-100 text-red-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-red-400 border border-red-400"
              >{{ time }}</span
            >
            <span
              v-if="props.todo.status == 2"
              class="bg-gray-100 text-gray-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded dark:bg-gray-700 dark:text-gray-400 border border-gray-500"
              >{{ time }}</span
            >
          </div>
        </div>

        <div class="flex gap-2" :id="`buttons_group_${props.todo.seq}`">
          <!-- 정지 버튼 -->
          <button v-if="props.todo.status == 1" @click="playPauseTodoItem">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              fill="currentColor"
              class="w-5 h-5"
            >
              <path
                fill-rule="evenodd"
                d="M6.75 5.25a.75.75 0 01.75-.75H9a.75.75 0 01.75.75v13.5a.75.75 0 01-.75.75H7.5a.75.75 0 01-.75-.75V5.25zm7.5 0A.75.75 0 0115 4.5h1.5a.75.75 0 01.75.75v13.5a.75.75 0 01-.75.75H15a.75.75 0 01-.75-.75V5.25z"
                clip-rule="evenodd"
              />
            </svg>
          </button>

          <!-- 재생 버튼 -->
          <button v-else @click="playPauseTodoItem">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              fill="currentColor"
              class="w-5 h-5"
            >
              <path
                fill-rule="evenodd"
                d="M4.5 5.653c0-1.426 1.529-2.33 2.779-1.643l11.54 6.348c1.295.712 1.295 2.573 0 3.285L7.28 19.991c-1.25.687-2.779-.217-2.779-1.643V5.653z"
                clip-rule="evenodd"
              />
            </svg>
          </button>

          <!-- 수정 버튼 -->
          <button @click="openUpdateTodo">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              fill="currentColor"
              class="w-5 h-5"
            >
              <path
                d="M21.731 2.269a2.625 2.625 0 00-3.712 0l-1.157 1.157 3.712 3.712 1.157-1.157a2.625 2.625 0 000-3.712zM19.513 8.199l-3.712-3.712-8.4 8.4a5.25 5.25 0 00-1.32 2.214l-.8 2.685a.75.75 0 00.933.933l2.685-.8a5.25 5.25 0 002.214-1.32l8.4-8.4z"
              />
              <path
                d="M5.25 5.25a3 3 0 00-3 3v10.5a3 3 0 003 3h10.5a3 3 0 003-3V13.5a.75.75 0 00-1.5 0v5.25a1.5 1.5 0 01-1.5 1.5H5.25a1.5 1.5 0 01-1.5-1.5V8.25a1.5 1.5 0 011.5-1.5h5.25a.75.75 0 000-1.5H5.25z"
              />
            </svg>
          </button>

          <!-- 삭제 버튼 -->
          <button @click="deleteTodoItem">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 24 24"
              fill="currentColor"
              class="w-5 h-5"
            >
              <path
                fill-rule="evenodd"
                d="M16.5 4.478v.227a48.816 48.816 0 013.878.512.75.75 0 11-.256 1.478l-.209-.035-1.005 13.07a3 3 0 01-2.991 2.77H8.084a3 3 0 01-2.991-2.77L4.087 6.66l-.209.035a.75.75 0 01-.256-1.478A48.567 48.567 0 017.5 4.705v-.227c0-1.564 1.213-2.9 2.816-2.951a52.662 52.662 0 013.369 0c1.603.051 2.815 1.387 2.815 2.951zm-6.136-1.452a51.196 51.196 0 013.273 0C14.39 3.05 15 3.684 15 4.478v.113a49.488 49.488 0 00-6 0v-.113c0-.794.609-1.428 1.364-1.452zm-.355 5.945a.75.75 0 10-1.5.058l.347 9a.75.75 0 101.499-.058l-.346-9zm5.48.058a.75.75 0 10-1.498-.058l-.347 9a.75.75 0 001.5.058l.345-9z"
                clip-rule="evenodd"
              />
            </svg>
          </button>
        </div>
      </div>

      <div v-if="updateState">
        <hr
          class="bg-white p-0 my-5 border-black border-0 border-t border-dashed"
        />
        <div class="my-5">
          <form @submit.prevent="updateTodo">
            <div
              class="w-full gap-7 max-w-sm p-4 bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700"
            >
              <!-- 할 일 작성 -->
              <div class="pb-3 m-0.5 w-full">
                <label
                  class="block mb-2 text-sm font-semibold text-gray-900 dark:text-white"
                  >할 일</label
                >
                <input
                  type="text"
                  v-model="updateTodoItem.title"
                  id="title"
                  autofocus
                  class="bg-gray-50 text-sm border border-gray-300 text-gray-900 rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
                  placeholder="오늘의 공부는?"
                  required
                />
              </div>

              <!-- 카테고리 선택 -->
              <div class="pb-3 m-0.5 w-full">
                <label
                  class="block mb-2 text-sm font-semibold text-gray-900 dark:text-white"
                  >카테고리 선택</label
                >
                <select
                  id="categories_seq"
                  v-model="updateTodoItem.categories_seq"
                  class="bg-gray-50 text-sm border border-gray-300 text-gray-900 rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                >
                  <option value="0" selected="selected">알고리즘</option>
                  <option value="1">CS</option>
                  <option value="2">개발</option>
                  <option value="3">기획</option>
                  <option value="4">기타</option>
                </select>
              </div>

              <div class="inline-flex w-full">
                <!-- 취소 button -->
                <div class="flex items-center justify-between w-full">
                  <button
                    @click="openUpdateTodo"
                    class="w-full text-sm text-black bg-gray-200 hover:bg-gray-300 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg px-4 py-2.5 m-1 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                  >
                    ❌ 취소
                  </button>
                </div>
                <!-- 수정 button -->
                <div class="flex items-center justify-between w-full">
                  <button
                    type="submit"
                    class="w-full text-sm text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg px-4 py-2.5 m-1 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                  >
                    ✔ 수정
                  </button>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="css" module>
@import "./TodoListItem.module.css" .nonClick {
  background: rgba(236, 236, 236, 0.9);
}

.click {
  background: rgb(175, 219, 255, 0.9);
}
</style>
