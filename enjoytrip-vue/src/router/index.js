import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
    {
    path: '/',
    name: "HomeView",
    component: HomeView
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
    ],
  },
  

  // {
  //   // path: '/about',
  //   // name: 'about',
  //   // // route level code-splitting
  //   // // this generates a separate chunk (about.[hash].js) for this route
  //   // // which is lazy-loaded when the route is visited.
  //   // component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // }

]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router;
