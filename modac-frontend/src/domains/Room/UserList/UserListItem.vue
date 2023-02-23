<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
// import UserStatusIndicator from "@/components/UserStatusIndicator.vue";
import { ref, computed, defineProps, useCssModule } from "vue";
import BASE_ASSETS_URL from "@/api/BASE_ASSETS_URL";
import catSkin1 from "@/assets/user_cats/cat1.png"
import catSkin2 from "@/assets/user_cats/cat2.png"
import catSkin3 from "@/assets/user_cats/cat3.png"
import catSkin4 from "@/assets/user_cats/cat4.png"
import catSkin5 from "@/assets/user_cats/cat5.png"
import catSkin6 from "@/assets/user_cats/cat6.png"
import catSkin7 from "@/assets/user_cats/cat7.png"
import catSkin8 from "@/assets/user_cats/cat8.png"
import catSkin9 from "@/assets/user_cats/cat9.png"
import catSkin10 from "@/assets/user_cats/cat10.png"
import catSkin11 from "@/assets/user_cats/cat11.png"
import catSkin12 from "@/assets/user_cats/cat12.png"


const catSkin = computed(() => {
  if( props.userItem.catSkin === 1){
    return catSkin1
  }
  else if(props.userItem.catSkin === 2){
    return catSkin2
  }
  else if(props.userItem.catSkin === 3){
    return catSkin3
  }
  else if(props.userItem.catSkin === 4){
    return catSkin4
  }
  else if(props.userItem.catSkin === 5){
    return catSkin5
  }
  else if(props.userItem.catSkin === 6){
    return catSkin6
  }
  else if(props.userItem.catSkin === 7){
    return catSkin7
  }
  else if(props.userItem.catSkin === 8){
    return catSkin8
  }
  else if(props.userItem.catSkin === 9){
    return catSkin9
  }
  else if(props.userItem.catSkin === 10){
    return catSkin10
  }
  else if(props.userItem.catSkin === 11){
    return catSkin11
  }
  else if(props.userItem.catSkin === 12){
    return catSkin12
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
};
const blurDropdownButtonHandler = () => {
  console.log("blurDropdownButtonHandler");
  if (isDropdownOpenState.value === true) {
    closeDropdown();
  }
};


import { useFeedStore } from "@/stores/feed.js";
import { useUserStore } from "@/stores/user.js";
import { storeToRefs } from "pinia";
import FollowButton from "@/components/FollowButton.vue";

const feedStore = useFeedStore();
const userStore = useUserStore();

const { article } = storeToRefs(feedStore);
const { loginUser } = storeToRefs(userStore);


const userSeq = props.userItem.usersSeq;
const myUserSeq = loginUser.value.seq;

console.log(props.userItem);

// console.log("userSeq", userSeq);
// console.log("myUserSeq", myUserSeq);
</script>

<template>
  <div>
    <div :class="$style.card_style">
      <div>
        <div
          v-if="isDropdownOpenState && myUserSeq != userSeq"
          id="dropdown"
          :class="$style.dropdown"
          role="menu"
          aria-orientation="vertical"
          aria-labelledby="dropdown_button"
          tabindex="-1"
        >
          <!-- 여기에 내용을 넣으세요  -->
          <div :class="$style.user_dropdown_div" >
            <div :class="$style.user_dropdown_title">
              <div>{{ props.userItem.nickname }}</div>              
              <FollowButton class="text-sm rounded-lg py-1">
              </FollowButton>
            </div>

            <div class="flex gap-2 font-semibold text-sm cursor-pointer" @click="">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="w-6 h-6">
                <path d="M4.913 2.658c2.075-.27 4.19-.408 6.337-.408 2.147 0 4.262.139 6.337.408 1.922.25 3.291 1.861 3.405 3.727a4.403 4.403 0 00-1.032-.211 50.89 50.89 0 00-8.42 0c-2.358.196-4.04 2.19-4.04 4.434v4.286a4.47 4.47 0 002.433 3.984L7.28 21.53A.75.75 0 016 21v-4.03a48.527 48.527 0 01-1.087-.128C2.905 16.58 1.5 14.833 1.5 12.862V6.638c0-1.97 1.405-3.718 3.413-3.979z" />
                <path d="M15.75 7.5c-1.376 0-2.739.057-4.086.169C10.124 7.797 9 9.103 9 10.609v4.285c0 1.507 1.128 2.814 2.67 2.94 1.243.102 2.5.157 3.768.165l2.782 2.781a.75.75 0 001.28-.53v-2.39l.33-.026c1.542-.125 2.67-1.433 2.67-2.94v-4.286c0-1.505-1.125-2.811-2.664-2.94A49.392 49.392 0 0015.75 7.5z" />
              </svg> 
              <div>채팅하기</div>
            </div>

          </div>
          
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
        <!-- <UserStatusIndicator
          :status="props.userItem.status"
        ></UserStatusIndicator> -->
      </div>
      <div>
        <img
          :src="catSkin"
          alt=""
        />
      </div>
    </div>
  </div>
</template>

<style lang="css" module>
@import "UserListItem.module.css";
</style>
