<template>
<div class="navigation">
  <nav class ="nav">
    <ul class="menu-bar">
      <RouterLink id="routerlink" to="/todoList">Todo List</RouterLink>
      <RouterLink id="routerlink" to="/room">Study Room</RouterLink>
      <RouterLink id="routerlink" to="/before">Github</RouterLink>
      <RouterLink id="routerlink" to="/login">Login</RouterLink>
      <RouterLink id="routerlink" to="/signup">SignUp</RouterLink>
      <RouterLink id="routerlink" to="/myPage">myPage</RouterLink>
      <Notification/>
    </ul>
  </nav>
</div>
</template>

<script setup>
import Notification from './Notification.vue';



</script>

<style lang="scss" scoped>
@import "@/components/NavbarTop.scss";
</style>
