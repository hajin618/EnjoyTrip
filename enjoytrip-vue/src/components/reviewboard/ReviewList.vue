 <template>
   <div>
    <div class = "title">
      <h2>여행 후기 게시판</h2>
    </div>

    <div class="searchBar">
      <select class="areaSelectbar" v-model="selectedArea">
        <option v-for="(item, index) in selectList" :key="index" :value="item.value">
          {{ item.name }}
        </option>
      </select>

      <select class="typeSelectbar" v-model="selectedType">
        <option v-for="(item, index) in selectType" :key="index" :value="item.value">
          {{ item.name }}
        </option>
      </select>

      <button class="searchBtn">검색</button>
    </div>

    <div class="registerdiv">
      <button class="registerBtn">등록</button>
    </div>

   </div>
 </template>
 
 <script>

export default {
  name: "ReviewList",
  components: {

  },
  data(){
    return{
      selectedArea: '',
      selectList: [{name: "시도 선택", value: ""},
                    {name: "name1", value: "a"},
                    {name: "name2", value: "b"},
                    {name: "name3", value: "c"},
                  ],
      selectedType: '',
      selectType: [{name: "선택", value: ""},
                    {name: "아이", value: "a"},
                    {name: "어른", value: "b"},
                  ],
    }
  },
  created(){

  },
  methods: {

  },
}
 </script>

 
 <style scoped>
  .title{
    margin-top : 80px;
    text-align : center;
  }
  
  .searchBar{
    margin-top: 20px;
    float: right;
  }

  .searchBtn{
    width: 60px;
    margin-right: 60px;
    background-color: #F1F4F1;
    border: 1px solid rgba(255, 255, 255, .2);
    border-radius: 20px / 20px;
  }

  .typeSelectbar{
    width: 70px;
    margin-right: 30px;
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 20px / 20px;
  }

  .areaSelectbar{
    width: 300px;
    margin-right: 30px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 20px / 20px;
  }

  .registerBtn{
    width: 100px;
    height: 35px;
    color: #F1F4F1;
    background-color: #7aab85;
    border: 1px solid rgba(255, 255, 255, .2);
    border-radius: 20px / 20px;
  }

  .registerdiv{
    margin-left: 1350px;
    margin-top: 80px;
    margin-bottom: 50px;
  }
 </style>