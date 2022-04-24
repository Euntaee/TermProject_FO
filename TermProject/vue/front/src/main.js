// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import vuetify from '@/plugins/vuetify' // path to vuetify export
import axios from 'axios'
import store from './store'

Vue.prototype.$EventBus = new Vue()
Vue.prototype.$axios = axios
Vue.config.productionTip = false

/* eslint-disable */
new Vue({
    el: '#app',
    vuetify,
    router,
    store,
    components: { App },
    template: '<App/>'
})