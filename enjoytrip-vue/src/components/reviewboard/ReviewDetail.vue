<template>
  <div>
    <div class = "title">
      <h2>여행 후기 게시판</h2>
    </div>

    <div class="mainContainer" style="width: 1000px; height:600px; margin: 0 auto; display: flex; flex-direction:column; justify-content: center;">

    <div class="top-container" style="display:flex; flex-direction:row;">
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
      <div class="right-container" style="margin-left:20px; display:flex; flex-direction:column;width:50%;justify-content: space-evenly;">
       
        <div class="titleBox" id="title">{{review.review_title}}</div>

                 
        <div class="postContentBox" id="content">{{review.review_content}}</div>
        <div style="display: flex;
    flex-direction: row;
    justify-content: space-evenly;">
          <div class="areaSelectbar col-6">
                      {{getSidoName(review.sido_code)}}
                    </div>
          <div class="typeSelectbar col-6">
                      {{review.review_type}}
          </div>
        </div>
        
      </div>
    </div>
      

    <div class="buttonBox" style="margin-top:50px" >
      <div style="margin-left:480px;">
        <button v-on:click.prevent="moveList" class="listBtn">목록</button>
      </div>
      
      <div v-if="review.userDto.user_id === user_id" class="editDeleteBox">
        <button v-on:click.prevent="edit" style="margin-left:260px" class="editBtn">수정</button>
        <button v-on:click.prevent="del" class="deleteBtn">삭제</button>
      </div>
    </div>    
      
    </div>

    <div class="commentContainer">
      <div style="text-align: center;" class="commentBox">
        <h3>댓글</h3>

        <div class="commentItem" v-for="comment in comments" :key="comment.comment_idx">
          <div class="commentUserName">{{comment.user_name}}</div>
          <div class="commentDate">{{comment.review_comment_create}}</div>
          <div class="commentContent">{{comment.review_comment_content}}</div>
          <button v-if="comment.user_name === user_name" v-on:click.prevent="deleteComment(comment.comment_idx)" class="deleteButton">삭제</button>
        </div>
      </div>

      <div class="commentWriteBox">
        <hr>
        <div class="loggedInUserName">{{user_name}}</div>
        <textarea placeholder="댓글을 입력하세요" v-model="context"></textarea>
        <button v-on:click.prevent="commentRegister" class="commentButton">댓글달기</button>
      </div>
    </div>


  </div>
</template>

<script>
import http from "@/api/http";
import Swal from "sweetalert2";
import ReviewImageItem from "@/components/reviewboard/item/ReviewImageItem.vue";

import { mapState } from "vuex";

export default {
  name: "ReviewDetailView",
  data(){
    return{
      review:[],
      review_image:[],
      comments: [],
      context: "",
    }
  },
  components:{
    ReviewImageItem
  },
  mounted(){
    // document.getElementsByClassName("carousel-inner")[0].style.height="100%";
    // document.getElementsByClassName("carousel-inner")[0].style.borderRadius="20px";
    console.log(document.getElementsByClassName("carousel-inner")[0]);
  },
  created() {
    http.get(`/review/${this.$route.params.review_idx}`).then(({ data }) => {
           console.log(data);
        this.review = data;
        this.review_image = this.review.review_image;
    });
    http.get(`/review/${this.$route.params.review_idx}/comment`).then(({data}) => {
      console.log(data);
      this.comments = data;
    })
    
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
    del(){
      http.delete(`/review/${this.review.review_idx}`)
      .then((response) => {
        if(response.status == 200){
          Swal.fire(
            '리뷰 등록 성공!',
            '리뷰 리스트 페이지로 이동합니다!',
            'success'
          )
          this.moveList(); 
        }
        else{
          Swal.fire({
            icon: 'error',
            title: '등록 실패!',
            text: '서버 에러입니다!',
          })
          this.moveList();
        }
      })
    },
    confirm(){
      if(this.context == ''){
          Swal.fire({
            icon: 'error',
            title: '등록 실패!',
            text: '내용을 작성해주세요!!',
          })
      }
      else{
        this.commentRegister();
      }
    },
    commentRegister(){
      console.log(this.review.review_idx);
      console.log(this.user_name);
      console.log(this.user_idx);
      console.log(this.context);

      const tmpComment = {
        comment_idx: '',
        review_idx: this.review.review_idx,
        user_name: this.user_name,
        user_idx: this.user_idx,
        review_comment_content: this.context,
      };

      http.post(`/review/${this.review.review_idx}/comment`, 
        tmpComment
      )
      .then((response) => {
        console.log(response.status);
        if(response.status == 200){
          const comment_idx = response.data;
          tmpComment.comment_idx = comment_idx;
          this.comments.push(tmpComment);
          this.context = '';
          Swal.fire(
            '댓글 등록 성공!',
            '댓글은 타인을 비방하면 안됩니다!',
            'success'
          )
        }
      })
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
  },
  computed: {
    ...mapState({
      user_idx: state => state.userStore.userInfo.user_idx,
      user_name: state => state.userStore.userInfo.user_name,
      user_id: state => state.userStore.userInfo.user_id,
      user_email: state => state.userStore.userInfo.user_email,
    }),
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
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .editBtn{
    width: 80px;
    height: 35px;
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .deleteBtn{
    margin-left: 20px;
    width: 80px;
    height: 35px;
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
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
    background-color:rgba(122, 187, 133, 0.2);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px /10px;
    text-align: center;
    line-height : 50px;
  }

  .areaSelectbar{
    width: 90px;
    height: 50px;
    /* margin-left: 20px; */
    background-color:rgba(122, 187, 133, 0.2);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
    text-align: center;
    line-height : 50px;
  }

  .typeSelectbar{
    width: 90px;
    height: 50px;
    /* margin-left: 20px; */
    background-color:rgba(122, 187, 133, 0.2);
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
    background-color:rgba(122, 187, 133, 0.2);
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
  background-color: #ff5555;
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