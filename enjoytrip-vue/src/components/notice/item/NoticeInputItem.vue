<template lang="">
    <b-container class="bv-example-row mt-5">
        <div class = "inputDiv">
            <b-row class="mb-1">
                <b-col>
                    <form v-on:submit.prevent="registForm">
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
                            class="Button"
                            type="submit"
                            v-if="this.type === 'register'"
                            >등록</button>

                            <button
                            class="Button"
                            type="submit"
                            v-else
                            >수정</button>

                            <button 
                            class="Button"
                            v-on:click="onReset"
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
import { mapState, mapGetters, mapActions } from "vuex";

const userStore = "userStore";

export default {
    name: "NoticeInputItem",
    data(){
        return{
            notice:{
                // user_idx 수정하기
                user_idx: "",
                notice_title: "",
                notice_content: "",
            }
        };
    },
    computed: {
        ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
        ...mapGetters(["checkisLogin", "checkUserInfo"]),
    },
    props: {
        type: {type: String},
    },
    created(){
        if(this.type === "modify"){
            http.get(`/notice/${this.$route.params.notice_idx}`).then(({data}) => {
                this.notice = data.notice;
                console.log(data);
            });
        }
        
    },

    methods:{
        ...mapActions(userStore, ["userLogout"]),
        registForm: function(){
            //console.log(this.notice.notice_title, this.notice.notice_content);
            this.type === "register" ? this.registNotice() : this.modifyNotice();
        },
        registNotice(){
            http.post(`/notice`, {
                user_idx : this.userInfo.user_idx,
                notice_title : this.notice.notice_title,
                notice_content : this.notice.notice_content,
            })
            .then(({ res }) => {
                let msg = "등록 완료";
                
                console.log(res);
                
                // if(res === "true"){
                //     msg = " 등록시 문제 발생";
                // }
                alert(msg);
                this.moveList();
            })
        },
        modifyNotice(){
            http.put(`/notice/${this.notice.notice_idx}`, {
                user_idx : this.userInfo.user_idx,
                notice_title : this.notice.notice_title,
                notice_content : this.notice.notice_content,
            })
            .then(({ res }) => {
                let msg = "수정 완료";
                console.log(res);
                alert(msg);
                this.$router.push({name: "noticeDetail", params: { notice_idx: this.notice.notice_idx}});
            })
        },
        moveList() {
            this.$router.push({ name: "noticeList" });
        },
        onReset(event){
            event.preventDefault();
            this.notice.notice_idx = 0;
            this.notice.notice_title = "";
            this.notice.notice_content = "";
            //this.$router.push({ name: "noticeList" });
            this.moveList();
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
    
    border: 0;
    border-top : 1px solid rgb(218, 218, 218);
    border-bottom : 1px solid rgb(218, 218, 218);
    width: 100%;
    height: 50px;
}
#title:focus{
   outline:none;
}   
#content{
    padding-top : 5%;
    border: 0;
    border-bottom : 1px solid rgb(218, 218, 218);
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