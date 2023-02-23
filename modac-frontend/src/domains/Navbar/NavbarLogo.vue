<script setup>
import CardTitle from "@/components/CardTitle.vue";
import fireLogo from "@/assets/fire_logo.svg";


</script>

<template>
  <link href='https://fonts.googleapis.com/css?family=Fredoka One' rel='stylesheet'>

  <div :class="$style.logo_wrapper">
    <RouterLink
      id="routerlink"
      to="/room"
      href="#"
      aria-current="page"
      >
        <CardTitle :class="$style.logo_text">
              <img :src="fireLogo" class="w-10 h-10 inline">
        Modac</CardTitle>
      </RouterLink
    >



    <!-- <img
      class="block h-8 w-auto lg:hidden"
      src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=500"
      alt="Your Company"
    />
    <img
      class="hidden h-8 w-auto lg:block"
      src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=500"
      alt="Your Company"
    /> -->
  </div>
</template>

<style lang="css" module>
@import "NavbarLogo.module.css";
</style>
