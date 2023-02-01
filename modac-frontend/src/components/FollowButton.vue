<script setup>
import { ref } from 'vue'
let is_followed = ref(false)
const follow = () => {
  is_followed.value ? is_followed.value = false : is_followed.value = true
}
</script>

<template>
  <button :class="$style.btn_flip" data-back="follow" data-front="Unfollow">
    <slot></slot>
  </button>
</template>

<style lang="css" module>
@import "./FollowButton.module.css";
</style>
