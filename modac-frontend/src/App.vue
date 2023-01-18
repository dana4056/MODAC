<script setup></script>

<template>
  <header>
    <nav>
      <RouterLink to="/todoList">투두 리스트</RouterLink><br />
      <RouterLink to="/studyRoom">스터디 룸</RouterLink>
    </nav>
  </header>

  <RouterView />
</template>

<style scoped></style>
