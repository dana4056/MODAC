import http from "@/api/http";

export default {
    // DB에 채팅 메시지 하나 저장?
    postChat(chatDaTA) {      
        http.post()
            .then(({ data }) => {
                console.log(data);
            })
            .catch((error) => {
                console.log(error);
            });
    },


}