<script setup>
import OverflowDiv from "@/components/OverflowDiv.vue";
import DMRoomList from "./DMRoomList.vue";
import DMLogList from "./DMLogList.vue";
import DMForm from "@/domains/Navbar/DM/DMForm.vue"
import { useDmStore } from "@/stores/dm";
import { storeToRefs } from "pinia";

const DMstore = useDmStore();
const { directMessageRoomSeq } = storeToRefs(DMstore);

</script>

<template>
  <div :class="$style.container">
    <OverflowDiv :class="$style.dm_container_wrap">
      <div :class="$style.dm_container">
        <DMRoomList />
      </div>
    </OverflowDiv>
    <div v-if="directMessageRoomSeq != 0"  :class="$style.dm_chatList">
      <DMLogList />
    </div>
    <div v-else :class="$style.dm_none_box">
      채팅목록을 선택해주세요 :-)
    </div>
  </div>
</template>

<style lang="css" module>
@import "./DMDropdown.module.css";
</style>
