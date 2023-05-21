<template lang="">
    <div>
        <div class = "title">
          <h2>여행 계획 등록</h2>
        </div>

        <div class="Div">
            <div class="mapDiv">
                <kakao-map></kakao-map>
            </div>

            <div class="horVerDiv">
                <div class="horDiv">
                    <input class="planTitleDiv" v-model="plan_title" id="title" autocomplete="off" type="text" placeholder="제목을 입력해주세요." >
                        
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
                <input v-model="plan_content" id="content" autocomplete="off" type="text" placeholder="내용을 입력해주세요." required>
            </div>

            <div class="ButtonDiv">
                <button v-on:click="confirm" class="Button">등록</button>
                <button v-on:click="removePlan" class="Button">취소</button>
            </div>   
        </div>
    </div>
</template>

<script>
import http from "@/api/http";
import KakaoMap from "@/components/layout/KakaoMap.vue";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
    name: "PlanRegister",
    components: {
        KakaoMap,
    },
    data(){
        return{
            plan:{
                plan_title: "",
                plan_content: "",
                plan_type: "",

            },
            // title: '',
            // content: '',
            // selectedType: '',
            selectType: [
                        {name: '아이', value: "아이"},
                        {name: '어른', value: "어른"},
                        ],
            attractions: [],        // 저장된 어른 여행지 저장할 배열 : kakao 지도에 넘길거임
            childAttractions: [],   // 저장된 어린이 여행지 저장할 배열 : kakao 지도에 넘길거임
            // 1 ~ 8000 대면 어린이 여행지, 나머지는 어른 여행지로
        }
    },
    created(){
        http.get(`/plan/${this.$route.params.plan_idx}`).then(({data}) => {
            this.plan = data;
            console.log(data.plan);
            console.log(data.planDetail);
        })
    },
    methods:{
        confirm(){
            if(this.title == ''){
                alert("제목을 입력해주세요!");
            }
            else if(this.content == ''){
                alert("내용을 입력해주세요!");
            }
            else if(this.selectedType == ''){
                alert("여행 타입을 선택해주세요!");
            }
            else{
                this.registPlan();
            }
        },
        registPlan(){
            // 디비 등록
            // 사용자 정보는 userInfo에 담겨있음
        },
        removePlan(){
            // 검색에서 들어올때 등록했던 녀석 삭제하고 라우팅
            this.$router.push({ name: "PlanView" });
        }
    },
    computed: {
      ...mapState(userStore, ["userInfo"]),
    },
}
</script>
<style scoped>
  .title{
      padding-top : 40px;
      padding-bottom: 40px;
    }
  h2{
      text-align : center;
    }
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
</style>