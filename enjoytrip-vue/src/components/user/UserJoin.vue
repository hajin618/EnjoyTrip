<template>
  <div class="main">
    <div class="kakao">
      <span><img src="../../assets/img/kakaoLogo.png"  width="50px" height="50px" alt=""></span>
      <button v-on:click.prevent="kakaoLogin" class="kakaoLoginBtn">카카오톡으로 간편 로그인하기</button>
    </div>

    <div class="joinBox" style="display: flex;
    flex-direction: column;">
      <div class="title">
        <router-link to="/" style="margin-left:50px; font-weight: 550; margin-top: 2vmin;"><img src="../../assets/img/logo.png" width="80px" height="60px" alt=""></router-link>
        
      </div>
      <div class="name" style="margin: 2vmin;
    display: flex;
    flex-direction: row;
    justify-content: space-evenly; text-align: center; align-items: center">
        <span style="width: 20%;">이름</span>
        <input class="box" id="name" v-model="user.user_name" autocomplete="off" type="text" required>
      </div>

      <div class="id" style="margin: 2vmin;
    display: flex;
    flex-direction: row;
    justify-content: space-evenly; text-align: center; align-items: center">
        <span style="width: 20%;">아이디</span>
        <input class="box" id="id" v-model="user.user_id" autocomplete="off" @focusout="idCheck" type="text" required>
      </div>

      <span v-show="idDuplicated" class="idDuplicate">
        중복된 아이디입니다.
      </span>

      <div class="pwd" style="margin: 2vmin;
    display: flex;
    flex-direction: row;
    justify-content: space-evenly; text-align: center; align-items: center">
        <span style="width: 20%;">비밀번호</span>
        <input class="box" id="pwd" v-model="user.user_pwd" autocomplete="off" type="password" @focusout="pwdCheck" required>
      </div>

      <div class="pwdCheck" style="margin: 2vmin;
    display: flex;
    flex-direction: row;
    justify-content: space-evenly; text-align: center; align-items: center">
        <span style="width: 20%; font-size:17px;">비밀번호 확인</span>
        <input class="box" id="pwdCheck" v-model="user.userPwdCheck" autocomplete="off" type="password" @focusout="pwdCheck" required>
      </div>

      <span v-show="pwdNotCorrect" class="idDuplicate">
        비밀번호를 다시 확인해주세요!
      </span>

      <div class="email" style="margin: 2vmin;
    display: flex;
    flex-direction: row;
    justify-content: space-evenly; text-align: center; align-items: center">
        <span style="width: 20%;">이메일</span>
        <input class="box" id="email" v-model="user.user_email" autocomplete="off" @focusout="emailCheck" type="text" required>
      </div> 

      <span v-show="emailDuplicated" class="idDuplicate">
        중복된 이메일입니다.
      </span>

      <button style="margin-left: 10vmin;
    margin-top: 4vmin;
    display: flex;
    flex-direction: row;
    justify-content: space-evenly; text-align: center; align-items: center" v-on:click.prevent="register" v-on:keyup.enter="register" class="joinBtn" type="submit">가입하기</button>
    </div>

      
    
  </div>

</template>
<script>
import http from "@/api/http";
import { mapState, mapActions } from "vuex";
import Swal from "sweetalert2";

const userStore = "userStore";

export default {
    name: "UserJoinView",
    data(){
      return {
        user: {
          user_name: "",
          user_id: "",
          user_pwd: "",
          userPwdCheck: "",
          user_email: "",
        },
        kakaoUser: {
          user_id: "",
          user_name: "",
        },
        idDuplicated: false,
        pwdNotCorrect: false,
        emailDuplicated: false,
      }
    },
    computed: {
        ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
    },
    methods: {
      ...mapActions(userStore, ["userConfirm", "getUserInfo", "kakaoUserConfirm"]),
        async confirm() {
            await this.userConfirm(this.user);
            let token = sessionStorage.getItem("access-token");
            if (this.isLogin) {
                await this.getUserInfo(token);
                this.$router.push({ name: "HomeView" });
            }
        },
        async kakaoConfirm() {
          await this.kakaoUserConfirm(this.kakaoUser);
          let token = sessionStorage.getItem("access-token");
          if (this.isLogin) {
            await this.getUserInfo(token);
            // this.$router.push({ name: "HomeView" });
          }
        },
      kakaoLogin(){
        console.log(window.Kakao);
        window.Kakao.Auth.login({
          scope: 'profile_nickname, account_email',
          success: this.getKakaoAccount,
        })
      },
      getKakaoAccount(){
        window.Kakao.API.request({
          url:'/v2/user/me',
          success: async (res) =>{
            const kakao_account = res.kakao_account;
            const nickname = res.properties.nickname;
            const email = kakao_account.email;
            console.log('nickname', nickname);
            console.log('email', email);

            this.kakaoUser.user_id = email;
            this.kakaoUser.user_name = nickname;
            await this.kakaoConfirm(this.kakaoUser);
            let token = sessionStorage.getItem("access-token");
            if (this.isLogin) {
                await this.getUserInfo(token);
                Swal.fire(
                    '카카오 로그인 성공!',
                    '성공적으로 로그인 되었습니다!',
                    'success'
                )
                this.$router.push({ name: "HomeView" });
            }
          },
          fail: error => {
            console.log(error);
          }
        })
      },
      idCheck() {
        if(this.user.user_id == ''){
          Swal.fire(
            '아이디를 다시 확인해주세요!',
            '아이디 칸은 공백일 수 없습니다!',
            'question'
          )
        }
        else{
          http.get(`/idCheck/${this.user.user_id}`).then((response) => {
            console.log(response);
            // 사용가능 ID
            if(response.status == 200){
                Swal.fire(
                    '아이디 중복확인 성공!',
                    '다음 스탭을 진행해주세요!',
                    'success'
                )
              this.idDuplicated = false;
            }
            // 사용불가 ID
            else{
              Swal.fire({
                icon: 'error',
                title: '사용 불가 ID!',
                text: '중복된 아이디입니다!',
              })
              this.idDuplicated = true;
            }
        });
        }
      },
      pwdCheck(){
        if(this.user.userPwdCheck == ''){
          this.pwdNotCorrect = false;
        }
        else if(this.user.user_pwd != this.user.userPwdCheck){
          this.pwdNotCorrect = true;
        }
        else{
          this.pwdNotCorrect = false;
        }
      },
      emailCheck(){
        if(this.user.user_email == ''){
          Swal.fire(
            '이메일을 다시 확인해주세요!',
            '이메일 칸은 공백일 수 없습니다!',
            'question'
          )
        }
        else{
          http.get(`/emailCheck/${this.user.user_email}`).then((response) => {
            console.log(response);
            // 사용가능 email
            if(response.status == 200){
              // Swal.fire(
              //     '이메일 중복확인 성공!',
              //     '다음 스탭을 진행해주세요!',
              //     'success'
              // )
              this.emailDuplicated = false;
            }
            // 사용불가 email
            else{
              Swal.fire({
                icon: 'error',
                title: '사용 불가 이메일!',
                text: '중복된 이메일입니다!',
              })
              this.emailDuplicated = true;
            }
        });
        }
      },
      register(){
        if(this.idDuplicated){
          Swal.fire({
            icon: 'error',
            title: '회원 등록 실패!',
            text: '중복된 아이디입니다!',
          })
        }
        else if(this.emailDuplicated){
          Swal.fire({
            icon: 'error',
            title: '회원 등록 실패!',
            text: '중복된 이메일입니다!',
          })
        }
        else if(this.user.user_name == ''){
          Swal.fire({
            icon: 'error',
            title: '회원 등록 실패!',
            text: '이름을 확인해주세요!',
          })
        }
        else if(this.user.user_id == ''){
          Swal.fire({
            icon: 'error',
            title: '회원 등록 실패!',
            text: '아이디를 확인해주세요!',
          })
        }
        else if(this.user.user_email == ''){
          Swal.fire({
            icon: 'error',
            title: '회원 등록 실패!',
            text: '이메일을 확인해주세요!',
          })
        }
        else if(this.pwdNotCorrect){
          Swal.fire({
            icon: 'error',
            title: '회원 등록 실패!',
            text: '비밀번호를 다시 확인해주세요!',
          })
        }
        else if(this.user.user_pwd.length < 4){
          Swal.fire({
            icon: 'error',
            title: '회원 등록 실패!',
            text: '비밀번호는 4자리 이상입니다!',
          })
        }
        else{
          Swal.fire({
            title: '회원가입 하시겠습니까?',
            text: "동의하시면 이메일 대한 개인정보가 사용됩니다.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'JOIN!'
          }).then((result) => {
            if (result.isConfirmed) {
              http.post(`/user`, this.user).then(({data}) => { 
                console.log(data);
                if(data == "SUCCESS"){
                  Swal.fire(
                    '가입 성공!',
                    '회원 정보가 성공적으로 등록되었습니다!',
                    'success'
                  )
                  this.$router.push({ name: "HomeView" });
                }
                else{
                  Swal.fire({
                    icon: 'error',
                    title: '회원 등록 실패!',
                    text: '서버 오류입니다!!',
                  })
                }
              });
            }
          })
        }
      }
    }
}

</script>

<style scoped>
    .kakaoLoginBtn{
        margin-left: 20px;
        height: 50px;
        color: white;
        width: 300px;
        background-color: #1F92FD54;
        border: 1px solid rgba(94, 157, 216, 0.1);
        border-radius: 20px / 20px;
    }

    .kakaoLoginBtn:hover{
      background-color: #97b1cd;
    }

    .joinBtn{
        height: 40px;
        font-size: 20px;
        color: white;
        width: 300px;
        background-color: #1F92FD54;
        border: 1px solid rgba(170, 207, 241, 0.1);
        border-radius: 20px / 20px;
    }

    .joinBtn:hover{
      background-color: #97b1cd;
    }

    .kakao{
      padding-top: 150px;
      margin-bottom: 30px;
      margin-left: 470px;
    }

    .main{
        width: 100%;
        height: 969px;
        background-image: linear-gradient(
        rgba(0, 0, 0, 0.1),
        rgba(0, 0, 0, 0.1)
      ), url("../../assets/img/background.jpg");
        background-size: cover;
        background-repeat: no-repeat;
    }

    .name{
      font-size: 20px;
    }

    .id{
      font-size: 20px;
    }

    .pwd{
      font-size: 20px;
    }

    .pwdCheck{
      font-size: 20px;
    }

    .email{
      font-size: 20px;
    }

    .title{
      color: black;
      margin-left: 170px;
      font-size: 20px;
      font-weight: 550;
    }

    .box{
        height: 40px;
        color: black;
        width: 300px;
        background-color: #F1F4F1;
        border: 1px solid rgba(255, 255, 255, .2);
        border-radius: 20px / 20px;
    }

    .idDuplicate{
      margin-left: 160px;
      color: red;
    }

    .joinBox{
      margin-left:400px;
      width: 500px;
      height: 550px;
        background-color : rgba(255, 255, 255 , 0.5);
      border-radius: 10px / 10px;
      border: 1px solid rgba(213, 120, 120, .2);
    }
</style>