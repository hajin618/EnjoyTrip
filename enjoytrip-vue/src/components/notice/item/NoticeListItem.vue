<template>
    <b-tr>
    <b-td>{{ notice_idx }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'noticeDetail', params: { notice_idx: notice_idx } }"
        >{{ notice_title }}</router-link>
    </b-th>
    <b-td>{{ user_name | fixUserName}}</b-td>
    <b-td>{{ notice_create | dateFormat }}</b-td>
    <b-td>{{ notice_hit }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
    name: "NoticeListItem",
    props: {
        notice_idx: Number,
        notice_title: String,
        user_name: String,
        notice_create: String,
        notice_hit: Number, 
    },
    filters: {
        dateFormat(notice_create){
            return moment(new Date(notice_create)).format("YY.MM.DD");
        },
        fixUserName(){
            return "관리자";
        }
    },
    // components: {},
    // data() {
    //     return {
    //         message: '',
    //     };
    // },
    // created() {},
    // methods: {},
};
</script>

<style scoped>
    a{
        color : black;
        font-weight : 500;
    }
</style>