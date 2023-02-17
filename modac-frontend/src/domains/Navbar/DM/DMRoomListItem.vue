<script setup>
import { defineProps , computed, useCssModule, ref } from "vue";
import BASE_ASSETS_URL from "@/api/BASE_ASSETS_URL";
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
import { useUserStore } from "@/stores/user";
import { storeToRefs } from "pinia";
const userStore = useUserStore();
const { loginUser } = storeToRefs(userStore);


const catSkin = computed(() => {
  if( loginUser.value.catSkin === 1){
    return catSkin1
  }
  else if(loginUser.value.catSkin === 2){
    return catSkin2
  }
  else if(loginUser.value.catSkin === 3){
    return catSkin3
  }
  else if(loginUser.value.catSkin === 4){
    return catSkin4
  }
  else if(loginUser.value.catSkin === 5){
    return catSkin5
  }
  else if(loginUser.value.catSkin === 6){
    return catSkin6
  }
  else if(loginUser.value.catSkin === 7){
    return catSkin7
  }
  else if(loginUser.value.catSkin === 8){
    return catSkin8
  }
  else if(loginUser.value.catSkin === 9){
    return catSkin9
  }
  else if(loginUser.value.catSkin === 10){
    return catSkin10
  }
  else if(loginUser.value.catSkin === 11){
    return catSkin11
  }
  else if(loginUser.value.catSkin === 12){
    return catSkin12
  }
});


const props = defineProps({
  chatRoom: Object,
  getMessages: Function,
  seq: Number,

});

const $style = useCssModule();

const lastTime = computed(() => {

  const h = props.chatRoom.lastMessageTime.slice(11,13);
  const m = props.chatRoom.lastMessageTime.slice(14,16);
  const s = props.chatRoom.lastMessageTime.slice(17);

  return h+":"+m;
})

// const profileImgUrl = ref(
//   BASE_ASSETS_URL + "user_cats/cat" + props.loginUser.catSkin + ".png"
// );


</script>x

<template>
  <li :class="$style.dm_items" @click="getMessages(props.chatRoom.seq)">
    <div :class="$style.dm_items_left">
      <img
        :src= "catSkin"
        :class="$style.profile_img"
        alt=""
      />
      <div :class="$style.dm_items_left_items">
        <span :class="$style.friend_name">{{ props.chatRoom.talker.nickname }}</span>
        <span :class="$style.last_text">{{ props.chatRoom.lastMessage }}</span>
      </div>
    </div>
    <div :class="$style.dm_items_right">
      <span :class="$style.time">{{lastTime}}</span>
    </div>
  </li>
</template>

<style lang="css" module>
@import "DMRoomListItem.module.css";
</style>
