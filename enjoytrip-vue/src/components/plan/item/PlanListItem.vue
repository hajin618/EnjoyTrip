<template lang="">
<div class="cardDiv">
    <router-link :to="{name: 'planDetail', params: { plan_idx: plan_idx } }">
        <b-card :img-src="imageURL">
            <b-card-text> 
                <div class="planType">
                    <span class="typeText">{{plan_type}}</span>
                </div>
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
        width: 70px;
        margin-top: 30px;
        font-size: 17px;
        float: right;
        margin-right: 20px;
        background-color: #97b1cd;
        border-radius: 10px / 10px;
        white-space: nowrap;
        color: white;
    }

    .planTitle{
        font-size: 20px;
        white-space: nowrap;
    }
</style>