<template>
    <div class="box">
        <div class="imageBox">
             <!-- 사진은 이미지 객체의 첫번째 원소 가져오기 -->
            <img width="100%" height="100%" :src="represent_imageUrl" alt="">
        </div>
        <div class="boxTitle">
          <router-link class="blacklink" :to="{ name: 'reviewDetail', params: { review_idx: review_idx } }">{{review_title}}</router-link>
          <!-- <router-link class="blacklink" :to="{ name: 'reviewDetail', params: { review_idx: 1 } }">{{review_title}}</router-link>   -->
        </div>
        <div class="boxArea">{{review_type}}</div>
    </div>
</template>

<script>
// import http from "@/api/http";

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
        console.log(this.review_image[0].image_url);
        const filePath = this.review_image[0].image_url;
        const uploadPath = "\\upload";
        const baseURL = "http://localhost:80/upload";  // 애플리케이션의 기본 URL을 입력하세요.
        const parts = filePath.split(uploadPath);
        const extractedPath = baseURL + parts[1].replaceAll('\\', '/');
        console.log(extractedPath);
        this.represent_imageUrl = extractedPath;
      }
    },
    mounted(){
      this.getImageURL();
    }
}
</script>

<style scoped>
  .box{
    width: 400px;
    height: 500px;
    background-color: #F1F4F1;
    text-align: center;
  }

  .imageBox{
    display: inline-block;
    width: 80%;
    height: 300px;
    background-color: rgba(200, 235, 207, 0.5);
  }

  .boxTitle{
    margin-top: 30px;
    font-size: 30px;
  }

  .boxArea{
    margin-top: 30px;
    font-size: 20px;
    float: right;
    margin-right: 30px;
  }
</style>