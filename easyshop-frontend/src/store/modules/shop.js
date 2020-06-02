import { getToken } from '@/utils/auth'
import { listCommodities } from "@/api/commodity";
import {addToCart} from "../../api/commodity";

const state = {
  token: getToken(),
  cart: []
}

const mutations = {
  SET_CART: (state, id) => {
    state.cart.push(id)
  }
}

const actions = {
  // get commodity list
  list({ commit, state }, pageData) {
    return new Promise((resolve, reject) => {
      listCommodities(pageData.page, state.token).then(response => {
        const { data } = response
        if (!data) {
          reject('Data wrong, please try again.')
        }
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  cart({ commit, state }, id) {
    return new Promise((resolve, reject) => {
      addToCart(id, state.token).then(response => {
        const { msg } = response
        commit('SET_CART', id)
        resolve(msg)
      }).catch(error => {
        reject(error)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

