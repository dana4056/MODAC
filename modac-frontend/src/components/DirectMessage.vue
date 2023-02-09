<script setup>
import OverflowDiv from "@/components/OverflowDiv.vue";
import DirectMessageChatRoom from "./DirectMessageChatRoom.vue";
</script>

<template>
  <div :class="$style.container">
    <OverflowDiv>
      <div :class="$style.dm_container">
        <DirectMessageChatRoom />
      </div>
    </OverflowDiv>
    <DirectMessageChatRoom />
  </div>
</template>

<style lang="css" module>
@import "./DirectMessage.module.css";
</style>
