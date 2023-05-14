<template>
  <div class="main">
    <div class="left">
      <div class="findId">
        <div class="findIdHeader" style="padding-top: 110px; padding-left: 300px;">
          <span><img src="../../assets/img/family.png" width="50px" height="50px" alt=""></span>
          <router-link to="/" style="padding-left: 20px; font-weight: 550;">여행객</router-link>
        </div>

        <!-- 이메일 찾을 경우 -->
        <form style="padding-top: 40px; padding-left: 150px;">
          <div style="padding-left: 40px;   font-weight: 550;">
            회원 정보에 등록된 이메일을 입력해주세요.
          </div>
          <div class="findIdform">
            <div>
              <input style="margin-top: 20px;" class="emailBox" id="pwd" autocomplete="off" type="text" placeholder="이메일" required>
            </div>
          </div>
          <button style="margin-top: 20px; text-align: center; color: white;" class="findBtn" type="submit">이메일로 아이디 찾기</button>
        </form>

        <!-- 이메일 찾은 경우 -->
        <!-- 버튼에 onclick 로그인페이지로 라우팅 -->
        <!-- <div style="padding-top: 80px; padding-left: 150px;">
          <div style="font-weight: 550;">
              회원님의 아이디는 ~~입니다.
          </div>
          <button style="margin-top: 50px; text-align: center; color: white;" class="findBtn" type="submit">로그인하러 가기</button>
        </div> -->
      </div>
    </div>
    <div class="right">
      <img src="../../assets/img/userPageImg.png" class="mainImage" alt="">

    </div>
  </div>
</template>

<script>
export default {
    name: "UserFindIdView",
    // components: {
        
    // }
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