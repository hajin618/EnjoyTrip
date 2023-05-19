<template>
    <b-carousel-slide>
        <template #img >
            <img
                class="d-block img-fluid w-100"
                :src="represent_imageUrl"
                alt="image slot"
                style="height:100%; object-fit:cover;"
            >
        </template>
    </b-carousel-slide>
</template>

<script>
export default {
    props: {
        image_name: String,
        image_num: Number,
        image_url: String,
        review_idx: Number,
    },
    data(){
      return{
        represent_imageUrl: '',
      }
    },
    methods:{
      getImageURL() {
        console.log(this.image_url);
        const filePath = this.image_url;
        const uploadPath = "\\upload";
        const baseURL = "http://localhost:80/upload";  // 애플리케이션의 기본 URL을 입력하세요.
        const parts = filePath.split(uploadPath);
        const extractedPath = baseURL + parts[1].replaceAll('\\', '/');
        console.log(extractedPath);
        this.represent_imageUrl = extractedPath;
      },
      changeSidoCode(){

      }
    },
    mounted(){
      this.getImageURL();
    },
}
</script>

<style>

</style>