
import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "http://localhost:8080",
  // baseURL: "http://70.12.247.126:8080",  // 민수 IP
//   headers: {
//      토큰가져와서 넣을까?
//   },
});