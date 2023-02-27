<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import CardContent from "@/components/CardContent.vue";
import CommonButton from "@/components/CommonButton.vue";
import { ref, defineProps, computed, onMounted } from "vue";
import { useDmStore } from "@/stores/dm";
import { useUserStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import userAPI from "@/api/user";

// store 관련
const DMstore = useDmStore();
const {
  directMessageRoomList,
  directMessageRoomSeq,
  isDropdownOpenState,
  directChatLogs,
} = storeToRefs(DMstore);

const userStore = useUserStore();
const { loginUser } = storeToRefs(userStore);

const props = defineProps({
  talker: Object,
  roomSeq: Number,
});

const isFollowed = ref(true);
console.log(isFollowed.value);
const follow = async () => {
  await userAPI.following({
    fromSeq: loginUser.value.seq,
    toSeq: props.talker.seq,
  });
  isFollowed.value = true;
};
const unfollow = async () => {
  const followingChecker = await userAPI.isFollowing({
    fromSeq: loginUser.value.seq,
    toSeq: props.talker.seq,
  });
  await userAPI.unfollowing(followingChecker.data.seq);
  isFollowed.value = false;
};
const handleClickButton = () => {
  isFollowed.value ? unfollow() : follow();
};

// const messageButtonState = ref("대화하기");
const buttonState = computed(() =>
  isFollowed.value === true ? "친구 해제" : "친구 추가"
);
const followButtonStyleState = computed(() =>
  isFollowed.value === true
    ? "user_card_button_unfollow"
    : "user_card_button_follow"
);

const directChatting = () => {
  for (const dmRoom of directMessageRoomList.value) {
    console.log("dmRoom" + dmRoom);
    if (dmRoom.talker == props.talker) {
      // 얘랑 채팅한 적 있음(채팅방 목록에 존재)
      directMessageRoomSeq.value = props.roomSeq; // 해당 친구와의 채팅방 선택
      DMstore.connect(); // 해당 채팅방 소켓 연결
      isDropdownOpenState.value = true; // DM 창 열기 (드롭다운)

      const payload = {
        roomSeq: props.roomSeq,
        page: 0,
      };

      DMstore.api.fetchMessages(payload); // 클릭한 채팅방 메시지 목록 불러오기
      return;
    }
  }

  // 얘랑 채팅해본 적 없음 (첫 DM)

  const chatRoom = {
    lastMessage: "",
    lastMessageTime: "",
    talker: props.talker,
  };
  directChatLogs.value = [];
  directMessageRoomList.value.unshift(chatRoom); // DM 채팅방 목록에 추가 (프론트에서만)
  directMessageRoomSeq.value = props.roomSeq; // 해당 친구와의 채팅방 선택
  DMstore.connect(); // 해당 채팅방 소켓 연결
  isDropdownOpenState.value = true; // DM 창 열기 (드롭다운)
};
</script>

<template>
  <Card :class="$style.user_card">
    <!-- <div :class="$style.user_card_item">
      <CardTitle>{{ props.talker.nickname }}</CardTitle>
    </div> -->

    <div :class="$style.user_card_item">
      <CommonButton
        :class="$style.user_card_button_message"
        @click="directChatting"
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 24 24"
          fill="currentColor"
          class="w-6 h-6"
        >
          <path
            d="M4.913 2.658c2.075-.27 4.19-.408 6.337-.408 2.147 0 4.262.139 6.337.408 1.922.25 3.291 1.861 3.405 3.727a4.403 4.403 0 00-1.032-.211 50.89 50.89 0 00-8.42 0c-2.358.196-4.04 2.19-4.04 4.434v4.286a4.47 4.47 0 002.433 3.984L7.28 21.53A.75.75 0 016 21v-4.03a48.527 48.527 0 01-1.087-.128C2.905 16.58 1.5 14.833 1.5 12.862V6.638c0-1.97 1.405-3.718 3.413-3.979z"
          />
          <path
            d="M15.75 7.5c-1.376 0-2.739.057-4.086.169C10.124 7.797 9 9.103 9 10.609v4.285c0 1.507 1.128 2.814 2.67 2.94 1.243.102 2.5.157 3.768.165l2.782 2.781a.75.75 0 001.28-.53v-2.39l.33-.026c1.542-.125 2.67-1.433 2.67-2.94v-4.286c0-1.505-1.125-2.811-2.664-2.94A49.392 49.392 0 0015.75 7.5z"
          />
        </svg>
      </CommonButton>
    </div>
    <div :class="$style.user_card_item">
      <CommonButton
        :class="$style[followButtonStyleState]"
        @click="handleClickButton"
        id="followButton"
      >
        {{ buttonState }}
      </CommonButton>
    </div>
  </Card>
</template>

<style lang="css" module>
@import "UserCard.module.css";
</style>
