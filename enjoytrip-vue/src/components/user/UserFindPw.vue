<template>
  <div class="main">
    <div class="left">
      <div class="findPwBox">
        <div class="findPwBoxHeader" style="padding-top: 50px; padding-left: 250px;">
          <router-link to="/" style="padding-left: 20px; font-weight: 550;"><img src="../../assets/img/logo.png" width="50px" height="50px" alt=""></router-link>
        </div>

        <!-- 이메일로 인증번호 받기 -->
        <form v-show="!getEmail" style="padding-top: 45px; padding-left: 100px;">
          <div style="padding-left: 60px;   font-weight: 550;">
            회원 정보에 등록된 이메일을 입력해주세요.
          </div>
          <div class="findPwform">
            <div>
              <input style="margin-top: 20px;" v-model="email" class="emailBox" id="pwd" autocomplete="off" type="text" placeholder="이메일"
                required>
            </div>
          </div>
          <button style="margin-top: 20px; text-align: center; color: white;" v-on:click.prevent="findEmail" class="findBtn" type="submit">이메일로 인증번호 받기</button>
        </form>

        <!--  인증번호 입력 -->
        <form v-show="putConfirmNumber" style="padding-top: 40px; padding-left: 100px;">
          <div style="font-weight: 550;">
            {{email}}로 받은 인증번호를 입력해주세요!
          </div>
          <div class="findPwform">
            <div>
              <input style="margin-top: 30px;" v-model="userConfirmNumber" class="emailBox" id="pwd" autocomplete="off" type="text" placeholder="인증번호 입력"
                required>
            </div>
          </div>
          <button style="margin-top: 20px; text-align: center; color: white;" v-on:click.prevent="checkConfirmNumber" class="findBtn" type="submit">인증번호 확인</button>
        </form>

                <!-- 버튼에 onclick 비밀번호 로직 수행후 메인으로 라우팅 -->
        <form v-show="nowChangePwd" style="padding-top: 45px; padding-left: 100px;">
          <div style="font-weight: 550;">
            변경할 비밀번호를 입력해주세요!
          </div>
          <div class="findPwform">
            <div>
              <input style="margin-top: 20px;" v-model="userPwdChange" class="emailBox" id="pwd" autocomplete="off" type="text" placeholder="변경할 비밀번호 입력"
                required>
            </div>
          </div>
          <button style="margin-top: 20px; text-align: center; color: white;" v-on:click.prevent="changePwd" class="findBtn" type="submit">비밀번호 변경</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import Swal from "sweetalert2";
import emailjs from "emailjs-com";

export default {
    name: "UserFindPwView",
    // components: {
        
    // }
    data() {
      return {
        email: '',
        confirmNumber: '',
        userConfirmNumber: '',
        userPwdChange: '',
        getEmail: false,
        putConfirmNumber: false,
        nowChangePwd: false,
      }
    },
    created(){
      this.getEmail = false;
    },
    methods: {
      findEmail(){
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
            // 이메일 찾기 실패
            if(response.status == 204){
              Swal.fire({
                icon: 'error',
                title: '이메일 찾기 실패!',
                text: '등록되지 않은 이메일입니다!',
              })
            }
            // 이메일 찾기 성공
            else{
              this.getEmail = true,
              this.putConfirmNumber = true;
              // 랜덤한 인증번호 생성, 이메일로 인증번호 보내기
              // 6자리 랜덤한 인증번호 

              //  실제 email 보내는 부분
              this.confirmNumber = Math.floor(Math.random() * 900001) + 100000;

              let templateParams = {
                user_email: this.email,
                sys_code: this.confirmNumber,
              };
              emailjs.init("c0nz-ynLc-qYrorYn");
              emailjs.send("service_enjoyYourLife", "template_enjoyYourLife", templateParams);

              // test 코드
              // this.confirmNumber = Math.floor(Math.random() * 900001) + 100000;
              // console.log(this.confirmNumber);
              Swal.fire({
                position: 'center-center',
                icon: 'success',
                title: '이메일 전송 성공!',
                showConfirmButton: true,
                timer: 1500
              })
            }
          });
        }
      },
      checkConfirmNumber(){
        // 인증번호 기억 
        // 사용자가 입력한 인증번호와 확인
        if(this.userConfirmNumber == this.confirmNumber){
          // 같다면
          Swal.fire(
            '인증 성공!',
            '비밀번호 변경 페이지로 이동합니다!',
            'success'
          )
          this.putConfirmNumber = false;
          this.nowChangePwd = true;
        }
        // 다르다면
        else{
          Swal.fire({
            icon: 'error',
            title: '인증 실패!',
            text: '인증번호를 잘못 입력하셨습니다!',
          })
          this.putConfirmNumber = false;
          this.getEmail = false;
          this.email = '';
        }
        this.userConfirmNumber = '';
      },
      changePwd(){
        if(this.userPwdChange.length < 4){
          Swal.fire({
            icon: 'error',
            title: '회원 정보 변경 실패!',
            text: '비밀번호는 4자리 이상입니다!',
          })
        }
        else{
            http.post(`/changePwd`,  {
              user_email: this.email,
              user_pwd: this.userPwdChange
            }  ).then((response) => { 
            console.log(response);
            if(response.status == 200){
              Swal.fire(
                '회원 정보 변경 성공!',
                '새로운 비밀번호로 로그인하세요!',
                'success'
              )
              this.$router.push({ name: "HomeView" });
            }
            else{
              Swal.fire(
                '회원 정보 변경 실패',
                '서버의 문제가 있습니다. 잠시 후 다시 시도해주세요!',
                'question'
              )
              this.getEmail = false;
              this.nowChangePwd = false;        
            }
          });
        }
      }
    }
}

</script>

<style scoped>
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

.findPwBox {
  width: 600px;
  height: 400px;
  background-color : rgba(255, 255, 255 , 0.5);
  margin-top: 250px;
  margin-left: 250px;

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