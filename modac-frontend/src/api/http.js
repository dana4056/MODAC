import axios from "axios";
import BACKEND_API_URL from "./backend";

// axios 객체 생성
export default axios.create({
  baseURL: BACKEND_API_URL,

  // headers: {
  //   "Content-Type": "application/json"
  // },
});
