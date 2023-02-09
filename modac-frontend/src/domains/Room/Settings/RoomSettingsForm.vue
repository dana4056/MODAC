<script setup></script>

<template>
  <form>
    <div :class="$style.room_title">
      <div :class="$style.room_title_name">제목</div>
      <input
        :class="$style.setting_box_input"
        name=""
        id=""
        cols="30"
        rows="10"
        placeholder="방 제목입니다."
      />
    </div>

    <div :class="$style.room_description">
      <label :class="$style.room_description_name" for="title">설명</label>
      <input
        :class="$style.setting_box_input"
        name=""
        id="title"
        cols="30"
        rows="10"
        placeholder="방 설명입니다."
      />
    </div>

    <div :class="$style.room_theme">
      <div>테마</div>
      <select name="" id="">
        <option value="modac1">모닥불1</option>
        <option value="modac2">모닥불2</option>
        <option value="modac3">모닥불3</option>
      </select>
    </div>
  </form>
</template>

<style lang="css" module>
@import "RoomSettingsForm.module.css";
</style>
