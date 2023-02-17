<script setup>
import { ref, computed, useCssModule } from "vue";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";
import { storeToRefs } from "pinia";
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

const router = useRouter();
const userStore = useUserStore();
const $style = useCssModule();

const { loginUser } = storeToRefs(userStore);

const profileImgUrl = ref(
  BASE_ASSETS_URL + "user_cats/cat" + loginUser.value.catSkin + ".png"
);

const logout = () => {
  userStore.logout();
  router.push({ name: "login" });
};

const routeMyPage = () => {
  router.push({ name: "myPage" });
};

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
  if (isDropdownOpenState.value === true) {
    closeDropdown();
  }
};

const dropdownStyleState = computed(() => {
  return isDropdownOpenState.value
    ? `${$style.dropdown} ${$style.dropdown_block}`
    : `${$style.dropdown} ${$style.dropdown_none}`;
});
</script>

<template>
  <div class="relative ml-3">
    <div>
      <button
        @click="clickDropdownButtonHandler"
        @blur="blurDropdownButtonHandler"
        type="button"
        :class="$style.dropdown_button"
        id="dropdown_button"
        aria-expanded="false"
        aria-haspopup="true"
      >
        <img class="h-8 w-8 rounded-full" :src="catSkin" alt="" />
      </button>
    </div>

    <div
      id="dropdown"
      :class="dropdownStyleState"
      role="menu"
      aria-orientation="vertical"
      aria-labelledby="dropdown_button"
      tabindex="-1"
    >
      <a
        href="#"
        :class="$style.item"
        role="menuitem"
        tabindex="-1"
        id="user-menu-item-0"
        @mousedown="routeMyPage"
        >마이페이지</a
      >
      <a
        href="#"
        :class="$style.item"
        role="menuitem"
        tabindex="-1"
        id="user-menu-item-2"
        @mousedown="logout"
        >로그아웃</a
      >
    </div>
  </div>
</template>

<style lang="css" module>
@import "ProfileButton.module.css";
</style>
