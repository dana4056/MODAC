<script setup>
import NavbarLogo from "./NavbarLogo.vue";
</script>

<template>
  <div
    class="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start"
  >
    <NavbarLogo />
    <div class="hidden sm:ml-6 sm:block">
      <div class="flex space-x-4">
        <!-- Current: "bg-gray-900 text-white", Default: "text-gray-300 hover:bg-gray-700 hover:text-white" -->
        <RouterLink
          id="routerlink"
          to="/room"
          href="#"
          class="bg-gray-900 text-white px-3 py-2 rounded-md text-sm font-medium"
          aria-current="page"
          >스터디룸</RouterLink
        >

        <RouterLink
          id="routerlink"
          to="/article"
          href="#"
          class="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium"
          >TIL</RouterLink
        >

        <RouterLink
          id="routerlink"
          to="/feed"
          href="#"
          class="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium"
          >SNS</RouterLink
        >

        <RouterLink
          id="routerlink"
          to="/myPage"
          href="#"
          class="text-gray-300 hover:bg-gray-700 hover:text-white px-3 py-2 rounded-md text-sm font-medium"
          >마이페이지</RouterLink
        >
      </div>
    </div>
  </div>
</template>

<style lang="css" module>
@import "NavbarList.module.css";
</style>
