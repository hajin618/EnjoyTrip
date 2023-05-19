<template>
    <b-container class="bv-example-row mt-5">
        <div class = "title">
          <h2>공지사항 수정</h2>
        </div>
        <div class = "noticeRegistDiv">
            <notice-input-item type="modify"/>
        </div>
    </b-container>
</template>

<script>
import NoticeInputItem from "@/components/notice/item/NoticeInputItem.vue";

export default {
    name: "NoticeModify",
    components: {
        NoticeInputItem, 
    },
    data() {
        return {
            message: '',
        };
    },
    created() {},
    methods: {},
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
</style>