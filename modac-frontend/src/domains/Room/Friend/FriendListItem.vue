<script setup>
const props = defineProps({
  friendItem: Object,
});
console.log(props.friendItem);
</script>
<template lang="">
  <div>
    <h1>여기는 각 FriendListItem 컴포넌트입니다.</h1>
    <div>{{ props.friendItem }}</div>
  </div>
</template>
<style lang=""></style>
