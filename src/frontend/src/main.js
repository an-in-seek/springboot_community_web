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

Vue.component('font-awesome-icon', FontAwesomeIcon);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
