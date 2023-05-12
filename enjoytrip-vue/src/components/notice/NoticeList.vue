<template>
    <h4>공지사항</h4>
</template>

<script>
import http from "@/api/http";
// import axios from "axios";
// import NoticeListItem from "@/components/notice/item/NoticeListItem.vue";

export default {
    name: "NoticeList",
    components: {
        // NoticeListItem,
    },
    data() {
        return {
            notices: [],
        };
    },
    created() {
        http.get(`/notice`).then(({data}) => {
            this.notices = data;
        });
        // axios({
        //     method: 'get',
        //     url: "http://localhost:80/vue/notice"
        // }).then(() => console.log(12))
    },
    methods: {
        moveRegist(){
            this.$router.push({ name: "noticeRegister" });
        },
    },
};
</script>

<style scoped></style>