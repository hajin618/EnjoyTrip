<template>
    <div></div>
</template>

<script>
import http from "@/api/http";

export default {
    name: "NoticeDelete",
    components: {},
    data() {
        return {
            message: '',
        };
    },
    created() {
        http.delete(`/notice/${this.$route.params.notice_idx}`)
        .then(({ res }) => {
                let msg = "삭제 완료";
                
                console.log(res);
                alert(msg);
                this.moveList();
        })
    },
    methods: {
        moveList() {
            this.$router.push({ name: "noticeList" });
        },
    },
};
</script>

<style scoped></style>