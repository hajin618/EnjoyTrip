<template>
    <div class="main">
        <div class="left">
            <div class="loginBox">

            </div>
        </div>
        <div class="right">
            <img src="../../assets/img/userPageImg.png" class="mainImage" alt="">
            
        </div>
    </div>
</template>

export default{
    name: "UserLoginView",
    // components: {
        
    // }
}

<style scoped>
    .loginBox{
        width: 500px;
        height: 500px;
        background-color : #F1F4F1;
        margin-top: 150px;
        margin-left: 150px;

        border-radius: 30%;
        align-content: center;
    }

    .mainImage{
        margin-left: 150px;
        margin-top: 150px;
        width: 500px;
        height: 500px;
    }

    .main{
        background-color: #C8EBCF;
        height: 800px;
        width: 100%;
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