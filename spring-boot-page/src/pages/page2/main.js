import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import '@/plugins/iview';


Vue.config.productionTip = false;

Vue.prototype.$QuestionUtils = QuestionUtils;

new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app');
