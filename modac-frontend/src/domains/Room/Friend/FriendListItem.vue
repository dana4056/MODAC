<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import CardContent from "@/components/CardContent.vue";
import UserCard from "@/domains/User/UserCard.vue";
import BASE_ASSETS_URL from "@/api/BASE_ASSETS_URL";
// import UserStatusIndicator from "@/components/UserStatusIndicator.vue";
import { useCssModule } from "vue";
import { ref, computed } from "vue";
import { useRoomStore } from "@/stores/room";
import catSkin1 from "@/assets/user_cats/cat1.png"
import catSkin2 from "@/assets/user_cats/cat2.png"
import catSkin3 from "@/assets/user_cats/cat3.png"
import catSkin4 from "@/assets/user_cats/cat4.png"
import catSkin5 from "@/assets/user_cats/cat5.png"
import catSkin6 from "@/assets/user_cats/cat6.png"
import catSkin7 from "@/assets/user_cats/cat7.png"
import catSkin8 from "@/assets/user_cats/cat8.png"
import catSkin9 from "@/assets/user_cats/cat9.png"
import catSkin10 from "@/assets/user_cats/cat10.png"
import catSkin11 from "@/assets/user_cats/cat11.png"
import catSkin12 from "@/assets/user_cats/cat12.png"


const catSkin = computed(() => {
  if( props.friendItem.toUser.catSkin === 1){
    return catSkin1
  }
  else if(props.friendItem.toUser.catSkin === 2){
    return catSkin2
  }
  else if(props.friendItem.toUser.catSkin === 3){
    return catSkin3
  }
  else if(props.friendItem.toUser.catSkin === 4){
    return catSkin4
  }
  else if(props.friendItem.toUser.catSkin === 5){
    return catSkin5
  }
  else if(props.friendItem.toUser.catSkin === 6){
    return catSkin6
  }
  else if(props.friendItem.toUser.catSkin === 7){
    return catSkin7
  }
  else if(props.friendItem.toUser.catSkin === 8){
    return catSkin8
  }
  else if(props.friendItem.toUser.catSkin === 9){
    return catSkin9
  }
  else if(props.friendItem.toUser.catSkin === 10){
    return catSkin10
  }
  else if(props.friendItem.toUser.catSkin === 11){
    return catSkin11
  }
  else if(props.friendItem.toUser.catSkin === 12){
    return catSkin12
  }
});

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
</script>

<template lang="">
  <Card :id="`user_card-${props.friendItem.seq}`" @click="toggleUserCard" :class="$style.friend_list_card">
    <div :class="$style.card_style">
      <img
          :src="catSkin"
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
