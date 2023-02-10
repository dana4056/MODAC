import http from "@/api/http";

export default {
    // 댓글 작성
    postComment(payload){
        http.post(`/comments`, payload)
            .then(({ data }) => {
                console.log(data);
            })
            .catch((error) => {
                console.log(error);
            });
    },
    // 게시글별 댓글 목록 조회
    findCommentList(articlesSeq){
        http.get(`/comments`, {
            params : {
                article : articlesSeq
            }})
        .then((response) => {
            const code = response.status;

            if (code == 200) {
                console.log("찾은 댓글들 "+ JSON.stringify(response.data));
            } else if (code == 204) {
                alert("댓글 찾기 실패: 게시글 없음");
            }
        })
        .catch((error) => {
            console.log(error);
        });
    },
    // 댓글 삭제
    deleteComment(seq){
        http.delete(`/comments/${seq}`)
        .then((response) => {
            const code = response.status;

            if (code == 200) {
                console.log(response.data)
            } else if (code == 204) {
                alert("댓글 찾기 실패: 댓글 없음");
            }
        })
        .catch((error) => {
            console.log(error);
        });
    }

}