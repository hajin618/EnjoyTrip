<template>
  <div class="main">
    <div class="left">
      <div class="box">
          <div class="findIdHeader" style="padding-top: 50px; padding-left: 250px;">
          <router-link to="/" style="padding-left: 20px; font-weight: 550;"><img src="../../assets/img/logo.png" width="50px" height="50px" alt=""></router-link>
        </div>

        <!-- 이메일 찾을 경우 -->
        <form v-if="!getId" style="padding-top: 45px; padding-left: 100px;">
          <div style="padding-left: 60px;   font-weight: 550;">
            회원 정보에 등록된 이메일을 입력해주세요.
          </div>
          <div class="findIdform">
            <div>
              <input style="margin-top: 20px;" v-model="email" class="emailBox" id="pwd" autocomplete="off" type="text" placeholder="이메일" required>
            </div>
          </div>
          <button style="margin-top: 20px; text-align: center; color: white;" v-on:click.prevent="findId" v-on:keyup.enter="findId" class="findBtn" type="submit">이메일로 아이디 찾기</button>
        </form>

        <!-- 이메일 찾은 경우 -->
        <!-- 버튼에 onclick 로그인페이지로 라우팅 -->
        <div v-else style="padding-top: 80px; padding-left: 100px;">
          <div style="font-weight: 550;">
              회원님의 아이디는 {{ id }}입니다.
          </div>
          <button style="margin-top: 50px; text-align: center; color: white;" v-on:click.prevent="moveLogin" class="findBtn" type="submit">로그인하러 가기</button>
        </div>
      </div>
    </div>
    
  </div>
</template>

<script>
import http from "@/api/http";
import Swal from "sweetalert2";

export default {
    name: "UserFindIdView",
    // components: {
        
    // }
    data() {
      return {
        email: '',
        getId: false,
        id: '',
      }
    },
    created(){
      this.getId = false;
    },
    methods: { 
      findId(){
        if(this.email == ''){
          Swal.fire(
            '이메일을 다시 확인해주세요!',
            '이메일은 공백일 수 없습니다!',
            'question'
          )
        }
        else{
          http.get(`/findId/${this.email}`).then((response) => {
            console.log(response);
            // 아이디 찾기 실패
            if(response.status == 204){
              Swal.fire({
                icon: 'error',
                title: '이메일 찾기 실패!',
                text: '등록되지 않은 이메일입니다!',
              })
            }
            // 아이디 찾기 성공
            else{
              this.getId = true;
              this.id = response.data;
            }
          });
        }
      },
      moveLogin(){
        this.$router.push({ name: "loginView" });
      }
    }
}
</script>

<style scoped>

.left{
          width: 50%;
        float: left;
        box-sizing: border-box;
}
/* 라우터 링크 처리 */
a {
  color: black;
  text-decoration: none;
  font-weight: 550;
}

.emailBox {
  height: 50px;
  width: 400px;
  background-color: #F1F4F1;
  border: 1px solid rgba(213, 120, 120, .2);
  border-radius: 10px / 10px;
}

.box{
          width: 600px;
        height: 400px;
        background-color : rgba(255, 255, 255 , 0.5);
        margin-top: 250px;
        margin-left: 250px;

        border-radius: 10%;
        align-content: center;
}

.findBtn {
  height: 40px;
  width: 400px;
  background-color: #1F92FD54;
  border-radius: 10px / 10px;
  border: 1px solid rgba(213, 120, 120, .2);
}

.findBtn:hover{
  background-color: #97b1cd;
}

.findId {
  width: 700px;
  height: 500px;
  background-color: #F1F4F1;
  margin-top: 150px;
  margin-left: 150px;

  border-radius: 10%;
  align-content: center;
}

.mainImage {
  margin-left: 80px;
  margin-top: 70px;
  width: 750px;
  height: 750px;
}

.main {
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
}</style>