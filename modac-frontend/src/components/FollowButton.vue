<script setup>
import { computed, ref, onMounted, useCssModule } from 'vue'

let is_followed = ref(false)
let followbtn = null
const button_content = computed(() => is_followed.value ? '언팔' : '팔로')


const followOrUnfollow = () => {
  console.log(is_followed.value);
  is_followed.value ? unfollow(): follow()
}

const $style = useCssModule()

const follow = () => {
  console.log('1');
  is_followed.value = true
  followbtn.classList.remove($style.follow_to_unfollow)
  followbtn.classList.add($style.unfollow_to_follow)
}

const unfollow = () => {
  console.log('2');
  is_followed.value = false
  followbtn.classList.add($style.follow_to_unfollow)
  followbtn.classList.remove($style.unfollow_to_follow)
}

onMounted(() => {
  followbtn = document.querySelector('#follow_btn')
})
</script>

<template>
<button id="follow_btn"
  :class="$style.follow_to_unfollow"
  @click="followOrUnfollow">
  {{ button_content  }}
</button>

</template>

<style lang="css" module>
@import "FollowButton.module.css"
</style>