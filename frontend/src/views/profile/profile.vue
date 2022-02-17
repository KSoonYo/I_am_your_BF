<template>
  <div class="flex column items-center" style="height:100vh; margin-top: 30px " >
    <div style="max-width: 1246px; width:100%">
      <h1 class="cont-title">프로필 설정</h1>
      <q-form class="update-profiles" ref='profileForm'>
        <div class="flex row justify-center">
          <div class="flex row">
            <!-- 선택별 이미지 -->
            <div class="q-ma-xl">
              <q-img v-if="state.now == 'now'" :src='state.form.src' style="width:150px; height:150px;"></q-img>
              <q-img v-if="state.now == 'def'" :src='state.basic' style="width:150px; height:150px"></q-img>
              <q-img v-if="state.now == 'pick'" :src='state.showthumbnail' style="width:150px; height:150px"></q-img>
            </div>
            <!-- 프로필 이미지 선택 -->
            <div class="column justify-center q-ml-xl">
              <q-radio v-model="state.now" val="now" label="현재 프로필 이미지"></q-radio>
              <q-radio v-model="state.now" val="def"  label="기본 이미지로 변경" ></q-radio>
              <q-radio v-model="state.now" val="pick" label="사진 불러오기 (jpg, png)"></q-radio>
            </div>
          </div>
        </div>
        <div class="flex justify-center">
          <!-- 이미지 파일 업로드 -->
          <q-file
              v-if="state.now == 'pick'"
              field-name="file"
              class='q-mt-lg flex justify-center items-center'
              v-model='state.thumbnail'
              accept='image/*'
              max-total-size='10485760'
              @rejected='onRejected'
              use-chips
              style='border: solid 1px rgba(0, 0, 0, .2); border-radius: 10px; width:50%; '
              :label='state.labelmessage'
              url="http://i6b107.p.ssafy.io/api/image/upload">
            </q-file>
        </div>
        <div class="flex justify-center">
          <q-separator class="q-mt-xl q-mb-xl" style="width:80%;"/>
        </div>
        <div class="column justify-center items-center" style="width:100%; margin-bottom: 20px;">
          <div style="width:50%">
            <!-- 이름(별명) -->
            <label for='Name' style="padding-left:10px">이름(별명)</label>
            <q-input outlined v-model="state.form.userName" label='이름(별명)'
            :rules='state.rules.userName'
            >
            </q-input>
          </div>
          <!-- 이메일 -->
          <div style="width:50%">
            <q-checkbox v-model="state.email" label="이메일 변경"/>
            <div v-if="state.email">
              <label for='Email'>이메일</label>
              <q-input outlined v-model="state.userEmailCh" label='이메일'
              ref='emailInput'
              :rules='state.rules.userEmail'
              >
              <q-btn rounded @click="clickEmailCheck" label='중복검사' style='background-color: #929291; color: #fff; margin-top: 10px; margin-bottom: 10px; margin-right: 5px; min-width:100px'></q-btn>
              </q-input>
            </div>
          </div>
          <!-- 비밀번호 -->
          <div style="width:50%">
            <q-checkbox v-model="state.private" label="비밀번호 변경"/>
            <div v-if="state.private">
              <label for='Password'>현재 비밀번호</label>
              <q-input outlined v-model="state.passw" label='현재 비밀번호'
              :type="isPwd ? 'password' : 'text'"
              >
                <template v-slot:append>
                  <q-icon
                    :name="isPwd ? 'visibility_off' : 'visibility'"
                    class='cursor-pointer'
                    @click="isPwd = !isPwd"
                  />
                </template>
              </q-input>

              <label for='Password'>새 비밀번호</label>
              <q-input outlined v-model="state.form.password" label='새 비밀번호'
              :rules='state.rules.password'
              :type="isPwd ? 'password' : 'text'"
              >
                <template v-slot:append>
                  <q-icon
                    :name="isPwd ? 'visibility_off' : 'visibility'"
                    class='cursor-pointer'
                    @click="isPwd = !isPwd"
                  />
                </template>
              </q-input>
              <!-- 비밀번호 확인 -->
              <label for='PasswordChk'>새 비밀번호 확인</label>
              <q-input outlined v-model="state.form.passwordChk" label='새 비밀번호 확인'
              :rules='state.rules.password'
              type='password'
              >
              </q-input>

            </div>
          </div>
        </div>
        <!-- 프로필 업데이트, 취소 버튼 -->
        <div class="flex justify-center">
          <q-btn class="q-ma-md" style="background-color: #55b955; color: #fff; min-width: 200px; max-width: 200px; height:40px; padding:0; line-height:40px; font-size:16px;" @click="clickUpdate">저장</q-btn>
          <q-btn class="q-ma-md" style="background-color: #929291; color: #fff; min-width: 200px; max-width: 200px; height:40px; padding:0; line-height:40px; font-size:16px;" @click="cancel">취소</q-btn>
        </div>
      </q-form>
    </div>
  </div>

                  
                    
</template>

<script>
import { onMounted, ref, watch, watchEffect } from 'vue'
import { useRouter } from 'vue-router'
import { useQuasar } from 'quasar'
import { useStore } from 'vuex'
import defaultImage from '@/assets/profile-default.png'

export default {
  name: 'profile',

  setup() {
    const emailInput = ref(null)
    const profileForm = ref(null)
    const router = useRouter();
    const store = useStore()
    const $q = useQuasar()
    // 이미지 파일 제한걸기
    function onRejected() {
      $q.notify({
        type: 'negative',
        message: `이미지 파일이 아니거나 파일 크기가 너무 큽니다.`
      })
    }
    onMounted(() => {
      // 유저 정보 받기
      store.dispatch('getUserInfo')
        .then(res => {
          state.value.form.userName = res.data.userName
          state.value.form.userEmail = res.data.userEmail
          state.value.userEmailCh = res.data.userEmail
          state.value.form.src = res.data.thumbnail
        })
        .then(() => {
          // 유저의 기본 사진의 유무에 따른 이미지 선정
          if (!state.value.form.src) {
            state.value.form.src = defaultImage
            console.log('기본 사진 설정완료')
          } else {
            if (state.value.form.src == 'profile-default.png') {
              state.value.form.src = defaultImage
            } else {
              store.dispatch('getThumbnail', state.value.form.src)
              .then((res) => {
                state.value.form.src = process.env.VUE_APP_BASE_URL + '/' + res.config.url
              })
            }
          }
        })
        .catch(() => {
          alert('로그인을 확인해주세요')
          router.push({name: 'conferenceList'})
        })
    })

    // 이메일 형식 체크 정규 표현식
    const emailChecker = /^([\w._-])*[a-zA-Z0-9]+([\w._-])*([a-zA-Z0-9])+([\w._-])+@([a-zA-Z0-9]+\.)+[a-zA-Z0-9]{2,}$/

    // 영어, 숫자, 특수문자 조합 9자리 이상
    const passwordChecker =  /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{9,}$/


    const state = ref({
      form: {
        userName : '',
        userEmail : '',
        password : '',
        passwordChk : '',
        src: '',
      },
      // 이름, 이메일, 비밀번호, 비밀번호 확인 조건문
      rules: {
                userName: [
                    val => val.trim() !== '' || '필수 입력입니다.',
                    val => val.replaceAll(' ','').length <= 12 || '12자 이하로 입력해주세요.',
                ],
                userEmail : [
                    val => emailChecker.test(val) || '이메일 형식을 지켜주세요.',
                ],
                password : [
                    val => val.trim() !== '' || '비밀번호를 입력해주세요.' ,
                    val => val.replaceAll(' ','').length >= 9 && val.replaceAll(' ','').length <= 16 || '비밀번호는 9자 이상 16자 이하입니다.',
                    val => passwordChecker.test(val) || '영어 대소문자, 숫자, 특수문자를 포함해야 합니다.',
                ],
                passwordChk : [
                    val => val.trim() !== '' || '비밀번호를 입력해주세요.', 
                    val => val === state.value.form.password || '비밀번호가 일치하지 않습니다.'
                ],
            },
      thumbnail: '',
      showthumbnail: defaultImage,
      userEmailCh: '',
      src: '',
      result: '',
      now: ref('now'),
      private: false,
      passw: '',
      basic: defaultImage,
      labelmessage:'이미지 파일 업로드(최대 10MB)',
      email: false,
    });

    // 이메일 중복 검사
    const checkedEmail = ref('')
    const clickEmailCheck = function(event){
      event.preventDefault()
      emailInput.value.validate()

      state.value.userEmailCh = state.value.userEmailCh.replaceAll(' ','')
      if(!emailInput.value.hasError){
          store.dispatch('requestGetUser', state.value.userEmailCh)
          .then(()=>{
            console.log('이메일 확인')
            checkedEmail.value = state.value.userEmailCh
            $q.notify({
                type: 'positive',
                message: '가능한 이메일 입니다.'
            })
          })
          .catch(()=>{
              checkedEmail.value = ''
              $q.notify({
                  type: 'negative',
                  message: '이미 가입된 이메일 입니다.'
              })
          })        
      }
    }
    // 프로필 업데이트
    const clickUpdate = function(event){
      event.preventDefault()
      // 이미지 선택지에 따른 프로필 사진 설정
      if (state.value.now == 'now') {
        state.value.result = state.value.form.src
      } else if (state.value.now == 'def') {
        state.value.result = 'profile-default.png'
      } else if (state.value.now == 'pick') {
        // 파일 첨부시 이미지 파일 업로드
        state.value.result = state.value.thumbnail
      }
      // 업데이트 유효성 검사
      profileForm.value.validate().then(success => {
          if ( success ){
            // 비밀번호 변경 체크박스 클릭 여부
            if (state.value.private) {
              store.dispatch('changePassword', {
                currentPassword : state.value.passw,
                newPassword  : state.value.form.password
              })
              .then(() => {
                alert('변경완료')
              })
            }
            state.value.form.userName = state.value.form.userName.replaceAll(' ','')
            // 이메일 변경
            if (state.value.email) {
              state.value.userEmailCh = state.value.userEmailCh.replaceAll(' ','')
              if(state.value.userEmailCh !== checkedEmail.value){
                $q.notify({
                  type: 'info',
                  message: '이메일 중복 검사가 필요합니다.'
                })
                return
              }
              // 프로필 업데이트
              store.dispatch('updateProfile', {
                  userId : JSON.parse(localStorage.getItem('userInfo')).userId,
                  userName : state.value.form.userName,
                  userEmail : state.value.userEmailCh,
                  thumbnail : state.value.result,
                  userDescription : '',
              })
              .then(()=>{
                  alert('유저 정보 수정 완료')
                  router.push({name: 'conferenceList'})
              })
              .catch(()=>{
                  alert('수정 실패')
              })
            } else {
              // 프로필 업데이트

              console.log(state.value.form.userEmailCh)
              store.dispatch('updateProfile', {
                  userId : JSON.parse(localStorage.getItem('userInfo')).userId,
                  userName : state.value.form.userName,
                  userEmail : state.value.form.userEmail,
                  thumbnail : state.value.result,
                  userDescription : '',
              })
              .then(()=>{
                  alert('유저 정보 수정 완료')
                  router.push({name: 'conferenceList'})
              })
              .catch(()=>{
                  alert('수정 실패')
              })
            }
          } 
      })
    }
    // 취소 버튼클릭 이벤트
    const cancel = function () {
      router.go(-1)
    }

    // 사진 src 변화에 따른 썸네일 변경
    watch(state.value.thumbnail)
    watchEffect(() => {
      if (state.value.thumbnail) {
        const formData = new FormData()
        formData.append('file', state.value.thumbnail)
        console.log(formData)
        store.dispatch('uploadThumbnail', formData)
          .then((thumbnail) => {
            console.log(thumbnail)
            state.value.thumbnail = thumbnail.data.thumbnail
            store.dispatch('getThumbnail', state.value.thumbnail)
            .then((res) => {
              // console.log(process.env.VUE_APP_BASE_URL + res.config.url)
              state.value.showthumbnail = process.env.VUE_APP_BASE_URL + '/' + res.config.url
              state.value.labelmessage = '사진을 바꾸시려면 다시 클릭해주세요.'
            })
            .catch(() => {
              console.log('실패')
            })
          })
          .catch(() => {
            console.log('노상관')
          })
      } else {
        state.value.thumbnail = defaultImage
      }
    })

    return { state, emailInput, onRejected, clickEmailCheck, cancel, clickUpdate, profileForm, isPwd: ref(true) }
  }


// 처음에 thumbnail 이미지가 생성될때 watch로 가지는데 이거 취소하는법이 있는지?


}
</script>

<style scoped>
.cont-title {
  margin-bottom: 26px;
  margin-bottom: 2.6rem;
  padding-bottom: 10px;
  line-height: 28px;
  border-bottom: 1px solid #e5e5e5;
  font-size: 24px;
  font-weight: 600;
  color: #333;
}

</style>
