<template>
  <div>
    <div class = "title">
      <h2>여행 후기 작성</h2>
    </div>

    <div class="InfoArea">
        <div class="postTitleBox">
            <input class="titleBox" v-model="title" id="title" autocomplete="off" type="text" placeholder="제목을 입력해주세요." required>
            <select class="areaSelectbar" v-model="selectedArea">
                <option value="" selected>시/도 선택</option>
                <option v-for="(item, index) in selectList" :key="index" :value="item.sido_code">
                {{ item.sido_name }}
                </option>
            </select>

            <select class="typeSelectbar" v-model="selectedType">
                <option value='' selected>여행 타입 선택</option>
                <option v-for="(item, index) in selectType" :key="index" :value="item.value">
                {{ item.name }}
                </option>
            </select>
          </div>

        <div class="postContentArea">
          <textarea name="content" id="content" class="postContentBox" v-model="content" type="textarea" rows="10" placeholder="내용을 입력해주세요!" required></textarea>
        </div>

        <div class="imageArea">
          <div>
            <input class="imageBtn" type="file" multiple @change="handleFileUpload">
          </div>
        </div>
    </div>
    
    <div class="buttonArea">
        <button v-on:click.prevent="confirm" class="registerBtn">등록</button>
        <button v-on:click.prevent="cancel" class="cancelBtn">취소</button>
    </div>
  </div>
</template>

<script>
import http from "@/api/http";
import Swal from "sweetalert2";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
    name: "ReviewRegisterView",
    components: {

    },
    data(){
        return{
        title: '',
        content: '',
        selectedArea: '',
        selectList: [],
        selectedType: '',
        selectType: [
                        {name: '아이', value: "아이"},
                        {name: '어른', value: "어른"},
                    ],
        selectedFiles: [],
        review_idx: 0,
        }
    },
    computed: {
      ...mapState(userStore, ["userInfo"]),
    },
    created(){
      http.get(`/sido`).then(({ data }) => {
        console.log(data.sidoList);
        this.selectList = data.sidoList;
      });
    },
    methods:{
      cancel(){
        this.$router.push({ name: "reviewBoardView" });
      },
      handleFileUpload(event) {
        this.selectedFiles = Array.from(event.target.files);
      },
      confirm(){
        if(this.title == ''){
          Swal.fire({
            icon: 'error',
            title: '등록 실패!',
            text: '제목은 필수입니다!',
          })
        }
        else if(this.content == ''){
          Swal.fire({
            icon: 'error',
            title: '등록 실패!',
            text: '내용은 필수입니다!',
          })
        }
        else if(this.selectedArea == ''){
          Swal.fire({
            icon: 'error',
            title: '등록 실패!',
            text: '지역은 필수입니다!',
          })
        }
        else if(this.selectedType == ''){
          Swal.fire({
            icon: 'error',
            title: '등록 실패!',
            text: '여행 타입은 필수입니다!',
          })
        }
        else if(this.selectedFiles.length == 0){
          Swal.fire({
            icon: 'error',
            title: '등록 실패!',
            text: '사진 한장은 필수입니다!',
          })
        }
        else{
          console.log(this.selectedFiles);
          this.register();
        }
      },
      register() {
        console.log(this.selectedType);
        // 데이터 실제 등록
        http.post(`/review`, {
          user_idx: this.userInfo.user_idx,
          sido_code: this.selectedArea,
          review_title: this.title,
          review_content: this.content,
          review_hit: 0,
          review_type: this.selectedType,
          // review_image: this.selectFiles
        }).then((response) => { 
            console.log(response.status);
            if(response.status == 200){
              this.review_idx = response.data;
              console.log("review_idx",response.data);
              // Swal.fire({
              //   'Alert 실행!!.',  // Alert 제목
              //   'Alert 내용이 나타나는 곳.',  // 내용
              //   'success',  // icon
              // });
              // 파일 등록하자
              // 파일 업로드 처리 로직 작성
              // this.selectedFiles를 서버로 전송하거나 필요한 작업을 수행합니다.

              const formData = new FormData();
              formData.append('review_idx', this.review_idx);
              for (let i = 0; i < this.selectedFiles.length; i++) {
                formData.append('upfile', this.selectedFiles[i]);
              }

              http.post(`/fileUpload`, formData, {
                headers: {
                  'Content-Type': 'multipart/form-data' // 멀티파트 요청을 위해 설정
                }
              })
              .then((response) => {
                console.log(response.status);
                if(response.status == 200){
                  Swal.fire(
                    '등록 성공!',
                    '리뷰 페이지로 이동합니다!',
                    'success'
                  )
                  this.$router.push({ name: "reviewBoardView" });
                }
                else{
                  Swal.fire({
                    icon: 'error',
                    title: '등록 실패!',
                    text: '서버 오류입니다! 잠시 후 다시 이용해주세요.',
                  })
                }
              })
              
            }
            else{
              Swal.fire({
                icon: 'error',
                title: '등록 실패!',
                text: '서버 오류입니다! 잠시 후 다시 이용해주세요.',
              })
            }
          });
      },
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
    /* background: rgb(0 123 255 / 7%); */
    border-radius: 20px;
  }

  .buttonArea{
    margin-left: 870px;
    margin-bottom: 30px;
  }

    .registerBtn{
    margin-right: 15px;
    width: 80px;
    height: 35px;
    background-color: #d1deec;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .cancelBtn{
    margin-right: 175px;
    width: 80px;
    height: 35px;
    background-color: #d1deec;
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
    
    background-color: white;
    border: 4px solid #007bff45;

    border-radius: 10px / 10px;
    /* line-height : 40px; */
  }

  .areaSelectbar{
    width: 220px;
    height: 37px;
    margin-left: 20px;
    background-color: white;
    border: 4px solid #007bff45;
    border-radius: 10px / 10px;
    /* line-height : 37px; */
  }

  .typeSelectbar{
    width: 110px;
    height: 37px;
    margin-left: 20px;
    background-color: white;
    border: 4px solid #007bff45;
    border-radius: 10px / 10px;
  }

    .postContentArea{
    margin-left: 280px;
    margin-top: 30px;
    }

    .postContentBox{
    width: 950px;
    height: 230px;
    background-color: white;
    border: 4px solid #007bff45;
    border-radius: 20px / 20px;
  }

  .imageBtn{
    margin-left: 280px;
    width: 950px;
    height: 40px;
    background-color: white;
    border: 4px solid #007bff45;
    border-radius: 10px / 10px;
  }

  .imageRegistBtn{
    margin-left: 120px;
    width: 200px;
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
    margin-left: 60px;
    width: 720px;
    height: 120px;
    background-color: #D9D9D9;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .itemDeleteBtn{
    float: right;
    margin-right: 10px;
    width: 50px;
    height: 25px;
    font-size: 10px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }
</style>