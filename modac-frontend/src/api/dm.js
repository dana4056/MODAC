import http from "@/api/http";
import { useDmStore } from '@/stores/dm';
import { storeToRefs } from "pinia"
import router from "@/router/index"

export default {

    // DM 채팅방 리스트 불러오기
    fetchRoomList(user) {      
        http.post(`/users`, user)
            .then(({ data }) => {
                console.log(data);
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
            const { liveDirectMessages } = storeToRefs(DMstore);
            
            liveDirectMessages.value = response.data;
        })
        .catch((error) => {
            console.log(error);
        });
    }
}