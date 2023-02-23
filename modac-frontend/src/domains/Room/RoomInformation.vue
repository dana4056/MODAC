<script setup>
// import RoundButton from "@/components/RoundButton.vue";
import SquareButton from "@/components/SquareButton.vue";
// import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import CardContent from "@/components/CardContent.vue";
import { defineProps } from "vue";
import { storeToRefs } from "pinia";
import { useRoomStore } from "@/stores/room";
import { useUserStore } from "@/stores/user";

const props = defineProps({
  openRoomExitConfirmModal: Function,
});

const roomStore = useRoomStore();
const { room_info, isEnteredRoom } = storeToRefs(roomStore);
const userStore = useUserStore();
const { loginUser } = storeToRefs(userStore);

const exitRoom = (event) => {
  // 공개방
  if (room_info.value.publicType === 1) {
    console.log("RoomInformation - 공개방 나가기 클릭")
    props.openRoomExitConfirmModal(event);
  }

  // 비공개 방
  else if (room_info.value.publicType === 0) {
    console.log("RoomInformation - 비공개방 나가기 클릭")
    console.log(loginUser.value.seq)
    const payload = {
      seq: room_info.value.seq,
      usersSeq: loginUser.value.seq,
      attend: false
    }
    roomStore.api.updateCurrentRoom(payload)
    roomStore.exitPrivateRoom();
  }
};



</script>

<template>
  <div :class="$style.flex_wrapper">
    <div :class="$style.room_info_and_button">
      <div :class="$style.room_info_div">
        <span :class="$style.room_info_title_size">
        <CardTitle>{{ room_info.title }}</CardTitle>
          <CardContent :class="$style.card_content_inline"
            >({{ room_info.currentSize }} / {{ room_info.maxSize }})</CardContent
          >
        </span>
        <span>
          <CardContent :class="$style.card_content_inline">{{
            room_info.description
          }}</CardContent>
        </span>
      </div>
      <div>
        <SquareButton @click="exitRoom"
          ><svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            stroke-width="3"
            stroke="currentColor"
            class="w-6 h-6 text-white"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              d="M9 15L3 9m0 0l6-6M3 9h12a6 6 0 010 12h-3"
            />
          </svg>
        </SquareButton>
      </div>
    </div>
  </div>
</template>

<style lang="css" module>
@import "RoomInformation.module.css";
</style>
