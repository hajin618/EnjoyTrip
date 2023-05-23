<template>
  <div>
    <header-navi-bar></header-navi-bar>
    <div class="main">
      <div class="tabBar">
        <button class="tabBarBtn" @click="selectedTab = 'plans'" ref="plans" style="border-top-left-radius:1rem; border-bottom-left-radius:1rem;">나의 계획들 보기</button>
        <button class="tabBarBtn" @click="selectedTab = 'edit'" ref="edit" style="background-color:#7aab85;">회원 정보 수정</button>
        <button class="tabBarBtn" @click="selectedTab = 'reviews'" ref="reviews" style="border-top-right-radius:1rem; border-bottom-right-radius:1rem;">나의 후기들 보기</button>
      </div>

      <div class="title" v-if="selectedTab === 'plans'">
        나의 계획들
      </div>
      <user-plan-list v-if="selectedTab === 'plans'"></user-plan-list>

      <div class="title" v-if="selectedTab === 'edit'">
        회원 정보 수정
      </div>
      <user-edit v-if="selectedTab === 'edit'"></user-edit>

      <div class="title" v-if="selectedTab === 'reviews'">
        나의 후기들
      </div>
      <user-review-list v-if="selectedTab === 'reviews'"></user-review-list>
    </div>
  </div>
</template>

<script>
import UserEdit from './item/UserEdit.vue';
 import UserPlanList from './item/UserPlanList.vue';
import UserReviewList from './item/UserReviewList.vue';
import HeaderNaviBar from "../layout/HeaderNaviBar.vue";

export default {
  name: "UserMyPage",
  components: {
    UserEdit,
    UserPlanList,
    UserReviewList,
    HeaderNaviBar,
  },
  data() {
    return {
      selectedTab: 'edit' // 초기 선택 탭 설정
    };
  },
  watch: {
      selectedTab(newTab) {
        if (newTab === 'edit') {
          this.$nextTick(() => {
            this.$refs.edit.style.backgroundColor = "#7aab85"; 
            this.$refs.plans.style.backgroundColor = "#C8EBCF";
            this.$refs.reviews.style.backgroundColor = "#C8EBCF";
          });
        }
        else if(newTab === 'plans'){
          this.$nextTick(() => {
            this.$refs.edit.style.backgroundColor = "#C8EBCF";
            this.$refs.plans.style.backgroundColor = "#7aab85";
            this.$refs.reviews.style.backgroundColor = "#C8EBCF";
          });
        }
        else if(newTab === 'reviews'){
          this.$nextTick(() => {
            this.$refs.edit.style.backgroundColor = "#C8EBCF";
            this.$refs.plans.style.backgroundColor = "#C8EBCF";
            this.$refs.reviews.style.backgroundColor = "#7aab85";
          });
        }
      }
    },
};
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
  }
  .titleEdit{
    margin-top: 40px;
    margin-left: 900px;
    color: black;
    font-size: 20px;
    font-weight: 550;
  }  
    .title{
    margin-top: 50px;
    margin-left: 900px;
    margin-bottom: 50px;
    color: black;
    font-size: 20px;
    font-weight: 550;
  }
</style>