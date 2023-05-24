<template>
    <div class="box">
        <div class="imageBox">
          <!-- 사진은 이미지 객체의 첫번째 원소 가져오기 -->
          <router-link class="blacklink" :to="{ name: 'reviewDetail', params: { review_idx: review_idx } }">
            <img width="100%" height="100%" :src="represent_imageUrl" alt="">
          </router-link>
        </div>
        <div class="boxTitle">
          <router-link class="blacklink" :to="{ name: 'reviewDetail', params: { review_idx: review_idx } }">{{review_title}}</router-link>
          <!-- <router-link class="blacklink" :to="{ name: 'reviewDetail', params: { review_idx: 1 } }">{{review_title}}</router-link>   -->
        </div>
        <div class="txt-container">
          <div class="hitArea">
          <span>조회 {{ review_hit }}</span>
        </div>
        <div class="boxArea">
          <span class="typeText">{{getSidoName(sido_code)}}</span>
        </div>
        <div class="boxArea">
          <span class="typeText">{{review_type}}</span>
        </div>
        </div>
    </div>
</template>

<script>
import imageUrl from "@/api/image";
export default { 
    name: "ReviewListItem",
    props: {
        review_idx: Number,
        review_title: String,
        review_content: String,
        review_hit: Number,
        review_type: String,
        review_create: String,
        review_update: String,
        sido_code: Number,
        review_image: Array,
    },
    data(){
      return{
        represent_imageUrl: '',
      }
    },
    methods:{
      getImageURL() {
        // console.log(this.review_image[0].image_url);
        const filePath = this.review_image[0].image_url;
        const uploadPath = "\\upload";
        // const baseURL = "http://localhost:80/upload";  // 애플리케이션의 기본 URL을 입력하세요.
        // const baseURL = "http://192.168.208.85:80/upload";  // 애플리케이션의 기본 URL을 입력하세요.
        const baseURL = imageUrl;
        const parts = filePath.split(uploadPath);
        const extractedPath = baseURL + parts[1].replaceAll('\\', '/');
        // console.log(extractedPath);
        this.represent_imageUrl = extractedPath;
      }
    },
    mounted(){
      this.getImageURL();
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
  .txt-container{
    display:flex;
    flex-direction:row;
  }
  .box{
    display:flex;
    flex-direction: column;
    width: 400px;
    /* height: 450px;*/
    height: 55vmin; 
    background-color: #e3ecec;
    text-align: center;
    align-items: center;
    border-radius: 2vmin;
    margin: 4vmin;
  }

  .blacklink{
      color: black;
      /* text-decoration: none; */
  }

  .imageBox{
    display: inline-block;
    width: 80%;
    height: 300px;
    margin: 3vmin;
    background-color: rgba(200, 235, 207, 0.5);
  }

  .boxTitle{
    font-size: 30px;
  }

  .boxArea{
    width: 70px;
    margin-top: 30px;
    font-size: 20px;
    float: right;
    margin-right: 20px;
    background-color: #98cfc4;
    border-radius: 10px / 10px;
    border: 1px solid rgba(213, 120, 120, .2);
  }

  .hitArea{
    width: 150px;
    margin-top: 30px;
    font-size: 20px;
    float: left;
  }

  .typeText{
    color: white;
  }
</style>