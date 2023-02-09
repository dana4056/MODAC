<script setup>
// import RoundButton from "@/components/RoundButton.vue";
import SquareButton from "@/components/SquareButton.vue";
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
  <div :class="$style.flex_wrapper">
    <div>
      <CardTitle>{{ title }}</CardTitle>
      <span>
        <CardContent :class="$style.card_content_inline"
          >({{ currentSize }} / {{ maxSize }})</CardContent
        >
      </span>
      <span>
        <CardContent :class="$style.card_content_inline">{{
          description
        }}</CardContent>
      </span>
    </div>
    <div>
      <SquareButton @click="props.openRoomExitConfirmModal"
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
</template>

<style lang="css" module>
@import "RoomInformation.module.css";
</style>
