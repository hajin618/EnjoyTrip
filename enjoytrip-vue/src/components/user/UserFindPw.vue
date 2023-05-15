<template>
  <div class="main">
    <div class="left">
      <div class="findPwBox">
        <div class="findPwBoxHeader" style="padding-top: 110px; padding-left: 300px;">
          <span><img src="../../assets/img/family.png" width="50px" height="50px" alt=""></span>
          <router-link to="/" style="padding-left: 20px; font-weight: 550;">여행객</router-link>
        </div>

        <!-- 이메일로 인증번호 받기 -->
        <form v-show="!getEmail" style="padding-top: 40px; padding-left: 150px;">
          <div style="padding-left: 40px;   font-weight: 550;">
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
        <form v-show="putConfirmNumber" style="padding-top: 40px; padding-left: 150px;">
          <div style="font-weight: 550;">
            {{email}}로 받은 인증번호를 입력해주세요!
          </div>
          <div class="findPwform">
            <div>
              <input style="margin-top: 20px;" v-model="userConfirmNumber" class="emailBox" id="pwd" autocomplete="off" type="text" placeholder="인증번호 입력"
                required>
            </div>
          </div>
          <button style="margin-top: 20px; text-align: center; color: white;" v-on:click.prevent="checkConfirmNumber" class="findBtn" type="submit">인증번호 확인</button>
        </form>

                <!-- 버튼에 onclick 비밀번호 로직 수행후 메인으로 라우팅 -->
        <form v-show="nowChangePwd" style="padding-top: 40px; padding-left: 150px;">
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
    <div class="right">
      <img src="../../assets/img/userPageImg.png" class="mainImage" alt="">
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
// import emailjs from "emailjs-com";

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
          alert("이메일을 입력해주세요!");
        }
        else{
          http.get(`/findId/${this.email}`).then((response) => {
            console.log(response); 
            // 이메일 찾기 실패
            if(response.status == 204){
              alert("이메일을 찾을 수 없습니다.");
            }
            // 이메일 찾기 성공
            else{
              this.getEmail = true,
              this.putConfirmNumber = true;
              // 랜덤한 인증번호 생성, 이메일로 인증번호 보내기
              // 6자리 랜덤한 인증번호 

              //  실제 email 보내는 부분
              // this.confirmNumber = Math.floor(Math.random() * 900001) + 100000;

              // let templateParams = {
              //   user_email: this.email,
              //   sys_code: this.confirmNumber,
              // };
              // emailjs.init("c0nz-ynLc-qYrorYn");
              // emailjs.send("service_enjoyYourLife", "template_enjoyYourLife", templateParams);

              // test 코드
              this.confirmNumber = Math.floor(Math.random() * 900001) + 100000;
              console.log(this.confirmNumber);
            }
          });
        }
      },
      checkConfirmNumber(){
        // 인증번호 기억 
        // 사용자가 입력한 인증번호와 확인
        if(this.userConfirmNumber == this.confirmNumber){
          // 같다면
          this.putConfirmNumber = false;
          this.nowChangePwd = true;
        }
        // 다르다면
        else{
          alert("인증 실패! 다시 인증번호를 받아주세요!");
          this.putConfirmNumber = false;
          this.getEmail = false;
          this.email = '';
        }
        this.userConfirmNumber = '';
      },
      changePwd(){
        if(this.userPwdChange.length < 4){
          alert("비밀번호는 4자리 이상이여야합니다!");
        }
        else{
            http.post(`/chagePwd`,  {
              user_email: this.email,
              user_pwd: this.userPwdChange
            }  ).then((response) => { 
            console.log(response);
            if(response.status == 200){
              alert("비밀번호 변경 완료! 바뀐 비밀번호로 로그인해주세요!");
              // Swal.fire({
              //   'Alert 실행!!.',  // Alert 제목
              //   'Alert 내용이 나타나는 곳.',  // 내용
              //   'success',  // icon
              // });
              this.$router.push({ name: "HomeView" });
            }
            else{
              alert("비밀번호 변경 실패!");
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
  background-color: #7aab75;
  border-radius: 10px / 10px;
  border: 1px solid rgba(213, 120, 120, .2);
}

.findPwBox {
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
  background-color: #C8EBCF;
  height: 850px;
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
}</style>