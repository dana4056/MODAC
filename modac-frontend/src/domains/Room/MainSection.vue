<script setup>
import Modal from "@/components/Modal.vue";
import { useRoomStore } from "@/stores/room";
import { useUserStore } from "@/stores/user";
import { ref } from "vue";
import CommonButton from "@/components/CommonButton.vue";
import Wrapper from "@/components/Wrapper.vue";
import RoomInformation from "./RoomInformation.vue";
import UserList from "./UserList/UserList.vue";
import { storeToRefs } from "pinia";

const roomStore = useRoomStore();
const { room_info } = storeToRefs(roomStore);
const userStore = useUserStore();
const { loginUser } = storeToRefs(userStore);

const roomExitConfirmModalState = ref(false);
const openRoomExitConfirmModal = () => {
  roomExitConfirmModalState.value = true;
};
const closeRoomExitConfirmModal = (event) => {
  const backdropElement = ref();
  const cancleElement = ref();
  const exitElement = ref();

  backdropElement.value = document.querySelector("#backdrop");
  cancleElement.value = document.querySelector("#cancle");
  exitElement.value = document.querySelector("#exit");

  if (
    backdropElement.value === event.target ||
    cancleElement.value === event.target ||
    exitElement.value === event.target
  ) {
    roomExitConfirmModalState.value = false;
  }
};

const exitRoom = (event) => {
  const payload = {
    seq: room_info.value.seq,
    usersSeq: loginUser.value.seq
  }
  closeRoomExitConfirmModal(event);
  roomStore.exitRoom();
  roomStore.api.exitRoom(payload)
  // roomStore.api.deleteRoom(room_info.value.seq);
};

const categoryValue = ref("기획");
const changeCategoryValue = () => {
  categoryValue.value = "알고리즘";
};
</script>

<template>
  <Wrapper :class="$style.main_section">
    <div :class="$style.main_section_top_side">
      <UserList />
    </div>
    <div :class="$style.main_section_down_side">
      <RoomInformation :openRoomExitConfirmModal="openRoomExitConfirmModal" />
    </div>
  </Wrapper>
  <Teleport to="body">
    <Modal
      v-if="roomExitConfirmModalState"
      :closeModal="closeRoomExitConfirmModal"
    >
      <h1 :class="$style.modal_header">스터디룸에서 나가시겠습니까?</h1>
      <CommonButton
        @click="closeRoomExitConfirmModal"
        :class="$style.cancle_button"
        id="cancle"
        >취소</CommonButton
      >
      <CommonButton @click="exitRoom" :class="$style.exit_button" id="exit"
        >나가기</CommonButton
      >
    </Modal>
  </Teleport>
</template>

<style lang="css" module>
@import "./MainSection.module.css";
</style>
