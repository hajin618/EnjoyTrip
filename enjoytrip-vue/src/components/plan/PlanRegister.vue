<template lang="">
    <div id="whole">
        <div class = "title">
          <h2>여행 계획 등록</h2>
        </div>

        <div class="Div">
            <div class="mapDiv" id="mapDiv">
                <kakao-map :attractions="attractions" :childAttractions="childAttractions"></kakao-map>
            </div>

            <div class="horVerDiv">
                <div class="horDiv">
                    <input class="planTitleDiv" v-model="plan.plan_title" id="title" autocomplete="off" type="text" placeholder="제목을 입력해주세요." >
                        
                <select class="planChildBtnDiv" v-model="plan.plan_type">
                    <option value='' selected>여행 타입 선택</option>
                    <option v-for="(item, index) in selectType" :key="index" :value="item.value">
                    {{ item.name }}
                    </option>
                </select>
                </div>

                <div class="verDiv">
                    <div class="textDiv">
                        <h5>순서 배치하기</h5>
                    </div>
                    <div class="orderDiv">
                        <draggable v-model="listForOrder">
                            <div v-for="item in listForOrder" :key="item.attraction_idx">
                                {{item.attraction_name}}
                            </div>
                        </draggable>
                    </div>
                </div>
            </div>   
        </div>

        <div class="bottomBox">
            <div class="contentDiv">
                <input v-model="plan.plan_content" id="content" autocomplete="off" type="text" placeholder="내용을 입력해주세요." required>
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
import draggable from 'vuedraggable';
import html2canvas from 'html2canvas';

const userStore = "userStore";

export default {
    name: "PlanRegister",
    components: {
        KakaoMap,
        draggable,
    },
    data(){
        return{
            plan:{
                plan_title: '',
                plan_content: '',
                plan_type: '',

            },
            // plan_title: '',
            // plan_content: '',
            // plan_type: '',

            selectType: [
                        {name: '아이', value: "아이"},
                        {name: '어른', value: "어른"},
                        ],
            planDetails : [],
            attractions: [],        // 저장된 어른 여행지 저장할 배열 : kakao 지도에 넘길거임
            childAttractions: [],   // 저장된 어린이 여행지 저장할 배열 : kakao 지도에 넘길거임
                                    // 1 ~ 8000 대면 어린이 여행지, 나머지는 어른 여행지로
            
            listForOrder : [],
        }
    },

    created(){
        http.get(`/plan/${this.$route.params.plan_idx}`).then(({data}) => {
            this.plan = data;
            //console.log(data.plan);
            //console.log(this.plan);
        }),

        http.get(`/planDetail/${this.$route.params.plan_idx}`).then(({ data }) => {
            this.planDetails = data;
            console.log("planDetail length : " + this.planDetails.length);
            for(var i=0; i<this.planDetails.length; i++){
                
                let contentId = this.planDetails[i].content_id;
                //console.log("content_id ::::: " + contentId);

                // 어린이 여행지인 경우
                if(contentId >= 1 && contentId <= 8580){
                    http.get(`/childAttraction/${contentId}`).then(({ data }) => {
                        //console.log("어린이 여행지 : " + data);
                        this.childAttractions.push(data);
                        this.listForOrder.push({
                            attraction_name : data.attraction_name, 
                            attraction_idx : data.attraction_idx,
                            attraction_type : "어린이",
                            });
                    })
                }
                // 어른 여행지인 경우
                else{
                    http.get(`/attraction/${contentId}`).then(({ data }) => {
                        //console.log("어른 여행지 : " + data);
                        this.attractions.push(data);
                        this.listForOrder.push({
                            attraction_name : data.title, 
                            attraction_idx : data.content_id,
                            attraction_type : "어른",
                            });
                    })
                }
            }

            //console.log("child attractiosn : " + this.childAttractions);
            //console.log("attractions : " + this.attractions);
        })


    },
    methods:{
        confirm(){
            if(this.plan_title == ''){
                alert("제목을 입력해주세요!");
            }
            else if(this.plan_content == ''){
                alert("내용을 입력해주세요!");
            }
            else if(this.plan_type == ''){
                alert("여행 타입을 선택해주세요!");
            }
            else{
                this.captureScreen();
                this.registPlan();
                
            }
        },
        captureScreen(){
            // mapDiv 캡쳐할거임
            html2canvas(document.querySelector("#mapDiv")).then(canvas => {
                // 캔버스를 이미지 데이터 URL로 변환

                const imageUrl = canvas.toDataURL('image/png'); // 또는 'image/png'으로 설정
  
                // 데이터 URL을 Blob 형식으로 변환
                const byteCharacters = atob(imageUrl.split(',')[1]);
                const byteArrays = new Uint8Array(byteCharacters.length);
                for (let i = 0; i < byteCharacters.length; i++) {
                    byteArrays[i] = byteCharacters.charCodeAt(i);
                }
                const blob = new Blob([byteArrays], { type: 'image/png' }); // 또는 'image/png'으로 설정
                
                // Blob을 FormData로 변환
                const formData = new FormData();
                // formData.append('image', blob, 'image.jpg');
                formData.append('plan_idx', this.plan.plan_idx);
                formData.append('upfile', blob);

                http.post(`/plan/fileUpload`, formData, {
                headers: {
                  'Content-Type': 'multipart/form-data' // 멀티파트 요청을 위해 설정
                }
                })
                .then((response) => {
                    console.log(response.status);
                    if(response.status == 200){
                    alert("사진 또한 등록 성공!!");
                    this.$router.push({ name: "planList" });
                    }
                    else{
                    alert("사진 등록 실패!!");
                    }
                })
            });
        },
        
        registPlan(){
            /*
            plan은 이미 생성되어 있음,
            update를 통해 plan_title, plan_content, plan_type만 수정하기
            */
           http.put(`/plan/${this.plan.plan_idx}`, {
            plan_idx : this.plan.plan_idx,
            plan_title : this.plan.plan_title,
            plan_content : this.plan.plan_content,
            plan_type : this.plan.plan_type,
           }).then((response) => {
            if(response.status == 200){
                alert("등록 성공");

                /*
                현재 plan_idx로 등록되어 있는 모든 detail 삭제하고
                listForOrder 순서대로 집어넣기
                */
               http.delete(`/planDetail/${this.plan.plan_idx}`)
               .then((response) => {
                if(response.status == 200){
                    console.log("detail 삭제 성공");

                    /*
                    기존 plan_detail 삭제했으면 listForOrder 순서대로 planDetail 생성하기
                    */
                   let idx = 1;
                   for(var i=0; i<this.listForOrder.length; i++){
                    http.post(`/planDetail`, {
                        plan_idx : this.plan.plan_idx,
                        content_id : this.listForOrder[i].attraction_idx,
                        detail_order : idx
                    }).then((response) =>{
                        if(response.status == 200){
                            console.log("plan detail 디비 들어감 !");
                        }
                    })
                    idx += 1;
                   }
                }
               })
            }
           })

            
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
    margin-top : 10px;
    margin-bottom: 20px;
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