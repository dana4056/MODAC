<script setup>
import Wrapper from "@/components/Wrapper.vue";
import StudyRoomListItem from "@/domains/Room/RoomList/StudyRoomListItem.vue";
import RoomListHeader from "@/domains/Room/RoomList/RoomListHeader.vue";
import OverflowDiv from "@/components/OverflowDiv.vue";
import { useRoomStore } from "@/stores/room.js";
import { useUserStore } from "@/stores/user.js";
import { storeToRefs } from "pinia";

const userStore = useUserStore();
const roomStore = useRoomStore();

const { loginUser } = storeToRefs(userStore);

const { room_list } = storeToRefs(roomStore);
roomStore.api.findRoomList(loginUser.value.seq);
</script>

<template>
  <Wrapper :class="$style.wrapper_flex">
    <aside :class="$style.side_bar_flex_item"></aside>
    <Wrapper :class="$style.section_flex_item">
      <RoomListHeader />
      <OverflowDiv class="h-[80%]">
        <section :class="$style.wrapper_flex_div">
          <StudyRoomListItem
            :class="$style.study_room_flex_item"
            v-for="roomItem in room_list"
            :key="roomItem.seq"
            :roomItem="roomItem"
          />
        </section>
      </OverflowDiv>
    </Wrapper>
    <aside :class="$style.side_bar_flex_item"></aside>
  </Wrapper>
</template>

<style lang="css" module>
@import "./StudyRoomList.module.css";
</style>
