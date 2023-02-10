<script setup>
import DM from "./DM/DM.vue";
import NotificationButton from "./NotificationButton.vue";
import ProfileButton from "./ProfileButton.vue";
</script>

<template>
  <div
    class="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0"
  >
    <DM />
    <NotificationButton />
    <ProfileButton />
  </div>
</template>

<style lang="css" module>
@import "NavbarRightSection.module.css";
</style>
