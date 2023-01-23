<template>
<div class="navigation">
  <nav>
    <ul class="menu-bar">
      <li><RouterLink id="routerlink" to="/todoList">Todo List</RouterLink></li>
      <li><RouterLink id="routerlink" to="/studyRoom">Study Room</RouterLink></li>
      <li><RouterLink id="routerlink" to="/before">Github</RouterLink></li>
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