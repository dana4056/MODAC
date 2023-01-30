<template>
  <div class="navigation">
    <nav>
      <ul class="menu-bar">
        <RouterLink id="routerlink" to="/todoList">Todo List</RouterLink>
        <RouterLink id="routerlink" to="/room">Study Room</RouterLink>
        <RouterLink id="routerlink" to="/before">Github</RouterLink>
      </ul>
    </nav>
  </div>
</template>

<script setup>

</script>

<style lang="scss" scoped>
@import "@/components/NavbarTop.scss";
</style>
