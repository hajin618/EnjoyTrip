<template>

    <b-tr>
      <b-td>
        <img class="img" width="100px" height="100px" :src="randomImagePath" alt="">
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
  .btn{
    border-color:#98c2a182;
    background-color : #d1deec;
    border-radius: 10px;
    width: 80px;
    height: 35px;
    margin-left: 70px;
  }
  .btn:hover{
    background-color : #97b1cd;
  }
  .img{
    margin: 12px;
  }
</style>