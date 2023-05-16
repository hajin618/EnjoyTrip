<template>
  <div>
    <header-navi-bar></header-navi-bar>
    <div class="main">
      <!-- 회원 정보 수정이 눌려있다면 초기값은 좋아요임 -->
      <div class="tabBar">
        <button class="tabBarBtn">나의 계획들 보기</button>
        <button class="tabBarBtn">회원 정보 수정</button>
        <button class="tabBarBtn">나의 후기들 보기</button>
      </div>


        <!-- 나의 계획들 눌려있다면 -->
    <!-- <div class="title">
        나의 계획들
    </div>
    <user-plan-list></user-plan-list> -->


      <!-- 회원 정보 수정이 눌려있다면 -->
    <!-- <div class="titleEdit">
      회원 정보 수정
    </div>
    <user-edit></user-edit> -->
    
    
      <!-- 나의 후기들 눌려있다면 -->
    <div class="title">
        나의 후기들
    </div>
    <user-review-list></user-review-list>

    </div>
    
  </div>
</template>

<script>
// import UserEdit from './item/UserEdit.vue';
// import UserPlanList from './item/UserPlanList.vue';
import UserReviewList from './item/UserReviewList.vue';
import HeaderNaviBar from "../layout/HeaderNaviBar.vue";

export default {
  name: "UserMyPage",
  components: {
    // UserEdit,
    // UserPlanList,
    UserReviewList,
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
    width: 240px;
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
    margin-bottom: 40px;
    color: black;
    font-size: 20px;
    font-weight: 550;
  }
</style>