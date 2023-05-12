<template>
      <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h3>공지사항</h3>
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="notices.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>작성자</b-th>
              <b-th>작성일</b-th>
              <b-th>조회수</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <notice-list-item
              v-for="notice in notices"
              :key="notice.notice_idx"
              v-bind="notice"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
import NoticeListItem from "@/components/notice/item/NoticeListItem.vue";

export default {
    name: "NoticeList",
    components: {
        NoticeListItem,
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
    },
};
</script>

<style scoped></style>