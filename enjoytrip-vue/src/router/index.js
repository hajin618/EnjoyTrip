import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SearchView from '../views/SearchView.vue'
import store from "@/store";

Vue.use(VueRouter)

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const checkToken = store.getters["userStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("userStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "loginView" }); 
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
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
        path: "datail/:review_idx",
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
