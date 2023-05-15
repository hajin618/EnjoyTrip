<template>
    <div style="background-color: #f9f8f8; ">
        <div class="logos">
            <div class="leftlogo">
                <img src="../../assets/img/ssafy-logo-black.b301994e.png" class="img-ssafy-logo" style="width:80px;" />
            </div>

            <div class="rightlogo">
                <a href="#" title="SSAFY">
                    <img src="../../assets/img/ssafy-logo-small.bd603755.png" class="img-link" style="width:100px;" />
                </a>
                <a href="#" title="TourAPI 4.0">
                    <img src="../../assets/img/tour-api-logo.ebcb1140.png" class="img-link" style="width:100px;" />
                </a>
                <a href="#" title="한국관광공사">
                    <img src="../../assets/img/visit-korea-logo.858325ef.png" class="img-link" style="width:100px;" />
                </a>
                <a href="#" title="문화체육관광부">
                    <img src="../../assets/img/mcst-logo.86700cdd.png" class="img-link" style="width:100px;" />
                </a>
            </div>
        </div>

        <div class="comment">
            대표 : Hi SSAM 주소:
            212, Teheran-ro, Gangnam-gu, Seoul, Republic of Korea
            <br /> 대표번호: 1588-3357 사업자번호 : 123-4567-987
        </div>
    </div>
</template>

<script>
export default{
    name: "FooterArea",
};
</script>

<style scoped>
    .comment{
        margin-left: 800px;
        font-weight: bolder;
        color: #888888;
    }
    .logos{
        display: flex;
        justify-content: space-between;
    }
    .leftlogo{
        margin-left: 50px;
    }
    .rightlogo{
        margin-right: 50px;
    }
</style>