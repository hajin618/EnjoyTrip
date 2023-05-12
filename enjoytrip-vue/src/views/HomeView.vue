<template>
    <div>
        <header-navi-bar></header-navi-bar>
        <div class="main">
            <div class="left">
                <img src="../assets/img/mainPageImg.png" class="mainImage" alt="">
            </div>
            <div class="right">
                <div class="mainTextBox">

                </div>
            </div>
        </div>
    </div>
</template>

<script>
import HeaderNaviBar from "../components/layout/HeaderNaviBar.vue"

export default{
    name: "HomeView",
    components: {
        HeaderNaviBar,
    }
}

</script>

<style scoped>
    .mainTextBox{
        width: 400px;
        height: 400px;
        background-color : #C8EBCF;
        background-color:rgba(C8, EB, CF, 0.4);
        margin-top: 150px;
        margin-left: 200px;

        border-radius: 30%;
        align-content: center;
    }
    .mainImage{
        width: 700px;
        height: 700px;
    }
    .main {
        width: 100%;
        height: 700px;
    }
    div.left {
        width: 50%;
        float: left;
        box-sizing: border-box;
    }
    div.right {
        width: 50%;
        float: right;
        box-sizing: border-box;
    }
</style>