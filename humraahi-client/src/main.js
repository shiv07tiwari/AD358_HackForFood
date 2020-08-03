import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
Vue.use(require('vue-faker'), {locale: 'en_IND'});
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.use(VueAxios, axios)

axios.defaults.baseURL = "https://da933c435f15.ngrok.io/";

axios.defaults.headers["Content-Type"] = "application/json;charset=UTF-8";

axios.interceptors.request.use((x) => {
  // to avoid overwriting if another interceptor
  // already defined the same object (meta)
  x.meta = x.meta || {};
  x.meta.requestStartedAt = new Date().getTime();
  return x;
});

axios.interceptors.response.use((x) => {
  console.log(
    `Execution time for: ${x.config.url} - ${new Date().getTime() -
      x.config.meta.requestStartedAt} ms`
  );
  return x;
});

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
