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
        </div>

        <div class="planDiv">
            <div class="planTopDiv">
                <div class="planTitleDiv">
                    제목
                </div>
                <div class="planChildBtnDiv">
                    버튼
                </div>
            </div>

            <div class="planMapDiv">

            </diV>

            <div class="planContentDiv">
            </div>
        </div>

        <div class="planButtonDiv">

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
    width: 380px;
    height: 560px;
    background-color: rgba(200, 235, 207, 0.5);
    border: 1px solid rgba(200, 235, 207, 0.5);
    border-radius: 10px / 10px;
  }

  
  .findTitle{
    margin-top: 20px;
    margin-left: 130px;
    font-size: 25px;
  }

  .findImageBox{
    margin-top: 20px;
    margin-left: 40px;
    width: 80%;
    height: 300px;
  }

  .findNameBox{
    margin-top: 20px;
    margin-left: 60px;
    font-size: 25px;
  }

  .findAgeBox{
    margin-left: 60px;
    font-size: 25px;
  }

  .findInfoBox{
    margin-left: 60px;
    font-size: 25px;
  }

  .planDiv{
    margin : 5px;
    background-color: rgba(200, 235, 207, 0.5);
  }
</style>