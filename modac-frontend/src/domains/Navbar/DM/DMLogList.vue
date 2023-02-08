<script setup>
import { useDmStore } from "@/stores/dm";
import { ref } from "vue";
import { storeToRefs } from "pinia";

const DMstore = useDmStore();
const { liveDirectMessages } = storeToRefs(DMstore);
const logs = ref([]);
console.log(liveDirectMessages.value);


const dateStyle = computed(() => {

  const h = props.chatLog.sendTime.slice(11,13);
  const m = props.chatLog.sendTime.slice(14,16);
  const s = props.chatLog.sendTime.slice(17);

  return h+":"+m;
})


</script>

<template>
  <!-- <div>
    <div>{{ liveDirectMessages }}</div>
    <div>{{ logs }}</div>
  </div> -->

  <div>
    <small>{{ dateStyle }}</small>
    <div :class="contentStyle">{{ props.chatLog.message }}</div>
    <div>
      <small v-if="props.chatLog.user.nickname != props.loginUser.nickname">{{props.chatLog.user.nickname}}</small>
      <img
        src="https://source.unsplash.com/vpOeXr5wmR4/600x600"
        :class="$style.chat_profile"
        alt=""
      />
    </div>
  </div>
</template>

<style lang="css" module>
@import "DMLogList.module.css";
</style>
