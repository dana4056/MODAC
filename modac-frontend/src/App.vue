<script setup>
import Navbar from "@/domains/Room/Navbar/Navbar.vue";
import Wrapper from "./components/Wrapper.vue";
import { useUserStore } from "@/stores/user"
import { useAuthStore } from "@/stores/auth";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";

const userStore = useUserStore();
const { loginUser } = storeToRefs(userStore);

const router = useRouter();

const routePageForEnteredUser = () => {
  console.log(loginUser);
  console.log(loginUser.value);

  if (loginUser.value) {
    // router.push({ name: "room" });
  } else {
    // router.push({ name: "login" });
  }
};

routePageForEnteredUser();
</script>

<template>
  <Wrapper :class="$style.app_wrapper">
    <div v-if="isLoggedIn" id="header" :class="$style.app_navbar">
      <Navbar />
    </div>
    <div id="body" :class="$style.app_section">
      <RouterView />
    </div>
    <div id="footer"></div>
  </Wrapper>
</template>

<style lang="css" module>
@import "./App.module.css";
</style>
