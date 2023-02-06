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
</template>

<style lang="css" module>
@import "./Navbar.module.css";
</style>
