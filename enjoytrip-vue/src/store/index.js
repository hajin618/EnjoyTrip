import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";

import userStore from "../store/UserStore.js";

Vue.use(Vuex)

export default new Vuex.Store({
  // 저장할 녀석들
  state: {
  },
  // 실제 화면 단에서 쓸 데이터 꺼내가는 부분
  getters: {
  },
  // 동기 처리 값 변경 메소드 파라미터는 state
  mutations: {
  },
  // 비동기로직
  // 실제 API 호출 개념
  // context 처리
  actions: {
  },
  modules: {
    userStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
})
