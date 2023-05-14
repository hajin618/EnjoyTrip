<template>
  <div>
    <header-navi-bar></header-navi-bar>
    <div class="main">
      <!-- 회원 정보 수정이 눌려있다면 초기값은 좋아요임 -->
    <div class="tabBar">
      <button class="tabBarBtn">회원 정보 수정</button>
      <button class="tabBarBtn">좋아요 목록 보기</button>
    </div>

      <!-- 회원 정보 수정이 눌려있다면 -->
    <div class="titleEdit">
      회원 정보 수정
    </div> -->
    <user-edit></user-edit>
    
    
      <!-- 좋아요가 눌려있다면 -->
    <!-- <div class="title">
      좋아요한 목록
    </div>
    
    <user-like-list></user-like-list> -->
    

    </div>
    
  </div>
</template>

<script>
import UserEdit from './item/UserEdit.vue';
// import UserLikeList from './item/UserLikeList.vue';
import HeaderNaviBar from "../layout/HeaderNaviBar.vue";

export default {
  name: "UserMyPage",
  components: {
    UserEdit,
    // UserLikeList,
    HeaderNaviBar,    
  }
}
</script>

<style scoped>
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
  .titleEdit{
    margin-top: 40px;
    margin-left: 900px;
    color: black;
    font-size: 20px;
    font-weight: 550;
  }  
    .title{
    margin-top: 40px;
    margin-left: 900px;
    color: black;
    font-size: 20px;
    font-weight: 550;
  }
</style>