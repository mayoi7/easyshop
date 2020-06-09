import { getToken } from '@/utils/auth'
import { getTotalAmount } from "@/api/count";

const state = {
  token: getToken()
}

const mutations = {

}

const actions = {
  // get commodity list
  transTotal({ commit, state }) {
    return new Promise((resolve, reject) => {
      getTotalAmount().then(response => {
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

