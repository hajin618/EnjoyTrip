<template lang="">
<div class="cardDiv">
    <router-link :to="{name: 'planDetail', params: { plan_idx: plan_idx } }">
        <b-card :img-src="imageURL">
            <b-card-text> 
                <div class="planType">{{plan_type}}</div>
                <div class="planTitle">{{plan_title}}</div>
            </b-card-text>

            
        </b-card>
    </router-link>
</div>
</template>

<script>
import imageUrl from "@/api/image";
export default {
    name: "PlanListItem",
    props: {
        plan_idx: Number,
        plan_title: String,
        plan_details: Array,
        plan_content: String,
        plan_type: String,
    },
    data(){
        return{
            imageURL: '',
        }
    },
    mounted(){
        this.getImageURL();
    },
    methods: {
        getImageURL(){
            const planIdx = this.plan_idx;
            // const baseURL = "http://localhost:80/upload/plan/";
            // const baseURL = "http://192.168.208.85:80/upload/plan/";
            const baseURL = imageUrl + "/plan/";
            const extractedPath = baseURL + planIdx + ".png";
            console.log("extractedPath : " + extractedPath);
            this.imageURL = extractedPath;
        }
    }
}
</script>

<style scoped>
    .card{
    width: 18vw;
    /*
    height: 16vw;
    */
    margin-bottom: 100px;

    }

    .cardDiv{
    margin-right: 40px;
    }

    .planType{
        white-space: nowrap;
    }

    .planTitle{
        white-space: nowrap;
    }
</style>