<template>
  <div>
    <div class = "title">
      <h2>여행 후기 수정</h2>
    </div>

    <div class="InfoArea">
        <div class="postTitleBox">
            <input class="titleBox" id="title" autocomplete="off" type="text" placeholder="제목을 입력해주세요." required>
            <!-- <select class="areaSelectbar" v-model="selectedArea">
              <option v-for="(item, index) in selectList" :key="index" :value="item.value">
                {{ item.name }}
              </option>
            </select> -->
            <select class="areaSelectbar" v-model="selectedArea">
                <option v-for="(item, index) in selectList" :key="index" :value="item.value">
                {{ item.name }}
                </option>
            </select>

            <select class="typeSelectbar" v-model="selectedType">
                <option v-for="(item, index) in selectType" :key="index" :value="item.value">
                {{ item.name }}
                </option>
            </select>
          </div>

        <div class="postContentArea">
            <input class="postContentBox" id="content" autocomplete="off" type="text" placeholder="내용을 입력해주세요." required>
        </div>
        
        <div class="imageArea">
            <button class="imageBtn">이미지 업로드</button>

            <div class="imageTextArea">
                <!-- 이미지 url 생성 -->
                <!-- 이미지 삭제도 -->
            </div>
        </div>

        
    </div>


    <div class="buttonArea">
        <button class="registerBtn">수정</button>
        <button class="cancelBtn">취소</button>
    </div>
  </div>
</template>

<script>
export default {
    name: "ReviewModifyView",
    components: {

    },
    data(){
        return{
        selectedArea: '',
        selectList: [{name: "시도 선택", value: ""},
                        {name: "name1", value: "a"},
                        {name: "name2", value: "b"},
                        {name: "name3", value: "c"},
                    ],
        selectedType: '',
        selectType: [{name: "선택", value: ""},
                        {name: "아이", value: "a"},
                        {name: "어른", value: "b"},
                    ],
        }
    },
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