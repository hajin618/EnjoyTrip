<template>
  <b-tr>
    <b-td v-if="first_image == ''">
      <img width="100px" height="100px" src="@/assets/img/mainPageImg.png" alt="">
    </b-td>
    <b-td v-else>
      <img width="100px" height="100px" :src="first_image" alt="">
    </b-td>
    <b-td>
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
export default {
  name: "SearchViewItem",
  props: {
    content_id: Number,
    first_image: String,
    title: String,
    addr1: String,
    savedAtt : Array,
  },
  methods: {
    saveAtt(){
      console.log(this.content_id);
      // emit 이벤트명 : saveAtt
      this.$emit("saveAtt", this.content_id); 
    },
  },
}
</script>

<style scoped>
  .btn{
    /* margin-left: 70px;
    width: 100px;
    height: 37px;
    background-color: #D9D9D9;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px; */
    border-color:#98c2a182;
    background-color : #98c2a182;
    border-radius: 10px;
    width: 80px;
    height: 35px;
    margin-left: 70px;
  }
  .btn:hover{
    background-color : #c8d6cc;
  }
</style>