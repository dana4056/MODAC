import { defineStore } from "pinia";
import { ref } from "vue";

export const useRoomStore = defineStore("room", () => {
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
    isOpenedLeftSideBar,
    toggleLeftSideBar,
    isOpenedRightSideBar,
    toggleRightSideBar,
    currentRightSideBarContent,
    changeRightSideBarContent,
  };
});
