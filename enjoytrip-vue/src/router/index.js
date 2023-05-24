import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SearchView from '../views/SearchView.vue'
import ChartView from '../views/ChartView.vue'
import store from "@/store";
import Swal from "sweetalert2";

Vue.use(VueRouter)

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const checkToken = store.getters["userStore/checkToken"];
  let token = sessionStorage.getItem("access-token");

  if (checkUserInfo != null && token) {
    await store.dispatch("userStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    Swal.fire({
      icon: 'error',
      title: '유효하지 않은 접근!',
      text: '로그인 후 이용해주세요!',
    })
    router.push({ name: "loginView" }); 
  } else {
    next();
  }
};

const routes = [
  {
    path: '/',
    name: "HomeView",
    component: HomeView
  },
  {
    path: "/search",
    name: "SearchView",
    component: SearchView,
    beforeEnter: onlyAuthUser,
  },
  {
    path: "/chart",
    name: "ChartView",
    component: ChartView,
  },
  {
    path: "/user",
    name: "UserView",
    component: () => import(/* webpackChunkName: "user" */ "../views/UserView.vue"),
    children: [
      {
        path: "login",
        name: "loginView",
        component: () => import(/* webpackChunkName: "user" */ "../components/user/UserLogin.vue"),
      },
      {
        path: "join",
        name: "joinView",
        component: () =>
          import(/* webpackChunkName: "user" */ "../components/user/UserJoin.vue"),
      },
      {
        path: "findId",
        name: "findIdView",
        component: () =>
          import(/* webpackChunkName: "user" */ "../components/user/UserFindId.vue"),
      },
      {
        path: "findPw",
        name: "findPwView",
        component: () =>
          import(/* webpackChunkName: "user" */ "../components/user/UserFindPw.vue"),
      },
      {
        path: "myPage",
        name: "myPageView",
        beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "user" */ "../components/user/UserMyPage.vue"),
      },
    ],
  },
  {
    path: '/notice',
    name: 'noticeView',
    component: () => import("@/views/NoticeView.vue"),
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "noticeList",
        component: () => import("@/components/notice/NoticeList.vue"),
      },
      {
        path: "regist",
        name: "noticeRegister",
        component: () => import("@/components/notice/NoticeRegister.vue"),
      },
      {
        path: "datail/:notice_idx",
        name: "noticeDetail",
        component: () => import("@/components/notice/NoticeDetail.vue"),
      },
      {
        path: "modify/:notice_idx",
        name: "noticeModify",
        component: () => import("@/components/notice/NoticeModify.vue"),
      },
      {
        path: "delete/:notice_idx",
        name: "noticeDelete",
        component: () => import("@/components/notice/NoticeDelete.vue")
      }
    ]
  },
  {
    path: "/review",
    name: "reviewBoardView",
    component: () => import(/* webpackChunkName: "review" */ "../views/ReviewBoardView.vue"),
    redirect: "/review/list",
    children: [ 
      {
        path: "list",
        name: "reviewList",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/reviewboard/ReviewList.vue"),
        //test
        // component: () => import("@/components/reviewboard/ReviewDetail.vue"),
        // component: () => import("@/components/reviewboard/ReviewRegister.vue"),
        // component: () => import("@/components/reviewboard/ReviewModify.vue"),
      },
      {
        path: "regist",
        name: "reviewRegister",
        component: () => import("@/components/reviewboard/ReviewRegister.vue"),
      },
      {
        path: "detail/:review_idx",
        name: "reviewDetail",
        component: () => import("@/components/reviewboard/ReviewDetail.vue"),
      },
      {
        path: "modify/:review_idx",
        name: "reviewModify",
        component: () => import("@/components/reviewboard/ReviewModify.vue"),
      },
      {
        path: "delete/:review_idx",
        name: "reviewDelete",
        component: () => import("@/components/reviewboard/ReviewDelete.vue")
      }
    ]
  },
  {
    path: '/plan',
    name: "PlanView",
    component: () => import("@/views/PlanView.vue"),
    redirect: "/plan/list",
    children: [
      {
        path: "list",
        name: "planList",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/plan/PlanList.vue"),
      },
      {
        path: "detail/:plan_idx",
        name: "planDetail",
        component: () => import("@/components/plan/PlanDetail.vue"),
      },
      {
        path: "modify/:plan_idx",
        name: "planModify",
        component: () => import("@/components/plan/PlanModify.vue"),
      },
      {
        path: "regist/:plan_idx",
        name: "planRegister",
        component: () => import("@/components/plan/PlanRegister.vue"),
      }
    ]
  },
]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router;
