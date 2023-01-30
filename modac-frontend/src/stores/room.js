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
  };
});
