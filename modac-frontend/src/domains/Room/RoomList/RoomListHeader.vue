<script setup>
import { ref } from "vue";
import Modal from "@/components/Modal.vue";
import { useRoomStore } from '@/stores/room.js';
import { useUserStore } from '@/stores/user.js';
import { storeToRefs } from "pinia";
const userStore = useUserStore();
const roomStore = useRoomStore();



const search_keyword = ref("")


const { loginUser } = storeToRefs(userStore);

const room_title = ref("");
const room_description = ref("");
const room_theme = ref("기본");
const room_max_size = ref(2);
const room_public_type = ref(1);

// const room_public_type_input = ref("");

const room_add_check_title = ref(false);
const room_add_check_description = ref(false);

const roomAddConfirmModalState = ref(false);
const openRoomAddConfirmModal = () => {
  roomAddConfirmModalState.value = true;
};

const closeRoomAddConfirmModal = (event) => {
  const backdropElement = ref();
  const cancleElement = ref();
  const AddElement = ref();

  backdropElement.value = document.querySelector("#backdrop");
  cancleElement.value = document.querySelector("#cancle")
  AddElement.value = document.querySelector("#add")

  if (backdropElement.value === event.target 
      || cancleElement.value === event.target
      || AddElement.value === event.target) {
    roomAddConfirmModalState.value = false;
  }
};
// const AddRoom = () => {
//   console.log("AddRoom")
// };

const checkByte = (obj) => {
  
  let maxByte = 100; //최대 100바이트
  if(obj.target.id == "room_title") maxByte = 30;
  const text_val = obj.target.value; //입력한 문자
  const text_len = text_val.length; //입력한 문자수

  let totalByte=0;
  for(let i=0; i<text_len; i++) {
    const each_char = text_val.charAt(i);
    const uni_char = escape(each_char); //유니코드 형식으로 변환
    if(uni_char.length>4) {
      // 한글 : 2Byte
        totalByte += 2;
    } else {
      // 영문,숫자,특수문자 : 1Byte
        totalByte += 1;
    }
  }

  if(totalByte>maxByte){
    if(obj.target.id == "room_title") alert('최대 30Byte까지만 입력가능합니다.');
    else alert('최대 100Byte까지만 입력가능합니다.');

    if(obj.target.id == "room_title") room_add_check_title.value = false;
    else if(obj.target.id == "room_description") room_add_check_description.value = false;

    document.getElementById(obj.target.id + "_byte").innerText = totalByte;
    document.getElementById(obj.target.id + "_byte").style.color = "red";
  } else {
    if(obj.target.id == "room_title") {
      if (totalByte != 0) room_add_check_title.value = true;
      else room_add_check_title.value = false;
    }
    else if(obj.target.id == "room_description") {
      if (totalByte != 0) room_add_check_description.value = true;
      else room_add_check_description.value = false;
    }

    document.getElementById(obj.target.id + "_byte").innerText = totalByte;
    document.getElementById(obj.target.id + "_byte").style.color = "green";
  }
}


const createRoom = () => {

  const payload = {
    usersSeq : loginUser.value.seq,
    title : room_title.value, 
    description : room_description.value,
    maxSize : room_max_size.value,
    multiTheme : room_theme.value,
    publicType : room_public_type.value,
  };

  // 룸 생성 요청
  roomStore.api.postRoom(payload);
  roomStore.enterRoom()
}

const room_add_reset = () => {
  room_title.value = "";
  room_description.value = "";
  room_max_size.value = 2;
  room_theme.value = "기본";
  room_public_type.value = "1";
}


const searchRoom = () => {

  const payload = {
    usersSeq: loginUser.value.seq,
    keyword : search_keyword.value
  }

  roomStore.api.searchRoom(payload)
}



</script>

<template>
  <div :class="$style.flex_row_div">
    <div :class="$style.flex_row">
      <h1 :class="$style.page_title">스터디룸 리스트</h1>
      <button type="button" @click="openRoomAddConfirmModal" :class="$style.room_add_button">스터디룸 생성하기 →</button>
    </div>
    <!-- <input type="text" placeholder="원하는 방을 검색해보세요." /> -->
    
    <form :class="$style.searchbar_flex" @submit.prevent="searchRoom">   
        <label for="simple-search" class="sr-only">Search</label>
        <div :class="$style.searchbar_content_bar">
          <div :class="$style.searchbar_content_icon">
            <svg aria-hidden="true" :class="$style.searchbar_content_icon_svg" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
              <path fill-rule="evenodd" d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z" clip-rule="evenodd"></path>
            </svg>
          </div>
          <input @input="event => search_keyword = event.target.value" type="text" id="simple-search" :class="$style.searchbar_content_input" placeholder="원하는 방을 검색해보세요" required>
        </div>
        <button type="submit" :class="$style.searchbar_content_button">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path></svg>
            <span class="sr-only">Search</span>
        </button>
    </form>

  </div>

  <Teleport to="body">
    <Modal
      v-if="roomAddConfirmModalState"
      :closeModal="closeRoomAddConfirmModal"
    >
      
      <div :class="$style.modal_item">
        <h1 :class="$style.modal_title">
          스터디룸 생성하기
          <button
          type="button"
          @click="room_add_reset"
          :class="$style.add_room_button_reset"
          id = "reset">입력 초기화</button>
        </h1>
        

        <div :class="$style.add_room_div">
          <label for="room_title" :class="$style.add_room_label">이름 <span :class="$style.text_red">*</span><br>
            <sup>(<span id="room_title_byte">0</span>/30bytes)</sup>
          </label>
          <input :class="$style.add_room_input" 
                  type="text" 
                  v-model="room_title" 
                  id="room_title" 
                  placeholder="방 이름을 입력하세요" 
                  :onkeyup="checkByte"
                  required>
        </div>
        
        <div :class="$style.add_room_div">
          <label for="room_description" :class="$style.add_room_label">설명 <span :class="$style.text_red">*</span><br>
            <sup>(<span id="room_description_byte">0</span>/100bytes)</sup>
          </label>
          <input :class="$style.add_room_input" 
                  type="text" 
                  v-model="room_description" 
                  id="room_description" 
                  placeholder="방 설명을 입력하세요" 
                  :onkeyup="checkByte"
                  required>
        </div>

        <div :class="$style.add_room_div">
          <label for="room_theme" :class="$style.add_room_label">테마  <span :class="$style.text_red">*</span></label>
                  <!-- @click="showDropdown.value = !showDropdown.value" -->
          <select :class="$style.add_room_input" 
                  v-model="room_theme"
                  id="room_theme">
            <option value="기본" selected="selected">모닥불🔥</option>
            <option value="우주">우주🪐</option>
            <option value="바다">바다🌊</option>
            <option value="사막">사막🌞</option>
          </select>
        </div>

        <div :class="$style.add_room_div">
          <label for="room_max_size" :class="$style.add_room_label">제한 인원 <span :class="$style.text_red">*</span></label>
                  <!-- @click="showDropdown.value = !showDropdown.value" -->
          <select :class="$style.add_room_input" 
                  v-model="room_max_size"
                  id="room_max_size">
            <option value=2 selected="selected">2명 😺😸</option>
            <option value=3>3명 😺😸😻</option>
            <option value=4>4명 😺😸😻😼</option>
            <option value=5>5명 😺😸😻😼😽</option>
            <option value=6>6명 😺😸😻😼😽🙀</option>
          </select>
        </div>

        <div :class="$style.add_room_radio_group_div">
          <label :class="$style.add_room_label">공개 여부 <span :class="$style.text_red">*</span></label>
          <div :class="$style.add_room_radio_div">
            <input id="radio-public" 
                    type="radio" 
                    value=1
                    checked
                    v-model="room_public_type"
                    name="room_public_type" 
                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300">
            <label for="radio-public" :class="$style.add_room_radio_label">&nbsp;공개</label>
          </div>
          
          <div :class="$style.add_room_radio_div">
            <input id="radio-private"
                    type="radio"
                    value=0 
                    v-model="room_public_type"
                    name="room_public_type"
                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300">
            <label for="radio-private" :class="$style.add_room_radio_label">&nbsp;비공개</label>
            
            <!-- <input v-if="room_public_type == 0" :class="$style.add_room_input"
                    type="text"
                    v-model="room_public_type_input"
                    placeholder="초대코드를 입력하세요"
                    required>
                    
            <input v-else :class="$style.add_room_input"
                    type="text"
                    placeholder="초대코드를 입력하세요"
                    disabled> -->
          </div>
        </div>
        
        <p :class="$style.add_room_p">
          제한 인원 및 공개 여부는 생성 후 바꿀 수 없습니다.
        </p>

        <div :class="$style.modal_button_group">
          <button
                  type="button"
                  @click="closeRoomAddConfirmModal"
                  :class="$style.add_room_button_cancle"
                  id = "cancle">취소</button>

                  <!-- type="submit" -->
          <button v-if="room_add_check_title && room_add_check_description"
                  @click="createRoom"
                  :class="$style.add_room_button_add" 
                  id="add">생성</button>
          <button v-else 
                  type="button"
                  :class="$style.add_room_button_add" 
                  id="add"
                  disabled>생성</button>

        </div>
      </div>
    </Modal>
  </Teleport>
</template>

<style lang="css" module>
@import "./RoomListHeader.module.css";
</style>
