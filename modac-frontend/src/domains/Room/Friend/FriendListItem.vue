<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import CardContent from "@/components/CardContent.vue";
import UserCard from "@/domains/User/UserCard.vue";
import UserStatusIndicator from "@/components/UserStatusIndicator.vue";
import { useCssModule } from "vue";
import { ref, computed } from "vue";
import { useRoomStore } from "@/stores/room";

const $style = useCssModule();

const props = defineProps({
  friendItem: Object,
});

const store = useRoomStore();

const userStatusIndicatorStyleState = computed(
  () =>
    `${$style.rounded_div} ${
      $style[store.statusStyleMap[props.friendItem.status]]
    } ${$style.inline_block_style}`
);

const isUserCardOpen = ref(false);
const openUserCard = () => {
  isUserCardOpen.value = true;
};
const closeUserCard = () => {
  isUserCardOpen.value = false;
};

const userCardElement = ref(undefined);
const toggleUserCard = (event) => {
  userCardElement.value = document.querySelector(
    `#user_card-${props.friendItem.seq}`
  );
  if (userCardElement.value === event.target) {
    return isUserCardOpen.value ? closeUserCard() : openUserCard();
  }
};
</script>

<template lang="">
  <Card :id="`user_card-${props.friendItem.seq}`" @click="toggleUserCard">
    <div :class="$style.card_style">
      <img
          :src="require('@/assets/user_cats/cat' + props.friendItem.toUser.catSkin + '.png')"
          :class="$style.friend_profile"
          alt=""
        />
      <CardTitle :class="$style.inline_block_style">
        {{ props.friendItem.toUser.nickname }}
      </CardTitle>
    </div>
    <UserCard
        v-if="isUserCardOpen"
        :seq="props.friendItem.toUser.id"
        :name="props.friendItem.toUser.nickname"
      />
  </Card>
</template>

<style lang="css" module>
@import "./FriendListItem.module.css";
</style>
