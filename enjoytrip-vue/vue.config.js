const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: [
    'vuetify'
  ],
  devServer: {
    port: 8080,

    // proxy : "http://localhost:80/api"
    proxy : "http://192.168.208.85:80/api"


  }
})
