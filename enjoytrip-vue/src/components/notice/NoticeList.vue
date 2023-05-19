<template>
    <b-container class="bv-example-row mt-5">
    <div class = "title">
          <h2>공지사항</h2>
    </div>
    <div v-if="this.userInfo.is_admin === 'Y'" class = "buttonDiv">
      <button
        v-on:click="moveRegist"
        class="registButton"
        >등록</button>
    </div>
    <div class="listDiv">
      <b-row>
        <b-col v-if="notices.length">
          <b-table-simple hover responsive>
            <b-thead head-variant="">
              <b-tr class="listTitle">
                <b-th>글번호</b-th>
                <b-th>제목</b-th>
                <b-th>작성자</b-th>
                <b-th>작성일</b-th>
                <b-th>조회수</b-th>
              </b-tr>
            </b-thead>
            <tbody>
              <notice-list-item
                v-for="notice in notices"
                :key="notice.notice_idx"
                v-bind="notice"
              />
            </tbody>
          </b-table-simple>
        </b-col>
      </b-row>
    </div>
  </b-container>
</template>

<script>
import http from "@/api/http";
import NoticeListItem from "@/components/notice/item/NoticeListItem.vue";
import { mapState, mapGetters, mapActions } from "vuex";

const userStore = "userStore";

export default {
    name: "NoticeList",
    components: {
        NoticeListItem,
    },
    computed: {
        ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
        ...mapGetters(["checkisLogin", "checkUserInfo"]),
    },
    data() {
        return {
            notices: [], 
        };
    },
    created() {
        http.get(`/notice`).then(({data}) => {
            // console.log(data);
            this.notices = data.notices;
        });
        //  axios({
        //     method: "get",
        //     url: "/notice"
        // }).then((res) => {
        //     console.log(res.data);
        //     this.notices = res.data.notices;
        // })

    },
    methods: {
        moveRegist(){
            this.$router.push({ name: "noticeRegister" });
        },

        ...mapActions(userStore, ["userLogout"]),
    },
};
</script>

<style scoped>
  .title{
      padding-top : 40px;
      padding-bottom: 50px;
    }
  h2{
      text-align : center;
    }
  .registButton{
    border-color:#98c2a182;
    background-color : #98c2a182;
    border-radius: 10px;
    width: 80px;
    height: 35px;
  }
  .registButton:hover{
    background-color : #c8d6cc;
  }
  .buttonDiv{
    padding-bottom : 15px;
    float:right;
  }
  .listDiv{
    clear:right;
    padding-bottom: 50px;
  }
  .listTitle{
    background-color:#e8ece8d1
  }
</style>