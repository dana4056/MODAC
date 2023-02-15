import http from "@/api/http";

export default {
  // todo 작성
  async postTodo(usersSeq, categoriesName, title) {
    const payload = {
      usersSeq,
      categoriesName,
      title,
    };

    const { data } = await http.post(`/todos`, payload);
    return data;
  },

  // todo 조회
  async findTodo(seq) {
    const response = await http.get(`/todos/${seq}`);
    // console.log(response);
    return response.data;

    // .then((response) => {
    //   const code = response.status;

    //   if (code == 200) {
    //     console.log(response.data);
    //   } else if (code == 204) {
    //     alert("todo 조회 실패: todo 없음");
    //   }
    // })
    // .catch((error) => {
    //   console.log(error);
    // });
  },

  // todo 수정
  async updateTodo(todosSeq, categoriesName, title, status, totalSecond) {
    const payload = {
      categoriesName,
      title,
      status,
      totalSecond,
    };
    const response = await http.put(`/todos/${todosSeq}`, payload);
    return response.data;

    // .then((response) => {
    //   const code = response.status;

    //   if (code == 201) {
    //     console.log(response.data);
    //   } else if (code == 204) {
    //     alert("todo 수정 실패: todo 없음");
    //   }
    // })
    // .catch((error) => {
    //   console.log(error);
    // });
  },

  // todo 삭제
  async deleteTodo(seq) {
    const response = await http.delete(`/todos/${seq}`);
    // console.log(response);
    return response.data;

    // .then((response) => {
    //   const code = response.status;

    //   if (code == 200) {
    //     console.log(response.data);
    //   } else if (code == 204) {
    //     alert("todo 삭제 실패: todo 없음");
    //   }
    // })
    // .catch((error) => {
    //   console.log(error);
    // });
  },

  // todo 목록조회
  async findTodoList(usersSeq) {
    const response = await http.get(`/todos`, {
      params: {
        user: usersSeq,
      },
    });

    return response.data;
    // response.status === 200
    //   ? console.log("찾은 todo들 " + JSON.stringify(response.data))
    //   : alert("todo 찾기 실패: 사용자없음");
    // return response.data;
  },
};
