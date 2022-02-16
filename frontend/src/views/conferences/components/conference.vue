<template>
  <div class="q-ma-md">
    <q-img class='my-card concard shadow-5 no-border' :fit="scale-down" :src="state.thumbnail">
      <q-card class="no-border" no-body style="width:100%; height:100%;">
          <div class='text-h3' style="opacity:0.7">
            <!-- 방 번호 -->
            <p>{{ conference.id }}</p>
          </div>
          <div class='text-h5' style="opacity:0.7">
            <!-- 방 제목 -->
            <p>{{ conference.title }}</p>
          </div>
          <!-- 방 호스트 이름 -->
          <p style="opacity:0.7">{{ conference.userName }}</p>
      </q-card>
      <div class="overlay flex column justify-center items-center">
        <!-- 방 설명 -->
        <div class="text-h6">{{ conference.description }}</div>
        <br>
        <br>
        <!-- 입장 버튼 -->
        <q-btn label="입장하기" rounded color="positive" @click='openConference(state.id)'></q-btn>
      </div>
    </q-img>
    
  </div>
</template>

<script scoped>
// import axios from 'axios'
import { useRouter } from 'vue-router'
import { ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useQuasar } from 'quasar'

export default {
  name: 'Conference',

  props: {
    conference: Object,
  },

  
  setup (props) {
    const $q = useQuasar()
    const store = useStore()
    onMounted(() => {
      // 사진 가져오기
      store.dispatch('getThumbnail', props.conference.thumbnail)
        .then((res) => {
          // console.log(process.env.VUE_APP_BASE_URL + res.config.url)
          state.value.thumbnail = process.env.VUE_APP_BASE_URL + '/' + res.config.url
        })
        .catch(() => {
          console.log('실패')
        })
    })
    const state = ref({
      thumbnail: '',
      password: '',
      id: props.conference.id,
    });

    // 강의실 생성
    const router = useRouter()
    const openConference = function (id) {
      // 아이디 가져오기
      store.dispatch('getConferenceDetail', id)
        .then((res) => {
          state.value.password = res.data.password
          // 비밀번호 유무 확인
          if (state.value.password) {
            // 모달창 생성
            $q.dialog({
              title: '비밀번호 확인',
              message: '비밀번호를 입력해주세요. (10자 이내의 숫자)',
              prompt: {
                model: '',
                isValid: val => val.trim().length <= 10 && val.trim() == val.replaceAll(/[^0-9]/g,""), // << here is the magic
                type: 'password' // optional
              },
              cancel: true,
              persistent: true
            }).onOk(data => {
              // 비밀번호 일치 확인
              if (state.value.password == data) {
                // 강의실로 이동
                router.push({
                  name: 'session-test',
                  params: {
                    conferenceId : id
                  }
                })
              } else {
                // 실패 알람 생성
                $q.notify({
                    type: 'negative',
                    message: '비밀번호가 일치하지 않습니다.'
                })
              }        
            })
          } else {
            // 강의실로 이동
            router.push({
              name: 'session-test',
              params: {
                conferenceId : id
              }
            })
          }
        })
        // 강의실을 불러올 수 없는 경우
        .catch (() => {
          alert('강의실을 찾지 못했습니다.')
        })
      
      
    }
    
    return { state, openConference }
  }
}
</script>

<style>
.my-card {
  width: 100%;
  max-width: 486px;
  border-radius: 20px;
}
.concard {
  min-width: 243px; 
  min-height: 121px; 
  max-width: 486px;
  max-height: 242px;
  margin: 10px;
}

.overlay {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  height: 100%;
  width: 100%;
  opacity: 0;
  transition: .5s ease;
  /* background-color: white; */
  /* background-color: #484d4e; */
}

.my-card:hover .overlay {
  opacity: 1;
  font-weight: bolder;
}

</style>