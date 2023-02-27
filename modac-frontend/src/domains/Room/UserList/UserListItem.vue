<script setup>
import CardTitle from "@/components/CardTitle.vue";
import { ref, computed, defineProps, useCssModule } from "vue";
import catSkin1 from "@/assets/user_cats/cat1.png";
import catSkin2 from "@/assets/user_cats/cat2.png";
import catSkin3 from "@/assets/user_cats/cat3.png";
import catSkin4 from "@/assets/user_cats/cat4.png";
import catSkin5 from "@/assets/user_cats/cat5.png";
import catSkin6 from "@/assets/user_cats/cat6.png";
import catSkin7 from "@/assets/user_cats/cat7.png";
import catSkin8 from "@/assets/user_cats/cat8.png";
import catSkin9 from "@/assets/user_cats/cat9.png";
import catSkin10 from "@/assets/user_cats/cat10.png";
import catSkin11 from "@/assets/user_cats/cat11.png";
import catSkin12 from "@/assets/user_cats/cat12.png";
import { useUserStore } from "@/stores/user.js";
import { storeToRefs } from "pinia";
import userAPI from "@/api/user.js";

const catSkin = computed(() => {
  if (props.userItem.catSkin === 1) {
    return catSkin1;
  } else if (props.userItem.catSkin === 2) {
    return catSkin2;
  } else if (props.userItem.catSkin === 3) {
    return catSkin3;
  } else if (props.userItem.catSkin === 4) {
    return catSkin4;
  } else if (props.userItem.catSkin === 5) {
    return catSkin5;
  } else if (props.userItem.catSkin === 6) {
    return catSkin6;
  } else if (props.userItem.catSkin === 7) {
    return catSkin7;
  } else if (props.userItem.catSkin === 8) {
    return catSkin8;
  } else if (props.userItem.catSkin === 9) {
    return catSkin9;
  } else if (props.userItem.catSkin === 10) {
    return catSkin10;
  } else if (props.userItem.catSkin === 11) {
    return catSkin11;
  } else if (props.userItem.catSkin === 12) {
    return catSkin12;
  }
});

const $style = useCssModule();

const props = defineProps({
  userItem: Object,
});

const isDropdownOpenState = ref(false);
const openDropdown = () => {
  isDropdownOpenState.value = true;
};
const closeDropdown = () => {
  isDropdownOpenState.value = false;
};
const clickDropdownButtonHandler = () => {
  isDropdownOpenState.value ? closeDropdown() : openDropdown();
  requestCheckFollowing();
};
const blurDropdownButtonHandler = () => {
  console.log("blurDropdownButtonHandler");
  if (isDropdownOpenState.value === true) {
    closeDropdown();
  }
};

const userStore = useUserStore();

const { loginUser } = storeToRefs(userStore);

// const userSeq = props.userItem.usersSeq;
const myUserSeq = loginUser.value.seq;

const isFollowingState = ref(undefined);

const requestCheckFollowing = async () => {
  const response = await userAPI.isFollowing({
    fromSeq: myUserSeq,
    toSeq: props.userItem.usersSeq,
  });
  if (response.data === "") {
    isFollowingState.value = null;
  } else {
    isFollowingState.value = response.data.seq;
  }
};

const handleClickFollowButton = async () => {
  await userAPI.following({
    fromSeq: myUserSeq,
    toSeq: props.userItem.usersSeq,
  });
  requestCheckFollowing();
};

const handleClickUnfollowButton = async () => {
  await userAPI.unfollowing(isFollowingState.value);
  requestCheckFollowing();
};
</script>

<template>
  <div>
    <div :class="$style.card_style">
      <div
        v-if="isDropdownOpenState && myUserSeq != props.userItem.usersSeq"
        id="dropdown"
        :class="$style.dropdown"
        role="menu"
        aria-orientation="vertical"
        aria-labelledby="dropdown_button"
        tabindex="-1"
      >
        <div :class="$style.user_dropdown_div">
          <h1 :class="$style.user_dropdown_title">
            {{ props.userItem.nickname }}
          </h1>
          <button
            v-if="!isFollowingState"
            @click="handleClickFollowButton"
            :class="$style.follow_to_unfollow"
          >
            친구 추가
          </button>
          <button
            v-else
            @click="handleClickUnfollowButton"
            :class="$style.unfollow_to_follow"
          >
            친구 해제
          </button>
          <!-- <button :class="$style.chat_button">채팅하기</button> -->
        </div>
      </div>
      <div
        :class="$style.user_card_button"
        @click="clickDropdownButtonHandler"
        @blur="blurDropdownButtonHandler"
      >
        <CardTitle :class="$style.display_inline_block">{{
          props.userItem.nickname
        }}</CardTitle>
      </div>
      <div>
        <img :src="catSkin" alt="" />
      </div>
    </div>
  </div>
</template>

<style lang="css" module>
@import "UserListItem.module.css";
</style>
