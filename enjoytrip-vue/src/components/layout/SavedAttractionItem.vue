<template lang="">
    <div class="main">
        <div v-if="first_image == ''">
            <img width="200px" height="200px" src="@/assets/img/mainPageImg.png" alt="">
        </div>
        <div v-else>
            <img width="200px" height="200px" :src="first_image" alt="">
        </div>
        <div>
        <span class="itemName">
            {{title}}
        </span>
        </div>
        <div class="itemInfo">
        <button class="findChildSpotBtn" v-on:click="searchAnotherCh">어린이를 위한 장소 찾기</button>
        <button class="itemDeleteBtn" v-on:click="deleteAtt">X</button>
        </div>
    </div>
</template>
<script>
export default {
    name: "SavedAttractionItem",
    props: {
        content_id: Number,
        first_image: String,
        title: String,
    },
    methods: {
        deleteAtt(){
            console.log(this.content_id);
            // emit 이벤트 명: deleteAtt
            this.$emit("deleteAtt", this.content_id);
        },
        searchAnotherCh(){
            this.$emit("searchAnotherCh")
        }
    }
}
</script>
<style scoped>
    .main{
        margin-bottom: 30px;
    }
    .itemInfo{
    margin-top: 10px;
    }
    .findChildSpotBtn{
    margin-left: 20px;
    width: 150px;
    height: 30px;
    font-size: 12px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
    }
    .findChildSpotBtn:hover{
        background-color : #c8d6cc;
    }
  .itemDeleteBtn{
    margin-left: 20px;
    width : 30px;
    height: 30px;
    font-size: 10px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }
  .itemDeleteBtn:hover{
    background-color : #c8d6cc;
  }
</style>