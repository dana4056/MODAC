import http from "@/api/http";
const headers = {
    "Content-Type":"application/json"
}

export default{
    postUser(user) {        // 회원가입

        // *user 데이터 형식 (vue파일에서 보내는 데이터)*
        // const user = {
        //     id: "vue test2",
        //     nickname: "vue nick2",
        //     email: "vue2@naver.com",
        //     password: "vue2",
        // };

        http.post(`/users`, user, { headers })
        .then(({ data }) => {
            console.log(data);
        })
        .catch((error) => {
            console.log(error);
        });
    },

    fetchUser(userSeq){     // 회원정보 조회
        http.get(`/users/${userSeq}`)
        .then(({data}) => {
            console.log(data);
        })
        .catch((error) => {
            console.log(error);
        });
    },

    updateUser(obj){     // 회원정보 수정

        // *user 데이터 형식 (vue파일에서 보내는 데이터)*
        // const user = {
        //     nickname: "vue nick2",
        //     email: "vue2@naver.com",
        // };

        http.put(`/users/${obj.userSeq}`, obj.user)
        .then(({data}) => {
            console.log(data);
        })
        .catch((error) => {
            console.log(error);
        });
    },

    // updatePW(userSeq, password){     // 비밀번호 수정

    //     // *password 데이터 형식 -> 문자열

    //     http.put(`/users/${userSeq}/password`, password)
    //     .then((response) => {
    //         console.log("비밀번호 변경 완료");
    //     })
    //     .catch((error) => {
    //         console.log(error);
    //     });
    // },

    // updatePoint(userSeq, point){     // 포인트 수정

    //     // *point 데이터 형식 -> 문자열

    //     http.put(`/users/${userSeq}/point`, point)
    //     .then(({data}) => {
    //         console.log(data);
    //     })
    //     .catch((error) => {
    //         console.log(error);
    //     });
    // },

    // deleteUser(userSeq, userSeq){     // 회원 탈퇴

    //     // *point 데이터 형식 -> 문자열

    //     http.delete(`/users/${userSeq}`)
    //     .then(({data}) => {
    //         console.log(data);
    //     })
    //     .catch((error) => {
    //         console.log(error);
    //     });
    // },


}