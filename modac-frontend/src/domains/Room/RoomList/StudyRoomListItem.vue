<script setup>
import Card from "@/components/Card.vue";
import { defineProps, toRefs, ref } from "vue";
import { useRoomStore } from '@/stores/room.js';
import { useUserStore } from '@/stores/user.js';
import { storeToRefs } from "pinia";
import Modal from "@/components/Modal.vue";


const userStore = useUserStore();
const roomStore = useRoomStore();

const { loginUser } = storeToRefs(userStore);

const props = defineProps({
  roomItem: Object,
});

const roomCodeInput = ref("");

const { roomItem } = toRefs(props)

const enterRoom = async () => {
  let payload = {
    seq: roomItem.value.seq,
    roomCode: roomCodeInput,
  }
  const res = await roomStore.api.checkRoomCode(payload);

  if (res && roomItem.value.maxSize > roomItem.value.currentSize) {  
    roomStore.enterRoom();
    payload = {
      seq: roomItem.value.seq,
      usersSeq: loginUser.value.seq
    }
    console.log("페이로드", payload)
    roomStore.api.joinRoom(payload)
  }
  else {
    alert("비밀번호가 일치하지 않습니다.");
    // console.log("비밀번호가 일치하지 않습니다.")
  }
}

const roomEnterConfirmModalState = ref(false);
const openRoomEnterConfirmModal = () => {
  roomEnterConfirmModalState.value = true;
};

const closeRoomEnterConfirmModal = (event) => {
  const backdropElement = ref();
  const cancleElement = ref();
  const AddElement = ref();

  backdropElement.value = document.querySelector("#backdrop");
  cancleElement.value = document.querySelector("#cancle")
  AddElement.value = document.querySelector("#add")

  if (backdropElement.value === event.target 
      || cancleElement.value === event.target
      || AddElement.value === event.target) {
    roomEnterConfirmModalState.value = false;
  }
};
</script>

<template>
  <Card :class="$style.list_item_style">
      <div :class="$style.list_item_div_col">
        <!-- <div :class="$style.item_seq">{{ roomItem.seq }}</div> -->
        <div :class="$style.item_title">

          <span v-if="roomItem.publicType == 1">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-4 h-4 inline-block">
              <path d="M18 1.5c2.9 0 5.25 2.35 5.25 5.25v3.75a.75.75 0 01-1.5 0V6.75a3.75 3.75 0 10-7.5 0v3a3 3 0 013 3v6.75a3 3 0 01-3 3H3.75a3 3 0 01-3-3v-6.75a3 3 0 013-3h9v-3c0-2.9 2.35-5.25 5.25-5.25z" />
            </svg>
          </span>

          <span v-else>
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-4 h-4 inline-block">
              <path fill-rule="evenodd" d="M12 1.5a5.25 5.25 0 00-5.25 5.25v3a3 3 0 00-3 3v6.75a3 3 0 003 3h10.5a3 3 0 003-3v-6.75a3 3 0 00-3-3v-3c0-2.9-2.35-5.25-5.25-5.25zm3.75 8.25v-3a3.75 3.75 0 10-7.5 0v3h7.5z" clip-rule="evenodd" />
            </svg>
          </span>
          {{ roomItem.title }}
          <span :class="$style.item_size">
            ({{ roomItem.currentSize }} / {{ roomItem.maxSize }})
          </span>
        </div>
        <div :class="$style.item_description">
          {{ roomItem.description }}
        </div>
        <div :class="$style.item_host_theme">
          방장 &nbsp; <span :class="$style.item_host_theme_name">{{ roomItem.host.nickname }}</span>
          &nbsp; | &nbsp; 테마 &nbsp; 
          <span v-if="roomItem.multiTheme === '기본'" :class="$style.item_host_theme_name">
            모닥불🔥
          </span>
          <span v-if="roomItem.multiTheme === '우주'" :class="$style.item_host_theme_name">
            우주🪐
          </span>
          <span v-if="roomItem.multiTheme === '바다'" :class="$style.item_host_theme_name">
            바다🌊
          </span>
          <span v-if="roomItem.multiTheme === '사막'" :class="$style.item_host_theme_name">
            사막🌞
          </span>
        </div>
        <button @click="openRoomEnterConfirmModal" :class="$style.item_enter_button">입장하기</button>
      </div>
  </Card>


  
  <Teleport to="body">
    <Modal
      v-if="roomEnterConfirmModalState"
      :closeModal="closeRoomEnterConfirmModal"
    >
      
      <div :class="$style.modal_item">
        <h1 :class="$style.modal_title">
          입장하기
        </h1>
        


        
        <Card :class="$style.list_item_style">
          <div :class="$style.list_item_div_col">
            <!-- <div :class="$style.item_seq">{{ roomItem.seq }}</div> -->
            <div :class="$style.item_title">

              <span v-if="roomItem.publicType == 1">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-4 h-4 inline-block">
                  <path d="M18 1.5c2.9 0 5.25 2.35 5.25 5.25v3.75a.75.75 0 01-1.5 0V6.75a3.75 3.75 0 10-7.5 0v3a3 3 0 013 3v6.75a3 3 0 01-3 3H3.75a3 3 0 01-3-3v-6.75a3 3 0 013-3h9v-3c0-2.9 2.35-5.25 5.25-5.25z" />
                </svg>
              </span>

              <span v-else>
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-4 h-4 inline-block">
                  <path fill-rule="evenodd" d="M12 1.5a5.25 5.25 0 00-5.25 5.25v3a3 3 0 00-3 3v6.75a3 3 0 003 3h10.5a3 3 0 003-3v-6.75a3 3 0 00-3-3v-3c0-2.9-2.35-5.25-5.25-5.25zm3.75 8.25v-3a3.75 3.75 0 10-7.5 0v3h7.5z" clip-rule="evenodd" />
                </svg>
              </span>
              {{ roomItem.title }}
              <span :class="$style.item_size">
                ({{ roomItem.currentSize }} / {{ roomItem.maxSize }})
              </span>
            </div>
            <div :class="$style.item_description">
              {{ roomItem.description }}
            </div>
            <div :class="$style.item_host_theme">
              방장 &nbsp; <span :class="$style.item_host_theme_name">{{ roomItem.host.nickname }}</span>
              &nbsp; | &nbsp; 테마 &nbsp; 
              <span v-if="roomItem.multiTheme === '기본'" :class="$style.item_host_theme_name">
                모닥불🔥
              </span>
              <span v-if="roomItem.multiTheme === '우주'" :class="$style.item_host_theme_name">
                우주🪐
              </span>
              <span v-if="roomItem.multiTheme === '바다'" :class="$style.item_host_theme_name">
                바다🌊
              </span>
              <span v-if="roomItem.multiTheme === '사막'" :class="$style.item_host_theme_name">
                사막🌞
              </span>
            </div>
          </div>
        </Card>

        <p :class="$style.enter_room_p">
          이 방에 입장하시겠습니까?
        </p>


        <div :class="$style.add_room_div" v-if="roomItem.publicType == 0">
          <div :class="$style.add_room_row">
            <label for="room_title" :class="$style.add_room_label">초대코드 <span :class="$style.text_red">*</span><br>
            </label>
            <input :class="$style.add_room_input"
                    type="text"
                    v-model="roomCodeInput"
                    placeholder="초대코드를 입력하세요"
                    required>
          </div>
          <p :class="$style.add_room_p">
            비공개 방은 초대코드 입력 후 입장 가능합니다.
          </p>
        </div>
        
<!--                     
            <input v-else :class="$style.add_room_input"
                    type="text"
                    placeholder="초대코드를 입력하세요"
                    disabled> -->
        

        <div :class="$style.modal_button_group">
          <button
                  type="button"
                  @click="closeRoomEnterConfirmModal"
                  :class="$style.add_room_button_cancle"
                  id = "cancle">취소</button>

                  <!-- type="submit" -->
          <button @click="enterRoom"
                  :class="$style.add_room_button_add" 
                  id="add">입장</button>

        </div>
      </div>
    </Modal>
  </Teleport>
</template>

<style lang="css" module>
@import "./StudyRoomListItem.module.css";
</style>
