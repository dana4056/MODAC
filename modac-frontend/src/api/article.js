import http from "@/api/http";

export default {
    // 게시글 작성
    postArticle(payload) {
        http.post(`/articles`, payload)
            .then(({ data }) => {
                console.log(data);
            })
            .catch((error) => {
                console.log(error);
            });
    },  
    // 사용자 게시글 전체 조회
    findArticleByUsersSeq(payload) {
        http.get(`/articles`, {
            params : {
                user: payload.usersSeq,
                offset: payload.offset,
                limit: payload.limit
            }})
            .then((response) => {
                const code = response.status;

                if (code == 200) {
                    console.log("찾은 게시글들 "+ JSON.stringify(response.data));
                } else if (code == 204) {
                    alert("게시글 찾기 실패: 사용자없음");
                }
            })
            .catch((error) => {
                console.log(error);
            })
    },
    // 게시글 조회
    findArticle(seq){
        http.get(`/articles/${seq}`)
            .then((response) => {
                const code = response.status;

                if (code == 200) {
                    console.log(response.data)
                } else if(code == 204) {
                    alert("게시글 조회 실패: 게시글 없음")
                }
            })
            .catch((error) => {
                console.log(error);
            });
    },
    // 게시글 조회수 올리기
    updateViewCount(seq){
        http.post(`/articles/${seq}/view`)
        .then((response) => {
            const code = response.status;

            if (code == 200) {
                console.log(response.data)
            } else if(code == 204) {
                alert("게시글 조회 실패: 게시글 없음")
            }
        })
        .catch((error) => {
            console.log(error);
        });
    },
    // 게시글 삭제
    deleteArticle(seq){
        http.delete(`/articles/${seq}`)
        .then((response) => {
            const code = response.status;

            if (code == 200) {
                console.log(response.data)
            } else if(code == 204) {
                alert("게시글 조회 실패: 게시글 없음")
            }
        })
        .catch((error) => {
            console.log(error);
        });
    },
    // 팔로잉 기반 게시글 목록 조회
    findArticleByFollowing(payload){
        http.get(`/articles/following`, {
            params : {
                user: payload.usersSeq,
                offset: payload.offset,
                limit: payload.limit
            }})
            .then((response) => {
                const code = response.status;

                if (code == 200) {
                    console.log("찾은 게시글들 "+ JSON.stringify(response.data));
                } else if (code == 204) {
                    alert("게시글 찾기 실패: 사용자없음");
                }
            })
            .catch((error) => {
                console.log(error);
            })
    },
    // 게시글 좋아요
    likeArticle(payload){
        http.post(`/articles/like`, payload)
        .then((response) => {
            const code = response.status;

            if (code == 201) {
                console.log(response.data)
            } else if(code == 204) {
                alert("게시글 좋아요 실패: 게시글 없음")
            }
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 게시글 좋아요 취소
    unlikeArticle(payload){
        http.delete(`/articles/like`, {
            params : {
                usersSeq : payload.usersSeq,
                articlesSeq: payload.articlesSeq
            }})
        .then((response) => {
            const code = response.status;

            if (code == 200) {
                console.log(response.data)
            } else if(code == 204) {
                alert("게시글 좋아요 취소 실패: 게시글 없음")
            }
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 게시글 좋아요 관계정보 조회
    isLikedArticle(payload){
        http.get(`/articles/like`, {
            params : {
                usersSeq : payload.usersSeq,
                articlesSeq: payload.articlesSeq
            }})
        .then((response) => {
            const code = response.status;

            if (code == 200) {
                console.log(response.data)
            } else if(code == 204) {
                alert("게시글 조회 실패: 게시글 없음")
            }
        })
        .catch((error) => {
            console.log(error);
        })
    }
}