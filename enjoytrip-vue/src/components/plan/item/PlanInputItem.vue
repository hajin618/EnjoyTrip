<template lang="">
    <div>
        <div class="Div">
            <div class="mapDiv">
                지도
            </div>

            <div class="horVerDiv">
                <div class="horDiv">
                    <div class="planTitleDiv"> 
                        제목
                    </div>
                    <div class="planChildBtnDiv">
                        아이
                    </div>
                </div>

                <div class="verDiv">
                    순서 재배치
                    <div class="textDiv"></div>
                    <div class="orderDiv">
                        <div class="miniContent">여행지1</div>
                        <div class="miniContent">여행지2</div>
                        <div class="miniContent">여행지3</div>
                        <div class="miniContent">여행지4</div>
                        <div class="miniContent">여행지5</div>
                        <div class="miniContent">여행지6</div>
                        <div class="miniContent">재배치 버튼</div>
                    </div>
                    <div class="orderBtnDiv">
                    </div>
                </div>
            </div>   
            
               
        </div>

        <div class="contentDiv">내용
        </div>
        
        <div class="ButtonDiv">
            <button
            v-on:click="modifyPlan"
            class="Button"
            >등록</button>
            <button
            v-on:click="removePlan"
            class="Button"
            >취소</button>
        </div>   
    </div>
</template>
<script>
export default {
    
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
.mapDiv{
    background-color:#e8ece8d1;
    width: 75%;
  
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
    width: 100%;
    height: 40px;
    background-color:#e8ece8d1;
    border-radius: 20px / 20px;
}
.planChildBtnDiv{
    margin-left: 70px;
    width: 20%;
    height: 40px;
    background-color:#e8ece8d1;
    border-radius: 20px / 20px;
}
.text{
    width: 100%;
}
.horVerDiv{
    display: flex; 
    flex-direction:column;
    width: 70%;
}
.verDiv{
    flex: none;
    text-align: center;
    margin-left: 100px;
    margin-top: 20px;
}
.miniContent{
    width: 97%;
    height: 40px;
    background-color:#e8ece8d1;
    border-radius: 20px / 20px;
    margin-top: 20px;
}

.contentDiv{
    background-color:#e8ece8d1;
    width: 80%;
    height: 100px;
    border-radius: 20px / 20px;
    margin-left: 200px;
    margin-bottom: 50px;
}
.ButtonDiv{
    text-align: center;
    margin-bottom: 50px;
}
.Button{
    border-color:#98c2a182;
    background-color : #98c2a182;
    border-radius: 10px;
    width: 80px;
    height: 35px;
    margin-right: 5px;
  }
</style>