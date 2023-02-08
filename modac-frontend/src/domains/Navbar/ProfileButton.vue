<script setup>
import { ref, computed, useCssModule } from "vue";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";

const router = useRouter();
const store = useUserStore();
const $style = useCssModule();

const profileImgUrl = ref(
  "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
);

const logout = () => {
  store.logout();
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
        <img class="h-8 w-8 rounded-full" :src="profileImgUrl" alt="" />
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
