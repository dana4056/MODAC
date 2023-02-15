<script setup>
import { ref } from 'vue';
import CommonButton from "@/components/CommonButton.vue";
import { useRoomStore } from '@/stores/room.js';
import { storeToRefs } from "pinia";

const roomStore = useRoomStore();
const { room_info } = storeToRefs(roomStore)

const room_title = ref(room_info.value.title)
const room_description = ref(room_info.value.description)
const room_multiTheme = ref(room_info.value.multiTheme)



const updateRoom = () => {
  const payload = {
    seq: room_info.value.seq,
    data: {
      title: room_title.value,
      description: room_description.value,
      multiTheme: room_multiTheme.value
    }
  }
  roomStore.api.updateRoom(payload)
}


</script>

<template>
  <form :class="$style.room_setting_form">
    <div :class="$style.room_title">
      <div :class="$style.room_title_name">제목</div>
      <input
        :class="$style.setting_box_input"
        v-model="room_title"
        cols="30"
        rows="10"
      />
    </div>

    <div :class="$style.room_description">
      <div :class="$style.room_description_name">설명</div>
      <input
        :class="$style.setting_box_input"
        v-model="room_description"
        id="title"
        cols="30"
        rows="10"
        placeholder="방 설명입니다."
      />
    </div>

    <div :class="$style.room_theme">
      <label :class="$style.room_theme_name">테마</label>
      <select name="" id="" v-model="room_theme" :class="$style.room_theme_select">
        <option value="기본" selected="selected">모닥불🔥</option>
        <option value="우주">우주🪐</option>
        <option value="바다">바다🌊</option>
        <option value="사막">사막🌞</option>
      </select>
    </div>
    <CommonButton :class="$style.update_button"
      @click.prevent="updateRoom"
      >
      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-4 h-4">
        <path stroke-linecap="round" stroke-linejoin="round" d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L10.582 16.07a4.5 4.5 0 01-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 011.13-1.897l8.932-8.931zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0115.75 21H5.25A2.25 2.25 0 013 18.75V8.25A2.25 2.25 0 015.25 6H10" />
      </svg>방 정보 수정하기</CommonButton
    >
  </form>
</template>

<style lang="css" module>
@import "RoomSettingsForm.module.css";
</style>
