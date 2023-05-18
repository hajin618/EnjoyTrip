<template>
  <b-tr>
    <b-td>
      <img width="100px" height="100px" src="/assets/img/mainPageImg.png" alt="">
    </b-td>
    <b-td>
      {{title}}
    </b-td>
    <b-td>
      {{addr1}}
    </b-td>
    <b-td>
      <button class="btn">저장</button>
    </b-td>
  </b-tr>
</template>

<script>
export default {
  name: "SearchViewItem",
  props: {
    first_image: String,
    title: String,
    addr1: String,
  },
}
</script>

<style scoped>
  .btn{
    margin-left: 70px;
    width: 100px;
    height: 37px;
    background-color: #D9D9D9;
    border: 1px solid rgba(213, 120, 120, .2);
    border-radius: 10px / 10px;
  }
</style>