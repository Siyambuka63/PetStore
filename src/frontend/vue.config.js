const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 3000,
    proxy: {
      '/petstore': {
        target: 'http://localhost:8080',
        ws: true,
        changeOrigin: true
      }
    }
  }
});