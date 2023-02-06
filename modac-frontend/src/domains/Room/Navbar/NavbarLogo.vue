<script setup>
import CardTitle from "@/components/CardTitle.vue";
</script>

<template>
  <div :class="$style.logo_wrapper">
    <CardTitle :class="$style.text_color">Modac</CardTitle>
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
