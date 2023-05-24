<template>
  <b-tr>
    <b-td v-if="first_image == ''">
      <img width="100px" height="100px" src="@/assets/img/mainPageImg.png" alt="">
    </b-td>
    <b-td v-else>
      <img width="100px" height="100px" :src="first_image" alt="">
    </b-td>
    <b-td @click="openModal2(content_id)">
      {{title}}
    </b-td>
    <b-td>
      {{addr1}}
    </b-td>
    <b-td>
      <button class="btn" v-on:click="saveAtt">저장</button>
    </b-td>
  </b-tr>
</template>

<script>
import http from "@/api/http";

export default {
  name: "SearchViewItem", 
  props: {
    content_id: Number,
    first_image: String,
    title: String,
    addr1: String,
    savedAtt : Array,
    sido_code: Number,
  },
  methods: {
    saveAtt(){
      console.log(this.content_id);
      // emit 이벤트명 : saveAtt
      this.$emit("saveAtt", this.content_id); 

      // statistics 테이블에 저장하기
      http.post(`/statistics`, {
        sido_code : this.sido_code,
        attraction_name : this.title,
      }).then((response) => {
        //console.log("result:  " + response.status);
        if(response.status == 200){
          console.log("success insert data");
        }
        else{
          console.log("fail insert data");
        }
      })
    },

    openModal2(content_id){
      this.$emit('openModal2', content_id);
    },
  },
}
</script>

<style scoped>
  .btn{
    border-color:#98c2a182;
    background-color : #cfcfcf80;
    border-radius: 10px;
    width: 80px;
    height: 35px;
    margin-left: 70px;
  }
  .btn:hover{
    background-color : #828ea280;
  }
</style>