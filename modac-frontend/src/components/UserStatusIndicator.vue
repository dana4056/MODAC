<script setup>
import { useRoomStore } from "@/stores/room";
import { computed, useCssModule, defineProps } from "vue";

const $style = useCssModule();

const store = useRoomStore();

const props = defineProps({
  status: Number,
});

const userStatusIndicatorStyleState = computed(
  () => `${$style.rounded_div}  ${$style.inline_block_style}
  ${$style[store.statusStyleMap[props.status]]}`
);
</script>

<template>
  <div :class="userStatusIndicatorStyleState"></div>
</template>

<style lang="css" module>
@import "UserStatusIndicator.module.css";
</style>
