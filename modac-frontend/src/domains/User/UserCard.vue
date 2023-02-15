<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import CardContent from "@/components/CardContent.vue";
import CommonButton from "@/components/CommonButton.vue";
import { ref, defineProps, computed } from "vue";
import { useRoomStore } from "@/stores/room";
import { useDmStore } from "@/stores/dm";
import { storeToRefs } from "pinia";


// store 관련
const DMstore = useDmStore();
const { directMessageRoomList, directMessageRoomSeq, isDropdownOpenState, directChatLogs } = storeToRefs(DMstore);


const props = defineProps({
  talker: Object,
  roomSeq: Number
});

const userStore = useRoomStore();

const statusMap = userStore.statusMap;

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

const directChatting = () => {

  for(const dmRoom of directMessageRoomList.value){
    console.log("dmRoom"+dmRoom)
    if(dmRoom.talker == props.talker){                // 얘랑 채팅한 적 있음(채팅방 목록에 존재)
      directMessageRoomSeq.value = props.roomSeq;     // 해당 친구와의 채팅방 선택
      DMstore.connect();                              // 해당 채팅방 소켓 연결
      isDropdownOpenState.value = true;               // DM 창 열기 (드롭다운)

      const payload = {
        roomSeq:props.roomSeq,
        page:0
      }

      DMstore.api.fetchMessages(payload);             // 클릭한 채팅방 메시지 목록 불러오기
      return 
    }
  }

  // 얘랑 채팅해본 적 없음 (첫 DM)
  
  const chatRoom = {
    lastMessage:"",
    lastMessageTime:"",
    talker: props.talker
  }
  directChatLogs.value = [];
  directMessageRoomList.value.unshift(chatRoom);  // DM 채팅방 목록에 추가 (프론트에서만)
  directMessageRoomSeq.value = props.roomSeq;     // 해당 친구와의 채팅방 선택
  DMstore.connect();                              // 해당 채팅방 소켓 연결
  isDropdownOpenState.value = true;               // DM 창 열기 (드롭다운)
}
</script>

<template>
  <Card :class="$style.user_card">
    <div :class="$style.user_card_item">
      <CardTitle>{{ props.talker.nickname }}</CardTitle>
    </div>


    <div :class="$style.user_card_item">
      <CommonButton :class="$style.user_card_button_message" @click="directChatting">
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
