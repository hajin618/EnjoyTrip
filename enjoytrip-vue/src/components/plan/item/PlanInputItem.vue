<template lang="">
    <div>
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
                <button v-on:click="registPlan" class="Button" v-if="this.type === 'register'">등록</button>
                <button v-on:click="editPlan" class="Button" v-else>수정</button>

                <button v-on:click="removePlan" class="Button">취소</button>
            </div>   
        </div>
    </div>
</template>
<script>
export default {
    name: "PlanInputItem",
    components:{

    },
    data(){
        return{
            title: '',
            content: '',
        }
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
    border-color:#98c2a182;
    background-color : #98c2a182;
    border-radius: 10px;
    width: 80px;
    height: 35px;
    margin-bottom:25px;
  }
</style>