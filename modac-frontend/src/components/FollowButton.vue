<script setup>
import { ref, onMounted } from 'vue'
let is_followed = ref(false)
let followbtn = null

const followOrUnfollow = () => {
  is_followed.value ? unfollow(): follow()
}


const follow = () => {
  is_followed.value = true
  followbtn.classList.add('button_unfollow')
  followbtn.classList.remove('button_follow')
}

const unfollow = () => {
  is_followed.value = false
  followbtn.classList.remove('button_unfollow')
  followbtn.classList.add('button_follow')
}

onMounted(() => {
  followbtn = document.querySelector('#follow_btn')
})

</script>

<template>
  <button id="follow_btn" class="button_follow" data-follow="팔로우" data-unfollow="팔로잉" @click="followOrUnfollow">
  </button>
</template>

<style lang="css">
@import "./FollowButton.module.css";
</style>
