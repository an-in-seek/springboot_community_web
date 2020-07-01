import Vue from 'vue';
import App from './App.vue';
import { router } from './router';
import store from './store';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-vue/dist/bootstrap-vue.css'
import { BootstrapVue, IconsPlugin, FormPlugin } from 'bootstrap-vue';
import VeeValidate from 'vee-validate';
import Vuex from 'vuex';
import moment from 'moment';
import VueMomentJS from 'vue-momentjs';
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import {
  faHome,
  faUser,
  faUserPlus,
  faSignInAlt,
  faSignOutAlt,
  faList
} from '@fortawesome/free-solid-svg-icons';
import GAuth from 'vue-google-oauth2'
import Constant from './constant';
library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt, faList);

Vue.config.productionTip = false;

// Install BootstrapVue
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);
// Optionally install the BootstrapVue Form plugin
Vue.use(FormPlugin)
// Install VeeValidate
Vue.use(VeeValidate, {
  fieldsBagName: 'veeFields'
});
// Install Vuex
Vue.use(Vuex);
// Install VueMoment
Vue.use(VueMomentJS, moment);

Vue.use(GAuth, {
  clientId: Constant.GOOGLE_API_KEY,
  scope: 'email profile',
  prompt: 'consent'//, fetch_basic_profile: true
})
Vue.component('font-awesome-icon', FontAwesomeIcon);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
