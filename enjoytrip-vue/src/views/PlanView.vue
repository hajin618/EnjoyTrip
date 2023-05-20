<template>
    <div>
        <header-navi-bar></header-navi-bar>
        <div>
            <router-view></router-view>
        </div>
        <div class="footer">
            <footer-area></footer-area>
        </div> 
    </div>
</template>

<script>

import HeaderNaviBar from "@/components/layout/HeaderNaviBar.vue";
import FooterArea from "../components/layout/FooterArea.vue";

export default {
    name: "PlanView",
    components: {
        HeaderNaviBar,
        FooterArea,
    },
    data() {
        return {
            message: '',
        };
    },
    created() {},
    methods: {},
};
</script>

<style scoped>
    .footer{
        clear: both;
    }
</style>