<script setup></script>

<template>
  <header>
    <nav>
      <RouterLink to="/todoList">투두 리스트</RouterLink><br />
      <RouterLink to="/studyRoom">스터디 룸</RouterLink><br />
      <RouterLink to="/before">깃헙 연동 테스트</RouterLink>
    </nav>
  </header>

  <RouterView />
</template>

<style scoped></style>