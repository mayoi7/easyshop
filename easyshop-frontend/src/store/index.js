import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import app from './modules/app'
import settings from './modules/settings'
import user from './modules/user'
import shop from "./modules/shop";
import count from "./modules/count";
import order from "./modules/order";

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    app,
    settings,
    user,
    shop,
    count,
    order
  },
  getters
})

export default store
