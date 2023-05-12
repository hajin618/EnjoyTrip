<template lang="">
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h3>공지사항</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyNotice"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteNotice"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
        <b-col>
            <b-card
            :header-html="`<h4>${notice.notice_idx} ${notice.notice_title} ${notice.notice_create}</h3>`"
            class="mb-2"
            border-variant=""
            no-body
            >
              <b-card-body class="text-left">
                  <div v-html="content"></div>
              </b-card-body>
            </b-card>
        </b-col>
    </b-row>
    <b-row class="mb-1">
        <b-col class="text-left">
        <b-button variant="outline-primary" @click="listNotice">목록</b-button>
      </b-col>
    </b-row>
  </b-container>
  
</template>

<script>
import http from "@/api/http";
import moment from "moment";

export default {
    name: "NoticeDetail",
    data(){
        return{
            notice: {},
        };
    },

    computed: {
        content() {
          if (this.notice.notice_content)
              return this.notice.notice_content.split("\n").join("<br>");
          return "";
        },
        date(){
          if(this.notice.notice_create)
            return moment(new Date(this.notice.notice_create)).format("YY.MM.DD");
          return "";
        }
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
        moveModifyNotice(){

        },
        deleteNotice(){

        },
    },

  
};
</script>
<style lang="">
    
</style>