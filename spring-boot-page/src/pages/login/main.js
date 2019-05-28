import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import '@/plugins/iview';

import ActionButtons   from '@/components/ActionButtons';
import CheckingDao  from '@/../public/static/js/dao/CheckingDao';

Vue.config.productionTip = false;
Vue.component('ActionButtons', ActionButtons);
Vue.prototype.$CheckingDao = CheckingDao;

new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app');
