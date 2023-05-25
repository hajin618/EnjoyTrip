<template lang="">
    <div class="main">
        <img class="img" width="200px" height="200px" :src="randomImagePath" alt="">
        <div>
        <span class="itemName">
            {{attraction_name}}
        </span>
        </div> 
        <div class="itemInfo">
        <button class="findChildSpotBtn" v-on:click="searchAnother">다른 여행지 찾기</button>
        <button class="itemDeleteBtn" v-on:click="deleteChAtt">X</button>
        </div>
    </div>
</template>
<script>
export default {
    name: "SavedChAttractionItem",
    data(){
        return{
            imagePaths: [
                require('@/assets/img/childAttraction1.jpg'),
                require('@/assets/img/childAttraction2.jpg'),
                require('@/assets/img/childAttraction3.jpg'),
                require('@/assets/img/childAttraction4.jpg'),
                require('@/assets/img/childAttraction5.jpg'),
                require('@/assets/img/childAttraction6.jpg'),
                require('@/assets/img/childAttraction7.jpg'),
                require('@/assets/img/childAttraction8.jpg'),
                require('@/assets/img/childAttraction9.jpg'),
                require('@/assets/img/childAttraction10.jpg'),
            ],
            randomImagePath: '',
        }
    },
    props: {
        attraction_idx: Number,
        attraction_name: String,
    },
    methods: {
        deleteChAtt(){
            console.log(this.attraction_idx);
            // emit 이벤트 명: deleteChAtt
            this.$emit("deleteChAtt", this.attraction_idx);
        },
        searchAnother(){
            this.$emit("searchAnother");
        },
        setRandomImagePath(){
            const randomIndex = Math.floor(Math.random() * this.imagePaths.length);
            this.randomImagePath = this.imagePaths[randomIndex];
            console.log(this.randomImagePath);
        },
    },
    created(){
        this.setRandomImagePath();
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