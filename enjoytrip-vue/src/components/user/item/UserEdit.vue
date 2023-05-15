<template>
  <div class="main">
    <form action="">

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

      <button class="editBtn" type="submit">회원 정보 수정</button>
    </form>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
  .main{
      background-color: white;
      height: 720px;
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
      margin-left: 870px;
      height: 40px;
      color: white;
      width: 300px;
      background-color: #7aab85;
      border: 1px solid rgba(213, 120, 120, .2);
      border-radius: 20px / 20px;
    }
</style>