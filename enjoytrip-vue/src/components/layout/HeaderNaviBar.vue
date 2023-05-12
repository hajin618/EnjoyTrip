<template>
    <header>
        <router-link to="/home" class="logo">
                <img src="../../assets/img/family.png" width="50px" height="50px">여행객</router-link>
            <!-- 로그인 안된 경우 -->
        <nav>
            <router-link to="/notice">공지사항</router-link>
            <router-link to="/plan">여행 계획</router-link>
            <router-link to="/review">여행 후기</router-link>
        </nav>
        <nav>
            <router-link to="/user/login">로그인</router-link>
            <router-link to="/user/join">회원가입</router-link>
        </nav>
    </header>
</template>

<script>

export default{
    name: "HeaderNaviBar",
};

</script>

<style scoped>
    header{
        background-color: #7aab85;
        border-bottom: 1px #ececec solid;
        display: flex;
        justify-content: space-between;
        
    }
    nav, ul {
        display: inline-block;
    }
    .logo{
        margin-left: 50px;
    }
</style>
