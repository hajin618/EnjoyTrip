<template>
    <div class="mainImage">
        <header-navi-bar></header-navi-bar>
        <div style="font-size:60px; font-weight:bold; margin-left:300px; margin-top:80px;">
            FAMILY
        </div>

        <div style="font-size:40px; font-weight:bold; margin-left:340px;">
            UNFORGOTTABLE
        </div>

        <div style="font-size:60px; font-weight:bold; margin-left:420px;">
            YOUTH
        </div>

        <div style="border: 1px solid black; display: inline-block; padding: 5px; margin-left:250px; width:150px; height:50px; text-align:center; line-height:40px;">
            <router-link style="text-decoration: none; color: black;" to="/search">SEARCH</router-link>
        </div>
    </div>
</template>

<script>
import HeaderNaviBar from "../components/layout/HeaderNaviBar.vue"
// import FooterArea from "../components/layout/FooterArea.vue";

export default{
    name: "HomeView",
    components: {
        HeaderNaviBar,
        // FooterArea,
    }
}

</script>

<style scoped>
    .mainImage {
        width: 100%;
        height: 1000px;
        background-image: url("../assets/img/background.jpg");
        background-size: cover;
        background-repeat: no-repeat;
    }
</style>