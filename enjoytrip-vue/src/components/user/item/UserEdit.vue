<template>
  <div class="main">
    <form action="">

      <div class="name">
        <span>이름</span>
        <input class="box" v-model="this.user_name" id="name" autocomplete="off" type="text" readonly required>
      </div>

      <div class="id">
        <span>아이디</span>
        <input class="box" v-model="this.user_id" id="id" autocomplete="off" type="text" readonly required>
      </div>

      <div class="pwd">
        <span>비밀번호</span>
        <input class="box" v-model="pwd" id="pwd" autocomplete="off" type="password" @focusout="pwdChecked" required>
      </div>

      <div class="pwdCheck">
        <span>비밀번호 확인</span>
        <input class="box" v-model="pwdCheck" id="pwdCheck" autocomplete="off" type="password" @focusout="pwdChecked" required>
      </div>

      <span v-show="pwdNotCorrect" class="idDuplicate">
        비밀번호를 다시 확인해주세요!
      </span>

      <div class="email">
        <span>이메일</span>
        <input class="box" v-model="this.user_email" id="email" autocomplete="off" type="text" readonly required>
      </div> 

      <button v-on:click.prevent="deleteUser" class="deleteBtn" >회원탈퇴</button>
      <button v-on:click.prevent="checkPwd" class="editBtn" >회원 정보 수정</button>
    </form>
  </div>
</template>

<script>
import http from "@/api/http";
// import Swal from "sweetalert2";
import { mapState, mapGetters, mapActions } from "vuex";

const userStore = "userStore";


export default {
  name: "UserEditView",
  // components: {
      
  // }
  data(){
      return {
          pwd: '',
          pwdCheck: '',
          pwdNotCorrect: false,
      }
  },
  computed: {
      ...mapState({
        user_name: state => state.userStore.userInfo.user_name,
        user_id: state => state.userStore.userInfo.user_id,
        user_email: state => state.userStore.userInfo.user_email,
      }),
      ...mapGetters(["checkisLogin", "checkUserInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userLogout"]),
    deleteUser(){
      if (!confirm("정말 탈퇴하시겠습니까?")) {
            alert("회원 탈퇴되지 않았습니다.");
        } else {
            console.log(this.user_id);
            http.get(`/userdelete/${this.user_id}`)
            .then(({data}) => { 
              console.log(data);
              if(data == "SUCCESS"){
                alert("탈퇴 성공하였습니다.");
                this.userLogout(this.user_id);
                sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
                sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
                // Swal.fire({
                //   'Alert 실행!!.',  // Alert 제목
                //   'Alert 내용이 나타나는 곳.',  // 내용 
                //   'success',  // icon
                // });
                this.$router.push({ name: "HomeView" });
              }
              else{
                alert("회원 탈퇴 실패!");
              }
          });
        }
    },
    pwdChecked(){
      if(this.pwd == ''){
        this.pwdNotCorrect = false;
      }
      else if(this.pwd != this.pwdCheck){
        this.pwdNotCorrect = true;
      }
      else{
        this.pwdNotCorrect = false;
      }
    },
    checkPwd(){
        if(this.pwdNotCorrect){
          alert("비밀번호, 비밀번호 확인란을 다시 확인해주세요!");
        }
        else if(this.pwd.length < 4){
          alert("비밀번호는 4자리 이상!!!");
        }
        else{
          http.put(`/user`, {
            user_name: this.user_name,
            user_id: this.user_id,
            user_pwd: this.pwd,
            user_email: this.user_email,
          }).then(({data}) => { 
            console.log(data);
            if(data == "SUCCESS"){
              Swal.fire(
                '회원 정보 변경 성공!',
                '새로운 비밀번호로 로그인하세요!',
                'success'
              )
              this.$router.push({ name: "HomeView" });
            }
            else{
              alert("회원 정보 수정 실패!");
            }
          });
        }
    }
  }
}
</script>

<style scoped>
  .main{
      background-color: white;
      height: 550px;
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

    .box{
        margin-left: 60px;
        height: 40px;
        color: black;
        width: 300px;
        background-color: #F1F4F1;
        border: 1px solid rgba(255, 255, 255, .2);
        border-radius: 20px / 20px;
    }
    .editBtn{
      margin-top: 60px;
      margin-left: 30px;
      height: 40px;
      color: white;
      width: 300px;
      background-color: #7aab85;
      border: 1px solid rgba(213, 120, 120, .2);
      border-radius: 20px / 20px;
    }

    .deleteBtn{
      margin-top: 60px;
      margin-left: 690px;
      height: 40px;
      color: white;
      width: 150px;
      background-color: #7aab85;
      border: 1px solid rgba(213, 120, 120, .2);
      border-radius: 20px / 20px;
    }
    .idDuplicate{
      margin-left: 940px;
      margin-top: 30px;
      color: red;
    }
</style>