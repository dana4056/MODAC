<script setup>
import { ref } from "vue";
import { useAuthStore } from "@/stores/auth";
import { useRouter } from "vue-router";

const router = useRouter();
const store = useAuthStore();

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
</script>

<template>
  <div class="relative ml-3">
    <div>
      <button
        type="button"
        :class="$style.user_menu_button"
        id="user-menu-button"
        aria-expanded="false"
        aria-haspopup="true"
      >
        <img class="h-8 w-8 rounded-full" :src="profileImgUrl" alt="" />
      </button>
    </div>

    <div
      :class="$style.menu__div"
      role="menu"
      aria-orientation="vertical"
      aria-labelledby="user-menu-button"
      tabindex="-1"
    >
      <a
        href="#"
        :class="$style.menu_item__a"
        role="menuitem"
        tabindex="-1"
        id="user-menu-item-0"
        @click="routeMyPage"
        >마이페이지</a
      >
      <a
        href="#"
        :class="$style.menu_item__a"
        role="menuitem"
        tabindex="-1"
        id="user-menu-item-2"
        @click="logout"
        >로그아웃</a
      >
    </div>
  </div>
</template>

<style lang="css" module>
@import "ProfileDropdown.module.css";
</style>
