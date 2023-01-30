<template>
  <div :class="$style.setting_box">

    <!-- header -->
    <div :class="$style.setting_box_header">
      <div :class="$style.setting_box_name">방 정보 수정</div>
    </div>
    <!-- end header -->

    <!-- body -->
    <div :class="$style.setting_box_body">
      <div :class="$style.setting_box_size1">
        <div :class="$style.setting_box_size2">

          <div :class="$style.room_title">
            <div :class="$style.room_title_name">제목</div>
            <input :class="$style.setting_box_input" name="" id="" cols="30" rows="10" placeholder="방 제목입니다.">
          </div>
          
          <div :class="$style.room_description">
            <div :class="$style.room_description_name">설명</div>
            <input :class="$style.setting_box_input" name="" id="" cols="30" rows="10" placeholder="방 설명입니다.">
          </div>
          
          <div :class="$style.room_theme">
            <div :class="$style.theme_title">테마</div>
            <select name="" id="">
              <option value="modac1">모닥불1</option>
              <option value="modac2">모닥불2</option>
              <option value="modac3">모닥불3</option>
            </select>
          </div>
          
          <div :class="$style.Sound_Controller">
            
            <div :class="$style.기본_음악">
              <label for="small-range" :class="$style.sound_bar_label">음악 볼륨</label>
              <input id="small-range" type="range" value="50" :class="$style.sound_bar_range">
            </div>
            
            <div :class="$style.ASMR">
              <label for="small-range" :class="$style.sound_bar_label">ASMR 볼륨</label>
              <input id="small-range" type="range" value="50" :class="$style.sound_bar_range">
            </div>
            
          </div>
          
          <div :class="$style.skin_change">
            <div :class="$style.고양이">고양이</div>
            <select name="" id="">
              <option value="cat1">스킨1</option>
              <option value="cat2">스킨2</option>
              <option value="cat3">스킨3</option>
            </select>
          </div>

          <div :class="$style.delete_room">
            <div :class="$style.방_삭제">방 삭제하기</div>
            <CommonButton @click="deleteRoom" :class="$style.delete_Button"
              >삭제하기</CommonButton
            >
          </div>

        </div>
      </div>
    </div>

  </div>
</template>

<script setup>
import CommonButton from "@/components/CommonButton.vue";
import { useRoomStore } from "@/stores/room";


const store = useRoomStore();
const deleteRoom = store.deleteRoom()


</script>

<style module>
@import './RoomSettings.module.css'
</style>