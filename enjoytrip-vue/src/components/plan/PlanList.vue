<template lang="">
  <div>
      <div class = "title">
        <h2>여행 계획</h2>
      </div>

      <div class = "buttonDiv">
            <button
                v-on:click="moveRegist"
                class="registButton"
                >등록</button>
      </div>

      <div class = "contentDiv" style="display: flex; flex-direction: row; flex-wrap: wrap; justify-content: space-evenly; margin-bottom:30px;">
        <b-card-group deck v-if="plans.length" id="listItem">
          <plan-list-item
              v-for="plan in plans"
              :key="plan.plan_idx"
              v-bind="plan"
          />
        </b-card-group>
      </div>
  </div>
</template>

<script>
import http from "@/api/http";
import PlanListItem from "@/components/plan/item/PlanListItem.vue";

export default {
    name: "PlanList",
    components: {
        PlanListItem,
    },
    data(){
        return{
            plans: [],
        };
    },
    created(){
        http.get(`/plan`).then(({data}) => {
            this.plans = data.resdata;
        });
    },
    methods: {
        moveRegist(){
          this.$router.push({ name: "planRegister" });
        },
    }
}
</script>

<style scoped>

  .card-deck .card {
    
    flex: none;
 
  }

  .card-deck{
    clear:right;
    justify-content: left;
    padding-top :20px;
    padding-bottom: 20px;
  }
  .card{
    width:18vw;
    height: 22vw;
    margin-bottom: 20px;
    margin-right: 50px;
  }
  .title{
      padding-top : 100px;
      padding-bottom: 50px;
    }
  h2{
      text-align : center;
    }
  .registButton{
    border-color:#98c2a182;
    background-color : #98c2a182;
    border-radius: 10px;
    width: 80px;
    height: 35px;
  }
  .registButton:hover{
    background-color : #c8d6cc;
  }
  .buttonDiv{
    padding-right : 200px;
    padding-bottom : 15px;
    float:right;
  }    
  #listItem{
    display: flex;
    width: 110%;
  }
  .contentDiv{
    text-align : center;
    clear:both;
    padding-bottom: 50px;
    display: flex;
    padding-left : 200px;
  }

</style>