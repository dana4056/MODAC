import http from "@/api/http";

export default {
    // 스터디룸 생성
    postRoom(payload) {
        console.log('김동현 바보')
        http.post(`/rooms`, payload)
            .then(({ data }) => {
                console.log(data)
            })
            .catch((error) => {
                console.log(error)
            })
    },
    // 스터디룸 목록조회
    findRoomList(usersSeq) {
        http.get(`/rooms`, {
            params : {
                user : usersSeq
            }
        })
        .then((response) => {
            const code = response.status;

            if(code == 200){
                console.log("스터디룸 목록: "+JSON.stringify(response.data));
            }
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 스터디룸 조회
    findRoom(seq) {
        http.get(`/rooms/${seq}`)
        .then((response) => {
            const code = response.status;

            if(code == 200) {
                console.log("스터디룸: "+ JSON.stringify(response.data));
            }
            else if(code == 204) {
                console.log("스터디룸 조회 실패: 스터디룸 없음")
            }
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 스터디룸 수정
    updateRoom(payload) {
        const payload2 = {
            title: payload.title,
            description: payload.description,
            multiTheme: payload.multiTheme
        };
        http.put(`/rooms/${payload.seq}`, payload2)
        .then((response) => {
            const code = response.status;

            if(code == 201) {
                console.log("스터디룸 수정 완료: "+ JSON.stringify(response.data));
            }
            else if(code == 204) {
                console.log("스터디룸 수정 실패: 스터디룸 없음")
            }
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 스터디룸 삭제
    deleteRoom(seq) {
        http.delete(`/rooms/${seq}`)
        .then((response) => {
            const code = response.status;

            if(code == 200) {
                console.log("스터디룸 삭제 완료");
            }
            else if(code == 204) {
                console.log("스터디룸 삭제 실패: 스터디룸 없음")
            }
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 스터디룸 참가
    joinRoom(payload) {
        http.post(`/rooms/${payload.seq}/join`, payload.usersSeq, {
            headers: {
                'Content-Type': 'text/plain'
            }})
        .then(({data}) => {
            console.log("스터디룸 참가 성공: " + JSON.stringify(data));
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 스터디룸 나가기
    exitRoom(payload) {
        http.delete(`/rooms/${payload.seq}/join`, {
        params : {
            user: payload.usersSeq
        }})
        .then((response) => {
            const code = response.status;

            if(code == 200) {
                console.log("스터디룸 나가기 완료: "+ JSON.stringify(response.data));
            }
            else if(code == 204) {
                console.log("스터디룸 나가기 실패: 참가정보 없음")
            }
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 비공개 스터디룸 접속 시 접속 상태변경
    updateCurrentRoom(payload) {
        const payload2 = {
            usersSeq: payload.usersSeq,
            attend: payload.attend
        }
        http.put(`/rooms/${payload.seq}/join`, payload2)
        .then((response) => {
            const code = response.data;

            if(code == 201) {
                console.log("참가자 상태 변경 완료: " + response.data);
            }
            else if(code == 204) {
                console.log("참가자 상태 변경 실패: 참가자 없음");
            }
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 참가중인 비공개 스터디룸 목록조회(즐찾)
    findPrivateRoomList(usersSeq) {
        http.get(`/rooms/favorite`, {
            params : {
                user : usersSeq
            }})
        .then((response) => {
            const code = response.status;

            if(code == 200){
                console.log("스터디룸 목록: " + JSON.stringify(response.data));
            }
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 특정 비공개 스터디룸 멤버 확인 (인가)
    checkRoomMember(payload) {
        http.get(`/rooms/${payload.seq}/authorization`, {
            params : {
                user : payload.usersSeq
            }})
        .then((response) => {
            const code = response.status;

            if(code == 200){
                console.log("스터디룸 목록: " + JSON.stringify(response.data));
            }
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 특정 비공개 스터디룸 입장 권한 확인(인증)
    checkRoomCode(payload) {
        http.get(`/rooms/${payload.seq}/authentication`, {
            params : {
                code: payload.roomCode
            }})
        .then((response) => {
            const code = response.status;

            if(code == 200){
                console.log("스터디룸 입장코드 확인결과: " + response.data);
            }
        })
        .catch((error) => {
            console.log(error);
        })
    },
    // 스터디룸 검색
    searchRoom(payload) {
        http.get(`/rooms`, {
            params : {
                user : payload.usersSeq,
                keyword: payload.keyword
            }})
        .then((response) => {
            const code = response.status;

            if(code == 200){
                console.log("스터디룸 목록: " + JSON.stringify(response.data));
            }
        })
        .catch((error) => {
            console.log(error);
        })
    },


}