<template lang="">
  <b-container class="bv-example-row mt-5">
    <div class = "title">
          <h2>공지사항</h2>
    </div>
    <div class = "topButtonDiv">
      <button
        v-if="this.userInfo.is_admin === 'Y'"
        v-on:click="moveModify"
        class="Button"
        >수정</button>
      <button
        v-if="this.userInfo.is_admin === 'Y'"
        v-on:click="moveDelete"
        class="Button"
        >삭제</button>
    </div>
    <div class="listDiv">
      <b-row class="mb-1">
          <b-col>
              <b-table-simple class="table">
                <b-thead>
                  <b-th width="10%" class="tableIdx">{{notice.notice_idx}}</b-th>
                  <b-th width="50%">{{notice.notice_title}}</b-th>
                  <b-th width="10%" class="tableDate">{{notice.notice_create | dateFormat}}</b-th>
                </b-thead>
                <b-tbody>
                  <b-td colspan="3" class="tableTd">{{notice.notice_content}}</b-td>
                </b-tbody>
              </b-table-simple>
          </b-col>
      </b-row>
    </div>
    <div class="listButtonDiv">
      <button
        v-on:click="listNotice"
        class="Button"
        >목록</button>
    </div>
  </b-container>
  
</template>

<script>
import http from "@/api/http";
import moment from "moment";
import { mapState, mapGetters, mapActions } from "vuex";

const userStore = "userStore";

export default {
    name: "NoticeDetail",
    data(){
        return{
            notice: {},
        };
    },

    computed: {
      ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
      ...mapGetters(["checkisLogin", "checkUserInfo"]),

      content() {
        if (this.notice.notice_content)
            return this.notice.notice_content.split("\n").join("<br>");
        return ""; 
      },
    },

    filters: {
        dateFormat(notice_create){
            return moment(new Date(notice_create)).format("YYYY-MM-DD");
        },
    },

    created() {
        http.get(`/notice/${this.$route.params.notice_idx}`).then(({ data }) => {
//            console.log(data);
            this.notice = data.notice;
        });
    },

    methods:{
        listNotice() {
            this.$router.push({ name: "noticeList" });
        },
        moveModify(){
            this.$router.replace({
              name: "noticeModify",
              params: { notice_idx : this.notice.notice_idx },
            });
        },
        moveDelete(){
            if(confirm("정말 삭제하시겠습니까?")){
              this.$router.replace({
                name: "noticeDelete",
                params: {notice_idx : this.notice.notice_idx},
              });
            }
        },
        ...mapActions(userStore, ["userLogout"]),

    },

};
</script>

<style scoped>
  .title{
      padding-top : 100px;
      padding-bottom: 50px;
    }
  h2{
      text-align : center;
    }
  .table{
    table-layout: fixed;
  }
  .tableIdx{
    text-align: center;
  }
  .tableDate{
    text-align: right;
  }
  .tableTd{
    height: 400px;
    border-bottom : 1px solid rgb(218, 218, 218);
    padding-top : 5%;
    padding-bottom : 5%;
  }
  .topButtonDiv{
    padding-bottom : 15px;
    float:right;
  }
  .listDiv{
    clear:right;
    padding-bottom: 10px;
  }
  .Button{
    border-color:#d1deec;
    background-color: #d1deec;
    border-radius: 10px;
    width: 80px;
    height: 35px;
    margin-right: 5px;
  }
  .Button:hover{
    background-color : #97b1cd;
  }
  .listButtonDiv{
    text-align: center;
    padding-bottom : 30px;
  }
    
</style>