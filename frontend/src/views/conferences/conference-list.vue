<template>

  <div>
    <div class='row justify-center'>
      <!-- 검색창 -->
      <q-input 
        v-model='state.searchValue' 
        :dense='dense'
        maxlength='20'
        placeholder='회의실 제목, 호스트를 검색'
        class='input'
      >
        <template v-slot:append>
            <q-icon v-if='state.searchValue !== ""' name='close' @click='state.searchValue = ""' class='cursor-pointer'></q-icon>
        </template>
        <template v-slot:after>
          <q-btn :loading='state.loading' round dense flat icon='send' @click='searchConference' />
        </template>
      </q-input>
    </div>
    <br>
    <div class='container row contents justify-center flex'>
      <!-- 목차 (수업 목록, 지난 수업 기록) -->
      <div class='column items-center col-auto content'>
        <div style='padding-top: 20px'>
          <router-link :to='{ name: "ConferenceList"}' class='link'>수업 목록</router-link>
        </div>
        <q-separator color='orange' inset />
        <div style='padding-top: 20px'>
          <router-link :to='{ name: "ConferenceList"}' class='link'>
            지난 수업 기록
          </router-link>
        </div>
      </div>
    

      <div class='col-8' style='margin-left: 20px; border: solid 1px; border-radius: 10px'>
        <div class='container create'>
          <!-- 회의실 생성 방법1 -->
          <div class='flex justify-between'>
            <div>
            <q-btn @click='titlerule' flat style='color: #ddb193'>제목순</q-btn>
            <q-btn @click='namerule' flat style='color: #ddb193'>이름순</q-btn>
          </div>
            <div v-if='state.isLogin'> 
              <router-link :to='{ name: "CreateConference"}' style='text-decoration: none;'><q-btn flat style='color: #ddb193'>회의실 생성</q-btn></router-link> 
            </div>
            <div v-else>
              <router-link :to='{ name: "CreateConference"}' style='text-decoration: none;'><q-btn flat style='color: #ddb193'>로그인</q-btn></router-link>
            </div>
          </div>
          <br>
          <!-- 회의실 목록 카드들 -->  
          <div v-if='state.conferenceList'>
            <ul class='infinite-list'>
              <li v-for='conference in state.conferenceList' :key='conference.datetime' class='infinite-list-item'>
                <conference />
              </li>
            </ul>
          </div>
          <div v-else>
            <p>아무 정보가 없네요?</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// 제목 검색시 쿼리 동적 전달방식 이용
import conference from './components/conference'

import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

export default {
  name: 'conferenceList',
  
  components: {
    conference,
    
  },

  setup () {
    
    // router 불러오기
    const router = useRouter();
    // 반응형 버튼
    const dense = ref(false);

    
    // 데이터 값 설정
    const state = ref({
      // 검색 입력 값
      searchValue: '',
      loading: false,
      conferenceList: null,
      isLogin: false,
      titleasc: true,
      nameasc: true,
    });

    const token = localStorage.getItem('jwt')
    if (token) {
      state.value.isLogin = true
    }
    const getConferences = function () {
      axios({
        method: 'get',
        url: 'http://localhost:8080/conferences',
      })
        .then(res => {
          console.log(res.data)
          state.value.conferenceList = res.data
        })
        .catch(err => {
          console.log(err)
        })
    };
    const titlerule = function () {
      if (state.value.titleasc) {
        state.value.titleasc = false
        state.value.conferenceList = state.value.conferenceList.sort(title => title)
      } else {
        state.value.titleasc = true
        state.value.conferenceList = state.value.conferenceList.sort(title => -title)
      }
    }
    const namerule = function () {
      if (state.value.nameasc) {
        state.value.nameasc = false
        state.value.conferenceList = state.value.conferenceList.sort(name => name)
      } else {
        state.value.nameasc = true
        state.value.conferenceList = state.value.conferenceList.sort(name => -name)
      }
    }
    const searchConference = function () {
      state.value.loading = true
      setTimeout(() => {
        state.value.loading = false
        // 필터기능
        state.value.conferenceList = state.value.conferenceList.filter(title => state.value.searchValue in title) // 호스트 검색 ||
      }, 3000)
    };

    // 회의실 생성 방법2
    const createConference = function () {
      if (state.value.isLogin) {
        router.push({ name: 'createConference'})
      } else {
        router.push({ name: 'conferenceList'})
      }
    };


    return { 
      getConferences,
      searchConference,
      createConference,
      titlerule,
      namerule,
      router,
      dense,
      state,
      token
    }
    
  }
}
</script>

<style>
  .input {
    width: 50%;
    
  }
  
  .infinite-list .infinite-list-item {
  min-width: 335px;
  max-width: 25%;
  display: inline-block;
  cursor: pointer;
  } 
  .infinite-list {
  padding-left: 0;
  max-height: calc(100% - 35px);
  }
  
  .create {
    margin: 20px;
  }
  .link {
      text-decoration: none;
      display: flex;
      align-items: center;
      height: 100%;
      width: 100%;
      font-size: 18px;
      color: black;
  }
  .contents {
    height: 100vh;
  }
  .content {
    padding: 10px;
    border: solid 1px;
    border-radius: 20px;
  }
</style>