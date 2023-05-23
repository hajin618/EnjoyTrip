import axios from "axios";

// axios 객체 생성
export default axios.create({
  // baseURL: "/",
  baseURL: "http://localhost:8080/",
  // baseURL: "http://192.168.208.10:8080/", 선진
  headers: { 
    "Content-Type": "application/json;charset=utf-8",
  },
});