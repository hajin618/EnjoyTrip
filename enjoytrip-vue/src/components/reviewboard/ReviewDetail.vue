<template>
  <div>
    <div class = "title">
      <h2>여행 후기 게시판</h2>
    </div>

    <div style="display: flex; flex-direction:row;">
      <div class="findKidZone">
        <div class="findTitle">실종 아동 정보</div>

        <div class="findImageBox">
          <img width="100%" height="100%" src="../../assets/img/mainPageImg.png" alt="">
        </div>

        <div class="findNameBox">
          <label>이름 : </label>
          <span>홍길동</span>
        </div>
        
        <div class="findAgeBox">
          <label>나이 : </label>
          <span>5</span>
        </div>

        <div class="findInfoBox">
          <label>인적사항 1 : </label>
          <span>마른 편</span>
        </div>

        <div class="findInfoBox">
          <label>인적사항 2 : </label>
          <span>눈이 이쁨</span>
        </div>

      </div>

      <div class="postZone">
        <div class="postImageBox">
          <b-carousel
            id="carousel-1"
            :interval="4000"
            controls
            indicators
          >
            <review-image-item
              v-for="image in review_image"
              :key="image.image_num"
              v-bind="image"
            />
          </b-carousel>
        </div>

        <div class="postInfoBox">
          
          <div class="postTitleBox">
            <div class="titleBox" id="title">{{review.review_title}}</div>

            <!-- <select class="areaSelectbar" v-model="selectedArea">
              <option v-for="(item, index) in selectList" :key="index" :value="item.value">
                {{ item.name }}
              </option>
            </select> -->
            <div class="areaSelectbar">
              {{getSidoName(review.sido_code)}}
            </div>
            <div class="typeSelectbar">
              {{review.review_type}}
            </div>
          </div>

          <div class="postContentBox" id="content">{{review.review_content}}</div>


        </div>
      </div>
    </div>

    <div class="buttonBox">
      <div class="listBox">
        <button v-on:click.prevent="moveList" class="listBtn">목록</button>
      </div>
      <div>
        <button v-on:click.prevent="edit" class="editBtn">수정</button>
        <button class="deleteBtn">삭제</button>
      </div>
    </div>

  </div>
</template>

<script>
import http from "@/api/http";
import ReviewImageItem from "@/components/reviewboard/item/ReviewImageItem.vue";

export default {
  name: "ReviewDetailView",
  data(){
    return{
      review:[],
      review_image:[],
    }
  },
  components:{
    ReviewImageItem
  },
  mounted(){
    document.getElementsByClassName("carousel-inner")[0].style.height="100%";
  },
  created() {
    http.get(`/review/${this.$route.params.review_idx}`).then(({ data }) => {
           console.log(data);
        this.review = data;
        this.review_image = this.review.review_image;
    });
    
  },
  methods:{
    moveList(){
      this.$router.push({ name: "reviewBoardView" });
    },
    edit(){
      this.$router.replace({
        name: "reviewModify",
        params: { review_idx : this.review.review_idx },
      });
    },
  },
  computed: {
    // 숫자를 지역명으로 변환하는 computed 속성
    getSidoName() {
      return (sidoCode) => {
        const sidoMap = {
          1: '서울',
          2: '인천',
          3: '대전',
          4: '대구',
          5: '광주',
          6: '부산',
          7: '울산',
          8: '세종특별자치시',
          31: '경기도',
          32: '강원도',
          33: '충청북도',
          34: '충청남도',
          35: '경상북도',
          36: '경상남도',
          37: '전라북도',
          38: '전라남도'
        };

        return sidoMap[sidoCode] || '';
      };
    }
  },
}

</script>


<style scoped> 
  .title{
    margin-top : 70px;
    margin-bottom: 80px;
    text-align : center;
  }

.carousel-inner, .carousel-item {
  height: 100%;
}
.carousel{
  height: 350px;
}

[role="list"]{
  height: 100% !important;
}
  .findKidZone{
    margin-left: 40px;
    width: 350px;
    height: 500px;
    background-color: rgba(200, 235, 207, 0.5);
    border: 1px solid rgba(200, 235, 207, 0.5);
    border-radius: 10px / 10px;
  }

  .postZone{
    margin-left: 150px;
    width: 1200px;
    height: 500px;
    background-color: rgba(200, 235, 207, 0.5);
    border: 1px solid rgba(200, 235, 207, 0.5);
    border-radius: 10px / 10px;
    display: flex;
    flex-direction:row;
  }
  
  .findTitle{
    margin-top: 20px;
    text-align: center;
    font-size: 25px;
  }

  .findImageBox{
    margin-top: 20px;
    margin-left: 40px;
    width: 80%;
    height: 220px;
  }

  .findNameBox{
    margin-top: 20px;
    margin-left: 60px;
    font-size: 20px;
  }

  .findAgeBox{
    margin-left: 60px;
    font-size: 20px;
  }

  .findInfoBox{
    margin-left: 60px;
    font-size: 20px;
  }

  .buttonBox{
    margin-top: 40px;
    margin-bottom: 40px;
    display: flex; 
    flex-direction:row;
    float: right;
  }

  .listBox{
    margin-right: 500px;
  }

  .listBtn{
    width: 80px;
    height: 35px;
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .editBtn{
    margin-right: 15px;
    width: 80px;
    height: 35px;
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .deleteBtn{
    margin-right: 175px;
    width: 80px;
    height: 35px;
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .postImageBox{
    margin-top: 70px;
    margin-left: 100px;
    width:350px;
    height:350px;
    background-color: #D9D9D9; 
  }
  .b-carousel-inner{
    height: 100%;
  }

  b-carousel-slide{
    width:100%;
    height:100%;
    object-fit:cover;
  }

  .postImageArea{
    margin-top: 20px;
    width: 200px;
    height: 300px;
  }

  .postTitleBox{
    margin-left: 140px;
    margin-top: 65px;
    display: flex;
    flex-direction:row;
    text-align: center;
  }

  .titleBox{
    width: 330px;
    height: 40px;
    background-color: #D9D9D9;
    background-color: #D9D9D9;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 20px / 20px;
    line-height : 40px;
  }

  .areaSelectbar{
    width: 90px;
    height: 37px;
    margin-left: 20px;
    background-color: #D9D9D9;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 20px / 20px;
    line-height : 37px;
  }

  .typeSelectbar{
    width: 90px;
    height: 37px;
    margin-left: 20px;
    background-color: #D9D9D9;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 20px / 20px;
    line-height : 37px;
  }

  .postContentBox{
    margin-left: 140px;
    margin-top: 30px;
    width: 550px;
    height: 280px;
    background-color: #D9D9D9;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 20px / 20px;
    line-height : 280px;
  }

</style>