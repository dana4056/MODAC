import { defineStore } from "pinia";
import { ref } from "vue";
import { useChatStore } from "@/stores/chat";
import { useUserStore } from "@/stores/user";
import { storeToRefs } from "pinia";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client/dist/sockjs.min.js";
import BACKEND_API_URL from "@/api/backend";
import room from "../api/room";

export const useRoomStore = defineStore(
  "room",
  () => {
    // 필요한 다른 스토어 데이터들
    const userStore = useUserStore();
    const chatStore = useChatStore();
    const { groupChatLogs } = storeToRefs(chatStore);
    const { loginUser } = storeToRefs(userStore);

    // 변수
    const isEnteredRoom = ref(false);
    const isDeleteRoom = ref(false);
    const api = room;
    const checkCode = ref(false);
    const room_info = ref({});
    const room_list = ref([]);
    const isOpenedLeftSideBar = ref(false);
    const isOpenedRightSideBar = ref(false);
    const currentRightSideBarContent = ref(0); // 초깃값 : Room Chat
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
    let stompClient = null; // 단체 채팅용
    let stompClient2 = null; // 참가자용



  // 함수
  const enterRoom = async (roomSeq) => {
    console.log("store의 enterRoom  seq:"+roomSeq)
    await api.findRoom(roomSeq);      // 입장한 룸 정보 불러오기
    connect2();                 // 소켓 연결 
    isEnteredRoom.value = true;
  };
  const exitRoom = () => {
    if (stompClient != null) {
      console.log("퇴장 요청 보냄");
      sendEnter(0); 
      stompClient.disconnect(function () {
        console.log("DISCONNECT 소켓 연결해제")
      });
    }
    api.findRoomList(loginUser.value.seq) // 룸리스트 정보 가져오기
    isEnteredRoom.value = false;
  };
  const deleteRoom = () => {
    isDeleteRoom.value = true;
  };

  const toggleLeftSideBar = () => {
    isOpenedLeftSideBar.value = isOpenedLeftSideBar.value ? false : true;
  };
  const toggleRightSideBar = () => {
    isOpenedRightSideBar.value = isOpenedRightSideBar.value ? false : true;
  };

  const changeRightSideBarContent = (targetContent) => {
    currentRightSideBarContent.value = targetContent;
  };
  
  const seq = ref(5);
  const title = ref("Flight A608");
  const currentSize = ref(3);
  const maxSize = ref(6);
  const description = ref("싸피 8기 공통 6반");
  
  
  // ================================ 채팅관련 =================================
  let chatListElement = ref(document);
  let chatFormElement = ref(null);


  const liftMessage = () => {
    setTimeout(() => {
      chatListElement.value.scrollTop = chatListElement.value.scrollHeight;
    }, 300);
  }

  const enterChat = (chatMessage) => {
    if (chatMessage) {
      liftMessage();
      
      // 채팅 input창 초기화
      chatFormElement.value.inputChatMessage = "";
      
      const date = new Date();
      const year = date.getFullYear();
      const month = ("0" + (date.getMonth() + 1)).slice(-2);
      const day = ("0" + date.getDate()).slice(-2);
      const dateString = year + "-" + month + "-" + day;
      const hours = ("0" + date.getHours()).slice(-2);
      const minutes = ("0" + date.getMinutes()).slice(-2);
      const seconds = ("0" + date.getSeconds()).slice(-2);
      const timeString = hours + ":" + minutes + ":" + seconds;
      
      const chatData = {
        user: loginUser.value,
        chatRoomSeq: room_info.value.chatRoom.seq,
        sendTime: dateString + " " + timeString,
        message: chatMessage,
      };
        
      const sendData = {
        usersSeq: chatData.user.seq,
        chatRoomsSeq: chatData.chatRoomSeq,
        sendTime: chatData.sendTime,
        message: chatData.message,
        messageType: "TALK",
        chatRoomType: "GROUP",
      };

      groupChatLogs.value.push(chatData);
      
      // 소켓 send
      stompClient.send(`/pub/messages/group`, JSON.stringify(sendData), {});
    }
  };
  
  const connect = () => {
    var socket = new SockJS(BACKEND_API_URL + "/ws");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
  }
  
  
  const onConnected = () => { // async ?
    stompClient.subscribe(
      `/topic/chat/rooms/enter/group/${room_info.value.chatRoom.seq}`,
      onMessageReceived
    );
  }
  
  const onError = () => {
    console.log("소켓 연결 실패");
  }
  
  const onMessageReceived = (res) => {
    setTimeout(() => {
      var chat = JSON.parse(res.body);
      console.log("구독으로 받은 메시지", chat);
  
      if (chat.user.seq != loginUser.value.seq) {
        groupChatLogs.value.push(chat);
      }
      api.findRoomList(loginUser.value.seq); // 룸리스트 정보 가져오기
      isEnteredRoom.value = false;
    };
    const deleteRoom = () => {
      isDeleteRoom.value = true;
    };

    const toggleLeftSideBar = () => {
      isOpenedLeftSideBar.value = isOpenedLeftSideBar.value ? false : true;
    };
    const toggleRightSideBar = () => {
      isOpenedRightSideBar.value = isOpenedRightSideBar.value ? false : true;
    };

    const changeRightSideBarContent = (targetContent) => {
      currentRightSideBarContent.value = targetContent;
    };

    const seq = ref(0);
    const title = ref("");
    const currentSize = ref(0);
    const maxSize = ref(0);
    const description = ref("");



    // ================================ 채팅관련 =================================
    let chatListElement = ref(document);
    let chatFormElement = ref(null);

    const liftMessage = () => {
      setTimeout(() => {
        chatListElement.value.scrollTop = chatListElement.value.scrollHeight;
      }, 300);
    };

    const enterChat = (chatMessage) => {
      if (chatMessage) {
        liftMessage();

        // 채팅 input창 초기화
        chatFormElement.value.inputChatMessage = "";

        const date = new Date();
        const year = date.getFullYear();
        const month = ("0" + (date.getMonth() + 1)).slice(-2);
        const day = ("0" + date.getDate()).slice(-2);
        const dateString = year + "-" + month + "-" + day;
        const hours = ("0" + date.getHours()).slice(-2);
        const minutes = ("0" + date.getMinutes()).slice(-2);
        const seconds = ("0" + date.getSeconds()).slice(-2);
        const timeString = hours + ":" + minutes + ":" + seconds;

        const chatData = {
          user: loginUser.value,
          chatRoomSeq: room_info.value.chatRoom.seq,
          sendTime: dateString + " " + timeString,
          message: chatMessage,
        };

        // 일단 배열에 추가 (하면 안되겠다 불러와야할듯 request랑 response dto가 다름)
        groupChatLogs.value.push(chatData);

        const sendData = {
          usersSeq: chatData.user.seq,
          chatRoomsSeq: chatData.chatRoomSeq,
          sendTime: chatData.sendTime,
          message: chatData.message,
          messageType: "TALK",
          chatRoomType: "GROUP",
        };

        // 소켓 send
        stompClient.send(`/pub/messages/group`, JSON.stringify(sendData), {});
      }
    };

    const connect = () => {
      var socket = new SockJS(BACKEND_API_URL + "/ws");
      stompClient = Stomp.over(socket);
      stompClient.connect({}, onConnected, onError);
    };

    const onConnected = () => {
      // async ?
      stompClient.subscribe(
        `/topic/chat/rooms/enter/group/${room_info.value.chatRoom.seq}`,
        onMessageReceived
      );
    };

    const onError = () => {
      console.log("소켓 연결 실패");
    };

    const onMessageReceived = (res) => {
      setTimeout(() => {
        var chat = JSON.parse(res.body);
        console.log("구독으로 받은 메시지", chat);

        if (chat.user.seq != loginUser.value.seq) {
          groupChatLogs.value.push(chat);
        }
        liftMessage();
      }, 500);
    };
    // =============================== 참가자 관련 ========================
    const connect2 = () => {
      var socket = new SockJS(BACKEND_API_URL + "/ws");
      stompClient2 = Stomp.over(socket);
      stompClient2.connect({}, onConnected2, onError2);
    };

    const onConnected2 = () => {
      // async ?
      console.log(room_info.value);
      stompClient2.subscribe(
        `/enter/rooms/${room_info.value.seq}`,
        onMessageReceived2
      );
      console.log("입장 메시지 보냄");
      sendEnter(1);
    };

    const onError2 = () => {
      console.log("소켓 연결 실패");
    };

    const sendEnter = (isAttend) => {
      const data = {
        usersSeq: loginUser.value.seq,
        attend: isAttend,
        roomsSeq: room_info.value.seq,
      };
      stompClient2.send(`/pub/message/enter`, JSON.stringify(data), {});
    };

    const onMessageReceived2 = (res) => {
      setTimeout(() => {
        api.findRoom(room_info.value.seq);
        var flag = res.body;
        console.log("구독으로 받은 메시지", flag);

        if (flag == "ENTER") {
          console.log("[참가자가 입장했습니다]");
        } else if (flag == "EXIT") {
          console.log("[참가자가 퇴장했습니다]");
        }
      }, 500);
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
      // participants,
      statusMap,
      statusStyleMap,
      room_info,
      room_list,
      //채팅관련
      stompClient,
      connect,
      liftMessage,
      enterChat,
      chatListElement,
      chatFormElement,
    };
  }
  // {
  //   persist: true,
  // }
);
