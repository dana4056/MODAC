<template>
<div class="navigation">
  <nav class ="nav">
    <ul class="menu-bar">
      <RouterLink id="routerlink" to="/todoList">Todo List</RouterLink>
      <RouterLink id="routerlink" to="/studyRoom">Study Room</RouterLink>
      <RouterLink id="routerlink" to="/before">Github</RouterLink>
      <RouterLink id="routerlink" to="/login">Login</RouterLink>
      <RouterLink id="routerlink" to="/signup">SignUp</RouterLink>
    </ul>
  </nav>
</div>
</template>

<script setup>
function myData() {
  retrun;
}

function show() {
  document.getElementById('anotherFunction').classList.toggle('Active');
}
</script>

<style lang='scss' scoped>
@import '@/components/NavbarTop.scss'
</style>