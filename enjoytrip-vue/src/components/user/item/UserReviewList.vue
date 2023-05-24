<template>
  <div>
  <!-- align-content:space-evenly 이거 하면 행간 띄운다는데 안 먹음 -->
    <div v-if="reviews.length" style="display: flex; flex-direction: row; flex-wrap: wrap; align-content:space-around;">

      <review-list-item
        v-for="review in reviews"
        :key="review.review_idx"
        v-bind="review"
      />

    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import ReviewListItem from "@/components/reviewboard/item/ReviewListItem.vue";

import { mapState  } from "vuex";

export default {
  name: "UserLikeListView", 
  components: { 
    ReviewListItem,
  },
  data(){
    return{
      reviews: [],
    }
  },
  computed: {
      ...mapState({
        user_name: state => state.userStore.userInfo.user_name,
        user_id: state => state.userStore.userInfo.user_id,
        user_email: state => state.userStore.userInfo.user_email,
        user_idx: state => state.userStore.userInfo.user_idx,
      }),
  },
  created(){
    http.get(`/myreview/${this.user_idx}`).then((response) => {
        console.log(response.status);
        console.log(response);
        if(response.status == 200){
          this.reviews = response.data;
        }
        else{
          alert("나의 후기들 불러오기 실패!!!");
        }
    });
  },
}
</script>

<style scoped>
  .card-deck .card {
    flex: none;
    
  }
  .card-deck{
    justify-content: center;
  }
  .card{
    width:26vw;
  }
  .title{
    margin-top : 80px;
    text-align : center;
  }
  
  .searchBar{
    margin-left: 1300px;
    width: 100%;
    margin-top: 20px;
  }

  .searchBtn{
    width: 60px;
    margin-right: 60px;
    height: 35px;
    background-color: #F1F4F1;
    border: 1px solid rgba(255, 255, 255, .2);
    border-radius: 20px / 20px;
  }

  .typeSelectbar{
    width: 70px;
    margin-right: 30px;
    height: 35px;
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 20px / 20px;
  }

  .areaSelectbar{
    width: 300px;
    height: 35px;
    margin-right: 30px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 20px / 20px;
  }

  .registerBtn{
    margin-top: 20px;
    margin-left: 340px;
    margin-bottom: 30px;
    width: 130px;
    height: 35px;
    color: #F1F4F1;
    background-color: #7aab85;
    border: 1px solid rgba(255, 255, 255, .2);
    border-radius: 10px / 10px;
  }

  .box{
    width: 400px;
    height: 500px;
    background-color: #F1F4F1;
    text-align: center;
  }

  .imageBox{
    display: inline-block;
    width: 80%;
    height: 300px;
    background-color: rgba(200, 235, 207, 0.5);
  }

  .boxTitle{
    margin-top: 30px;
    font-size: 30px;
  }

  .boxArea{
    margin-top: 30px;
    font-size: 20px;
    float: right;
    margin-right: 30px;
  }
 </style>
