<script setup>
import { ref } from 'vue';

let room_title = ref("");
let room_description = ref("");
let room_theme = ref("기본");




</script>

<template>
  <form>
    <div :class="$style.room_title">
      <div :class="$style.room_title_name">제목</div>
      <input
        :class="$style.setting_box_input"
        v-model="room_title"
        cols="30"
        rows="10"
        placeholder="방 제목입니다."
      />
    </div>

    <div :class="$style.room_description">
      <label :class="$style.room_description_name" for="title">설명</label>
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
  </form>
</template>

<style lang="css" module>
@import "RoomSettingsForm.module.css";
</style>
