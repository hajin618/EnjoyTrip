<template>
    <div>
        <div class = "title">
          <h2>여행 계획</h2>
        </div>

        <div style="display: flex; flex-direction:row; margin-bottom:100px">
            
            <div class="planDiv">
                <div class="planTopDiv">
                    <div class="planTitleDiv"> 
                        <h4>{{plan.plan_title}}</h4>
                    </div>
                    <div class="planChildBtnDiv">
                        {{plan.plan_type}}
                    </div>
                </div>

                <div class="planMapDiv">
                  <img width="100%" height="100%" :src="imageURL" alt="">
                </diV>

                <div class="planContentDiv">
                  {{plan.plan_content}}
                </div>
            </div>

            <div class="findKidZone">
              <div class="textDiv">
                <h5>여행 순서</h5>
              </div>
              <div v-for="detail in attractionList" :key="detail.attraction_idx" class="orderItem">
                {{ detail.attraction_name }}
              </div>
            </div>
        </div>

      <div class="buttonBox">
        <div class="listBox">
          <button v-on:click.prevent="listPlan" class="listBtn">목록</button>
        </div>
        
        <!-- <div v-if="plan.userDto.user_id === user_id"> -->
        <div>
          <button v-on:click.prevent="modifyPlan" class="editBtn">수정</button>
          <button v-on:click.prevent="removePlan" class="deleteBtn">삭제</button>
        </div>
      </div>

    </div>


</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

export default {
    name: "PlanDetailView",

    data(){
        return{
            plan_idx : 0,
            plan: [],
            planDetails: [],
            // attractions: [],
            // childAttractions: [],
            attractionList: [],     // attractions + childAttractions 
            imageURL: '',
        };
    },
    
    created(){
      http.get(`/plan/${this.$route.params.plan_idx}`).then(({ data }) => {
        // console.log(data);
        this.plan = data;
        //console.log(this.plan.plan_idx);
        this.plan_idx = data.plan_idx;
      }),

      http.get(`/planDetail/${this.$route.params.plan_idx}`).then(({ data }) => {
        this.planDetails = data;
        // console.log("planDetails : "+ this.planDetails);
        for(var i=0; i<this.planDetails.length; i++){
          let contentId = this.planDetails[i].content_id;

          // 어린이 여행지인 경우
          if(contentId >=1 && contentId <= 8580){
            http.get(`/childAttraction/${contentId}`).then(({ data }) => {
              //this.childAttractions.push(data);
              this.attractionList.push({
                attraction_name : data.attraction_name,
                attraction_idx : data.attraction_idx,
                attraction_type : "어린이",
              });
            })
          }
          // 어른 여행지인 경우
          else{
            http.get(`/attraction/${contentId}`).then(({ data }) => {
              this.attractionList.push({
                attraction_name : data.title,
                attraction_idx : data.content_id,
                attraction_type : "어른",
              });
            })
          }

        }
      })
    },
    mounted(){
      this.plan_idx = this.$route.params.plan_idx,
      this.getImageURL();
    },
    methods:{
      listPlan(){
        this.$router.push({ name: "PlanView" });
      },
      modifyPlan(){
        this.$router.replace({
          name: "planRegister",
          params: { plan_idx : this.plan.plan_idx },
        });
      },
      removePlan(){
        // 계획 상세 삭제하기
        http.delete(`/planDetail/${this.plan.plan_idx}`)
          .then((response) => {
            if(response.status == 200){
              console.log("계획 상세 삭제 성공");

              // 계획 삭제하기
              http.delete(`/plan/${this.plan.plan_idx}`)
              .then((response) => {
              if(response.status == 200){
                // alert("계획 삭제 성공!")
                console.log("계획 삭제 성공")
                alert("계획 삭제 성공");
                this.listPlan();
              }
              else{
                alert("계획 삭제 실패!");
                this.listPlan();
              }
            })
            }
            else{
                alert("계획 삭제 실패!");
                this.listPlan();
            }
          })

      },
      getImageURL(){
        // const planIdx = this.plan_idx;
        // console.log("plan : " + this.plan);
        const baseURL = "http://localhost:80/upload/plan/";
        const extractedPath = baseURL + this.plan_idx + ".png";
        console.log("extractedPath : " + extractedPath);
        this.imageURL = extractedPath;
      }
    },
    computed: {
    ...mapState({
      user_name: state => state.userStore.userInfo.user_name,
      user_id: state => state.userStore.userInfo.user_id,
      user_email: state => state.userStore.userInfo.user_email,
      }),
    }
}
</script>
<style scoped>
  .title{
      padding-top : 50px;
      padding-bottom: 50px;
    }
  h2{
      text-align : center;
    }

  .findKidZone{
    margin-left: 40px;
    width: 25%;
    height: 620px;
    /* background-color: rgba(200, 235, 207, 0.5); */
    /* border: 1px solid rgba(200, 235, 207, 0.5); */
    border-radius: 10px / 10px;
    padding-top: 80px;
  }

  
  .findTitle{
    margin-top: 20px;
    margin-left: 20%;
    font-size: 25px;
  }

  .findImageBox{
    margin-top: 20px;
    margin-left: 10%;
    width: 70%;
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
    width: 75%;
    height: 50px;
    line-height : 50px;
    background-color:#e8ece8d1;
    border-radius: 20px / 20px;
    padding-top: 10px;
  }
  .planChildBtnDiv{
    margin-left: 120px;
    width: 15%;
    height: 50px;
    line-height : 50px;
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
    padding-top: 20px;
    padding-left: 20px;
  }
  .Button{
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
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

.buttonBox{
    margin-top: 40px;
    margin-bottom: 40px;
    display: flex; 
    flex-direction:row;
  }

  .listBox{
    margin-left: 940px;
  }

  .listBtn{
    width: 80px;
    height: 35px;
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .editBtn{
    margin-left: 540px;
    width: 80px;
    height: 35px;
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .deleteBtn{
    margin-left: 20px;
    width: 80px;
    height: 35px;
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .orderItem{
    border-top : 1px solid #9b9b9bd1;
    border-bottom : 1px solid #9b9b9bd1;
    width: 100%;
    height: 40px;
    /* border-radius: 10px / 10px; */
    margin-bottom : 5px;
    padding-top: 6px;
    background-color: #e8ece8d1;
    text-align: center;
  }
  
  .textDiv{
    text-align: center;
    margin-top : 10px;
    margin-bottom: 20px;
}
</style>