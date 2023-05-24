 <template>
   <div>
    <div class = "title">
      <h2>여행 후기 게시판</h2>
    </div>

    <div class="searchBar">
      <select class="areaSelectbar" v-model="selectedArea">
          <option value=0 selected>시/도 선택</option>
          <option v-for="(item, index) in selectList" :key="index" :value="item.sido_code">
          {{ item.sido_name }}
          </option> 
      </select>

      <select class="typeSelectbar" v-model="selectedType">
          <option value="" selected>여행 타입 선택</option>
          <option v-for="(item, index) in selectType" :key="index" :value="item.value">
          {{ item.name }}
          </option>
      </select>

      <button v-on:click.prevent="sort" class="searchBtn">검색</button>
       
      <div> 
        <button v-on:click.prevent="register" class="registerBtn">등록</button> 
      </div>
   </div>

  <!-- align-content:space-evenly 이거 하면 행간 띄운다는데 안 먹음 -->
    <div v-if="reviews.length" style="display: flex; flex-direction: row; flex-wrap: wrap; align-content:space-around;">
      <!-- justify-content: space-evenly;  -->
      <review-list-item
        style=""
        v-for="review in reviews"
        :key="review.review_idx"
        v-bind="review"
      />
    </div>

   </div>
</template>
 
<script>
import http from "@/api/http";
import Swal from "sweetalert2";
import ReviewListItem from "@/components/reviewboard/item/ReviewListItem.vue";

export default {
  name: "ReviewList",
  components: {
    ReviewListItem,
  },
  data(){
    return{
      reviews: [],
        selectedArea: 0,
        selectList: [],
        selectedType: '',
        selectType: [
                        {name: '아이', value: "아이"},
                        {name: '어른', value: "어른"},
                    ],
    }
  },
  created(){
      http.get(`/review`).then((response) => {
          //console.log(response.status);
          //console.log(response);
          if(response.status == 200){
            this.reviews = response.data;
          }
          else{
            Swal.fire({
              icon: 'error',
              title: '데이터 불러오기 실패!',
              text: '서버 오류입니다!',
            })
          }
      });
      http.get(`/sido`).then(({ data }) => {
        //console.log(data.sidoList);
        this.selectList = data.sidoList;
      });
  },
  methods: {
    register(){
      this.$router.push({ name: "reviewRegister" });
    },
    sort(){
      //console.log(this.selectedArea, this.selectedType);
      http.get(`/reviewsort`, {
        params: {
          sido_code: this.selectedArea,
          review_type: this.selectedType
        }
      })
      .then((response) => {
        //console.log(response.status);
        if(response.status == 200){
          this.reviews = response.data;
        }
        else{
          Swal.fire({
            icon: 'error',
            title: '정렬 실패!',
            text: '서버 오류입니다!',
          })
        }
      })
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
    margin-top : 60px;
    text-align : center;
  }
  
  .searchBar{
    margin-left: 1200px;
    width: 100%;
    margin-top: 20px;
  }

  .searchBtn{
    width: 90px;
    margin-right: 30px;
    height: 35px;
    background-color: #d1deec;
    border: 1px solid rgba(255, 255, 255, .2);
    border-radius: 20px / 20px;
  }

  .searchBtn:hover{
    background-color: #97b1cd;
  }

  .typeSelectbar{
    width: 150px;
    margin-right: 30px;
    height: 35px;
    background-color: #cfcfcf80;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 20px / 20px;
  }

  .areaSelectbar{
    width: 300px;
    height: 35px;
    margin-right: 30px;
    background-color: #cfcfcf80;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 20px / 20px;
  }

  .registerBtn{
    margin-top: 50px;
    margin-left: 470px;
    margin-bottom: 20px;
    width: 130px;
    height: 35px;
    color: black;
    background-color: #d1deec;
    border: 1px solid rgba(255, 255, 255, .2);
    border-radius: 10px / 10px;
  }

  .registerBtn:hover{
    background-color: #97b1cd;
  }

  .blacklink{
      color: black;
  }
 </style>