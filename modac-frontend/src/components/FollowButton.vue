<script setup>
import { computed, onMounted, useCssModule } from 'vue'
import { useUserStore } from "@/stores/user.js";
import { useFeedStore } from "@/stores/feed.js";
import { storeToRefs } from "pinia";
import userAPI from "@/api/user.js";

const userStore = useUserStore();
const feedStore = useFeedStore();
const { loginUser } = storeToRefs(userStore);
const { isFollowing } = storeToRefs(userStore);
const { article } = storeToRefs(feedStore);
let feedArticle = article.value;


// let is_followed = ref(false)
let followbtn = null
const button_content = computed(() => isFollowing.value ? '친구 해제' : '친구 추가')
const button_css = computed(() => isFollowing.value ? 'unfollow_to_follow' : 'follow_to_unfollow')

const follow = async () => {
  console.log('follow');
  
  console.log("from", loginUser.value.seq);
  console.log("to", feedArticle.user.seq);
  const payload = {
    fromSeq : loginUser.value.seq,
    toSeq : feedArticle.user.seq
  }
  await userAPI.following(payload);

  isFollowing.value = true
  console.log("isFollowing.value", isFollowing.value);

  followbtn.classList.remove($style.follow_to_unfollow)
  followbtn.classList.add($style.unfollow_to_follow)
}

const unfollow = async () => {
  console.log('unfollow');

// 이거 어케 알고 보내줄 건지 물어봐야 함
  const followSeq = null;

  await userAPI.unfollowing(followSeq);
  
  isFollowing.value = false
  console.log("isFollowing.value", isFollowing.value);
  followbtn.classList.add($style.follow_to_unfollow)
  followbtn.classList.remove($style.unfollow_to_follow)
}

const followOrUnfollow = () => {
  isFollowing.value ? unfollow(): follow()
  console.log("isFollowing.value", isFollowing.value);
}

const $style = useCssModule()

onMounted(async() => {
  followbtn = document.querySelector('#follow_btn');
  // await followOrUnfollow();
  // console.log("isFollowing.value2", isFollowing.value);
})


// followOrUnfollow();
</script>

<template>
  <!-- :class="$style.follow_to_unfollow" -->
<button id="follow_btn"
  :class="$style[button_css]"
  @click="followOrUnfollow">
  {{ button_content  }}
  <!-- <slot></slot> -->
</button>

</template>

<style lang="css" module>
@import "FollowButton.module.css";
</style>