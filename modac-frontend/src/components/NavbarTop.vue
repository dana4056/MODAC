<template>
  <div class="navigation">
    <nav class="nav">
      <ul class="menu-bar">
        <RouterLink id="routerlink" to="/room">스터디룸</RouterLink>
        <RouterLink id="routerlink" to="/article">TIL</RouterLink>
        <RouterLink id="routerlink" to="/feed">SNS</RouterLink>
        <RouterLink id="routerlink" to="/myPage">마이페이지</RouterLink>
      </ul>
    </nav>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped>
@import "@/components/NavbarTop.scss";
</style>
