import http from "@/api/http";

export default {
    // todo 작성
    postTodo(payload){
        http.post(`/todos`, payload)
        .then(({data}) => {
            console.log(data);
        })
        .catch((error) => {
            console.log(error);
        });
    },
    // todo 조회
    findTodo(seq){
        http.get(`/todos/${seq}`)
            .then((response) => {
                const code = response.status;

                if (code == 200) {
                    console.log(response.data)
                } else if(code == 204) {
                    alert("todo 조회 실패: todo 없음")
                }
            })
            .catch((error) => {
                console.log(error);
            });
    },
    // todo 수정
    updateTodo(payload){
        const payload2 = {
            categoriesName: payload.categoriesName,
            title: payload.title,
            status: payload.status,
            totalSecond: payload.totalSecond
        }
        http.put(`/todos/${payload.todosSeq}`, payload2)
            .then((response) => {
                const code = response.status;

                if (code == 201) {
                    console.log(response.data)
                } else if(code == 204) {
                    alert("todo 수정 실패: todo 없음")
                }
            })
            .catch((error) => {
                console.log(error);
            });
    },
    // todo 삭제
    deleteTodo(seq){
        http.delete(`/todos/${seq}`)
            .then((response) => {
                const code = response.status;

                if (code == 200) {
                    console.log(response.data)
                } else if(code == 204) {
                    alert("todo 삭제 실패: todo 없음")
                }
            })
            .catch((error) => {
                console.log(error);
            });
    },
    // todo 목록조회
    findTodoList(usersSeq){
        http.get(`/todos`, {
            params : {
                user: usersSeq
            }})
            .then((response) => {
                const code = response.status;

                if (code == 200) {
                    console.log("찾은 todo들 "+ JSON.stringify(response.data));
                } else if (code == 204) {
                    alert("todo 찾기 실패: 사용자없음");
                }
            })
            .catch((error) => {
                console.log(error);
            })
    },
}