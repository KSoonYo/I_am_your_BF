<template>
  <navbarConference/>
  <div style="background-image:url(assets/img/gallery/bg.png);">
    <div class="q-pt-lg"> 
      <!-- 강의실 생성 방법1 -->
      
      <div class='justify-end flex q-mt-lg' style="padding: 75px 10% 0px 0px">
        <div class="search q-mr-md" :class="{ active: state.open }" @keyup.enter="searchConference">
          <div class="icon" @click="move"></div>
          <div class="input">
            <input type="text" v-model='state.searchValue' maxlength='20' placeholder="강의실 제목, 호스트를 검색">
          </div>
          <span v-if="state.searchValue != ''" class="clear" @click="state.searchValue = ''"></span>
        </div>
        <div class="flex justify-center items-center">
          <q-btn @click='numrule' flat style='color: #ddb193' v-if="state.numasc">방번호<i class="fas fa-angle-up"></i></q-btn>
          <q-btn @click='numrule' flat style='color: #ddb193' v-if="!state.numasc">방번호<i class="fas fa-angle-down"></i></q-btn>
          <q-btn @click='titlerule' flat style='color: #ddb193' v-if="state.titleasc">제목순<i class="fas fa-angle-up"></i></q-btn>
          <q-btn @click='titlerule' flat style='color: #ddb193' v-if="!state.titleasc">제목순<i class="fas fa-angle-down"></i></q-btn>
          <q-btn @click='namerule' flat style='color: #ddb193' v-if="state.nameasc">이름순<i class="fas fa-angle-up"></i></q-btn>
          <q-btn @click='namerule' flat style='color: #ddb193' v-if="!state.nameasc">이름순<i class="fas fa-angle-down"></i></q-btn>
        </div>
        <div class="flex justify-center items-center">
          <router-link :to='{ name: "CreateConference"}' style='text-decoration: none;'><q-btn rounded style="background: #E6A377; color: #FFFFFF; min-width:100px;">강의실 생성</q-btn></router-link> 
        </div>
      </div> 
      <br>
      <div class="flex justify-center items-center">
        <div v-if='state.perPageList' class="row flex justify-center">
          <Conference 
            v-for='conference in state.perPageList' :key='conference.id'
            :conference="conference"
            class="justify-center items-center flex col-sm-6 col-4"
            style="max-width:486px; max-height:242px; width:100%; height:100%"
          />
        </div>
        <div v-else>
          <p>강의실이 없습니다.</p>
        </div>
      </div>
      <div class="justify-center items-center flex">
        <q-pagination
          size='30px'
          input-style='color: #fb8c00'
          v-if="state.conferenceList"
          v-model="state.currentpage"
          :max="state.maxpage"
          input
          color='orange-7'
        />
      </div>
    </div>
  </div>
  
</template>

<script>
// 제목 검색시 쿼리 동적 전달방식 이용
// import conference from './components/conference'

import { ref, onMounted, watch, watchEffect } from 'vue'
import { useRouter } from 'vue-router'
// import axios from 'axios'
import Conference from './components/conference'
import navbarConference from './components/navbar-conference'
import { useStore } from 'vuex'

export default {
  name: 'conferenceList',
  
  components: {
    Conference,
    navbarConference,
  },
  
  setup () {
    const store = useStore()

    onMounted(() => {
      const token = localStorage.getItem('accessToken')
      if (!token) {
        router.push( {name:"home"} )
      }
      // 회의실 불러오기
      store.dispatch('getConference')
        .then(res => {
          state.value.conferenceList = res.data
          state.value.maxpage = Math.ceil(state.value.conferenceList.length/30)
          state.value.perPageList = state.value.conferenceList.slice(30)
        })
        .catch(() => {
          console.log('에러')
        })
    })
    // router 불러오기
    const router = useRouter();
    // 반응형 버튼
    const dense = ref(false);

    // drawer(사이드바)
    const drawer = ref(true)

    const move = function () {
      state.value.open = !state.value.open
    }


    // 데이터 값 설정
    const state = ref({
      // 검색 입력 값
      perPageList: null,
      searchValue: '',
      loading: false,
      conferenceList: null,
      isLogin: false,
      titleasc: false,
      numasc: false,
      nameasc: false,
      open: false,
      currentpage: ref(1),
      maxpage: ref(0),
    });

    const token = localStorage.getItem('accessToken')
    if (token) {
      state.value.isLogin = true
    }
    const titlerule = function () {
      // 회의실 불러오기
      store.dispatch('getConference')
        .then(res => {
          // 제목 오름,내림차순 정렬
          if (state.value.titleasc) {
            console.log(res.data)
            state.value.titleasc = false
            state.value.conferenceList = res.data.sort(function(a,b){
              if(a.title > b.title){
                return 1;
              }else if (a.title < b.title){
                return -1;
              }else{
                return 0;
              }
            })
          } else {
            console.log(res.data)
            state.value.titleasc = true
            state.value.conferenceList = res.data.sort(function(a,b){
              if(a.title > b.title){
                return -1;
              }else if (a.title < b.title){
                return 1;
              }else{
                return 0;
              }
            })
          }
          // console.log(state.value.conferenceList)
        })
        .catch(err => {
          console.log(err)
        })
      
    }
    const numrule = function () {
      store.dispatch('getConference')
        .then(res => {
          // 방번호 오름,내림차순 정렬
          if (state.value.numasc) {
            state.value.numasc = false
            state.value.conferenceList = res.data.sort(function(a,b){
              if(a.id > b.id){
                return 1;
              }else if (a.id < b.id){
                return -1;
              }else{
                return 0;
              }
            })
          } else {
            state.value.numasc = true
            state.value.conferenceList = res.data.sort(function(a,b){
              if(a.id > b.id){
                return -1;
              }else if (a.id < b.id){
                return 1;
              }else{
                return 0;
              }
            })
          }
          // console.log(state.value.conferenceList)
        })
        .catch(err => {
          console.log(err)
        })
    }
    const namerule = function () {
      store.dispatch('getConference')
        .then(res => {
          // 이름 오름,내림차순 정렬
          if (state.value.nameasc) {
            state.value.nameasc = false
            state.value.conferenceList = res.data.sort(function(a,b){
              if(a.userName > b.userName){
                return 1;
              }else if (a.userName < b.userName){
                return -1;
              }else{
                return 0;
              }
            })
          } else {
            state.value.nameasc = true
            state.value.conferenceList = res.data.sort(function(a,b){
              if(a.userName > b.userName){
                return -1;
              }else if (a.userName < b.userName){
                return 1;
              }else{
                return 0;
              }
            })
          }
          // console.log(state.value.conferenceList)
        })
        .catch(err => {
          console.log(err)
        })
    }

    // 회의실 검색
    const searchConference = function () {
        store.dispatch('getConference')
          .then(res => {
            state.value.conferenceList = []
            for (const data of res.data) {
              if (data.title.includes(state.value.searchValue)) {
                state.value.conferenceList.push(data)
              } else if (data.userName.includes(state.value.searchValue)) {
                state.value.conferenceList.push(data)
              }
            }
            state.value.maxpage = Math.ceil(state.value.conferenceList.length/30)
            state.value.perPageList = state.value.conferenceList.slice(30)
          })
          .catch(err => {
            console.log(err)
          })
    };

    // 회의실 생성 방법2
    const createConference = function () {
      if (state.value.isLogin) {
        router.push({ name: 'CreateConference'})
      } else {
        router.push({ name: 'conferenceList'})
      }
    };

    
    watch(state.value.currentpage)
    watchEffect(() => {
      if (state.value.conferenceList) {
        const start = (state.value.currentpage-1) * 30
        const end = start + 30
        state.value.perPageList = state.value.conferenceList.slice(start, end)
      }
    })


    return { 
      move,
      numrule,
      searchConference,
      createConference,
      titlerule,
      namerule,
      router,
      dense,
      state,
      token,
      drawer
    }
    
  }
}
</script>

<style scoped> 
  .link {
      text-decoration: none;
      display: flex;
      align-items: center;
      height: 100%;
      width: 100%;
      font-size: 18px;
      color: black;
  }
  .search{
    position: relative;
    width: 45px;
    height: 45px;
    background: #fff;
    border-radius: 60px;
    transition: 0.5s;
    box-shadow: 0 0 0 1px rgb(230, 163, 119);
    overflow: hidden;
  }
  .search.active {
    width: 360px;
  }
  .search .icon {
    position: absolute;
    top: 0;
    left: 0;
    width: 45px;
    height: 45px;
    background: #fff;
    border-radius: 60px;
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;;
    cursor: pointer;
  }
  .search .icon::before {
    content: '';
    position: absolute;
    width: 15px;
    height: 15px;
    border: 3px solid rgb(230, 163, 119);
    border-radius: 50%;
    transform: translate(-4px, -4px);
  }
  .search .icon::after {
    content: '';
    position: absolute;
    width: 3px;
    height: 12px;
    background: rgb(230, 163, 119);
    transform: translate(6px, 6px) rotate(315deg);
  }
  .search .input {
    position: relative;
    width: 300px;
    height: 45px;
    left: 45px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .search .input input{
    position: absolute;
    top: 0;
    width: 100%;
    height: 100%;
    border: none;
    outline: none;
    font-size: 18px;
    padding: 10px 0;
  }
  .clear {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    width: 15px;
    height: 15px;
    right: 15px;
    cursor: pointer;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .clear::before {
    position: absolute;
    content: '';
    width: 1px;
    height: 15px;
    background: #999;
    transform: rotate(45deg);
  }
  .clear::after {
    position: absolute;
    content: '';
    width: 1px;
    height: 15px;
    background: #999;
    transform: rotate(315deg);
  }
</style>