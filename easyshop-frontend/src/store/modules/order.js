import { placeOrder, placeOrderList, getOrderList } from "@/api/order";

const state = {
}

const mutations = {

}

const actions = {
  // get commodity list
  post({ commit, state }, order) {
    return new Promise((resolve, reject) => {
      placeOrder(order).then(response => {
        const { msg, data } = response
        if (!data) {
          reject('Data wrong, please try again.')
        }
        resolve(msg)
      }).catch(error => {
        reject(error)
      })
    })
  },
  postList({ commit, state }, orders) {
    return new Promise((resolve, reject) => {
      placeOrderList(orders).then(response => {
        const { msg, data } = response
        if (!data) {
          reject(msg)
        }
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  getList({ commit, state }, orders) {
    return new Promise((resolve, reject) => {
      getOrderList().then(response => {
        const { msg, data } = response
        if (!data) {
          reject(msg)
        }
        resolve(data)
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

