<template>
    <div>
        <header-navi-bar></header-navi-bar>
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

</style>