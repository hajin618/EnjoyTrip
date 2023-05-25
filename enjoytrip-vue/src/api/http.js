import axios from "axios";

// axios 객체 생성
export default axios.create({
  // baseURL: "/",
  // baseURL: "http://localhost:8080/",
  baseURL: "http://192.168.208.85:8080/",
  headers: { 
    "Content-Type": "application/json;charset=utf-8",
  },
});