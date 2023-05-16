<template>
    <div>
        <div class = "title">
          <h2>여행 계획</h2>
        </div>

        <div style="display: flex; flex-direction:row; margin-bottom:100px">
            <div class="findKidZone">
                <div class="findTitle">실종 아동 정보</div>

                <div class="findImageBox">
                <img width="100%" height="100%" src="../../assets/img/mainPageImg.png" alt="">
                </div>

                <div class="findNameBox">
                <label>이름 : </label>
                <span>홍길동</span>
                </div>
                
                <div class="findAgeBox">
                <label>나이 : </label>
                <span>5</span>
                </div>

                <div class="findInfoBox">
                <label>인적사항 1 : </label>
                <span>마른 편</span>
                </div>

                <div class="findInfoBox">
                <label>인적사항 2 : </label>
                <span>눈이 이쁨</span>
                </div>

            </div>
        
            <div class="planDiv">
                <div class="planTopDiv">
                    <div class="planTitleDiv"> 
                        제목
                    </div>
                    <div class="planChildBtnDiv">
                        아이
                    </div>
                </div>

                <div class="planMapDiv">
                  지도
                </diV>

                <div class="planContentDiv">
                  내용
                </div>

                <div class="planButtonDiv">
                  <div class="planListBtnDiv">
                    <button
                      v-on:click="listPlan"
                      class="Button"
                      >목록</button>
                  </div>
                  <div class="planMdRmBtnDiv">
                    <button
                      v-on:click="modifyPlan"
                      class="Button"
                      >수정</button>
                    <button
                    v-on:click="removePlan"
                    class="Button"
                    >삭제</button>
                  </div>
                </div>

            </div>

            

        </div>
    </div>


</template>

<script>
import http from "@/api/http";
export default {
    name: "PlanDetail",

    data(){
        return{
            plan: {},
        };
    },

    created(){
        http.get(`/plan/${this.$route.params.plan_idx}`).then(({ data }) => {
            console.log(data);
            this.plan = data.plan;
        });
    },

    methods:{
      listPlan(){

      },
      modifyPlan(){

      },
      removePlan(){

      },
    }

}
</script>
<style scoped>
  .title{
      padding-top : 100px;
      padding-bottom: 50px;
    }
  h2{
      text-align : center;
    }

  .findKidZone{
    margin-left: 40px;
    width: 15%;
    height: 560px;
    background-color: rgba(200, 235, 207, 0.5);
    border: 1px solid rgba(200, 235, 207, 0.5);
    border-radius: 10px / 10px;
  }

  
  .findTitle{
    margin-top: 20px;
    margin-left: 20%;
    font-size: 25px;
  }

  .findImageBox{
    margin-top: 20px;
    margin-left: 10%;
    width: 80%;
    height: 300px;
  }

  .findNameBox{
    margin-top: 20px;
    margin-left: 10%;
    font-size: 20px;
  }

  .findAgeBox{
    margin-left: 10%;
    font-size: 20px;
  }

  .findInfoBox{
    margin-left: 10%;
    font-size: 20px;
  }

  .planDiv{
    margin-left : 100px;
    width : 60%;
    height: 600px;
    border-radius: 20px / 20px;
  }
  .planTopDiv{
    display: flex; 
    flex-direction: row; 
    margin-bottom: 30px;
    text-align: center;
  }

  .planTitleDiv{
    width: 95%;
    height: 40px;
    background-color:#e8ece8d1;
    border-radius: 20px / 20px;
  }
  .planChildBtnDiv{
    margin-left: 70px;
    width: 10%;
    height: 40px;
    background-color:#e8ece8d1;
    border-radius: 20px / 20px;
  }
  .planMapDiv{
    background-color:#e8ece8d1;
    height: 60%;
    margin-bottom: 30px;
  }
  .planContentDiv{
    background-color:#e8ece8d1;
    height: 25%;
    border-radius: 20px / 20px;
    margin-bottom: 30px;
  }
  .Button{
    border-color:#98c2a182;
    background-color : #98c2a182;
    border-radius: 10px;
    width: 80px;
    height: 35px;
    margin-right: 5px;
  }
  .planButtonDiv{
    display: flex; 
    flex-direction:row;
    margin-bottom:100px;
  }
  .planListBtnDiv{
    width: 50%;
    text-align: right;
  }
  .planMdRmBtnDiv{
    width: 50%;
    text-align: right;
  }
</style>