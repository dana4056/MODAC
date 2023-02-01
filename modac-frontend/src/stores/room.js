import { defineStore } from "pinia";
import { ref } from "vue";

export const useRoomStore = defineStore("room", () => {
  const isEnteredRoom = ref(false);
  const isDeleteRoom = ref(false);

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

  const currentRightSideBarContent = ref(0); // 초깃값 : Room Chat
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
      nickname: "A_NICKNAME",
      status: 0,
      catSkin: 1,
      categoriesName: "알고리즘",
    },
    {
      userSeq: 2,
      nickname: "B_NICKNAME",
      status: 1,
      catSkin: 2,
      categoriesName: "CS",
    },
    {
      userSeq: 3,
      nickname: "C_NICKNAME",
      status: 2,
      catSkin: 3,
      categoriesName: "기획",
    },
  ]);

  return {
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
  };
});
