<template>
  <div>
    <div class = "title">
      <h2>여행 후기 수정</h2>
    </div>

    <div class="InfoArea">
        <div class="postTitleBox">
            <input class="titleBox" v-model="review.review_title" id="title" autocomplete="off" type="text" required>
            <!-- <select class="areaSelectbar" v-model="selectedArea">
              <option v-for="(item, index) in selectList" :key="index" :value="item.value">
                {{ item.name }}
              </option>
            </select> -->
            <select class="areaSelectbar" v-model="review.sido_code">
                <option value=0 selected>시/도 재선택</option>
                <option v-for="(item, index) in selectList" :key="index" :value="item.sido_code">
                {{ item.sido_name }}
                </option>
            </select>

            <select class="typeSelectbar" v-model="review.review_type">
                <option value="" selected>여행 타입 재선택</option>
                <option v-for="(item, index) in selectType" :key="index" :value="item.value">
                {{ item.name }}
                </option>
            </select>
          </div>

        <div class="postContentArea">
            <input class="postContentBox" v-model="review.review_content" id="content" autocomplete="off" type="text" required>
        </div>
        
        <div class="imageArea">
          <div>
            <input class="imageBtn" type="file" multiple @change="handleFileUpload">
          </div>

          <div v-if="review_image.length">
            <div v-for="image in review_image" :key="image.image_num">
              <span>{{image.image_name}}</span>
              <button v-on:click.prevent="addDeleteList(image.image_num); review_image = review_image.filter(item => item.image_num !== image.image_num)">삭제하기</button>
            </div>

          </div>


            <!-- <button class="imageBtn">이미지 업로드</button>

            <div class="imageTextArea">
                이미지 url 생성 
                이미지 삭제도 
            </div> -->
        </div>

        
    </div>


    <div class="buttonArea">
        <button v-on:click.prevent="confirm" class="registerBtn">수정</button>
        <button v-on:click.prevent="cancel" class="cancelBtn">취소</button>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import Swal from "sweetalert2";

export default {
    name: "ReviewModifyView",
    components: {
      
    },
    data(){
      return{
        review:[],
        review_image:[],
        selectedArea: 0,
        selectList: [],
        selectedType: '',
        selectType: [
                        {name: '아이', value: "아이"},
                        {name: '어른', value: "어른"},
                    ],
        selectedFiles:[],
        deleteFiles:[],
      }
    },
    methods:{
      cancel(){
        this.$router.push({ name: "reviewBoardView" });
      },
      handleFileUpload(event) {
        this.selectedFiles = Array.from(event.target.files);
      },
      confirm(){
        if(this.review.review_title == ''){
          alert("제목을 입력해주세요!");
        }
        else if(this.review.review_content == ''){
          alert("내용을 입력해주세요!");
        }
        else if(this.review.sido_code == ''){
          alert("지역을 선택해주세요!");
        }
        else if(this.review.review_type == ''){
          alert("여행 타입을 선택해주세요!");
        }
        else{
          console.log(this.selectedFiles);
          this.edit();
        }
      },
      edit(){
        console.log(this.review);
        http.put(`/review/${this.review.review_idx}`, {
          user_idx: this.review.user_idx,
          sido_code: this.review.sido_code,
          review_title: this.review.review_title,
          review_content: this.review.review_content,
          review_type: this.review.review_type,
          // review_image: this.selectFiles
        }).then((response) => { 
            console.log(response.status);
            if(response.status == 200){

              // 삭제 로직
              // 사진 삭제한거랑 새로 넣는거랑 생각해야해
              // 서버에서 삭제할 필요는 없으니까 그냥 디비만 관리한다 생각하자
              http.post(`/fileDelete`, this.deleteFiles)
                .then((response) =>{
                  if(response.status == 200){
                    console.log("사진 삭제 성공");
                  }
                })


              const formData = new FormData();
              formData.append('review_idx', this.review.review_idx);
              for (let i = 0; i < this.selectedFiles.length; i++) {
                formData.append('upfile', this.selectedFiles[i]);
              }

              if(this.selectedFiles.length != 0){
                http.post(`/fileUpload`, formData, {
                  headers: {
                    'Content-Type': 'multipart/form-data' // 멀티파트 요청을 위해 설정
                  }
                })
                .then((response) => {
                  console.log(response.status);
                  if(response.status == 200){
                    Swal.fire(
                      '리뷰 수정 성공!',
                      '리뷰 페이지로 이동합니다!',
                      'success'
                    )
                  }
                  else{
                    console.log("사진 수정 실패");
                  }
                })
              }
            }
            else{
              Swal.fire({
                icon: 'error',
                title: '수정 실패!',
                text: '서버 오류입니다!',
              })
            }
            this.$router.push({ name: "reviewBoardView" });
          });
      },
      addDeleteList(imageNum){
        this.deleteFiles.push(imageNum);
      }
    },
    created(){
      http.get(`/review/${this.$route.params.review_idx}`).then(({ data }) => {
            console.log(data);
          this.review = data;
          this.review_image = this.review.review_image;
      });
      http.get(`/sido`).then(({ data }) => {
        console.log(data.sidoList);
        this.selectList = data.sidoList;
      });
    }
}
</script>

<style scoped>
  .title{
    margin-top : 70px;
    margin-bottom: 50px;
    text-align : center;
  }

  .InfoArea{
    width: 1500px;
    height: 530px;
    margin-left: 220px;

    background: rgba(200, 235, 207, 0.4);
    border-radius: 20px;
  }

  .buttonArea{
    margin-top: 50px;
    margin-left: 870px;
    margin-bottom: 30px;
  }

    .registerBtn{
    margin-right: 15px;
    width: 80px;
    height: 35px;
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .cancelBtn{
    margin-right: 175px;
    width: 80px;
    height: 35px;
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .postTitleBox{
    padding-left: 285px;
    padding-top: 30px;
    display: flex;
    flex-direction:row;
    text-align: center;
  }

  .titleBox{
    width: 570px;
    height: 40px;
    background-color: #D9D9D9;
    background-color: #D9D9D9;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
    line-height : 40px;
  }

  .areaSelectbar{
    width: 220px;
    height: 37px;
    margin-left: 20px;
    background-color: #D9D9D9;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
    line-height : 37px;
  }

  .typeSelectbar{
    width: 110px;
    height: 37px;
    margin-left: 20px;
    background-color: #D9D9D9;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
    line-height : 37px;
  }

    .postContentArea{
    margin-left: 280px;
    margin-top: 30px;
    }

    .postContentBox{
    width: 950px;
    height: 230px;
    background-color: #D9D9D9;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 20px / 20px;
    line-height : 280px;
  }

  .imageBtn{
    margin-left: 280px;
    width: 180px;
    height: 40px;
    background-color: #D9D9D9;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .imageArea{
    margin-top: 30px;
    display: flex;
  }

  .imageTextArea{
    margin-left: 50px;
    width: 720px;
    height: 120px;
    background-color: #D9D9D9;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }
</style>