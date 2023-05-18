<template>
    <header>
        <div class="toorbar">
            <router-link class="blacklink" to="/">
                    <img src="../../assets/img/logo.png" width="60px" height="60px"></router-link>
            <router-link class="whitelink" style="margin-left: 700px;" to="/notice">공지사항</router-link>
            <router-link class="whitelink" style="margin-left: 30px;" to="/plan">여행 계획</router-link>
            <router-link class="whitelink" style="margin-left: 30px;" to="/review">여행 후기</router-link>

            <!-- 로그인 안된 경우 -->
            <span v-if="!userInfo">
                <router-link class="whitelink" style="margin-left: 400px;" to="/user/login">로그인</router-link>
                <router-link class="whitelink" style="margin-left: 30px;" to="/user/join">회원가입</router-link>
            </span>
            
            <!-- 로그인 된 경우 -->
            <span v-else>
                <span style="margin-left: 300px;">{{userInfo.user_name}}님 안녕하세요!</span>
                <router-link class="whitelink" style="margin-left: 30px;" to="/user/mypage">마이페이지</router-link>
                <router-link class="whitelink" style="margin-left: 30px;" to="/search">검색</router-link>
				<button class="btnlogout" style="margin-left: 30px;" v-on:click.prevent="logout">로그아웃</button>
            </span>
        </div>
        
    </header>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

const userStore = "userStore";

export default{
    name: "HeaderNaviBar",
    data(){
        return{
        }
    },
    computed: {
        ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
        ...mapGetters(["checkisLogin", "checkUserInfo"]),
    },
    methods:{
        ...mapActions(userStore, ["userLogout"]),
        logout(){
            console.log(this.userInfo.user_id);
            this.userLogout(this.userInfo.user_id);
            sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
            sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
            console.log(this.isLogin);
            if (this.$route.path != "/") this.$router.push({ name: "HomeView" });
            // else{
            //     this.$router.go();
            // }
        },
    }
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
    .btnlogout{
        background-color:transparent;
        color: white;
        text-decoration: none;
        border: none;
    }
</style>
