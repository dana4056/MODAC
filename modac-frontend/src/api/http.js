
import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "http://localhost:8080",
//   headers: {
//      토큰가져와서 넣을까?
//   },
});