<script setup>
import RoundButton from "@/components/RoundButton.vue";
import Modal from "@/components/Modal.vue";
import { useRoomStore } from "@/stores/room";
import { ref } from "vue";
import CommonButton from "@/components/CommonButton.vue";
import CategoryTagCopy from "@/components/CategoryTagCopy.vue";

const store = useRoomStore();

const roomExitConfirmModalState = ref(false);
const openRoomExitConfirmModal = () => {
  roomExitConfirmModalState.value = true;
};
const closeRoomExitConfirmModal = () => {
  roomExitConfirmModalState.value = false;
};

const exitRoom = () => {
  closeRoomExitConfirmModal();
  store.exitRoom();
};

const categoryValue = ref("기획");
const changeCategoryValue = () => {
  categoryValue.value = "알고리즘";
};
</script>

<template>
  <div :class="$style.main_section">
    <CategoryTagCopy>{{ categoryValue }}</CategoryTagCopy>
    <button @click="changeCategoryValue">카테고리변경</button>
  </div>

  <RoundButton @click="openRoomExitConfirmModal">방 나가기</RoundButton>
  <Teleport to="body">
    <Modal
      v-if="roomExitConfirmModalState"
      :closeModal="closeRoomExitConfirmModal"
    >
      <h1 :class="$style.modal_header">스터디룸에서 나가시겠습니까?</h1>
      <CommonButton
        @click="closeRoomExitConfirmModal"
        :class="$style.cancle_button"
        >취소</CommonButton
      >
      <CommonButton @click="exitRoom" :class="$style.exit_button"
        >나가기</CommonButton
      >
    </Modal>
  </Teleport>
</template>

<style lang="css" module>
@import "./MainSection.module.css";
</style>
