<template>
    <div>
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
import {mapState} from "vuex";

export default {
    name: "UserPlanList",
    components: {
        PlanListItem,
    },

    data(){
        return{
            plans: [],
        }
    },

    computed: {
        ...mapState({
            user_idx: state => state.userStore.userInfo.user_idx,
        }),
    },

    created(){
        http.get(`/myPlan/${this.user_idx}`).then((response) => {
            if(response.status == 200){
                this.plans = response.data;
                console.log("data ::: " + response.data);
            }
        })
    }
}
</script>
<style scoped>
  .contentDiv{
    text-align : center;
    clear:both;
    padding-bottom: 50px;
    display: flex;
    padding-left : 200px;
  }    
</style>