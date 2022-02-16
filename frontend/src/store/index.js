import { createStore } from 'vuex'
import axios from 'axios'

const instance = axios.create({
  baseURL: process.env.VUE_APP_BASE_URL
})

// axios.defaults.baseURL = 'https://localhost:8080/api'
// axios.defaults.headers['Content-Type'] = DEFAULT_ACCEPT_TYPE


export default createStore({
  state: {

  },
  mutations: {
  
  },
  actions: {
    // 회의실 상세조회
    getConferenceDetail({state}, id) {
      state
      const url = 'api/conferences/' + id
      return instance.get(url)
    },

    // 회의실 불러오기
    getConference({state}) {
      state
      const url = 'api/conferences'
      return instance.get(url)
    },

    // 회의실 종료
    deleteConference({state}, conferenceId){
      state
      const url = `api/openvidu/close-session/${conferenceId}`
      return instance.delete(url)
    },

    // 회의실 닫기
    closeConference({state}, conferenceId){
      state
      const url = `api/conferences/end/${conferenceId}`
      return instance.put(url)
    },

    // 수업 기록 이메일 발송
    sendEduLog({state}, content ){
      state
      const token = localStorage.getItem('accessToken')
      const url = `api/users/send/edu-log`
      return instance.post(url, content, {
        headers: {
          Authorization:  `Bearer ${token}`
        }
      })
    },

    // 이미지 불러오기
    getThumbnail({state},thumbnail) {
      state
      const url = 'api/image/' + thumbnail
      return instance.get(url)
    },

    // 이미지 업로드
    uploadThumbnail({state},thumbnail) {
      state
      const url = 'api/image/upload'
      
      return instance({ method: 'post', url: url, data: thumbnail, headers: {'Content-Type': 'multipart/form-data'} })
    },

    // 회의실 생성
    createConference({state}, data) {
      state
      const url = 'api/conferences'
      return instance.post(url, data, { headers: {'Content-Type': 'application/json'} })
    },

    // 로그인 JWT 토큰 
    getToken({ state }, payload){
      state
      const url = 'api/auth/login'
      return instance.post(url, payload)
    },
    
    // 유저 확인(userId or userEmail)
    requestGetUser({ state }, user){
      state
      const url = `api/users/${user}`
      return instance.get(url)
    },


    // 유저 정보 조회
    getUserInfo({state}){
      state
      const url = 'api/users/profile'
      const token = localStorage.getItem('accessToken')
      return instance.get(url, {
        headers: {
          Authorization:  `Bearer ${token}`
        }
      })
    },
    
    // 프로필 업데이트
    updateProfile({state}, data){
      state
      const userId = data.userId
      const url = `api/users/${userId}`
      const userdata = {
        userName : data.userName,
        userEmail : data.userEmail,
        thumbnail : data.thumbnail,
      }
      const token = localStorage.getItem('accessToken')
      return instance.patch(url, userdata, {
        headers: {
          Authorization:  `Bearer ${token}`
        }
      })
    },

    // 회원가입
    signUp({state}, userInfo){
      state
      const url = 'api/users'
      return instance.post(url, userInfo)
    },

    // 아이디 찾기
    findId({state}, data){
      console.log(data)
      state
      const url = 'api/users/find/userId'
      return instance.post(url, data)
    },

    // 비밀번호 찾기
    findPassword({state}, data){
      state
      const url = 'api/users/find/password'
      return instance.post(url, data)
    },
    
    // 비밀번호 변경
    changePassword({state}, data){
      state
      const token = localStorage.getItem('accessToken')
      const url = 'api/users/password'
      console.log(data)
      return instance.post(url, data, {
        headers: {
          Authorization:  `Bearer ${token}`
        }
      })
    }
      
  },
  modules: {

  }
})
