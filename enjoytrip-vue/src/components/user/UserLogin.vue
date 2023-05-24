<template>
    <div class="main">
        <div class="left">
            <div class="loginBox">
                <div class="loginBoxHeader" style="padding-top: 40px; padding-left: 250px;">
                    <router-link to="/" style="padding-left: 20px; font-weight: 550;"><img src="../../assets/img/logo.png"  width="50px" height="50px" alt=""></router-link>
                </div>

                <form style="padding-top: 20px; padding-left: 150px;">
                    <div class="loginform">
                        <div>
                            <input class="idBox" id="Nick" v-model="user.user_id" autocomplete="off" type="text" placeholder="아이디" required>
                        </div>
                        <div>
                            <input style="margin-top: 20px;" v-model="user.user_pwd" class="pwBox" id="Pw" autocomplete="off" maxlength="20" type="password" placeholder="비밀번호" required>
                        </div>
                    </div>
                    <button style="margin-top: 20px;" class="loginBtn" v-on:click.prevent="confirm" type="submit">로그인</button>
                </form>

                <div class="linker">
                    <router-link to="/user/findId">아이디 찾기</router-link>
                    <router-link to="/user/findPw">비밀번호 찾기</router-link>
                    <router-link to="/user/join">회원가입</router-link>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import { mapState, mapActions } from "vuex";
import Swal from "sweetalert2";

const userStore = "userStore";

export default {
    name: "UserLoginView",
    // components: {
        
    // }
    data(){
        return {
            user:{
                user_id: null,
                user_pwd: null,
            }
        }
    },
    computed: {
        ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
    },
    methods: {
        ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
        async confirm() {
            await this.userConfirm(this.user);
            let token = sessionStorage.getItem("access-token");
            if (this.isLogin) {
                await this.getUserInfo(token);
                Swal.fire(
                    '로그인 성공!',
                    '성공적으로 로그인 되었습니다!',
                    'success'
                )
                this.$router.push({ name: "HomeView" });
            }
            else{
                Swal.fire({
                    icon: 'error',
                    title: '로그인 실패!',
                    text: '사용자 인증에 실패하였습니다!',
                })
            }
        },
    },
}

</script>

<style scoped>
    .linker{
        width: 40%;
        margin-top: 20px;
        margin-left: 170px;
        display: flex;
        justify-content: space-between;
    }

    /* 라우터 링크 처리 */
    a {
        color: black;
        text-decoration: none;
        font-weight: 550;
    }
    
    .idBox, .pwBox{
        height: 50px;
        width: 300px;
        background-color: #F1F4F1;
        border: 1px solid rgba(213, 120, 120, .2);
        border-radius: 10px / 10px;
    }

    .loginBtn{
        height: 40px;
        width: 300px;
        background-color: #1F92FD54;
        border-radius: 10px / 10px;
        border: 1px solid rgba(213, 120, 120, .2);
    }
    .loginBtn:hover{
        background-color: #97b1cd;
    }
    .loginBox{
        width: 600px;
        height: 400px;
        background-color : rgba(255, 255, 255 , 0.5);
        margin-top: 250px;
        margin-left: 250px;

        border-radius: 10%;
        align-content: center;
    }

    .mainImage{
        margin-left: 80px;
        margin-top: 70px;
        width: 750px;
        height: 750px;
    }

    .main{
        width: 100%;
        height: 970px;
        background-image: url("../../assets/img/background.jpg");
        background-size: cover;
        background-repeat: no-repeat;
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