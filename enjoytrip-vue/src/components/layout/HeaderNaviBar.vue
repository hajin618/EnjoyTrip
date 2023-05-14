<template>
    <header>
        <div class="toorbar">
            <router-link class="blacklink" to="/">
                    <img src="../../assets/img/family.png" width="60px" height="60px"></router-link>
            
            <span style="margin-left: 20px;">여행객</span>
            <router-link class="whitelink" style="margin-left: 800px;" to="/notice">공지사항</router-link>
            <router-link class="whitelink" style="margin-left: 30px;" to="/plan">여행 계획</router-link>
            <router-link class="whitelink" style="margin-left: 30px;" to="/review">여행 후기</router-link>

            <!-- 로그인 안된 경우 -->
            <router-link class="whitelink" style="margin-left: 400px;" to="/user/login">로그인</router-link>
            <router-link class="whitelink" style="margin-left: 30px;" to="/user/join">회원가입</router-link>

            <!-- 로그인 된 경우 -->
        </div>
        <router-link class="blacklink" style="margin-left: 30px;" to="/user/mypage">마이페이지</router-link>
    </header>
</template>

<script>

export default{
    name: "HeaderNaviBar",
};

</script>

<style scoped>
    .toorbar{
        padding-top: 7px;
    }

    /* 라우터 링크 처리 */
    .blacklink{
        margin-left: 50px;
        color: black;
        text-decoration: none;
    }
    .whitelink {
        color: white;
        text-decoration: none;
    }
    header{
        height: 70px;
        width: 100%;
        background-color: #7aab85;
        border-bottom: 1px #ececec solid;
        box-shadow: 0 4px 4px -4px black;
    }
</style>
