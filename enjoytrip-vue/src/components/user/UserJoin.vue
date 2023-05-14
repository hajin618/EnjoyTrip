<template>
  <div class="main">
    <div class="kakao">
      <span><img src="../../assets/img/kakaoLogo.png"  width="50px" height="50px" alt=""></span>
      <button class="kakaoLoginBtn">카카오톡으로 간편 로그인하기</button>
    </div>

    <form action="">

      <div class="title">
        회원가입
      </div>

      <div class="name">
        <span>이름</span>
        <input class="box" id="name" v-model="user.user_name" autocomplete="off" type="text" required>
      </div>

      <div class="id">
        <span>아이디</span>
        <input class="box" id="id" v-model="user.user_id" autocomplete="off" @focusout="idCheck" type="text" required>
      </div>

      <span v-show="idDuplicated" class="idDuplicate">
        중복된 아이디입니다.
      </span>

      <div class="pwd">
        <span>비밀번호</span>
        <input class="box" id="pwd" v-model="user.user_pwd" autocomplete="off" type="text" @focusout="pwdCheck" required>
      </div>

      <div class="pwdCheck">
        <span>비밀번호 확인</span>
        <input class="box" id="pwdCheck" v-model="user.userPwdCheck" autocomplete="off" @focusout="pwdCheck" type="text" required>
      </div>

      <span v-show="pwdNotCorrect" class="idDuplicate">
        비밀번호를 다시 확인해주세요!
      </span>

      <div class="email">
        <span>이메일</span>
        <input class="box" id="email" v-model="user.user_email" autocomplete="off" type="text" required>
      </div> 

      <button v-on:click.prevent="register" v-on:keyup.enter="register" class="joinBtn" type="submit">가입하기</button>
    </form>
  </div>
</template>

<script>
import http from "@/api/http";

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
        idDuplicated: false,
        pwdNotCorrect: false,
      }
    },
    methods: {
      idCheck() {
        http.get(`/idCheck/${this.user.user_id}`).then(({data}) => {
            console.log(data);
            // 사용가능 ID
            if(data == 'SUCCESS'){
              // alert("사용 가능 ID입니다!");
              this.idDuplicated = false;
            }
            // 사용불가 ID
            else{
              // alert("사용 불가 ID입니다!");
              this.idDuplicated = true;
            }
        });
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
      register(){
        if(this.idDuplicated){
          alert("중복된 아이디로 로그인 불가!");
        }
        else if(this.user.user_name == ''){
          alert("이름을 입력해주세요!");
        }
        // 이메일 로직 추가해야할듯
        else if(this.user.user_email == ''){
          alert("이메일을 입력해주세요!");
        }
        else if(this.pwdNotCorrect){
          alert("비밀번호, 비밀번호 확인란을 다시 확인해주세요!");
        }
        else if(this.user.user_pwd.length < 4){
          alert("비밀번호는 4자리 이상!!!");
        }
        else{
          http.post(`/user`, this.user).then(({data}) => {
            console.log(data);
            if(data == "SUCCESS"){
              alert("회원 가입 완료!");
              this.$router.push({ name: "HomeView" });
            }
            else{
              alert("회원 가입 실패!");
            }
          });
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
        background-color: #7aab85;
        border: 1px solid rgba(213, 120, 120, .2);
        border-radius: 20px / 20px;
    }

    .joinBtn{
        margin-top: 60px;
        margin-left: 870px;
        height: 40px;
        color: white;
        width: 300px;
        background-color: #7aab85;
        border: 1px solid rgba(213, 120, 120, .2);
        border-radius: 20px / 20px;
    }

    .kakao{
      margin-top: 80px;
      margin-left: 800px;
    }

    .main{
        background-color: white;
        height: 775px;
        width: 100%;
    }

    .name{
      margin-top: 40px;
      margin-left: 770px;
      font-size: 20px;
    }

    .id{
      margin-top: 40px;
      margin-left: 750px;
      font-size: 20px;
    }

    .pwd{
      margin-top: 40px;
      margin-left: 730px;
      font-size: 20px;
    }

    .pwdCheck{
      margin-top: 40px;
      margin-left: 680px;
      font-size: 20px;
    }

    .email{
      margin-top: 40px;
      margin-left: 750px;
      font-size: 20px;
    }

    .title{
      margin-top: 40px;
      margin-left: 930px;
      color: black;
      font-size: 20px;
      font-weight: 550;
    }

    .box{
        margin-left: 60px;
        height: 40px;
        color: black;
        width: 300px;
        background-color: #F1F4F1;
        border: 1px solid rgba(255, 255, 255, .2);
        border-radius: 20px / 20px;
    }

    .idDuplicate{
      margin-left: 940px;
      margin-top: 30px;
      color: red;
    }
</style>