<template lang="">
    <b-container class="bv-example-row mt-5">
        <div class = "title">
          <h2>여행 계획</h2>
        </div>
        <div class = "buttonDiv">
            <button
                v-on:click="moveRegist"
                class="registButton"
                >등록</button>
        </div>
        <div class="listDiv">
            <div v-if="plans.length" id="listItem">
                <plan-list-item
                    v-for="plan in plans"
                    :key="plan.plan_idx"
                    v-bind="plan"
                />
            </div>
        </div>
    </b-container>
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
    padding-bottom : 15px;
    float:right;
  }    
  .listDiv{
    clear:right;
    padding-bottom: 50px;
    display: flex;
  }
  #listItem{
    display: flex;
  }
</style>