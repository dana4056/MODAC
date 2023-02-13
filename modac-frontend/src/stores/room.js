import { defineStore } from "pinia";
import { ref } from "vue";
import room from "../api/room";

export const useRoomStore = defineStore("room", () => {
  const isEnteredRoom = ref(false);
  const isDeleteRoom = ref(false);
  const api = room;
  const checkCode = ref(false);

  const room_info = ref({})
  const room_list = ref([])


  const enterRoom = () => {
    isEnteredRoom.value = true;
  };
  const exitRoom = () => {
    isEnteredRoom.value = false;
  };
  const deleteRoom = () => {
    isDeleteRoom.value = true;
  };

  const isOpenedLeftSideBar = ref(true);
  const isOpenedRightSideBar = ref(true);

  const toggleLeftSideBar = () => {
    isOpenedLeftSideBar.value = isOpenedLeftSideBar.value ? false : true;
  };
  const toggleRightSideBar = () => {
    isOpenedRightSideBar.value = isOpenedRightSideBar.value ? false : true;
  };

  const currentRightSideBarContent = ref(1); // 초깃값 : Room Chat
  const changeRightSideBarContent = (targetContent) => {
    if (targetContent === currentRightSideBarContent.value) {
      toggleRightSideBar();
      return;
    }
    currentRightSideBarContent.value = targetContent;
  };

  const seq = ref(5);
  const title = ref("Flight A608");
  const currentSize = ref(3);
  const maxSize = ref(6);
  const description = ref("싸피 8기 공통 6반");

  const participants = ref([
    {
      userSeq: 1,
      nickname: "일싸피",
      status: 0,
      catSkin: 1,
      categoriesName: "알고리즘",
    },
    {
      userSeq: 2,
      nickname: "이싸피",
      status: 1,
      catSkin: 2,
      categoriesName: "CS",
    },
    {
      userSeq: 3,
      nickname: "삼싸피",
      status: 2,
      catSkin: 4,
      categoriesName: "기획",
    },
    {
      userSeq: 4,
      nickname: "사싸피",
      status: 0,
      catSkin: 5,
      categoriesName: "알고리즘",
    },
    {
      userSeq: 5,
      nickname: "오싸피",
      status: 1,
      catSkin: 6,
      categoriesName: "CS",
    },
    {
      userSeq: 6,
      nickname: "육싸피",
      status: 2,
      catSkin: 3,
      categoriesName: "기획",
    },
  ]);

  const statusMap = {
    0: "공부 상태",
    1: "휴식 상태",
    2: "자리비움 상태",
  };

  const statusStyleMap = {
    0: "green",
    1: "yellow",
    2: "red",
  };

  return {
    api,
    checkCode,
    isEnteredRoom,
    isDeleteRoom,
    enterRoom,
    exitRoom,
    deleteRoom,
    isOpenedLeftSideBar,
    toggleLeftSideBar,
    isOpenedRightSideBar,
    toggleRightSideBar,
    currentRightSideBarContent,
    changeRightSideBarContent,
    seq,
    title,
    currentSize,
    maxSize,
    description,
    participants,
    statusMap,
    statusStyleMap,
    room_info,
    room_list,
  };
});
