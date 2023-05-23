<template lang="">
    <div>
        <div class = "title">
          <h2>여행 계획 수정</h2>
        </div>

        <div class="Div">
            <div class="mapDiv">
                지도
            </div>

            <div class="horVerDiv">
                <div class="horDiv">
                    <input class="planTitleDiv" v-model="title" id="title" autocomplete="off" type="text" placeholder="제목을 입력해주세요." >
                        
                <select class="planChildBtnDiv" v-model="selectedType">
                    <option value='' selected>여행 타입 선택</option>
                    <option v-for="(item, index) in selectType" :key="index" :value="item.value">
                    {{ item.name }}
                    </option>
                </select>
                </div>

                <div class="verDiv">
                    <div class="textDiv">
                        순서 재배치
                    </div>
                    <div class="orderDiv">
                        순서자리
                    </div>
                </div>
            </div>   

        </div>

        <div class="bottomBox">
            <div class="contentDiv">
                <input v-model="content" id="content" autocomplete="off" type="text" placeholder="내용을 입력해주세요." required>
            </div>

            <div class="ButtonDiv">
                <button v-on:click="confirm" class="Button">수정</button>
                <button v-on:click="cancel" class="Button">취소</button>
            </div>   
        </div>
    </div>
</template>

<script>
// import http from "@/api/http";
import Swal from "sweetalert2";

import { mapState } from "vuex";

const userStore = "userStore";

export default {
    name: "PlanModify",
    components: {
        
    },
    data(){
        return{
            title: '',
            content: '',
            selectedType: '',
            selectType: [
                        {name: '아이', value: "아이"},
                        {name: '어른', value: "어른"},
                        ],
        }
    },
    methods:{
        confirm(){
            if(this.title == ''){
                Swal.fire({
                    icon: 'error',
                    title: '계획 수정 실패!',
                    text: '제목은 빈 칸이 안됩니다!',
                })
            }
            else if(this.content == ''){
                Swal.fire({
                    icon: 'error',
                    title: '계획 수정 실패!',
                    text: '내용은 빈 칸이 안됩니다!',
                })
            }
            else if(this.selectedType == ''){
                Swal.fire({
                    icon: 'error',
                    title: '계획 수정 실패!',
                    text: '타입을 선택해주세요!',
                })
            }
            else{
                this.editPlan();
            }
        },
        editPlan(){
            // 디비 수정
        },
        cancel(){
            this.$router.push({ name: "PlanView" });
        }
    },
    computed: {
      ...mapState(userStore, ["userInfo"]),
    },
    created(){
      // 그 번호 데이터 가져오기
    },
}
</script>
<style scoped>
.Div{
    display: flex; 
    flex-direction:row;
    margin-left: 200px;
    margin-bottom: 30px;
    width : 80%;
    height: 550px;
}

.bottomBox{
    display:flex;    
    flex-direction:row;
    width:90%;
    height:300px;
}
.mapDiv{
    background-color:#e8ece8d1;
    width: 90%;
  
    margin-bottom: 30px;
}
.horDiv{
    display: flex; 
    flex-direction:row;
    margin-left: 100px;
    width: 85%;
    height: 10%;
    text-align: center;
}
.planTitleDiv{
    width: 65%;
    height: 40px;
    background-color:#e8ece8d1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 20px / 20px;
    line-height : 40px;
}
.planChildBtnDiv{
    margin-left: 35px;
    width: 25%;
    height: 40px;
    background-color:#7aab85;
    border-radius: 20px / 20px;
    color: white;
    border: 1px solid rgba(213, 120, 120, .2);
    line-height : 40px;
}
.text{
    width: 100%;
}
.horVerDiv{
    display: flex; 
    flex-direction:column;
    width: 70%;
}
.orderDiv{
    background-color: #7aab85;
    height: 405px;
    width: 97%;
    margin-bottom:40px;
}
.verDiv{
    flex: none;
    text-align: center;
    margin-left: 100px;
    margin-top:20px;
}
.miniContent{
    width: 97%;
    height: 40px;
    background-color:#e8ece8d1;
    border-radius: 20px / 20px;
    margin-top: 20px;
}

.textDiv{
    margin-bottom: 30px;
}

.contentDiv{
    background-color:#e8ece8d1;
    width: 80%;
    height: 100px;
    border-radius: 20px / 20px;
    margin-left: 200px;
}
.ButtonDiv{
    text-align: center;
}
.Button{
    width: 80px;
    height: 35px;
    background-color:rgba(122, 187, 133, 0.5);
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
    margin-bottom:25px;
  }
  .title{
      padding-top : 40px;
      padding-bottom: 40px;
    }
  h2{
      text-align : center;
    }    
</style>