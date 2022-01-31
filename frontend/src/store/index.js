import { createStore } from 'vuex'
import axios from 'axios'

// axios.defaults.baseURL = BASE_URL
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
      const url = '/auth/login'
      axios.post(url, payload)
      .then((response)=>{
        localStorage.setItem('accessToken', response.data.accessToken)
        state.accessToken = response.data.accessToken
      })
      .catch(()=>{
        alert('로그인에 실패하였습니다.')
      })
    },
    
    // 유저 확인(userId or userEmail)
    requestGetUser({ state }, user){
      state
      const url = `/users/${user}`
      return axios.get(url)
    },
    
    // 회원가입
    signUp(userInfo){
      const url = '/users/'
      return axios.post(url, userInfo)      
    }
      

  },
  modules: {

  }
})
