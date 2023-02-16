<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import { storeToRefs } from "pinia";

const userStore = useUserStore();
const { loginUser } = storeToRefs(userStore)
const { followingList } = storeToRefs(userStore)
const { followerList } = storeToRefs(userStore)

const follower = followerList.value.length
const following = followingList.value.length


const profileImgUrl = ref(
  "/src/assets/user_cats/cat" + loginUser.value.catSkin + ".png"
);

</script>

<template lang="">
  <div :class="$style.profile_info_flex">
    <div :class="$style.profile_info_group_row">
      <img
        :src="profileImgUrl"
        :class="$style.chat_profile"
        alt=""
      />
      <div :class="$style.flex_col">
        <div :class="$style.flex_row">
          <div :class="$style.grade_flex">
            <div :class="$style.grade_circle"></div>
            <div :class="$style.grade_text">{{ loginUser.membershipLevel }}</div>
          </div>
          <h1 :class="$style.profile_info_name">{{ loginUser.nickname }}</h1>
          <h3 :class="$style.profile_info_gray">{{ loginUser.email }}</h3>
        </div>
        <div :class="$style.flex_row">
          <h3 :class="$style.profile_info_gray">팔로우</h3>
          <h3 :class="$style.profile_info_white">{{ follower }}</h3>
          <h3 :class="$style.profile_info_gray">명 | 팔로잉</h3>
          <h3 :class="$style.profile_info_white">{{ following }}</h3>
          <h3 :class="$style.profile_info_gray">명</h3>
        </div>
      </div>
    </div>
  </div>
</template>



<style lang="css" module>
  @import "./ProfileInfo2.module.css";
</style>