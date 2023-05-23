<template>
    <div></div>
</template>

<script>
import http from "@/api/http";
import Swal from "sweetalert2";

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
                // let msg = "삭제 완료";
                
                console.log(res);
                Swal.fire(
                    '삭제 성공!',
                    '안전하게 삭제되었습니다!',
                    'success'
                )
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