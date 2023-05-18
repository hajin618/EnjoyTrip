import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function kakaoLogin(user, success, fail) {
  await api.post(`/kakaoLogin`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/logout/${userid}`).then(success).catch(fail);
}

async function deleteKakao(user_id, success, fail) {
  await api.delete(`/user/${user_id}`).then(success).catch(fail);
}

export { login, kakaoLogin, findById, tokenRegeneration, logout, deleteKakao };
