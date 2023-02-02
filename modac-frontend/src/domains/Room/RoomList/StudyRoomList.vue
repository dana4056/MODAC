<script setup>
import Wrapper from "@/components/Wrapper.vue";
import StudyRoomListItem from "@/domains/Room/RoomList/StudyRoomListItem.vue";
import RoomListHeader from "@/domains/Room/RoomList/RoomListHeader.vue";
import { ref } from "vue";

// seq:"Integer",
// usersSeq:"Integer",
// title:"String",
// description:"String",
// maxSize:"Integer",
// multiTheme:"String",
// publicType:"Integer"
// invitationCode:"String"
const roomList = ref([
  {
    seq: 1,
    title: "알고리즘 공부방",
    description: "같이 알고리즘 공부해요 :)",
    maxSize: 6,
    currentSize: 4,
    multiTheme: "기본",
    publicType: 1,
    invitationCode: "",
    participants: [],
    host: {
      seq: 1,
      id: "kimssafy",
      nickname: "김싸피",
      email: "kim@ssafy.com",
      password: "password",
      catSkin: 10,
      singleTheme: "기본",
      totalSecond: 100000,
      membership: {
          grade: "gold"
      }
    },
    chatRoom: {
      seq: 1,
      lastMessageSeq: 100,
      lastMessageTime: ""
    }
  },
  {
    seq:2,
    title: "아무나 들어오세요",
    description: "조용히 공부합시다~",
    maxSize: 4,
    currentSize: 2,
    multiTheme: "우주",
    publicType: 0,
    invitationCode: "1234",
    participants: [],
    host: {
      seq: 2,
      id: "leessafy",
      nickname: "이싸피",
      email: "lee@ssafy.com",
      password: "password",
      catSkin: 1,
      singleTheme: "기본",
      totalSecond: 100000,
      membership: {
          grade: "silver"
      }
    },
    chatRoom: {
      seq: 2,
      lastMessageSeq: 100,
      lastMessageTime: ""
    }
  },
  {
    seq:3,
    title: "Flight A608✈",
    description: "싸피 8기 공통6반 Flight A608조",
    maxSize: 6,
    currentSize: 6,
    multiTheme: "바다",
    publicType: 0,
    invitationCode: "4608",
    participants: [],
    host: {
      seq: 3,
      id: "minsu",
      nickname: "이민수",
      email: "minsu@naver.com",
      password: "password",
      catSkin: 5,
      singleTheme: "기본",
      totalSecond: 100000,
      membership: {
          grade: "diamond"
      }
    },
    chatRoom: {
      seq: 3,
      lastMessageSeq: 100,
      lastMessageTime: ""
    }
  },
  {
    seq: 4,
    title: "알고리즘 공부방",
    description: "같이 알고리즘 공부해요 :)",
    maxSize: 6,
    currentSize: 4,
    multiTheme: "기본",
    publicType: 1,
    invitationCode: "",
    participants: [],
    host: {
      seq: 1,
      id: "kimssafy",
      nickname: "김싸피",
      email: "kim@ssafy.com",
      password: "password",
      catSkin: 10,
      singleTheme: "기본",
      totalSecond: 100000,
      membership: {
          grade: "gold"
      }
    },
    chatRoom: {
      seq: 1,
      lastMessageSeq: 100,
      lastMessageTime: ""
    }
  },
  {
    seq:5,
    title: "아무나 들어오세요",
    description: "조용히 공부합시다~",
    maxSize: 4,
    currentSize: 2,
    multiTheme: "우주",
    publicType: 0,
    invitationCode: "1234",
    participants: [],
    host: {
      seq: 2,
      id: "leessafy",
      nickname: "이싸피",
      email: "lee@ssafy.com",
      password: "password",
      catSkin: 1,
      singleTheme: "기본",
      totalSecond: 100000,
      membership: {
          grade: "silver"
      }
    },
    chatRoom: {
      seq: 2,
      lastMessageSeq: 100,
      lastMessageTime: ""
    }
  },
  {
    seq:6,
    title: "Flight A608✈",
    description: "싸피 8기 공통6반 Flight A608조",
    maxSize: 6,
    currentSize: 6,
    multiTheme: "바다",
    publicType: 0,
    invitationCode: "4608",
    participants: [],
    host: {
      seq: 3,
      id: "minsu",
      nickname: "이민수",
      email: "minsu@naver.com",
      password: "password",
      catSkin: 5,
      singleTheme: "기본",
      totalSecond: 100000,
      membership: {
          grade: "diamond"
      }
    },
    chatRoom: {
      seq: 3,
      lastMessageSeq: 100,
      lastMessageTime: ""
    }
  },
  {
    seq: 7,
    title: "알고리즘 공부방",
    description: "같이 알고리즘 공부해요 :)",
    maxSize: 6,
    currentSize: 4,
    multiTheme: "기본",
    publicType: 1,
    invitationCode: "",
    participants: [],
    host: {
      seq: 1,
      id: "kimssafy",
      nickname: "김싸피",
      email: "kim@ssafy.com",
      password: "password",
      catSkin: 10,
      singleTheme: "기본",
      totalSecond: 100000,
      membership: {
          grade: "gold"
      }
    },
    chatRoom: {
      seq: 1,
      lastMessageSeq: 100,
      lastMessageTime: ""
    }
  },
  {
    seq:8,
    title: "아무나 들어오세요",
    description: "조용히 공부합시다~",
    maxSize: 4,
    currentSize: 2,
    multiTheme: "우주",
    publicType: 0,
    invitationCode: "1234",
    participants: [],
    host: {
      seq: 2,
      id: "leessafy",
      nickname: "이싸피",
      email: "lee@ssafy.com",
      password: "password",
      catSkin: 1,
      singleTheme: "기본",
      totalSecond: 100000,
      membership: {
          grade: "silver"
      }
    },
    chatRoom: {
      seq: 2,
      lastMessageSeq: 100,
      lastMessageTime: ""
    }
  },
  {
    seq:9,
    title: "Flight A608✈",
    description: "싸피 8기 공통6반 Flight A608조",
    maxSize: 6,
    currentSize: 6,
    multiTheme: "바다",
    publicType: 0,
    invitationCode: "4608",
    participants: [],
    host: {
      seq: 3,
      id: "minsu",
      nickname: "이민수",
      email: "minsu@naver.com",
      password: "password",
      catSkin: 5,
      singleTheme: "기본",
      totalSecond: 100000,
      membership: {
          grade: "diamond"
      }
    },
    chatRoom: {
      seq: 3,
      lastMessageSeq: 100,
      lastMessageTime: ""
    }
  },
  {
    seq:10,
    title: "Flight A608✈",
    description: "싸피 8기 공통6반 Flight A608조",
    maxSize: 6,
    currentSize: 6,
    multiTheme: "바다",
    publicType: 0,
    invitationCode: "4608",
    participants: [],
    host: {
      seq: 3,
      id: "minsu",
      nickname: "이민수",
      email: "minsu@naver.com",
      password: "password",
      catSkin: 5,
      singleTheme: "기본",
      totalSecond: 100000,
      membership: {
          grade: "diamond"
      }
    },
    chatRoom: {
      seq: 3,
      lastMessageSeq: 100,
      lastMessageTime: ""
    }
  },
  {
    seq:11,
    title: "Flight A608✈",
    description: "싸피 8기 공통6반 Flight A608조",
    maxSize: 6,
    currentSize: 6,
    multiTheme: "바다",
    publicType: 0,
    invitationCode: "4608",
    participants: [],
    host: {
      seq: 3,
      id: "minsu",
      nickname: "이민수",
      email: "minsu@naver.com",
      password: "password",
      catSkin: 5,
      singleTheme: "기본",
      totalSecond: 100000,
      membership: {
          grade: "diamond"
      }
    },
    chatRoom: {
      seq: 3,
      lastMessageSeq: 100,
      lastMessageTime: ""
    }
  },
  {
    seq:12,
    title: "Flight A608✈",
    description: "싸피 8기 공통6반 Flight A608조",
    maxSize: 6,
    currentSize: 6,
    multiTheme: "바다",
    publicType: 0,
    invitationCode: "4608",
    participants: [],
    host: {
      seq: 3,
      id: "minsu",
      nickname: "이민수",
      email: "minsu@naver.com",
      password: "password",
      catSkin: 5,
      singleTheme: "기본",
      totalSecond: 100000,
      membership: {
          grade: "diamond"
      }
    },
    chatRoom: {
      seq: 3,
      lastMessageSeq: 100,
      lastMessageTime: ""
    }
  },
  {
    seq:9,
    title: "Flight A608✈",
    description: "싸피 8기 공통6반 Flight A608조",
    maxSize: 6,
    currentSize: 6,
    multiTheme: "바다",
    publicType: 0,
    invitationCode: "4608",
    participants: [],
    host: {
      seq: 3,
      id: "minsu",
      nickname: "이민수",
      email: "minsu@naver.com",
      password: "password",
      catSkin: 5,
      singleTheme: "기본",
      totalSecond: 100000,
      membership: {
          grade: "diamond"
      }
    },
    chatRoom: {
      seq: 3,
      lastMessageSeq: 100,
      lastMessageTime: ""
    }
  },
  // { seq: 1, title: "알고리즘 공부방" },
  // { seq: 2, title: "아무나 들어오세요." },
  // { seq: 3, title: "Flight A608" },
  // { seq: 4, title: "역삼초3학년모여" },
]);
</script>

<template>
  <Wrapper :class="$style.wrapper_flex">
    <aside :class="$style.side_bar_flex_item"></aside>
    <Wrapper :class="$style.section_flex_item">
      <RoomListHeader />
      <section :class="$style.wrapper_flex">
        <StudyRoomListItem
          :class="$style.study_room_flex_item"
          v-for="roomItem in roomList"
          :key="roomItem.seq"
          :roomItem="roomItem"
        />
      </section>
    </Wrapper>
    <aside :class="$style.side_bar_flex_item"></aside>
  </Wrapper>
</template>

<style lang="css" module>
@import "./StudyRoomList.module.css";
</style>
