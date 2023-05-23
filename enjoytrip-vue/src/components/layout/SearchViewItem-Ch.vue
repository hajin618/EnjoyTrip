<template>

    <b-tr>
      <b-td>
        <img class="img" width="100px" height="100px" src="@/assets/img/childAttraction.jpg" alt="">
      </b-td>
      <b-td @click="openModal(attraction_idx)">
        {{attraction_name}}
      </b-td>
      <b-td>
        {{address}}
      </b-td>
      <b-td>
        <button class="btn" v-on:click="saveChAtt">저장</button>
      </b-td>
    </b-tr>

</template>

<script>
export default {
  name: "SearchViewItem-Ch",
  data(){
    return{ 

    }
  },
  props: {
    attraction_idx: Number,
    attraction_name: String,
    address: String,
    savedAtt : Array,
  },
  methods: {
    saveChAtt(){
      console.log(this.attraction_idx);
      // emit 이벤트명 : saveAtt
      this.$emit("saveChAtt", this.attraction_idx); 
    },
    openModal(attraction_idx){
      this.$emit('openModal', attraction_idx);
    },
    
  },
}
</script>

<style scoped>
  .btn{
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
  .img{
    margin: 12px;
  }
</style>