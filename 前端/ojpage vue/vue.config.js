// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true,
//   devServer: {
//     hot: true,
//     hotOnly: true, // 是否热更新
//     // 配置多个代理
//     proxy: {
//       '/api': {
//         target: 'http://localhost:8090',
//         changeOrigin: true,
//         ws: true,
//         pathRewrite: {
//           '^/api': ''
//         }
//       }
//     }
//   },
// })
module.exports = {
  publicPath: './',
  transpileDependencies: true,
  devServer: {
    // 配置多个代理
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:8090',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  },
};
