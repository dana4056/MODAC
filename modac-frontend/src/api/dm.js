import http from "@/api/http";
import { useDmStore } from '@/stores/dm';
import { storeToRefs } from "pinia"
import router from "@/router/index"

export default {

    // DM 채팅방 리스트 불러오기
    fetchRoomList(userSeq) {      
        console.log("채팅방 리스트 불러오기"+userSeq);
        http.get(`/chat/rooms`, {
            params:{
                user:userSeq,
            }})
            .then((response) => {
                const DMstore = useDmStore();
                const { directMessageRoomList } = storeToRefs(DMstore);
                
                directMessageRoomList.value = response.data;
            })
            .catch((error) => {
                console.log(error);
            });
    },

    fetchMessages(payload){
        http.get(`chat/rooms/${payload.roomSeq}/messages`,{
            params:{
                page:payload.page
            }
        })
        .then((response) => {
            const DMstore = useDmStore();
            const { directChatLogs } = storeToRefs(DMstore);
            
            directChatLogs.value = response.data;
        })
        .catch((error) => {
            console.log(error);
        });
    }
}