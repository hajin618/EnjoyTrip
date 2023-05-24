<template>
    <div>
        <router-view></router-view>
        <div class="footer">
            <!-- <footer-area></footer-area> -->
        </div> 
    </div>
    
</template>

<script>
// import FooterArea from "../components/layout/FooterArea.vue";

export default{
    name: "UserView",
    components: {
        // FooterArea,
    }
}

</script>

<style scoped>
    .footer{
        clear: both;
    }
</style>
