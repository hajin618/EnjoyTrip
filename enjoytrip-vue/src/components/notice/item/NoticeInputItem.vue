<template lang="">
    <b-container class="bv-example-row mt-5">
        <div class = "inputDiv">
            <b-row class="mb-1">
                <b-col>
                    <form v-on:submit="registForm">
                        <input
                        id="title"
                        v-model="notice.notice_title"
                        type="text"
                        placeholder="제목 입력"
                        required>

                        <textarea
                        id="content"
                        rows="10"
                        v-model="notice.notice_content"
                        type="textarea"
                        placeholder="내용 입력"
                        required></textarea>

                        <div class = "ButtonDiv">
                            <button
                            type="submit"
                            >등록</button>
                            <button
                            type="reset"
                            >취소</button>
                        </div>
                    </form>
                </b-col>
            </b-row>
        </div>
        
    </b-container>
</template>
<script>
import http from "@/api/http";

export default {
    name: "NoticeInputItem",
    data(){
        return{
            notice:{
                // user_idx 수정하기
                user_idx: 1,
                notice_title: "",
                notice_content: "",
            }
        };
    },
    props: {
        type: {type: String},
    },
    created(){
        /*
        if(this.type === "modify"){

        }
        */
    },

    methods:{
        registForm: function(){
            //console.log(this.notice.notice_title, this.notice.notice_content);
            this.type === "register" ? this.registNotice() : this.modifyNotice();
        },
        registNotice(){
            http.post(`/notice`, {
                user_idx : this.notice.user_idx,
                notice_title : this.notice.notice_title,
                notice_content : this.notice.notice_content,
            })
            .then(({ res }) => {
                //let msg = "등록시 문제 발생";
                console.log(res);
                /*
                if(data === "success"){
                    msg = " 등록 완료";
                }*/
                alert("aaaa");
                this.moveList();
            })
        },
        moveList() {
            this.$router.push({ name: "noticeList" });
        },
    }
}
</script>
<style scoped>
.tableTitle{
    height: 50px;
}
.tableContent{
    height: 400px;
    border-bottom : 1px solid rgb(218, 218, 218);
    padding-top : 5%;
    padding-bottom : 5%;
}
#title{
    /*
    border: 0;
    */
    width: 100%;
    height: 50px;
}
#title:focus{
   outline:none;
}   
#content{
    /*
    border: 0;
    */
    width: 100%;
    height: 400px;
}
#content:focus{
   outline:none;
}
.Button{
    border-color:#98c2a182;
    background-color : #98c2a182;
    border-radius: 10px;
    width: 80px;
    height: 35px;
    margin-right: 5px;
  }
  .Button:hover{
    background-color : #c8d6cc;
  }
  .ButtonDiv{
    text-align: center;
    padding-bottom : 30px;
  }
</style>