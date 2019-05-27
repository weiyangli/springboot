process.env.VUE_APP_VERSION = new Date().getTime();

module.exports = {
    devServer: {
        port: 8888,
        proxy: 'http://192.168.10.158:8080'
    },

    // 多页的页面
    pages: {
        page2: 'src/pages/page2/main.js',
        login: 'src/pages/login/main.js',
    },

    // yarn build 的输出目录
    outputDir: '../../cwc_mc/src/main/webapp/WEB-INF/page-vue',
    assetsDir: 'static',

    css: {
        loaderOptions: {
            sass: {
                data: `
                    @import "@/../public/static/css/variables.scss";
                `
            }
        }
    }
};
