<template>
    <div class='container column items-center col-6 justify-center' style='padding: 10px; height: 80vh;'>
      <img src='@/assets/logo.png' alt='로고' class='logo'>
      <p style='font-size:30px'>회의실 생성</p>  
      <form class='column' style='width: 350px; '>
        <q-input class='q-mb-sm' v-model='state.title' type='text' label='회의실 제목 *'
          :rules='[ 
            val => !!val.trim() || "필수 항목입니다.",
            val => val.trim().length <= 30 || "30자 이내로 작성 가능합니다.",
          ]'
        />

        <q-input class='q-mb-sm' v-model='state.content' type='textarea' label='회의실 설명'/>

        <q-file
          v-model='state.thumbnail'
          label='이미지 파일 업로드(최대 8KB)'
          accept='image/*'
          max-total-size='8192'
          @rejected='onRejected'
          use-chips
          style='border: dashed 2px green; border-radius: 10px'
        >
          <template v-slot:prepend>
            <q-icon name='cloud_upload' />
          </template>
        </q-file>
        <q-btn @click='qweqwe'>출력</q-btn>
        

        <q-checkbox v-model='state.private' label='비공개방' color='cyan'/>
        <q-input v-if='state.private' class='q-mb-sm' v-model='state.password' type='text' style='min-width: 335px;' label='회의실 비밀번호(10자 이내 숫자만 입력)'
          :rules='[ 
            val => val.trim().length <= 10 || "10자 이내로 작성 가능합니다.",
            val => val.trim() == val.replace(/[^0-9]/g,"") || "숫자만 입력 가능합니다."
          ]'
        />


        <q-btn color='secondary' dense :loading='state.loading' @click='createConferenceRoom' style='b'>회의실 생성</q-btn>
      </form>
    </div>

</template>

<script>
// 필수, 선택 구분, 전역가드, 비밀번호 선택시 input등장 multipart
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import { useQuasar } from 'quasar'

export default {
    name: 'CreateConference',
    
    setup() {
      const router = useRouter();
      const dense = ref(false);
      const token = localStorage.getItem('jwt')
      const $q = useQuasar()
      
      
      const qweqwe = function () {
        console.log(state.value.thumbnail)
      }
      function onRejected () {
        $q.notify({
          type: 'negative',
          message: `이미지 파일이 아니거나 파일 크기가 너무 큽니다.`
        })
      }
      if (token) {
        state.value.isLogin = true
      }
      const state = ref({
          loading: false,
          title: '',
          content: '',
          thumbnail: ref(null),
          password: null,
          isLogin: false,
          filename: '',
          private: false
      });
      const setprivate = function () {
        if (state.value.private) {
          state.value.private = false
        } else {
          state.value.private = true
        }
      };
      const createConferenceRoom = function () {
        if (state.value.isLogin) {
          state.value.loading = true
          setTimeout(() => {
            state.value.loading = false
            if (!state.value.private) {
              state.value.password = ''
            }
            axios({
              method: 'post',
              url: 'http://localhost:8080/conferences/create',
              params: {
                conferenceRoomTitle: state.value.title,
                conferenceRoomPassword: state.value.password,
                conferenceRoomDescription: state.value.content,
                conferenceRoomThumbnail: state.value.thumbnail,
                // 아이디를 넘겨줘야하지 않나?
              }
            })
              .then(() => {
                  router.push({ name: 'ConferenceRoom' })
              })
          }, 3000)
        } else {
          alert('로그인해주세요')
          router.push({ name: 'ConferenceRoom'}) // 수정요망(로그인으로 이동)
        }
      };

      

      return {
        setprivate,
        onRejected,
        createConferenceRoom,
        // drop, 
        // selectedFile,
        qweqwe,
        state,
        router,
        dense,
        token,
      }
      
    }
}
</script>

<style>
.logo{
    width: 120px;
    height: 100px;
}
</style>