<script setup>
import { defineProps , computed, useCssModule } from "vue";

const props = defineProps({
  chatRoom: Object,
  getMessages: Function,
});

const $style = useCssModule();

const lastTime = computed(() => {

  const h = props.chatRoom.lastMessageTime.slice(11,13);
  const m = props.chatRoom.lastMessageTime.slice(14,16);
  const s = props.chatRoom.lastMessageTime.slice(17);

  return h+":"+m;
})

</script>

<template>
  <li :class="$style.dm_items" @click="getMessages(props.chatRoom.seq)">
    <div :class="$style.dm_items_left">
      <img
        src="https://source.unsplash.com/vpOeXr5wmR4/600x600"
        :class="$style.profile_img"
        alt=""
      />
      <div :class="$style.dm_items_left_items">
        <span :class="$style.friend_name">{{ props.chatRoom.talker.nickname }}</span>
        <span :class="$style.last_text">{{ props.chatRoom.lastMessage }}</span>
      </div>
    </div>
    <div :class="$style.dm_items_right">
      <span :class="$style.time">{{lastTime}}</span>
    </div>
  </li>
</template>

<style lang="css" module>
@import "DMRoomListItem.module.css";
</style>
