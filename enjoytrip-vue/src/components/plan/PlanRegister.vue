<template lang="">
    <div>
        <div class = "title">
          <h2>여행 계획 등록</h2>
        </div>
        <div class = "planRegistDiv">
            <plan-input-item/>
        </div>
    </div>
</template>

<script>
import PlanInputItem from "@/components/plan/item/PlanInputItem.vue";
export default {
    name: "PlanRegister",
    components: {
        PlanInputItem,
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
</style>