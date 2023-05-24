<template>
  <div style="background-color: #f9f8f8;">
    <div class="logos">
      <div class="leftlogo">
        <img src="../../assets/img/ssafy-logo-black.b301994e.png" class="img-ssafy-logo" style="width: 80px;" />
      </div>

      <div class="rightlogo">
        <a href="#" title="SSAFY">
          <img src="../../assets/img/ssafy-logo-small.bd603755.png" class="img-link" style="width: 100px;" />
        </a>
        <a href="#" title="TourAPI 4.0">
          <img src="../../assets/img/tour-api-logo.ebcb1140.png" class="img-link" style="width: 100px;" />
        </a>
        <a href="#" title="한국관광공사">
          <img src="../../assets/img/visit-korea-logo.858325ef.png" class="img-link" style="width: 100px;" />
        </a>
        <a href="#" title="문화체육관광부">
          <img src="../../assets/img/mcst-logo.86700cdd.png" class="img-link" style="width: 100px;" />
        </a>
      </div>
    </div>

    <div class="comment">
      대표: 김선진 그리고 김하진<br />
      주소: 대전 유성구 동서대로 98-39<br />
    </div>
  </div>
</template>

<script>
export default {
  name: "FooterArea",
};
</script>

<style scoped>
.logos {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
}

.leftlogo {
  margin-right: 20px;
}

.rightlogo {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.comment {
  margin-top: 20px;
  text-align: center;
  font-weight: bold;
  color: #888888;
}
</style>
