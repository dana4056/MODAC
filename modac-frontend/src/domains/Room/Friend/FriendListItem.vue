<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import CardContent from "@/components/CardContent.vue";
import UserCard from "@/domains/User/UserCard.vue";
import { ref, computed } from "vue";

const props = defineProps({
  friendItem: Object,
});

const isUserCardOpen = ref(false);
const userCardOpenButtonState = computed(() =>
  isUserCardOpen.value ? "접기" : "펼치기"
);
const openUserCard = () => {
  isUserCardOpen.value = true;
};
const closeUserCard = () => {
  isUserCardOpen.value = false;
};
const toggleUserCard = () => {
  return isUserCardOpen.value ? closeUserCard() : openUserCard();
};
</script>

<template lang="">
  <Card @click="toggleUserCard">
    <CardTitle>
      {{ props.friendItem.name }}
    </CardTitle>
    <CardContent>
      {{ props.friendItem.status }}
    </CardContent>
    <UserCard
      v-if="isUserCardOpen"
      :seq="props.friendItem.id"
      :name="props.friendItem.name"
      :status="props.friendItem.status"
      :categoryName="props.friendItem.categoryName"
    />
  </Card>
</template>

<style lang="css" module>
@import "./FriendListItem.module.css";
</style>
