<template>
  <div>
    <header-navi-bar></header-navi-bar>
    <div class="main">
      <!-- 회원 정보 수정이 눌려있다면 초기값은 좋아요임 -->
    <div class="tabBar">
      <button class="tabBarBtn">회원 정보 수정</button>
      <button class="tabBarBtn">좋아요 목록 보기</button>
    </div>
    
    <!-- <user-edit></user-edit> -->

    </div>
    
  </div>
</template>

<script>
// import UserEdit from './item/UserEdit.vue';
import HeaderNaviBar from "../layout/HeaderNaviBar.vue"

export default {
  name: "UserMyPage",
  components: {
    // UserEdit,
    HeaderNaviBar,
  }
}
</script>

<style>
  .tabBar{
    margin-top: 50px;
    align-content: center;
    display: flex;
    justify-content: center;
  }
  .tabBarBtn{
    height: 40px;
    color: black;
    width: 300px;
    background-color: #C8EBCF;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 20px / 20px;
  }
</style>