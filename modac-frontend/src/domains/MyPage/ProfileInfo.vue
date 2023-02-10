<template lang="">
  <div :class="$style.profile_info_flex">
    <div :class="$style.profile_info_group_row">
      <img
        src="https://source.unsplash.com/vpOeXr5wmR4/600x600"
        :class="$style.chat_profile"
        alt=""
      />
      <div :class="$style.flex_col">
        <div :class="$style.flex_row">
          <h1 :class="$style.profile_info_name">김싸피</h1>
          <h3 :class="$style.profile_info_gray">kim@ssafy.com</h3>
        </div>
        <div :class="$style.flex_row">
          <h3 :class="$style.profile_info_gray">팔로우</h3>
          <h3 :class="$style.profile_info_white">10</h3>
          <h3 :class="$style.profile_info_gray">명 | 팔로잉</h3>
          <h3 :class="$style.profile_info_white">8</h3>
          <h3 :class="$style.profile_info_gray">명</h3>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>

</script>

<style lang="css" module>
  @import "./ProfileInfo.module.css";
</style>