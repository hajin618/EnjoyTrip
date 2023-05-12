<template>
  <div id="app">    
    <router-view/>
    <footer-area></footer-area>     
  </div>
</template>

<script>
import FooterArea from "./components/layout/FooterArea.vue";

export default{
  name : "App",
  components: {
    FooterArea,
  }
}
</script>


<style scoped>
/* #app { */
  /* 푸터 하단 고정해야함 */
  /* https://pickhada.tistory.com/16  참고해서*/
  /* min-height: 100%;
  position: relative;
  padding-bottom: 170px; */


  /* font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center; */
/* } */
</style>
