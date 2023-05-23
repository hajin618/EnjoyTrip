<template>
    <div>
    <div v-if="plans.length" style="display: flex; flex-direction: row; flex-wrap: wrap; justify-content: space-evenly; margin-bottom:30px;">

      <plan-list-item
        v-for="plan in plans"
        :key="plan.plan_idx"
        v-bind="plan"
      />

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
<style lang="">
    
</style>