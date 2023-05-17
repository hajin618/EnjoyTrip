<template>
  <div>
    <header-navi-bar></header-navi-bar>

    <div class="title">
      <h2>여행지 검색</h2>
    </div>

    <div class="searchBar">
      <select class="sidoSelect" v-model="sidoSelected" @change="getGuGun()">
        <option value="" selected>시/도 선택</option>
        <option v-for="(item, index) in sidoList" :key="index" :value="item.sido_code">
        {{ item.sido_name }}
        </option>
      </select>

      <select class="gugunSelect" v-model="gugunSelect">
        <option value="" selected>구/군 선택</option>
        <option v-for="(item, index) in gugunList" :key="index" :value="item.gugun_code">
        {{ item.gugun_name }}
        </option>
      </select>

      <select class="attractionSelect" v-model="attractionSelect">
        <option value="" selected>관광지 유형</option>
        <option value="12">관광지</option>
        <option value="14">문화시설</option>
        <option value="15">축제공연행사</option>
        <option value="25">여행코스</option>
        <option value="28">레포츠</option>
        <option value="32">숙박</option>
        <option value="38">쇼핑</option>
        <option value="39">음식점</option>
        <!-- <option v-for="(item, index) in attractionSelectList" :key="index" :value="item.value">
        {{ item.name }}
        </option> -->
      </select>

      <input class="searchWord" v-model="searchWord" id="title" autocomplete="off" type="text" placeholder="검색어 입력" required>

      <button class="searchBtn">검색</button>
    </div>

    <div style="display: flex; flex-direction: row; margin-bottom: 50px;">

      <div class="findKidZone">
        <div class="findTitle">실종 아동 정보</div>
        <div class="findImageBox">
          <img width="100%" height="100%" src="../assets/img/mainPageImg.png" alt="">
        </div>

        <div class="findNameBox">
          <label>이름 : </label>
          <span>홍길동</span>
        </div>
        
        <div class="findAgeBox">
          <label>나이 : </label>
          <span>5</span>
        </div>

        <div class="findInfoBox">
          <label>인적사항 1 : </label>
          <span>마른 편</span>
        </div>

        <div class="findInfoBox"> 
          <label>인적사항 2 : </label>
          <span>눈이 이쁨</span>
        </div>
      </div>

      <div class="mapZone">
        <kakao-map></kakao-map>
      </div>

      <div class="rememberSpotZone">
        <div class="rememberTitle">여행지 저장</div>
        
        <!-- for 돌릴 녀석 -->
        <div class="rememberItem">
          <img width="200px" height="200px" src="../assets/img/mainPageImg.png" alt="">
          <div>
            <span class="itemName">
              여행지명
            </span>
          </div>
          <div class="itemInfo">
            <button class="findChildSpotBtn">어린이를 위한 장소 찾기</button>
            <button class="itemDeleteBtn">X</button>
          </div>
        </div>

        <div class="rememberItem">
          <img width="200px" height="200px" src="../assets/img/mainPageImg.png" alt="">
          <div>
            <span class="itemName">
              여행지명
            </span>
          </div>
          <div class="itemInfo">
            <button class="findChildSpotBtn">어린이를 위한 장소 찾기</button>
            <button class="itemDeleteBtn">X</button>
          </div>
        </div>

        <div class="rememberItem">
          <img width="200px" height="200px" src="../assets/img/mainPageImg.png" alt="">
          <div>
            <span class="itemName">
              여행지명
            </span>
          </div>
          <div class="itemInfo">
            <button class="findChildSpotBtn">어린이를 위한 장소 찾기</button>
            <button class="itemDeleteBtn">X</button>
          </div>
        </div>

        <div class="rememberItem">
          <img width="200px" height="200px" src="../assets/img/mainPageImg.png" alt="">
          <div>
            <span class="itemName">
              여행지명
            </span>
          </div>
          <div class="itemInfo">
            <button class="findChildSpotBtn">어린이를 위한 장소 찾기</button>
            <button class="itemDeleteBtn">X</button>
          </div>
        </div>

      </div>

    </div>

    <div class="searchedArea">
      <b-row>
        <b-col>
          <b-table-simple hover responsive>
            <b-thead>
              <b-tr class="listTitle">
                <b-th>대표 이미지</b-th>
                <b-th>관광지명</b-th>
                <b-th>주소</b-th>
                <b-th>저장</b-th>
              </b-tr>
            </b-thead>
            <tbody>
              <!-- <search-view-item>
                
              </search-view-item> -->

              <b-tr>
                <b-td>
                  <img width="100px" height="100px" src="../assets/img/mainPageImg.png" alt="">
                </b-td>
                <b-td>
                  관광지1
                </b-td>
                <b-td>
                  대전 유성구 동서대로 98-39
                </b-td>
                <b-td>
                  <button class="btn">저장</button>
                </b-td>
              </b-tr>

              <b-tr>
                <b-td>
                  <img width="100px" height="100px" src="../assets/img/mainPageImg.png" alt="">
                </b-td>
                <b-td>
                  관광지1
                </b-td>
                <b-td>
                  대전 유성구 동서대로 98-39
                </b-td>
                <b-td>
                  <button class="btn">저장</button>
                </b-td>
              </b-tr>

              <b-tr>
                <b-td>
                  <img width="100px" height="100px" src="../assets/img/mainPageImg.png" alt="">
                </b-td>
                <b-td>
                  관광지1
                </b-td>
                <b-td>
                  대전 유성구 동서대로 98-39
                </b-td>
                <b-td>
                  <button class="btn">저장</button>
                </b-td>
              </b-tr>

            </tbody>
          </b-table-simple>
        </b-col>
      </b-row>
    </div>


  </div>
</template>

<script>
import http from "@/api/http";

import HeaderNaviBar from "../components/layout/HeaderNaviBar.vue"
// import SearchViewItem from "../components/layout/SearchViewItem.vue"
import KakaoMap from "@/components/layout/KakaoMap.vue";

export default {
  name: "SearchView",
  components: {
    HeaderNaviBar,
    // SearchViewItem,
    KakaoMap,
  },
  data(){
    return{
      searchWord: '',
      sidoSelected: '',
      sidoList: [],
      gugunSelect: '',
      gugunList: [],
      
      attractionSelect: '',
      attractionSelectList: [{name: "관광지 선택", value: ""},
                      {name: "name1", value: "a"},
                      {name: "name2", value: "b"},
                      {name: "name3", value: "c"},
                  ],
    }
  },
  created(){
    http.get(`/sido`).then(({ data }) => {
      //console.log(data.sidoList);
      this.sidoList = data.sidoList;
    });
  },

  methods: {
    getGuGun(){
      http.get(`/${this.sidoSelected}/gugun`).then(({data}) => {
        //console.log(data.gugunList);
        this.gugunList = data.gugunList;
      })
    }
  }
}
</script>

<style scoped>
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
  }

  .attractionSelect{
    width: 300px;
    height: 37px;
    margin-left: 70px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
    line-height : 37px;
  }

  .searchWord{
    width: 400px;
    height: 37px;
    margin-left: 70px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
    line-height : 37px;
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
    margin-left: 40px;
    position: sticky;
    top: 200px;
    right: 300px;
    width: 350px;
    height: 500px;
    background-color: rgba(200, 235, 207, 0.5);
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

  .mapZone{
    margin-left: 40px;
    margin-right: 40px;
    width: 1000px;
    height: 500px;
    background-color: rgb(200, 235, 207);
    border-radius: 10px / 10px;
  }

  .rememberSpotZone{
    width: 350px;
    padding-bottom: 20px;
    background-color: rgba(200, 235, 207, 0.5);
    border: 1px solid rgba(200, 235, 207, 0.5);
    border-radius: 30px / 30px;
    text-align: center;
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
    position: absolute; left: 432px; top: 870px;
    width: 1000px;
  }

  .btn{
    width: 70px;
    height: 37px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .findChildSpotBtn{
    margin-left: 20px;
    width: 150px;
    height: 30px;
    font-size: 12px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .itemDeleteBtn{
    margin-left: 20px;
    height: 30px;
    font-size: 10px;
    background-color: #F1F4F1;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }

  .itemInfo{
    margin-top: 10px;
  }
</style>