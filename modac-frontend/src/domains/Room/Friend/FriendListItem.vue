<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import CardContent from "@/components/CardContent.vue";
import UserCard from "@/domains/User/UserCard.vue";
<<<<<<< HEAD
import UserStatusIndicator from "@/components/UserStatusIndicator.vue";
import BASE_ASSETS_URL from "@/api/BASE_ASSETS_URL";
=======
// import UserStatusIndicator from "@/components/UserStatusIndicator.vue";
>>>>>>> 3c6066167b77509467dcf8de5972d5740eea1810
import { useCssModule } from "vue";
import { ref, computed } from "vue";
import { useRoomStore } from "@/stores/room";

const $style = useCssModule();

const props = defineProps({
  friendItem: Object,
});

const store = useRoomStore();

// const userStatusIndicatorStyleState = computed(
//   () =>
//     `${$style.rounded_div} ${
//       $style[store.statusStyleMap[props.friendItem.status]]
//     } ${$style.inline_block_style}`
// );

const isUserCardOpen = ref(false);
const openUserCard = () => {
  isUserCardOpen.value = true;
};
const closeUserCard = () => {
  isUserCardOpen.value = false;
};

const userCardElement = ref(undefined);
const toggleUserCard = (event) => {
  userCardElement.value = document.querySelector("notClickBackground");
  if (userCardElement.value !== event.target) {
    return isUserCardOpen.value ? closeUserCard() : openUserCard();
  }
};

const profileImgUrl = ref(
  BASE_ASSETS_URL + "user_cats/cat" + props.friendItem.toUser.catSkin + ".png"
);

</script>

<template lang="">
  <Card :id="`user_card-${props.friendItem.seq}`" @click="toggleUserCard" :class="$style.friend_list_card">
    <div :class="$style.card_style">
      <img
          :src="profileImgUrl"
          :class="$style.friend_profile"
          alt=""
        />
      <CardTitle :class="$style.inline_block_style">
        {{ props.friendItem.toUser.nickname }}
      </CardTitle>
    </div>
    <UserCard
        v-if="isUserCardOpen"
        :talker="props.friendItem.toUser"
        :roomSeq="props.friendItem.chatRoom.seq"
        :id="notClickBackground"
      />
  </Card>
</template>

<style lang="css" module>
@import "./FriendListItem.module.css";
</style>
