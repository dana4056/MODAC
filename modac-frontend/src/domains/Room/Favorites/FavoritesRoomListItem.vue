<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import CardContent from "@/components/CardContent.vue";
import CommonButton from "@/components/CommonButton.vue";
import Wrapper from "@/components/Wrapper.vue";
import { storeToRefs } from "pinia";
import { useRoomStore } from "@/stores/room";
import { useUserStore } from "@/stores/user";
import { defineProps } from "vue";
const roomStore = useRoomStore();
const userStore = useUserStore();
const { loginUser } = storeToRefs(userStore);

const props = defineProps({
  favoritesRoom: Object,
});

const roomChange = async() => {
  const payload = {
        seq: room_info.value.seq,
        usersSeq: loginUser.value.seq,
        attend: false
      }
      roomStore.api.updateCurrentRoom(payload)
      roomStore.disconnectSocket(); // 기존 소켓 해제
  await roomstore.enterRoom(props.favoritesRoom.seq);
}

const roomDelete = () => {
  roomStore.deleteRoom(props.favoritesRoom.seq)
  .then(() => {
    roomStore.api.findPrivateRoomList(loginUser.value.seq)
  })
}

</script>

<template>
  <Card :class="$style.flex_wrapper">
    <Wrapper :class="$style.flex_left">
      <CardTitle>
        {{ props.favoritesRoom.title }}
      </CardTitle>
      <CardContent>
        {{ props.favoritesRoom.description }}
      </CardContent>
      <CardContent>방장 {{ props.favoritesRoom.host.nickname }}</CardContent>
      <CardContent> 테마 {{ props.favoritesRoom.multiTheme }}</CardContent>
    </Wrapper>
    <Wrapper :class="$style.flex_right">
      <CommonButton :class="$style.enter_button" @click="roomChange">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-4 h-4">
          <path stroke-linecap="round" stroke-linejoin="round" d="M4.5 12h15m0 0l-6.75-6.75M19.5 12l-6.75 6.75" />
        </svg>
        <span>&nbsp;입장하기</span>
      </CommonButton>
      <CommonButton :class="$style.exit_button" @click="roomDelete">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-4 h-4">
          <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
        </svg>
        <span>&nbsp;즐겨찾기 해제</span>
      </CommonButton>
    </Wrapper>
  </Card>
</template>

<style lang="css" module>
@import "./FavoritesRoomListItem.module.css";
</style>
