import { getToken } from '@/utils/auth'
import { getTotalAmount } from "@/api/count";

const state = {
  token: getToken()
}

const mutations = {

}

const actions = {
  // get commodity list
  trans({ commit, state }) {
    return new Promise((resolve, reject) => {
      getTotalAmount().then(response => {
        const { data } = response
        if (!data) {
          reject('Data wrong, please try again.')
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

