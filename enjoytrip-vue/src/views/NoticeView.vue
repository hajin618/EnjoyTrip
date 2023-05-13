<template>
    <div>
        <header-navi-bar></header-navi-bar>
        <div class = "title">
            <h2>공지사항</h2>
        </div>
        <div>
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
import HeaderNaviBar from "../components/layout/HeaderNaviBar.vue"

export default{
    name: "NoticeView",
    components: {
        HeaderNaviBar,
    }
}

</script>

<style scoped>
.title{
    padding-top : 100px;
    padding-bottom: 50px;
  }
h2{
    text-align : center;
  }
</style>