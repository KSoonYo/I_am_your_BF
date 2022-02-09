import { createStore } from 'vuex'
import axios from 'axios'


// axios.defaults.baseURL = 'https://localhost:8080/api'
// axios.defaults.headers['Content-Type'] = DEFAULT_ACCEPT_TYPE


export default createStore({
  state: {

  },
  mutations: {
  
  },
  actions: {

    // 로그인 JWT 토큰 
    getToken({ state }, payload){
      state
      const url = 'http://localhost:8080/api/auth/login'
      return axios.post(url, payload)
    },
    
    // 유저 확인(userId or userEmail)
    requestGetUser({ state }, user){
      state
      const url = `http://localhost:8080/api/users/${user}`
      return axios.get(url)
    },


    // 유저 정보 조회
    getUserInfo({state}){
      state
      const url = 'http://localhost:8080/api/users/profile'
      const token = localStorage.getItem('accessToken')
      return axios.get(url, {
        headers: {
          Authorization:  `Bearer ${token}`
        }
      })
    },
    
    // 회원가입
    signUp({state}, userInfo){
      state
      const url = 'http://localhost:8080/api/users'
      return axios.post(url, userInfo)
    }
      
  },
  modules: {

  }
})
