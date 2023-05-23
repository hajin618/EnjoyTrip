const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    port: 8080,
    proxy : "http://192.168.208.10:80/api"
    // proxy : "http://192.168.208.10:80/api" 선진
  }
})
