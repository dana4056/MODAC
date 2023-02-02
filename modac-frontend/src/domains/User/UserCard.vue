<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import CardContent from "@/components/CardContent.vue";
import { ref, defineProps, computed } from "vue";

const props = defineProps({
  seq: Number,
  name: String,
  status: Number,
  categoryName: String,
});

const isFriend = ref(true);
const messageButtonState = computed(() => (isFriend.value ? "대화하기" : ""));
const addFriendButtonState = computed(() =>
  isFriend.value ? "친구삭제" : "친구추가"
);
</script>

<template>
  <Card>
    <CardTitle>{{ props.name }}</CardTitle>
    <CardContent>{{ props.status }}</CardContent>
    <CardContent>{{ props.categoryName }}</CardContent>
    <button>{{ messageButtonState }}</button>
    <button>{{ addFriendButtonState }}</button>
  </Card>
</template>

<style lang="css" module>
@import "UserCard.module.css";
</style>
