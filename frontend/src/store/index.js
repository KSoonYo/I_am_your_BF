import { createStore } from 'vuex'
import axios from 'axios'


axios.defaults.baseURL = 'https://localhost:8080/api'
// axios.defaults.headers['Content-Type'] = DEFAULT_ACCEPT_TYPE


export default createStore({
  state: {
    accessToken : ''

  },
  mutations: {
    
  },
  actions: {

    // 로그인 JWT 토큰 
    getToken({ state }, payload){
      state
      const url = '/auth/login'
      return axios.post(url, payload)
    },
    
    // 유저 확인(userId or userEmail)
    requestGetUser({ state }, user){
      state
      const url = `/users/${user}`
      return axios.get(url)
    },
    
    // 회원가입
    signUp({state}, userInfo){
      state
      console.log(userInfo)
      const url = '/users'
      return axios.post(url, userInfo)      
    }
      
  },
  modules: {

  }
})
