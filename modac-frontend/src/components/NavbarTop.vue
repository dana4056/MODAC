<template>
<div class="navigation">
            <nav>
              <ul class="nav-type">
                <RouterLink id="routerlink" to="/todoList">투두 리스트</RouterLink>
                <RouterLink id="routerlink" to="/studyRoom">스터디 룸</RouterLink>
                <RouterLink id="routerlink" to="/before">깃헙 연동 테스트</RouterLink>
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

<style>
@import '@/components/NavbarTop.css'
</style>