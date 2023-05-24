<template>
  <div>
    <div class="mainContainer" style="width: 1000px; height:800px; margin: 0 auto; display: flex; flex-direction:column; justify-content: center;">
    <div class = "title">
      <h2>여행 후기 수정</h2>
    </div>
    <div class="top-container" style="display:flex; flex-direction:row;">
      
      <div class="right-container" style="margin-left:20px; display:flex; flex-direction:column;width:100%;justify-content: space-evenly;">
       
        <input class="titleBox" v-model="review.review_title" id="title" autocomplete="off" type="text" required>

        <div style="display: flex;
    flex-direction: row;
    justify-content: space-evenly;">
            <select class="areaSelectbar col-6" v-model="review.sido_code">
                <option value=0 selected>시/도 재선택</option>
                <option v-for="(item, index) in selectList" :key="index" :value="item.sido_code">
                {{ item.sido_name }}
                </option>
            </select>
            <select class="typeSelectbar col-6" v-model="review.review_type">
                <option value="" selected>여행 타입 재선택</option>
                <option v-for="(item, index) in selectType" :key="index" :value="item.value">
                {{ item.name }}
                </option>
            </select>
        </div>

        <textarea class="postContentBox" v-model="review.review_content" id="content" type="textarea" rows="10" placeholder="내용을 입력해주세요!" required></textarea>
        
        <div class="imageArea" style="padding-top: 10px; text-align:right;">
          <div>
            <input class="imageBtn" type="file" multiple @change="handleFileUpload">
          </div>

          <div v-if="review_image.length">
            <div v-for="image in review_image" :key="image.image_num" style="margin-bottom:10px;">
              <span>{{image.image_name}}</span>
              <button style="margin-left:10px; color: #ffffff; width:70px; border-radius:10px / 10px; background-color: #e98585; border: 1px solid rgba(213, 120, 120, .2);" v-on:click.prevent="addDeleteList(image.image_num); review_image = review_image.filter(item => item.image_num !== image.image_num)">삭제하기</button>
            </div>

          </div>
        </div>

      </div>
    </div>


    <div class="buttonBox" style="margin-top:50px" >
      <span style="margin-left:460px;">
        <button v-on:click.prevent="moveList" class="listBtn">목록</button>
      </span>
      
      <span v-if="review.userDto.user_id === user_id" class="editDeleteBox">
        <button v-on:click.prevent="edit" style="margin-left:280px" class="editBtn">수정</button>
        <button v-on:click.prevent="del" class="deleteBtn">취소</button>
      </span>
    </div>    
      
    </div>

    <div class="commentContainer">
      <div style="text-align: center;" class="commentBox">
        <h3>댓글 관리</h3>

        <div class="commentItem" v-for="comment in comments" :key="comment.comment_idx">
          <div class="commentUserName">{{comment.user_name}}</div>
          <div class="commentDate">{{comment.review_comment_create}}</div>
          <div class="commentContent">{{comment.review_comment_content}}</div>
          <button v-on:click.prevent="deleteComment(comment.comment_idx)" class="deleteButton">삭제</button>
        </div>
      </div>
    </div>


  </div>
</template>

<script>
import http from "@/api/http";
import Swal from "sweetalert2";
import { mapState } from "vuex";

export default {
  name: "ReviewModifyView",
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
        comments: [],
      }
    },
  computed: {
    ...mapState({
      user_idx: state => state.userStore.userInfo.user_idx,
      user_name: state => state.userStore.userInfo.user_name,
      user_id: state => state.userStore.userInfo.user_id,
      user_email: state => state.userStore.userInfo.user_email,
    }),
  },
  methods:{
    moveList(){
      this.$router.push({ name: "reviewBoardView" });
    },
    deleteComment(comment_idx){
      http.post(`review/${this.review.review_idx}/comment/${comment_idx}`)
      .then((response) => {
        if(response.status == 200){
          const commentIndex = this.comments.findIndex(comment => comment.comment_idx === comment_idx);
          this.comments.splice(commentIndex, 1);

          console.log(this.review.review_idx);
          console.log(comment_idx);
          Swal.fire(
            '댓글 삭제 성공!',
            '안전하게 삭제되었습니다!',
            'success'
          )
        }
      })
    },
      cancel(){
        this.$router.push({ name: "reviewBoardView" });
      },
      handleFileUpload(event) {
        this.selectedFiles = Array.from(event.target.files);
      },
      confirm(){
        if(this.review.review_title == ''){
          Swal.fire({
            icon: 'error',
            title: '등록 실패!',
            text: '제목은 필수입니다!',
          })
        }
        else if(this.review.review_content == ''){
          Swal.fire({
            icon: 'error',
            title: '등록 실패!',
            text: '내용은 필수입니다!',
          })
        }
        else if(this.review.sido_code == ''){
          Swal.fire({
            icon: 'error',
            title: '등록 실패!',
            text: '지역은 필수입니다!',
          })
        }
        else if(this.review.review_type == ''){
          Swal.fire({
            icon: 'error',
            title: '등록 실패!',
            text: '여행 타입은 필수입니다!',
          })
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
      },
      del(){
        this.$router.push({ name: "reviewBoardView" });
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
      http.get(`/review/${this.$route.params.review_idx}/comment`).then(({data}) => {
      console.log(data);
      this.comments = data;
    })
    }
}

</script>


<style scoped> 
  .title{
    margin-top : 70px;
    margin-bottom: 30px;
    text-align : center;
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
    /* margin-top: 40px;
    margin-bottom: 40px; */
    display: flex; 
    flex-direction:row;
  }

  .listBtn{
    width: 80px;
    height: 35px;
    background-color: #d1deec;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .listBtn:hover{
    background-color: #97b1cd;
  }

  .editBtn{
    width: 80px;
    height: 35px;
    background-color: #d1deec;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .editBtn:hover{
    background-color: #97b1cd;
  }

  .deleteBtn{
    margin-left: 20px;
    width: 80px;
    height: 35px;
    background-color: #d1deec;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .deleteBtn:hover{
    background-color: #97b1cd;
  }

  .postImageBox{
    /* width:1000px;
    height:800px; */
    width:50%;
    height:500px;
  }
  .b-carousel-inner{
    height: 100%;
    border-radius:20px;
  }

.carousel-inner{
  border-radius: 20px;
}

  b-carousel-slide{
    width:100%;
    height:100%;
    object-fit:cover;
  }
  .carousel-inner, .carousel-item {
    height: 100%;
  }
  .carousel{
    height: 100%;
  }

  [role="list"]{
    height: 100% !important;
  }

  .postImageArea{
    margin-top: 20px;
    width: 200px;
    height: 300px;
  }

  .postTitleBox{
    /* margin-left: 140px;
    margin-top: 65px; */
    display: flex;
    flex-direction:row;
    text-align: center;
  }

  .titleBox{
    /* width: 330px; */
    height: 50px; 
    background-color:rgb(118 154 189 / 20%);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px /10px;
    text-align: center;
    line-height : 50px;
  }

  .areaSelectbar{
    width: 90px;
    height: 50px;
    /* margin-left: 20px; */
    background-color:rgb(118 154 189 / 20%);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
    text-align: center;
    line-height : 50px;
  }

  .typeSelectbar{
    width: 90px;
    height: 50px;
    /* margin-left: 20px; */
    background-color:rgb(118 154 189 / 20%);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
    text-align: center;
    line-height : 50px;
  }

  .postContentBox{
    /* margin-left: 140px;
    margin-top: 30px;
    width: 550px;
    height: 280px; */
    text-align: center;
    background-color:rgb(118 154 189 / 20%);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
    line-height : 280px;
  }
  
.commentContainer {
  max-width: 1000px; /* 최대 너비 조정 */
  margin: 50px auto; /* 중앙 정렬을 위해 좌우 마진을 auto로 설정 */
}

.commentBox {
  text-align: center;
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
}

.commentBox h3 {
  margin-bottom: 20px;
}

.commentItem {
  background-color: #ffffff;
  border: 1px solid #e1e1e1;
  border-radius: 4px;
  padding: 10px;
  margin-bottom: 10px;
}

.commentUserName {
  font-weight: bold;
  margin-bottom: 5px;
}

.commentDate {
  color: #777777;
  font-size: 12px;
  margin-bottom: 5px;
}

.commentContent {
  margin-bottom: 10px;
}

.deleteButton {
  background-color: #e98585;
  color: #ffffff;
  border: none;
  border-radius: 4px;
  padding: 5px 10px;
  font-size: 12px;
  cursor: pointer;
}

.commentWriteBox {
  padding-top: 20px;
}

.commentWriteBox hr {
  border: none;
  border-top: 1px solid #e1e1e1;
  margin: 20px 0;
}

.loggedInUserName {
  font-weight: bold;
  margin-bottom: 10px;
}

textarea {
  width: 100%;
  height: 100px;
  padding: 10px;
  border: 1px solid #e1e1e1;
  border-radius: 4px;
  margin-bottom: 10px;
}

.commentButton {
  background-color: #7aab85;
  color: #ffffff;
  border: none;
  border-radius: 4px;
  padding: 10px 20px;
  font-size: 14px;
  cursor: pointer;
}
</style>