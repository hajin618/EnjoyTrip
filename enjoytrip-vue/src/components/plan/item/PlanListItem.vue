<template lang="">
<div class="cardDiv">
    <router-link :to="{name: 'planDetail', params: { plan_idx: plan_idx } }">
        <b-card 
            img-src="https://picsum.photos/400/300/?image=41" 
            img-alt="Image" 
            img-top >
                
                <b-card-text> text text
                </b-card-text>
            
        </b-card>
    </router-link>
</div>
</template>

<script>
export default {
    name: "PlanListItem",
    props: {
        plan_idx: Number,
        plan_title: String,
        plan_details: Array,
        plan_content: String,
    },
}
</script>

<style scoped>
    .card{
    width: 17vw;
    /*
    height: 16vw;
    */
    margin-bottom: 100px;

    }

    .cardDiv{
    margin-right: 40px;
    }
</style>