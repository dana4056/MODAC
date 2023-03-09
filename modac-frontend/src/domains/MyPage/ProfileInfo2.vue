<script setup>
import { ref, computed } from "vue";
import { useUserStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import BASE_ASSETS_URL from "@/api/BASE_ASSETS_URL";
import catSkin1 from "@/assets/user_cats_background/cat1.png"
import catSkin2 from "@/assets/user_cats_background/cat2.png"
import catSkin3 from "@/assets/user_cats_background/cat3.png"
import catSkin4 from "@/assets/user_cats_background/cat4.png"
import catSkin5 from "@/assets/user_cats_background/cat5.png"
import catSkin6 from "@/assets/user_cats_background/cat6.png"
import catSkin7 from "@/assets/user_cats_background/cat7.png"
import catSkin8 from "@/assets/user_cats_background/cat8.png"
import catSkin9 from "@/assets/user_cats_background/cat9.png"
import catSkin10 from "@/assets/user_cats_background/cat10.png"
import catSkin11 from "@/assets/user_cats_background/cat11.png"
import catSkin12 from "@/assets/user_cats_background/cat12.png"


const catSkin = computed(() => {
  if( loginUser.value.catSkin === 1){
    return catSkin1
  }
  else if(loginUser.value.catSkin === 2){
    return catSkin2
  }
  else if(loginUser.value.catSkin === 3){
    return catSkin3
  }
  else if(loginUser.value.catSkin === 4){
    return catSkin4
  }
  else if(loginUser.value.catSkin === 5){
    return catSkin5
  }
  else if(loginUser.value.catSkin === 6){
    return catSkin6
  }
  else if(loginUser.value.catSkin === 7){
    return catSkin7
  }
  else if(loginUser.value.catSkin === 8){
    return catSkin8
  }
  else if(loginUser.value.catSkin === 9){
    return catSkin9
  }
  else if(loginUser.value.catSkin === 10){
    return catSkin10
  }
  else if(loginUser.value.catSkin === 11){
    return catSkin11
  }
  else if(loginUser.value.catSkin === 12){
    return catSkin12
  }
});


const userStore = useUserStore();
const { loginUser } = storeToRefs(userStore);
const { followingList } = storeToRefs(userStore);
const { followerList } = storeToRefs(userStore);

const follower = followerList.value.length;
const following = followingList.value.length;

const profileImgUrl = ref(
  BASE_ASSETS_URL + "user_cats/cat" + loginUser.value.catSkin + ".png"
);
</script>

<template lang="">
  <div :class="$style.profile_info_flex">
    <div :class="$style.profile_info_group_row">
      <img :src="catSkin" :class="$style.chat_profile" alt="" />
      <div :class="$style.flex_col">
        <div :class="$style.flex_row">
          <div :class="$style.grade_flex">
            <div :class="$style.grade_circle"></div>
            <div :class="$style.grade_text">
              {{ loginUser.membershipLevel }}
            </div>
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
