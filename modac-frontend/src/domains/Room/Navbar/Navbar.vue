<script setup>
import NavbarList from "./NavbarList.vue";
import NavbarRightSection from "./NavbarRightSection.vue";
</script>

<template>
  <nav class="bg-gray-800 h-full">
    <div class="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
      <div class="relative flex h-16 items-center justify-between">
        <NavbarList />
        <NavbarRightSection />
      </div>
    </div>
  </nav>

  <!-- <div>
    <nav>
      <ul>
        <RouterLink id="routerlink" to="/room">스터디룸</RouterLink>
        <RouterLink id="routerlink" to="/article">TIL</RouterLink>
        <RouterLink id="routerlink" to="/feed">SNS</RouterLink>
        <RouterLink id="routerlink" to="/myPage">마이페이지</RouterLink>
      </ul>
    </nav>
  </div> -->
</template>

<style lang="scss" module>
@import "./Navbar.module.css";
</style>
