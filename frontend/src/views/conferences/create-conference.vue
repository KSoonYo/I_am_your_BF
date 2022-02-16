<template>
  <div class='column items-center flex justify-center' style='min-height:100vh; background-image:url(../../../public/assets/img/illustrations/big-main.png); '>
		<div class='container'>
			<div class='row justify-center backimg'>
				<div class='col-12 flex items-center justify-center'>
					<div class='bg-white shadow-3' style='max-width: 1000px; width: 100%; height: 100vh; max-height: 800px; padding: 3rem; border: 0; border-radius: 0.5rem'>
						<div>
							<!-- 메인 상단 글씨 -->
							<div class='text-center q-mb-4'>
								<h3 style='margin-bottom: 0px'> 강의실 생성 </h3>
								<br>
								<p style='font-size: 1rem; font-weight: 400; line-height: 1.6; margin-top: 0; margin-bottom: 1rem;'></p>
							</div>

							<div class='flex justify-center'>
								<q-form ref='CreateConferenceForm' class='column create-conference-form'>
									
									<!-- 강의실 제목 -->
									<label for='title'>강의실 제목<span style='color: red;'>*</span></label>
									<q-input outlined v-model="state.title" label='강의실 제목'
                    :rules='[ 
                      val => !!val.trim() || "필수 항목입니다.",
                      val => val.trim().length <= 30 || "30자 이내로 작성 가능합니다.",
                    ]'
                  >
										<template v-slot:prepend>
											<i class='fab fa-battle-net'></i>
										</template>
									</q-input>

									<!-- 강의실 설명 -->
									<label for='content'>강의실 설명</label>
									<q-input outlined v-model="state.content" label='강의실 설명'
                    type='textarea'
									>
									</q-input>

                  <!-- 비공개방 설정 -->
                  <q-checkbox v-model='state.private' label='비공개방' color='cyan'/>

                  <!-- 비밀번호 입력 -->
                  <q-input v-if='state.private' class='q-mb-sm' v-model='state.password' style='min-width: 335px;' label='강의실 비밀번호(10자 이내 숫자만 입력)'
                    :type="isPwd ? 'password' : 'text'"
                    :rules='[ 
                      val => val.trim().length <= 10 || "10자 이내로 작성 가능합니다.",
                      val => val.trim() == val.replaceAll(/[^0-9]/g,"") || "숫자만 입력 가능합니다."
                    ]'
                    outlined
                  >
                    <template v-slot:prepend>
											<i class='fas fa-unlock-alt'></i>
										</template>
										<template v-slot:append>
											<q-icon
												:name="isPwd ? 'visibility_off' : 'visibility'"
												class='cursor-pointer'
												@click="isPwd = !isPwd"
											/>
										</template>
                  </q-input>

                  <!-- 이미지 파일 업로드 -->
                  <q-form>
                    <q-file
                      field-name="file"
                      class='q-mt-lg flex justify-center items-center'
                      v-model='state.thumbnail'
                      accept='image/*'
                      max-total-size='10485760'
                      @rejected='onRejected'
                      use-chips
                      style='border: dashed 2px black; border-radius: 10px; width:100%'
                      label='이미지 파일 업로드(최대 10MB)'
                      url="http://localhost:8080/api/image/upload"
                    >
                    <template v-slot:prepend>
                        <i class="fas fa-cloud-upload-alt"></i>
                    </template>
                    
                    </q-file>
                  </q-form>
                  <!-- 강의실 생성 버튼 -->
                  <q-btn @click='createConferenceRoom' label='강의실 생성' type='submit' style='background-color:#F17228; color:#fff; margin-top: 20px; border-radius:10px' />
                
								</q-form>
							</div>
						</div> 
					</div>
				</div>
			</div>
		</div>
	</div>


    

</template>

<script>
// 필수, 선택 구분, 전역가드, 비밀번호 선택시 input등장 multipart
import { ref } from 'vue'
import { useRouter } from 'vue-router'
// import axios from 'axios'
import { useQuasar } from 'quasar'
import { useStore } from 'vuex'

export default {
    name: 'CreateConference',
    
    setup() {
      const CreateConferenceForm = ref(null)
      const router = useRouter();
      const dense = ref(false);
      const store = useStore()
      const $q = useQuasar()

      // 이미지 파일 유효성 검사
      function onRejected () {
        $q.notify({
          type: 'negative',
          message: `이미지 파일이 아니거나 파일 크기가 너무 큽니다.`
        })
      }
      const state = ref({
          title: '',
          content: '',
          thumbnail: ref(null),
          password: '',
          isLogin: false,
          filename: '',
          private: false,
      });

      // 강의실 생성
      const createConferenceRoom = function (event) {
        event.preventDefault()

        CreateConferenceForm.value.validate().then(success => {
          if (success) {
            const formData = new FormData()
            formData.append('file', state.value.thumbnail)
            // 비밀번호 체크박스 확인
            if (!state.value.private) {
              state.value.password = ''
              console.log('비밀번호 인식')
            }
            // 사진 업로드
            if (state.value.thumbnail) {
              store.dispatch('uploadThumbnail', formData)
                .then((thumbnail) => {
                  console.log(thumbnail)
                  // 강의실 생성
                  store.dispatch('createConference', {
                    'title': state.value.title,
                    'description': state.value.content,
                    'userId': JSON.parse(localStorage.getItem('userInfo')).userId,
                    'thumbnail': thumbnail.data.thumbnail,
                    'password' : state.value.password
                  })
                  .then((res) => {
                    // 생성된 강의실로 이동
                    router.push({ name: 'session-test', params: { conferenceId : res.data.id }})
                  })
                  .catch(() => {
                    $q.notify({
                      type: 'negative',
                      message: `회의실 생성 실패`
                    })
                  })
                });
            } else {
              store.dispatch('createConference', {
                    'title': state.value.title,
                    'description': state.value.content,
                    'userId': JSON.parse(localStorage.getItem('userInfo')).userId,
                    'thumbnail': '202201279661680879100young-people-giving-high-five-illustrations-set_23-2148373642.jpg',
                    'password' : state.value.password
                  })
                  .then((res) => {
                    // 생성된 강의실로 이동
                    router.push({ name: 'session-test', params: { conferenceId : res.data.id }})
                  })
                  .catch(() => {
                    $q.notify({
                      type: 'negative',
                      message: `회의실 생성 실패`
                    })
                  })
            }
          }
        })
        
      };
    

      

      return {
        onRejected,
        createConferenceRoom,
        // drop, 
        // selectedFile,
        state,
        router,
        dense,
        isPwd: ref(true),
        CreateConferenceForm,
      }
      
    }
}
</script >

<style scoped>
.logo{
    width: 120px;
    height: 100px;
}
.create-conference-form{
    width: 30rem;
}

.input-group {
    position: relative;
    display: flex;
    flex-wrap: wrap;
    align-items: stretch;
    width: 100%;
}
.input-group-text {
    display: flex;
    align-items: center;
    padding: 0.5rem 0.625rem;
    font-size: 0.875rem;
    font-weight: 400;
    line-height: 1.5;
    color: #6B7280;
    text-align: center;
    white-space: nowrap;
    background-color: #ffffff;
    border: 0.0625rem solid #D1D5DB;
    border-radius: 0.5rem;
    
}

.form-control {
    display: block;
    width: 100%;
    padding: 0.5rem 1rem;
    font-size: 0.875rem;
    font-weight: 400;
    line-height: 1.5;
    color: #6B7280;
    background-color: #ffffff;
    background-clip: padding-box;
    border: 0.0625rem solid #D1D5DB;
    -webkit-appearance: none;
    appearance: none;
    border-radius: 0.5rem;
    box-shadow: 0 1px 2px 0 rgb(0 0 0 / 7%);
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}
label {
    font-weight: 600;
    margin-bottom: 0.5rem;
		display: inline-block;
}
</style>