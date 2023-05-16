<template>
  <div>
  <!-- align-content:space-evenly 이거 하면 행간 띄운다는데 안 먹음 -->
    <div style="display: flex; flex-direction: row; flex-wrap: wrap; justify-content: space-evenly; margin-bottom:30px;">
      <div class="box">
        <div class="imageBox">
          <img width="100%" height="100%" src="../../../assets/img/mainPageImg.png" alt="">
        </div>
        <div class="boxTitle">제목</div>
        <div class="boxArea">지역</div>
      </div>
      
      <div class="box">
        <div class="imageBox">
          <img width="100%" height="100%" src="../../../assets/img/mainPageImg.png" alt="">
        </div>
        <div class="boxTitle">제목</div>
        <div class="boxArea">지역</div>        
      </div>

      <div class="box">
        <div class="imageBox">
          <img width="100%" height="100%" src="../../../assets/img/mainPageImg.png" alt="">
        </div>
        <div class="boxTitle">제목</div>
        <div class="boxArea">지역</div>        
      </div>

      <div class="box">
        <div class="imageBox">
          <img width="100%" height="100%" src="../../../assets/img/mainPageImg.png" alt="">
        </div>
        <div class="boxTitle">제목</div>
        <div class="boxArea">지역</div>        
      </div>

      <div class="box">
        <div class="imageBox">
          <img width="100%" height="100%" src="../../../assets/img/mainPageImg.png" alt="">
        </div>
        <div class="boxTitle">제목</div>
        <div class="boxArea">지역</div>        
      </div>

      <div class="box">
        <div class="imageBox">
          <img width="100%" height="100%" src="../../../assets/img/mainPageImg.png" alt="">
        </div>
        <div class="boxTitle">제목</div>
        <div class="boxArea">지역</div>        
      </div>

      <div class="box">
        <div class="imageBox">
          <img width="100%" height="100%" src="../../../assets/img/mainPageImg.png" alt="">
        </div>
        <div class="boxTitle">제목</div>
        <div class="boxArea">지역</div>        
      </div>

      <div class="box">
        <div class="imageBox">
          <img width="100%" height="100%" src="../../../assets/img/mainPageImg.png" alt="">
        </div>
        <div class="boxTitle">제목</div>
        <div class="boxArea">지역</div>        
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "UserLikeListView"
  
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
