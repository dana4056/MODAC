<script setup>
import RoomSettingsForm from "./RoomSettingsForm.vue";
import RoomSettingsSound from "./RoomSettingsSound.vue";
import CommonButton from "@/components/CommonButton.vue";
import { useRoomStore } from "@/stores/room";

const store = useRoomStore();
const deleteRoom = store.deleteRoom();
</script>

<template>
  <div :class="$style.setting_box_body">
    <div :class="$style.setting_box_size1">
      <div :class="$style.setting_box_size2">
        <RoomSettingsForm />
        <RoomSettingsSound />
        <div :class="$style.skin_change">
          <div :class="$style.고양이">고양이</div>
          <select name="" id="">
            <option value="cat1">스킨1</option>
            <option value="cat2">스킨2</option>
            <option value="cat3">스킨3</option>
          </select>
        </div>
        <div :class="$style.delete_room">
          <CommonButton @click="deleteRoom" :class="$style.delete_button"
            >삭제하기</CommonButton
          >
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="css" module>
@import "RoomSettingsBody.module.css";
</style>
