<script setup>
import NavbarList from "./NavbarList.vue";
import NavbarRightSection from "./NavbarRightSection.vue";
</script>

<template>
  <nav class="bg-gray-800 h-full">
    <div :class="$style.navbar_wrapper">
      <div :class="$style.navbar_section_flex_wrapper">
        <NavbarList />
        <NavbarRightSection />
      </div>
    </div>
  </nav>
</template>

<style lang="css" module>
@import "./Navbar.module.css";
</style>
