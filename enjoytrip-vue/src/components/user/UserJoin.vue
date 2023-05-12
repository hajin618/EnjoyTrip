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
        <input class="box" id="name" autocomplete="off" type="text" required>
      </div>

      <div class="id">
        <span>아이디</span>
        <input class="box" id="id" autocomplete="off" type="text" required>
      </div>

      <div class="pwd">
        <span>비밀번호</span>
        <input class="box" id="pwd" autocomplete="off" type="text" required>
      </div>

      <div class="pwdCheck">
        <span>비밀번호 확인</span>
        <input class="box" id="pwdCheck" autocomplete="off" type="text" required>
      </div>

      <div class="email">
        <span>이메일</span>
        <input class="box" id="email" autocomplete="off" type="text" required>
      </div> 

      <button class="joinBtn" type="submit">가입하기</button>
    </form>
  </div>
</template>

<script>
export default {

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
        color: white;
        width: 300px;
        background-color: #F1F4F1;
        border: 1px solid rgba(255, 255, 255, .2);
        border-radius: 20px / 20px;
    }
</style>