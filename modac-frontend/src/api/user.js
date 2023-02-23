import http from "@/api/http";
import { useUserStore } from "@/stores/user";
import { useRoomStore } from "@/stores/room";
import { storeToRefs } from "pinia";
import router from "@/router/index";
import Message from "vue-m-message";

// const headers = {
//     'Content-Type': 'application/json'
// }

export default {
  // 회원가입
  postUser(user) {
    http
      .post(`/users`, user)
      .then(({ data }) => {
        Message.success("회원가입이 완료되었습니다 :-)", {
          position: "top-right",
          closable: true,
        });

        router.push({ name: "login" });
        console.log(data);
        // return data;
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 회원정보 조회
  fetchUser(userSeq) {
    http
      .get(`/users/${userSeq}`)
      .then((response) => {
        console.log(response.data);
        // const code = response.status;
        return response.data;
        // if (code == 200) {
        //   console.log(response.data);
        // } else if (code == 204) {
        //   alert("회원정보 조회 실패: 회원 없음");
        // }
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 회원정보 수정
  updateUser(payload) {
    http
      .put(`/users/${payload.userSeq}`, payload.update)
      .then((response) => {
        const code = response.status;

        if (code == 201) {
          Message.success("회원정보가 수정되었습니다 :-)", {
            position: "top-right",
            closable: true,
          });
        } else if (code == 204) {
          Message.error("존재하지 않는 회원입니다 :-(", {
            position: "top-right",
            closable: true,
          });
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 비밀번호 수정
  updatePW(payload) {
    http
      .put(`/users/${payload.userSeq}/password`, payload.password, {
        headers: {
          "Content-Type": "text/plain",
        },
      })
      .then((response) => {
        const code = response.status;

        if (code == 201) {
          Message.success("비밀번호 변경이 완료되었습니다 :-)", {
            position: "top-right",
            closable: true,
          });
        } else if (code == 204) {
          Message.error("존재하지 않는 회원입니다 :-(", {
            position: "top-right",
            closable: true,
          });
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 포인트 수정
  updatePoint(payload) {
    http
      .put(`/users/${payload.userSeq}/point`, payload.point, {
        headers: {
          "Content-Type": "text/plain",
        },
      })
      .then((response) => {
        const code = response.status;

        if (code == 201) {
          console.log(response.data);
        } else if (code == 204) {
          Message.error("존재하지 않는 회원입니다 :-(", {
            position: "top-right",
            closable: true,
          });
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 회원 탈퇴
  deleteUser(userSeq) {
    http
      .delete(`/users/${userSeq}`)
      .then((response) => {
        const code = response.status;

        if (code == 200) {
          Message.error("탈퇴가 완료되었습니다.", {
            position: "top-right",
            closable: true,
          });
          router.push({ name: "login" });
        } else if (code == 204) {
          Message.error("존재하지 않는 회원입니다 :-(", {
            position: "top-right",
            closable: true,
          });
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 아이디찾기
  findUserId(email) {
    http
      .get(`/users/find-id`, {
        params: {
          email: email,
        },
      })
      .then((response) => {
        const code = response.status;

        if (code == 200) {
          console.log("찾은 ID: " + response.data);
        } else if (code == 204) {
          Message.error("존재하지 않는 회원입니다 :-(", {
            position: "top-right",
            closable: true,
          });
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 비밀번호 찾기
  findUserPW(email) {
    http
      .get(`/users/find-password`, {
        params: {
          email: email,
        },
      })
      .then((response) => {
        const code = response.status;

        if (code == 200) {
          console.log("찾은 PW: " + response.data);
        } else if (code == 204) {
          Message.error("존재하지 않는 회원입니다 :-(", {
            position: "top-right",
            closable: true,
          });
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 닉네임 중복확인
  checkUserNick(nick) {
    http
      .get(`/users/check-nick`, {
        params: {
          nick: nick,
        },
      })
      .then((response) => {
        const code = response.status;

        if (code == 200) {
          console.log("중복된 닉네임");
        } else if (code == 204) {
          console.log("사용 가능한 닉네임");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 아이디 중복확인
  checkUserId(id) {
    http
      .get(`/users/check-id`, {
        params: {
          id: id,
        },
      })
      .then((response) => {
        const code = response.status;

        if (code == 200) {
          console.log("중복된 아이디");
        } else if (code == 204) {
          console.log("사용 가능한 아이디");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 이메일 중복확인
  checkUserEmail(email) {
    http
      .get(`/users/check-email`, {
        params: {
          email: email,
        },
      })
      .then((response) => {
        const code = response.status;

        if (code == 200) {
          console.log("중복된 이메일");
        } else if (code == 204) {
          console.log("사용 가능한 이메일");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 로그인
  login(payload) {
    http
      .post(`/users/login`, payload)
      .then((response) => {
        const code = response.status;

        if (code == 200) {
          if (response.data) {
            Message.success(response.data.nickname + "님 환영합니다 :-)", {
              closable: true,
            });
            localStorage.setItem("jwt", response.data.token); // 로컬 스토리지에 저장

            const store = useUserStore();
            const { loginUser } = storeToRefs(store);

            loginUser.value = response.data; // userStore에 멤버 저장

            router.push({ name: "room" }); // 룸리스트뷰로 이동
            this.fetchFollowingUsers(loginUser.value.seq);
            this.fetchFollowerUsers(loginUser.value.seq);
          } else {
            Message.error("비밀번호가 일치하지 않습니다", {
              title: "로그인 실패",
              closable: true,
            });
          }
        } else if (code == 204) {
          Message.error("해당 회원을 찾을 수 없습니다", {
            title: "로그인 실패",
            closable: true,
          });
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 팔로잉
  async following(payload) {
    const response = await http.post(`/users/follow`, payload);

    if (response.status == 201) {
      console.log(payload.toSeq + "번 유저 팔로잉 성공");
    } else if (response.status == 204) {
      console.log("사용자를 찾을 수 없음");
    }

    // console.log("response", response);
  },

  // 언팔로잉
  async unfollowing(followSeq) {
    const response = await http.delete(`/users/follow/${followSeq}`);

    if (response.status == 200) {
      console.log("언팔로우 성공");
    } else if (response.status == 204) {
      console.log("언팔로우 실패: 팔로잉 정보 없음");
    }

    // console.log("response", response);
  },

  // 팔로잉 회원목록 조회(친구 조회)
  fetchFollowingUsers(userSeq) {
    http
      .get(`/users`, {
        params: {
          filter: "following",
          user: userSeq,
        },
      })
      .then((response) => {
        console.log(response.data);

        const userStore = useUserStore();
        const { followingList } = storeToRefs(userStore);

        followingList.value = response.data;
      });
  },

  // 팔로워 회원목록 조회(친구 조회)
  fetchFollowerUsers(userSeq) {
    http
      .get(`/users`, {
        params: {
          filter: "follower",
          user: userSeq,
        },
      })
      .then((response) => {
        // console.log(response.data);

        const userStore = useUserStore();
        const { followerList } = storeToRefs(userStore);

        followerList.value = response.data;
      });
  },

  // 팔로잉 여부 조회
  async isFollowing(payload) {
    const response = await http.get("/users/follow", {
      params: {
        from: payload.fromSeq,
        to: payload.toSeq,
      },
    });
    return response;
    // .then((response) => {
    //   const userStore = useUserStore();
    //   const { isFollowing } = storeToRefs(userStore);

    //   isFollowing.value = response.data;
    //   // console.log("isFollowing.value", isFollowing.value);
    // });
  },
};
