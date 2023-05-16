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
        <div class="footer">
            <footer-area></footer-area>
        </div> 
    </div>
</template>

<script>
import HeaderNaviBar from "../components/layout/HeaderNaviBar.vue"
import FooterArea from "../components/layout/FooterArea.vue";

export default{
    name: "HomeView",
    components: {
        HeaderNaviBar,
        FooterArea,
    }
}

</script>

<style scoped>
    .mainTextBox{
        width: 500px;
        height: 450px;
        background-color : #C8EBCF;
        background-color:rgba(C8, EB, CF, 0.4);
        margin-top: 150px;
        margin-left: 200px;

        border-radius: 50px / 50px;
        align-content: center;
    }
    .mainImage{
        width: 700px;
        height: 700px;
    }
    .main {
        width: 100%;
        height: 785px;
    }
    div.left {
        width: 50%;
        float: left;
        padding-top: 30px;
        padding-left: 200px;
        box-sizing: border-box;
    }
    div.right {
        width: 50%;
        float: right;
        box-sizing: border-box;
    }

    .footer{
        clear: both;
    }
</style>