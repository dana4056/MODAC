<script setup>
import { useDmStore, ref } from "@/stores/dm";
import { storeToRefs } from "pinia";

const store = useDmStore();
const { liveDirectMessageRoomNumber } = storeToRefs(store);
const logs = ref([]);
console.log(liveDirectMessageRoomNumber.value);
</script>

<template>
  <div>
    <div>{{ liveDirectMessageRoomNumber }}</div>
    <div>{{ logs }}</div>
  </div>
</template>

<style lang="css" module>
@import "DMLogList.module.css";
</style>
