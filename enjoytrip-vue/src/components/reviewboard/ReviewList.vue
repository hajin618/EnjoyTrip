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
      <div> 
        <button v-on:click.prevent="register" class="registerBtn">등록</button> 
      </div>
    </div>

  <!-- align-content:space-evenly 이거 하면 행간 띄운다는데 안 먹음 -->
    <div v-if="reviews.length" style="display: flex; flex-direction: row; flex-wrap: wrap; justify-content: space-evenly; margin-bottom:30px;">
      
      <review-list-item
        v-for="review in reviews"
        :key="review.review_idx"
        v-bind="review"
      />
    </div>

   </div>
 </template>
 
 <script>
import http from "@/api/http";
import ReviewListItem from "@/components/reviewboard/item/ReviewListItem.vue";

export default {
  name: "ReviewList",
  components: {
    ReviewListItem,
  },
  data(){
    return{
      reviews: [],
      selectedArea: '',
      selectList: [{name: "시도 선택", value: ""},
                    {name: "name1", value: "a"},
                    {name: "name2", value: "b"},
                    {name: "name3", value: "c"},
                  ],
      selectedType: '',
      selectType: [{name: "선택", value: ""},
                    {name: "아이", value: "아이"},
                    {name: "어른", value: "어른"},
                  ],
    }
  },
  created(){
      http.get(`/review`).then((response) => {
          console.log(response.status);
          console.log(response);
          if(response.status == 200){
            this.reviews = response.data;
          }
          else{
            alert("후기들 불러오기 실패!!!");
          }
      });
  },
  methods: {
    register(){
      this.$router.push({ name: "reviewRegister" });
    }
  },
}
 </script>

 
 <style scoped>
  .card-deck .card {
    flex: none;
    
  }
  .card-deck{
    justify-content: center;
  }
  .card{
    width:26vw;
  }
  .title{
    margin-top : 80px;
    text-align : center;
  }
  
  .searchBar{
    margin-left: 1300px;
    width: 100%;
    margin-top: 20px;
  }

  .searchBtn{
    width: 60px;
    margin-right: 60px;
    height: 35px;
    background-color: #F1F4F1;
    border: 1px solid rgba(255, 255, 255, .2);
    border-radius: 20px / 20px;
  }

  .typeSelectbar{
    width: 70px;
    margin-right: 30px;
    height: 35px;
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 20px / 20px;
  }

  .areaSelectbar{
    width: 300px;
    height: 35px;
    margin-right: 30px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 20px / 20px;
  }

  .registerBtn{
    margin-top: 20px;
    margin-left: 340px;
    margin-bottom: 30px;
    width: 130px;
    height: 35px;
    color: #F1F4F1;
    background-color: #7aab85;
    border: 1px solid rgba(255, 255, 255, .2);
    border-radius: 10px / 10px;
  }
  .blacklink{
      color: black;
  }
 </style>