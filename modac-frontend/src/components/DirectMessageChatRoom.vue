<script setup></script>

<template>
  <ul>
    <li :class="$style.dm_items">
      <div :class="$style.dm_items_left">
        <img
          src="https://i.imgur.com/aq39RMA.jpg"
          :class="$style.profile_img"
        />
        <div :class="$style.dm_items_left_items">
          <span :class="$style.friend_name">Jessica Koel</span>
          <span :class="$style.last_text"
            >Hey, Joel, I here to help you out please tell me</span
          >
        </div>
      </div>
      <div :class="$style.dm_items_right">
        <span :class="$style.time">11:26</span>
      </div>
    </li>
    <li :class="$style.dm_items">
      <div :class="$style.dm_items_left">
        <img
          src="https://i.imgur.com/eMaYwXn.jpg"
          :class="$style.profile_img"
        />
        <div :class="$style.dm_items_left_items">
          <span :class="$style.friend_name">Komeial Bolger</span>
          <span :class="$style.last_text"
            >I will send you all documents as soon as possible</span
          >
        </div>
      </div>
      <div :class="$style.dm_items_right">
        <span :class="$style.time">12:26</span>
      </div>
    </li>
  </ul>
</template>

<style lang="css" module>
@import "DirectMessageChatRoom.module.css";
</style>
