<script setup>
import { defineProps, computed, useCssModule } from "vue";

const props = defineProps({

});

const $style = useCssModule();

const dateStyle = computed(() => {

    const h = props.chatLog.sendTime.slice(11,13);
    const m = props.chatLog.sendTime.slice(14,16);
    const s = props.chatLog.sendTime.slice(17);

    return h+":"+m;
})



</script>

<template>
  <ul>
    <DMRoomListItem
      v-for="roomItem in directMessageRoomList"
      :key="roomItem.seq"
      :chatRoom="roomItem"
      :seq="roomItem.seq"
      :getItemSeq="getItemSeq"
    />
  </ul>
</template>

<style lang="css" module>
@import "DMRoomList.module.css";
</style>
