<template>
  <div>
    <header-navi-bar></header-navi-bar>

    <div class="searchBar">
      <select class="sidoSelect" v-model="sidoSelected" @change="getGuGun()">
        <option value="null">시/도 선택</option>
        <option v-for="(sido, index) in sidoList" :key="index" :value="sido.sido_code">
          {{ sido.sido_name }}</option>
      </select>

      <select class="gugunSelect" v-model="gugunSelected">
        <option value="null">구/군 선택</option>
        <option v-for="(gugun, index) in gugunList" :key="index" :value="gugun.gugun_code">
        {{ gugun.gugun_name }}
        </option>
      </select>

      <select class="attractionSelect" v-model="content_type_id">
        <option value="null">관광지 유형</option>
        <option value="12">관광지</option>
        <option value="14">문화시설</option>
        <option value="15">축제공연행사</option>
        <option value="25">여행코스</option>
        <option value="28">레포츠</option>
        <option value="32">숙박</option>
        <option value="38">쇼핑</option>
        <option value="39">음식점</option>

      </select>

      <input class="searchWord" v-model="searchWord" id="title" autocomplete="off" type="text" placeholder="검색어 입력" required>

      <button class="searchBtn" v-on:click="searchAtt">검색</button>
    </div>



    

    <div style="display: flex; flex-direction: row; margin-bottom: 50px;">
      <div class="findKidZone">
        <div class="findTitle">실종 아동 정보</div>
        <div class="findImageBox">
          <img width="100%" height="100%" src="../assets/img/mainPageImg.png" alt="">
        </div>

        <div class="findNameBox">
          <label>이름 : </label>
          <span>{{mChild.nm}}</span>
        </div>
        
        <div class="findAgeBox">
          <label>당시 나이 : </label>
          <span>{{mChild.age1}}</span>
        </div>

        <div class="findGenderBox">
          <label>성별 : </label>
          <span>{{mChild.sexdstnDscd}}</span>
        </div>

        <div class="findInfoBox">
          <label>인적사항 : </label>
          <span>{{mChild.etcSpfeatr}}</span>
        </div>

        <div class="findInfoBox">
          <label>실종일자 : </label>
          <span>{{mChild.detailDate1}}</span>
        </div>

      </div>

      <div class="mapZone">
        <kakao-map :attractions="attractions" :childAttractions="childAttractions"></kakao-map> 
      </div>

      <div class="rememberSpotZone">
        <div class="rememberTitle">여행지 저장</div>
        
        <div class="rememberItem" v-if="savedAttInfo.length">
            <saved-attraction-item
              v-for="item in savedAttInfo"
              :key="item.content_id"
              @deleteAtt="deleteAtt"
              v-bind="item"/>
        </div>

        <div class="rememberItem" v-if="savedChAttInfo.length">
            <saved-ch-attraction-item
              v-for="item in savedChAttInfo"
              :key="item.attraction_idx"
              @deleteChAtt="deleteChAtt"
              v-bind="item"/>
        </div>
        <button class = "goPlanBtn" v-on:click="goPlan">계획 생성하러 가기</button>
      </div>
    </div>

    <div class="searchedArea"> 
      <div class="tabBar">
        <button class="tabBarBtn" @click="selectedTab = 'attraction'" ref="attraction" style="background-color:#1F92FD54; border-top-left-radius:1rem; border-bottom-left-radius:1rem;">관광지 정보</button>
        <button class="tabBarBtn" @click="selectedTab = 'children'" ref="children" style="border-top-right-radius:1rem; border-bottom-right-radius:1rem;">어린이 놀이시설 정보</button>
      </div>
      <b-row>
        <b-col v-if="attractions.length">
          <b-table-simple hover responsive>
            <b-thead>
              <b-tr class="listTitle">
                <b-th>대표 이미지</b-th>
                <b-th>관광지명</b-th>
                <b-th>주소</b-th>
                <b-th>저장</b-th>
              </b-tr>
            </b-thead> 
            <tbody v-if="selectedTab === 'attraction'">

              <search-view-item 
                v-for="att in attractions"
                :key="att.content_id"
                @saveAtt="saveAtt"
                @openModal2="openModal2"
                v-bind="att"/>

            </tbody>
            <tbody v-if="selectedTab === 'children'">

              <search-view-item-ch
                v-for="att in childAttractions"
                :key="att.attraction_idx"
                @saveChAtt="saveChAtt"
                @openModal="openModal"
                v-bind="att"/>

            </tbody>
          </b-table-simple>
        </b-col>
      </b-row>
    </div>



    <div v-if="showModal2" class="modal">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">{{ modal2SelectedItem.title }}</h4>
          <button class="close-modal-btn" v-on:click="closeModal2">x</button>
        </div>

        <div class="modal-body">
          <div class="address">
            <strong>주소:</strong> {{ modal2SelectedItem.addr1 }}
          </div>

           <div class="category">
            <strong>설명:</strong> {{ modal2SelectedItemDesc.overview }}
          </div>
<!--
          <div class="phone-number">
            <strong>전화번호:</strong> {{ modalSelectedItem.phone_number }}
          </div>

          <div class="homepage">
            <strong>homepage:</strong>
            <a style="color: black; word-break: break-word;" :href="modalSelectedItem.homepage" target="_blank">{{ modalSelectedItem.homepage }}</a>
          </div>

          <div class="operating-time">
            <strong>운영시간:</strong> {{ modalSelectedItem.operating_time }}
          </div>

          <div class="closed-day">
            <strong>휴무일:</strong> {{ modalSelectedItem.closed_day }}
          </div>

          <div class="stroller">
            <strong>유모차 대여 여부:</strong> {{ modalSelectedItem.stroller }}
          </div>

          <div class="nursing-room">
            <strong>수유실 여부:</strong> {{ modalSelectedItem.nursing_room }}
          </div>

          <div class="admission-fee">
            <strong>입장료:</strong> {{ modalSelectedItem.admission_fee }}
          </div> -->
        </div>
      </div>
    </div>

    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">{{ modalSelectedItem.attraction_name }}</h4>
          <button class="close-modal-btn" v-on:click="closeModal">x</button>
        </div>

        <div class="modal-body">
          <div class="address">
            <strong>주소:</strong> {{ modalSelectedItem.address }}
          </div>

          <div class="category">
            <strong>카테고리:</strong> {{ modalSelectedItem.category1 }}, {{ modalSelectedItem.category2 }}
          </div>

          <div class="phone-number">
            <strong>전화번호:</strong> {{ modalSelectedItem.phone_number }}
          </div>

          <div class="homepage">
            <strong>homepage:</strong>
            <a style="color: black; word-break: break-word;" :href="modalSelectedItem.homepage" target="_blank">{{ modalSelectedItem.homepage }}</a>
          </div>

          <div class="operating-time">
            <strong>운영시간:</strong> {{ modalSelectedItem.operating_time }}
          </div>

          <div class="closed-day">
            <strong>휴무일:</strong> {{ modalSelectedItem.closed_day }}
          </div>

          <div class="stroller">
            <strong>유모차 대여 여부:</strong> {{ modalSelectedItem.stroller }}
          </div>

          <div class="nursing-room">
            <strong>수유실 여부:</strong> {{ modalSelectedItem.nursing_room }}
          </div>

          <div class="admission-fee">
            <strong>입장료:</strong> {{ modalSelectedItem.admission_fee }}
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
import Swal from "sweetalert2";

import HeaderNaviBar from "../components/layout/HeaderNaviBar.vue"
import SearchViewItem from "../components/layout/SearchViewItem.vue"
import KakaoMap from "@/components/layout/KakaoMap.vue";
import SavedAttractionItem from "@/components/layout/SavedAttractionItem.vue";
import SearchViewItemCh from "@/components/layout/SearchViewItem-Ch.vue";
import SavedChAttractionItem from "@/components/layout/SavedChAttractionItem.vue";

const userStore = "userStore";

export default {
  name: "SearchView",
  components: {
    HeaderNaviBar,
    SearchViewItem,
    KakaoMap,
    SavedAttractionItem,
    SearchViewItemCh,
    SavedChAttractionItem
  },
  data(){
    return{
      showModal: false,     // 어린이 모달 열림/닫힘 상태
      showModal2: false,    // 어른 모달 열림/닫힘 상태
      modalSelectedItem: null,   // 어린이 모달 선택한 항목 데이터
      modal2SelectedItem: null,  // 어른 모달 선택한 항목 데이터
      modal2SelectedItemDesc: null, // 어른 모달 선택한 항목 데이터 description
      searchWord: null,
      sidoSelected: null,
      sidoList: [],
      gugunSelected: null,
      gugunList: [],
      content_type_id: null,
      attractions : [],
      childAttractions : [],
      savedAtt : [],          // 저장한 여행지 번호 저장
      savedAttInfo : [],      // 저장한 여행지 번호로 조회한 정보 저장
      savedChAtt : [],        // 저장한 어린이 여행지 번호 저장
      savedChAttInfo : [],    // 저장한 어린이 여행지 번호로 조회한 정보 저장
      plan_idx: 0,
      mChild: [],
      selectedTab: 'attraction' // 초기 선택 탭 설정

    }
  },
  computed: {
      ...mapState(userStore, ["userInfo"]),
    },
  created(){
    http.get(`/sido`).then(({ data }) => {
      //console.log(data.sidoList);
      this.sidoList = data.sidoList;
    });

    http.get(`/mChild/2`).then(({ data }) => {
      this.mChild = data;
      // console.log(data);
    })
  },
  // watch: {
  //   // 저장한 여행지 정보 변할 때 마다 수행
  //   savedAtt(){
  //     // this.savedAttInfo = [];
  //     this.savedAtt.forEach(item =>{
  //       http.get(`/attraction/${item}`).then(({ data }) =>{
  //         console.log(data);
  //         this.savedAttInfo.push(data);
          
  //       })
  //     });
  //     console.log("savedAttInfo ::: "+this.savedAttInfo);
  //   }
  // },
  watch: {
      selectedTab(newTab) {
        if (newTab === 'attraction') {
          this.$nextTick(() => {
            this.$refs.attraction.style.backgroundColor = "#1F92FD54"; 
            this.$refs.children.style.backgroundColor = "#007BFF14";
          });
        }
        else if(newTab === 'children'){
          this.$nextTick(() => {
            this.$refs.attraction.style.backgroundColor = "#007BFF14";
            this.$refs.children.style.backgroundColor = "#1F92FD54";
          });
        }
      }
    },
  methods: {
    getGuGun(){
      // console.log("시도 : "+this.sidoSelected);
      http.get(`/${this.sidoSelected}/gugun`).then(({data}) => {
        //console.log(data.gugunList);
        this.gugunList = data.gugunList;
      })
    },

    searchAtt(){
      // console.log("시도 : "+this.sidoSelected);
      // console.log("구군 : "+this.gugunSelected);
      // console.log("유형 : "+this.content_type_id)
      // console.log("단어 : "+this.searchWord);
      http.post(`/attraction`, {
        sido_code : this.sidoSelected,
        gugun_code : this.gugunSelected,
        content_type_id : this.content_type_id,
        searchWord : this.searchWord,
      })
      .then(({ data }) => {
        // console.log(data);
        this.attractions = data;
        //console.log(this.attractions);
      });

      http.post(`/childAttraction`, {
        sido_code : this.sidoSelected,
        gugun_code : this.gugunSelected,
        content_type_id : this.content_type_id,
        searchWord : this.searchWord,
      })
      .then(({ data }) => {
        //console.log("child Attraction !! " + data);
        this.childAttractions = data;
        //console.log(this.childAttractions);
      });
      
    },

    saveAtt(value){
      // 중복값 확인
      if(!this.savedAtt.includes(value)){
        this.savedAtt.push(value);

        http.get(`/attraction/${value}`).then(({ data }) =>{
          // console.log("attraction title ::: " + data.title);
          this.savedAttInfo.push(data);
        });
        
        //console.log("savedAttInfo length ::: "+this.savedAttInfo.length);
      }
      // console.log(this.savedAtt);
      // console.log("savedAttInfo length ::: "+this.savedAttInfo.length);
    },

    
    saveChAtt(value){
      // console.log(value);
      if(!this.savedChAtt.includes(value)){
        this.savedChAtt.push(value);

        http.get(`/childAttraction/${value}`).then(({ data }) =>{
          this.savedChAttInfo.push(data);
        });
      }
    },

    deleteAtt(value){
      // 삭제할 content_id 들어오면 savedAtt, savedAttInfo에서 삭제하기
      const index = this.savedAtt.indexOf(value);
      if(index !== -1){
        // console.log(index);
        this.savedAtt.splice(index, 1);
      }
      else{
        console.log("no value !!");
      }

      console.log("savedAtt : " + this.savedAtt);
      const index2 = this.savedAttInfo.findIndex(item => item.content_id === value);
      if(index !== -1){
        this.savedAttInfo.splice(index2, 1);
      }
    },

    deleteChAtt(value){
      const index=this.savedChAtt.indexOf(value);
      if(index !== -1){
        this.savedChAtt.splice(index, 1);
      }
      else{
        console.log("no value !!");
      }

      const index2 = this.savedChAttInfo.findIndex(item => item.attraction_idx === value);
      if(index !== -1){
        this.savedChAttInfo.splice(index2, 1);
      }
    },
    openModal2(content_id){
      this.showModal2 = true;

      for(var i=0; i<this.attractions.length; i++){
        if(this.attractions[i].content_id == content_id){
          this.modal2SelectedItem = this.attractions[i];
          //console.log(this.modal2SelectedItem);
        }
      }

      http.get(`/attractionDesc/${content_id}`).then(({ data }) => {
        this.modal2SelectedItemDesc = data;
        // console.log(data);
      })
    },

    openModal(attraction_idx){
      this.showModal = true;

      for(var i=0; i<this.childAttractions.length; i++){
        if(this.childAttractions[i].attraction_idx == attraction_idx){
          this.modalSelectedItem  = this.childAttractions[i];
        }
      }
    },
    
    closeModal(){
      this.showModal = false;
    },

    closeModal2(){
      this.showModal2 = false;
    },

    goPlan(){
      if(this.savedAttInfo.length == 0 && this.savedChAttInfo.length == 0){
        Swal.fire({
          icon: 'error',
          title: '여행지 등록 실패!!',
          text: '여행지를 하나 이상 선택해주세요',
        })
      }
      else{
        // console.log(this.savedAtt);
        // console.log(this.savedChAtt);
        
        // db에 넣자ㅏ

        // 새로운 계획 생성하기
        http.post(`/plan`, {
          user_idx : this.userInfo.user_idx,
        }).then((response) => {
          if(response.status == 200){
            this.plan_idx = response.data;

            // plan idx 얻어왔으면 이걸로 plan detail에 데이터 넣자
            //console.log(this.plan_idx);
            // 일단 순서는 넣은 순서대로
            let idx = 1;
            for(let i=0; i<this.savedAtt.length; i++){
              http.post(`/planDetail`, {
                plan_idx : this.plan_idx,
                content_id : this.savedAtt[i],
                detail_order : idx
              }).then((response) =>{
                if(response.status == 200){
                  // console.log("디비 들어감");
                }
              })

              idx += 1;
            }

            for(let j=0; j<this.savedChAtt.length; j++){
              http.post(`/planDetail`, {
                plan_idx : this.plan_idx,
                content_id : this.savedChAtt[j],
                detail_order : idx
              }).then((response) =>{
                if(response.status == 200){
                  // console.log("디비 들어감!");
                }
              })

              idx += 1;
            }

            // 페이지 이동하자
            this.$router.replace({ 
              name: "planRegister",
              params: {plan_idx : this.plan_idx},
            });
        }
      })



      }
    }
  }
}
</script>

<style scoped>
  .tabBar{
    margin-bottom: 20px;
    align-content: center;
    display: flex;
    justify-content: center;
  }
  .tabBarBtn{
    height: 40px;
    color: black;
    width: 240px;
    background-color: #007BFF14;
    border: 1px solid rgba(213, 120, 120, .2);
  }

  .title{
    margin-top : 70px;
    text-align : center;
  }

  .searchBar{
    margin-left: 70px;
    margin-bottom: 40px;
    padding-top: 50px;
    display: flex;
    flex-direction:row;
    text-align: center;
  }

  .sidoSelect, .gugunSelect{
    width: 300px;
    height: 37px;
    margin-left: 70px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
    line-height : 37px;
    padding-left : 10px;
  }

  .attractionSelect{
    width: 300px;
    height: 37px;
    margin-left: 70px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
    line-height : 37px;
    padding-left : 10px;
  }

  .searchWord{
    width: 400px;
    height: 37px;
    margin-left: 70px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
    line-height : 37px;
    padding-left : 10px;
  }

  .searchBtn{
    margin-left: 70px;
    width: 100px;
    height: 37px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .findKidZone{
    margin-left: 20px;
    position: fixed;
    left: 30px;
    width: 350px;
    height: 600px;
    background-color: #cfcfcf80;
    border: 1px solid rgba(200, 235, 207, 0.5);
    border-radius: 30px / 30px;
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
    margin-left: 50px;
    font-size: 20px;
  }

  .findAgeBox{
    margin-left: 50px;
    font-size: 20px;
  }

  .findGenderBox{
    margin-left: 50px;
    font-size: 20px;
  }

  .findInfoBox{
    margin-left: 50px;
    font-size: 20px;
    margin-right: 30px;
  }

  .mapZone{
    margin-left: 23%;
    margin-right: 40px;
    width: 1000px;
    height: 500px;
    background-color: rgb(200, 235, 207);
    border-radius: 10px / 10px;
    text-align: center;
  }

  .rememberSpotZone{
    width: 350px;
    height: 600px;
    padding-bottom: 20px;
    background-color: #cfcfcf80;
    /* border: 1px solid rgba(200, 235, 207, 0.5); */
    border-radius: 30px / 30px;
    text-align: center;
    position: fixed;
    left: 78%;
    right: 100px;
    overflow-y: scroll;
  }

  .rememberTitle{
    width: 100%;
    margin-top: 15px;
    font-size: 20px;
  }
  .rememberItem{
    width: 100%;
    margin-top: 10px;
    /* display: inline-block; */
  }

  .searchedArea{
    position: absolute; left: 432px; top: 760px;
    width: 1000px;
  }

  .btn{
    width: 70px;
    height: 37px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }


  .goPlanBtn{
    margin-top : 180px;
    margin-left: 20px;
    width: 200px;
    height: 50px;
    font-size: 15px;
    background-color: #cfcfcf80;
    border: 1px solid rgba(103, 132, 177, 0.2);
    border-radius: 10px / 10px;
  }

  .goPlanBtn:hover{
    background-color : #828ea280;
  }
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 9999;
}

.modal-content {
  background-color: #fffcf6;
  padding: 20px;
  border-radius: 5px;
  width: 450px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.modal-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.modal-title {
  margin: 0;
  color: #7aab85;
}

.close-modal-btn {
  background-color: transparent;
  border: none;
  color: #000;
  font-size: 20px;
  cursor: pointer;
}

.modal-body {
  margin-top: 10px;
}

.address,
.category,
.phone-number,
.homepage,
.operating-time,
.closed-day,
.stroller,
.nursing-room,
.admission-fee {
  margin-bottom: 10px;
}

strong {
  margin-right: 5px;
  color: #7aab85;
}

.listTitle{
  text-align : center;
}

</style>