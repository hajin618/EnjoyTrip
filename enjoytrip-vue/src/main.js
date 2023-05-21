import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

// Import Bootstrap and BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import vuetify from './plugins/vuetify'

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

// Vue 개발자 도구를 활성화합니다.
Vue.config.devtools = true;

Vue.config.productionTip = false;

// Vue 인스턴스 생성 전에 Kakao JavaScript SDK 로드
var KakaoInitPromise = new Promise((resolve) => {
  const script = document.createElement('script');
  script.onload = () => {
    resolve();
  };
  script.src = '//developers.kakao.com/sdk/js/kakao.js';
  document.head.appendChild(script);
});

new Vue({
  router,
  store,
  render: h => h(App),
  vuetify,

  created() {
    KakaoInitPromise.then(() => {
      // Kakao SDK 로드 후에 초기화 코드 실행
      window.Kakao.init("e2b21eab2fd3c794822457b8091ad15d");
    });
  }
}).$mount('#app')

