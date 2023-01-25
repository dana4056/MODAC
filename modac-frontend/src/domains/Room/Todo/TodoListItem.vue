<template lang="">
  <input type="checkbox" v-model="updateTodoItem.completed" @click="clickCompleteTodoItem"/>
  <div @click="showFullTodo">
    <!-- Todo가 한 줄을 넘어가면 말줄임표(...)으로 표시되도록 함 -->
    <div
      v-if="ellipsisState"
      style="
      text-overflow: ellipsis;
      white-space: nowrap;
      overflow: hidden;">
    Todo : [{{ props.todo.title }}]</div>
    <!-- 전체를 보고 싶다면 해당 Todo 영역을 클릭 -->
    <div v-else @click="showFullTodo">  Todo : [{{ props.todo.title }}]  </div>

    <div>
      Category :  [{{ props.todo.categories_seq }}]
    </div>
    <div>
      ( {{ props.todo.seq + 1 }}번째 Todo )
    </div>
    
    <!-- timer test -->
    <div id="clock">
      <span class="time">{{ time }}</span>
    </div>

    <button v-if="props.todo.status == 1" @click="playPauseTodoItem">[⏸ 멈춤]</button>
    <button v-else @click="playPauseTodoItem">[⏩ 재생]</button>

    <button @click="deleteTodoItem">[❌삭제]</button>
    <button @click="openUpdateTodo">[✏수정]</button>
    <button @click="printUpdateTodoItem">[updateTodoItem 출력]</button>
    <button @click="printPropsItem">[Props.todo 출력]</button>

    <div v-if="updateState">
      <br>=======================
      <div>
        <form @submit.prevent="updateTodo">
        <!-- Todo title input -->
        <div>
          <label for="input1">Todo 제목 :</label>
          <input
            id="title"
            v-model="updateTodoItem.title"
            class="w-full border px-4 py-2 rounded focus:border-blue-500 focus:shadow-outline outline-none"
            type="text"
            placeholder="TODO..."
            autofocus
          />
        </div>

        <!-- Todo categories_seq dropdown -->
        <div>
          <label for="select-item">Todo 카테고리 :</label>

          <div class="relative">
            <select
              class="w-full border px-4 pr-8 py-2 rounded focus:border-blue-500 focus:shadow-outline outline-none appearance-none"
              id="categories_seq"
              v-model="updateTodoItem.categories_seq"
            >
            <option value=0 selected="selected">알고리즘</option>
            <option value=1>CS</option>
            <option value=2>개발</option>
            <option value=3>기획</option>
            <option value=4>기타</option>
          </select>

          <div
            class="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700"
          >
            <svg
              class="fill-current h-4 w-4"
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 20 20"
            >
              <path
                d="M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z"
              />
            </svg>
          </div>
        </div>
      </div>

          <div style="display: inline-flex;">
            <!-- Submit button -->
            <div class="flex items-center justify-between">
              <button
                class="bg-blue-600 hover:bg-black text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                type="submit"
              >
                ✔ 수정하기
              </button>
            </div>
            &nbsp;
            <!-- 취소 button -->
            <div class="flex items-center justify-between">
              <button
                class="bg-blue-600 hover:bg-black text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                @click="openUpdateTodo()"
              >
              ❌ 닫기
              </button>
            </div>
          </div>
        </form>
      </div>  =======================
    </div>

    </div>
    <br><br>
</template>

<script setup>
import { useTodoStore } from "../../../stores/todo";
import { ref } from "vue";
const store = useTodoStore();
let msTime = ref(0);
let time = ref('00:00:00.000');

const props = defineProps({
  todo : Object,
  nowPlay : Number,
});

const emits = defineEmits(['nowPlay']);

// const nowPlayUpdate = (index) => {
//   emits('nowPlay', index);
// }
const nowPlayUpdate = (seq) => {
  emits('nowPlay', seq);
}

// const todo = ref([]);
// const todo = reactive([]);
// const seq = ref(props.todo.seq);

let updateTodoItem = {
  // seq: seq.value,
  seq: props.todo.seq,
  users_seq: props.todo.users_seq,
  title: props.todo.title,
  categories_seq: props.todo.categories_seq,
  status: props.todo.status,
  total_second: String(msTime.value),
  // index: props.index,
  index: store.todos.indexOf(props.todo),
  completed: false,
  time : time,
};

const printUpdateTodoItem = () => {
  console.log(updateTodoItem);
}
const printPropsItem = () => {
  console.log(props.todo);
}

// status 변화 시
// 0 -> 1 : 멈춤 -> 재생 [재생 버튼 클릭 시]
// 0 -> 2 : 멈춤 -> 할일 완료 [완료 버튼 클릭 시]
// 1 -> 0 : 재생 -> 멈춤 [멈춤 버튼 클릭 시]
// 1 -> 2 : 재생 -> 멈춤 & 할일 완료 [재생 중에 완료 버튼 클릭 시]
// 2 -> 0 : 완료 -> 완료 해제 [완료 버튼 해제 시]
// 2 -> 1 : 완료 -> 재생 & 완료 해제 [완료 중에 재생 버튼 클릭 시]

// completed = 할일 완료 여부
// const completed = ref(false);

const clickCompleteTodoItem = () => {
  // 할일 완료하기
  if (!updateTodoItem.completed) {
    updateTodoItem.completed = true;
    updateTodoItem.status = 2;
    stop();
  } 
  // 할일 완료 해제하기
  else {
    updateTodoItem.completed = false;
    updateTodoItem.status = 0;
  }
  updateTodo();
}

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
  }

  else if (updateTodoItem.status == 1) {
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
}

const deleteTodoItem = () => {
  // console.log("delete props.todo");
  // console.log(props.todo);
  store.deleteTodoItem(props.todo);
  // store.deleteTodoItem(updateTodoItem.seq);
}

let updateState = ref(false);
const openUpdateTodo = () => {
  updateState.value = !updateState.value;
}

const updateTodo = () => {

  //   // updateTodoItem.index = props.index;
  // updateTodoItem = {
  //   // seq: seq.value,
  //   seq: props.todo.seq,
  //   users_seq: props.todo.users_seq,
  //   title: props.todo.title,
  //   categories_seq: props.todo.categories_seq,
  //   status: props.todo.status,
  //   total_second: String(msTime.value),
  //   index: props.index,
  //   completed: updateTodoItem.completed,
  //   time : time,
  // };

  // console.log("now updateTodoItem : ");
  // console.log(updateTodoItem);

  store.updateTodoItem({
    "seq": Number(updateTodoItem.seq),
    "users_seq" : Number(updateTodoItem.users_seq),
    "categories_seq" : Number(updateTodoItem.categories_seq),
    "title" : updateTodoItem.title,
    "status" : updateTodoItem.status,
    "total_second" : String(msTime.value),
    "index" : store.todos.indexOf(props.todo),
  });  

  if (updateState.value == true) {
    openUpdateTodo();
  }
  
}

// 말줄임표로 열고 닫기
let ellipsisState = ref(true);
const showFullTodo = () => {
  ellipsisState.value = !ellipsisState.value;
}

// timer test

var timeBegan = null, 
timeStopped = null, 
stoppedDuration = 0, 
started = null, 
running = false;


const start = () => {
  if(running) return;
  
  if (timeBegan === null) {
    reset();
    timeBegan = new Date();
  }

  if (timeStopped !== null) {
    stoppedDuration += (new Date() - timeStopped);
  }

  started = setInterval(clockRunning, 10);	
  running = true;

  nowPlayUpdate(updateTodoItem.seq);
}

const stop = () => {
  running = false;
  timeStopped = new Date();
  clearInterval(started);

  nowPlayUpdate(-1);
}

// 일단은 넣어두지 않은 기능
const reset = () => {
  running = false;
  clearInterval(started);
  stoppedDuration = 0;
  timeBegan = null;
  timeStopped = null;
  time.value = "00:00:00.000";
  // clock.time = "00:00:00.000";
}

const clockRunning = () => {
  var currentTime = new Date()
  , timeElapsed = new Date(currentTime - timeBegan - stoppedDuration)
  , hour = timeElapsed.getUTCHours()
  , min = timeElapsed.getUTCMinutes()
  , sec = timeElapsed.getUTCSeconds()
  , ms = timeElapsed.getUTCMilliseconds();

  msTime.value = timeElapsed.getTime();

  time.value = 
    zeroPrefix(hour, 2) + ":" + 
    zeroPrefix(min, 2) + ":" + 
    zeroPrefix(sec, 2) + "." + 
    zeroPrefix(ms, 3);
}

const zeroPrefix = (num, digit) => {
  var zero = '';
  for(var i = 0; i < digit; i++) {
    zero += '0';
  }
  return (zero + num).slice(-digit);
}

</script>

<style lang=""></style>
