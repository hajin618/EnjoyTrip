<template>
  <div class="row q-col-gutter-lg">
    <q-card v-for="c in 4" :key="c" class="col-6 col-md-4">
      <div style="text-align:center; background-color: rgba(200, 235, 207, .5);" >
        <img width="200px" height="200px" src="../../../assets/img/family.png" alt="">
        <div class="text-center text-black">제목</div>
      </div>
      
    </q-card>
  </div>




    <!-- <div class="row q-col-gutter-lg">
    <div v-for="m in members" :key="m" class="col-6 col-md-4">
      <q-card>
        <div class="text-center bg-primary text-white">멤버</div>
        <q-card-section>
          <div>{{ m.name }}</div>
          <div>{{ m.team }}</div>
          <div>{{ m.contact }}</div>
        </q-card-section>
      </q-card>
    </div>
  </div> -->
</template>

<script>
export default {
  name: "UserLikeListView"
  
}
</script>

<style scoped>

</style>
