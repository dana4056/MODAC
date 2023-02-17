<script setup>
import NavbarLogo from "./NavbarLogo.vue";
</script>

<template>
  <div :class="$style.navbar_flex_wrapper">
    <NavbarLogo />
    <div class="hidden sm:ml-6 sm:block">
      <div :class="$style.navbar_list_flex_wrapper">
        <RouterLink
          id="routerlink"
          to="/room"
          href="#"
          :class="$style.navbar_item_unselected"
          aria-current="page"
          >스터디룸</RouterLink
        >

        <!-- <RouterLink
          id="routerlink"
          to="/article"
          href="#"
          :class="$style.navbar_item_unselected"
          >TIL</RouterLink
        > -->

        <RouterLink
          id="routerlink"
          to="/feed"
          href="#"
          :class="$style.navbar_item_unselected"
          >SNS</RouterLink
        >
      </div>
    </div>
  </div>
</template>

<style lang="css" module>
@import "NavbarList.module.css";
</style>
