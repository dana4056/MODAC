import http from "@/api/http";
import { useCommentStore } from "@/stores/comment";
import { storeToRefs } from "pinia";
import Message from "vue-m-message"

export default {
    // 댓글 작성
    postComment(payload){
        http.post(`/comments`, payload)
            .then(({ data }) => {
                const commentStore = useCommentStore();
                const { comments } = storeToRefs(commentStore);

                console.log("data api", data);
                comments.value.push(data);
                console.log("comments api", comments);
            })
            .catch((error) => {
                console.log(error);
            });
    },
    // 게시글별 댓글 목록 조회
    async findCommentList(articlesSeq){
        const response = await http.get(`/comments`, {
            params : {
                article : articlesSeq
            }
        })
        const commentStore = useCommentStore();
        const { comments } = storeToRefs(commentStore);
        const { commentTotalCnt } = storeToRefs(commentStore);
        
        comments.value = response.data;
        commentTotalCnt.value = await response.data.length;
        
        console.log("comments api", comments);
        console.log("commentTotalCnt api", commentTotalCnt);

            
        // .then((response) => {
        //     const code = response.status;

        //     if (code == 200) {

        //         const commentStore = useCommentStore();
        //         const { comments } = storeToRefs(commentStore);

        //         comments.value = response.data;

        //         // console.log("찾은 댓글들 "+ JSON.stringify(response.data));
        //     } else if (code == 204) {
        //         alert("댓글 찾기 실패: 게시글 없음");
        //     }
        // })
        // .catch((error) => {
        //     console.log(error);
        // });
    },
    // 댓글 삭제
    deleteComment(seq){
        http.delete(`/comments/${seq}`)
        .then((response) => {
            const code = response.status;

            if (code == 200) {
                console.log(response.data)
            } else if (code == 204) {
                // alert("댓글 찾기 실패: 댓글 없음");
                Message.error("존재하지 않는 댓글입니다 :-(",{position:"top-right",closable:true});
            }
        })
        .catch((error) => {
            console.log(error);
        });
    }

}