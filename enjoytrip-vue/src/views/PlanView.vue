<template>
    <div>
        <header-navi-bar></header-navi-bar>
        <div>
            <router-view></router-view>
        </div>
    </div>
</template>

<script>

import HeaderNaviBar from "@/components/layout/HeaderNaviBar.vue";

export default {
    name: "PlanView",
    components: {
        HeaderNaviBar
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

<style scoped></style>