<script setup>
import RoomSettingsForm from "./RoomSettingsForm.vue";
import RoomSettingsSound from "./RoomSettingsSound.vue";
import CommonButton from "@/components/CommonButton.vue";
import { useRoomStore } from "@/stores/room";
import { storeToRefs } from "pinia";
import { ref } from "vue";

const roomStore = useRoomStore();
const deleteRoom = roomStore.deleteRoom();
const { room_info } = storeToRefs(roomStore)
const inviteCode = ref(room_info.value.invitationCode)

</script>

<template>
  <RoomSettingsForm />
  <!-- <RoomSettingsSound /> -->
  <div :class="$style.skin_change">
    <label :class="$style.room_cat_name">고양이</label>

    <!-- <div :class="$style.고양이">고양이</div> -->
    <select name="" id="" :class="$style.cat_theme_select">
      <option value="cat1">스킨1 - 경계하는 고양이</option>
      <option value="cat2">스킨2 - 헐 고양이</option>
      <option value="cat3">스킨3 - 보라쿠션 고양이</option>
      <option value="cat4">스킨4 - 걸어가는 고양이</option>
      <option value="cat5">스킨5 - 노는 고양이</option>
      <option value="cat6">스킨6 - 항의하는 고양이</option>
      <option value="cat7">스킨7 - 자는 고양이</option>
      <option value="cat8">스킨8 - 쭈욱 고양이</option>
      <option value="cat9">스킨9 - 사고치기 1초 전 고양이</option>
      <option value="cat10">스킨10 - 놀란 아기 고양이</option>
      <option value="cat11">스킨11 - 식빵 고양이</option>
      <option value="cat12">스킨12 - 상자 고양이</option>
    </select>
  </div>
  <div v-if="inviteCode"
  :class="$style.invite_flex">
    <div :class="$style.invite_code">
      <div :class="$style.invite_code_name">초대코드</div>
      <input
        :class="$style.setting_box_input"
        v-model="inviteCode"
        id="inviteCode"
        cols="30"
        rows="10"
        disabled
      />
    </div>
  </div>

  <div :class="$style.edit_cat">
    <CommonButton :class="$style.edit_button"
      >
      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-4 h-4">
        <path stroke-linecap="round" stroke-linejoin="round" d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L10.582 16.07a4.5 4.5 0 01-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 011.13-1.897l8.932-8.931zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0115.75 21H5.25A2.25 2.25 0 013 18.75V8.25A2.25 2.25 0 015.25 6H10" />
      </svg> 고양이 스킨 저장하기</CommonButton
    >
  </div>
  <div :class="$style.delete_room">
    <CommonButton @click="deleteRoom" :class="$style.delete_button"
      >
      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-4 h-4">
        <path stroke-linecap="round" stroke-linejoin="round" d="M14.74 9l-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 01-2.244 2.077H8.084a2.25 2.25 0 01-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 00-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 013.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 00-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 00-7.5 0" />
      </svg> 방 나가기</CommonButton
    >
  </div>
</template>

<style lang="css" module>
@import "RoomSettingsBody.module.css";
</style>
