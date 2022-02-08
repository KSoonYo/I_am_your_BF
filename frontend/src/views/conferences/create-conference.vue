<template>
  <div class='column items-center bg-grey-3 flex justify-center' style='min-height:100vh'>
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
                      val => val.trim() == val.replace(/[^0-9]/g,"") || "숫자만 입력 가능합니다."
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
                  <q-file
                    class='q-mt-lg flex justify-center items-center'
                    v-model='state.thumbnail'
                    accept='image/*'
                    max-total-size='8192'
                    @rejected='onRejected'
                    use-chips
                    style='border: dashed 2px black; border-radius: 10px'
                    label='이미지 파일 업로드(최대 8KB)'
                  >
                  <template v-slot:prepend>
											<i class="fas fa-cloud-upload-alt"></i>
                  </template>
                  
                  </q-file>

									

									<!-- 강의실 생성 버튼 -->
									<q-btn @click='createConferenceRoom' :loading='state.loading' color='black' label='강의실 생성' type='submit' style='margin-top: 20px; border-radius:10px' />
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
import axios from 'axios'
import { useQuasar } from 'quasar'
// import { useStore } from 'vuex'

export default {
    name: 'CreateConference',
    
    setup() {
      // const store = useStore()
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
              url: 'http://localhost:8080/api/image/upload',
              params: {
                thumbnail: state.value.thumbnail,
                
              }
              .then((thumbnail) => {
                axios({
                  method: 'post',
                  url: 'http://localhost:8080/api/',
                  params: {
                    title: state.value.title,
                    password: state.value.password,
                    description: state.value.content,
                    thumbnail: thumbnail,
                    userId: ''
                    // 아이디를 넘겨줘야하지 않나?
                  }
                })
                .then(() => {
                  console.log('성공')
                })
              })
              
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
        isPwd: ref(true),
      }
      
    }
}
</script>

<style>
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