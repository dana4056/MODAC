<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import CardContent from "@/components/CardContent.vue";
import CommonButton from "@/components/CommonButton.vue";
import { ref, defineProps, computed } from "vue";
import { useRoomStore } from "@/stores/room";
import { storeToRefs } from "pinia";

const props = defineProps({
  seq: Number,
  name: String,
  status: Number,
  categoryName: String,
});

const store = useRoomStore();

const statusMap = store.statusMap;

const isFollowed = ref(false);
const follow = () => {
  isFollowed.value = true;
};
const unfollow = () => {
  isFollowed.value = false;
};
const toggleFollowState = () => {
  return isFollowed.value ? unfollow() : follow();
};

const messageButtonState = ref("대화하기");
const followButtonState = computed(() =>
  isFollowed.value === true ? "언팔로우" : "팔로우"
);
const followButtonStyleState = computed(() =>
  isFollowed.value === true
    ? "user_card_button_unfollow"
    : "user_card_button_follow"
);
</script>

<template>
  <Card :class="$style.user_card">
    <div :class="$style.user_card_item">
      <CardTitle>{{ props.name }}</CardTitle>
    </div>
    <div :class="$style.user_card_item">
      <CardContent>{{ statusMap[props.status] }}</CardContent>
    </div>
    <div :class="$style.user_card_item">
      <CardContent>{{ props.categoryName }} 학습중</CardContent>
    </div>
    <div :class="$style.user_card_item">
      <CommonButton :class="$style.user_card_button_message">
        {{ messageButtonState }}
      </CommonButton>
    </div>
    <div :class="$style.user_card_item">
      <CommonButton
        :class="$style[followButtonStyleState]"
        @click="toggleFollowState"
      >
        {{ followButtonState }}
      </CommonButton>
    </div>
  </Card>
</template>

<style lang="css" module>
@import "UserCard.module.css";
</style>
