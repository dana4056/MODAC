<script setup>
import RoundButton from "@/components/RoundButton.vue";
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import CardContent from "@/components/CardContent.vue";
import { defineProps } from "vue";
import { storeToRefs } from "pinia";
import { useRoomStore } from "@/stores/room";

const props = defineProps({
  openRoomExitConfirmModal: Function,
});

const store = useRoomStore();
const { title, currentSize, maxSize, description } = storeToRefs(store);
</script>

<template>
  <Card :class="$style.flex_wrapper">
    <div>
      <CardTitle>{{ title }}</CardTitle>
      <CardContent :class="$style.card_content_inline"
        >({{ currentSize }} / {{ maxSize }})</CardContent
      >
      <CardContent :class="$style.card_content_inline">{{
        description
      }}</CardContent>
    </div>
    <div>
      <RoundButton @click="props.openRoomExitConfirmModal"
        >방 나가기</RoundButton
      >
    </div>
  </Card>
</template>

<style lang="css" module>
@import "RoomInformation.module.css";
</style>
