<template>
	<div class='column items-center bg-grey-3 flex justify-center' style='min-height:100vh'>
		<div class='container'>
			<div class='row justify-center backimg'>
				<div class='col-12 flex items-center justify-center'>
					<div class='bg-white shadow-3' style='max-width: 500px; width: 100%; padding: 3rem; border: 0; border-radius: 0.5rem'>
						<div>
							<!-- 메인 상단 글씨 -->
							<div class='text-center q-mb-4'>
								<h3 style='margin-bottom: 0px'> 로그인 </h3>
								<br>
								<p style='font-size: 1rem; font-weight: 400; line-height: 1.6; margin-top: 0; margin-bottom: 1rem;'>베프의 일원이 되어 보세요</p>
							</div>
							<div class='flex justify-center'>
								<q-form ref='loginForm' class='column login-form'>
									<!-- 아이디 -->
									<label for='Id'>아이디</label>
									<q-input outlined v-model="state.form.userId" label='아이디'
									:rules='state.rules.userId'
									>
										<template v-slot:prepend>
											<i class='fas fa-user-circle'></i>
										</template>
									</q-input>

									<!-- 비밀번호 -->
									<label for='Password'>비밀번호</label>
									<q-input outlined v-model="state.form.password" label='비밀번호'
									:rules='state.rules.password'
									:type="isPwd ? 'password' : 'text'"
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

									<!-- 아이디, 비밀번호 찾기 버튼 -->
									<div class='row justify-end'>
										<div style='margin-bottom: 1.5rem;'>
											<router-link :to='{name: "findId"}' style='text-decoration: none; font-size: 1em;'>아이디 찾기 /</router-link>
											<router-link :to='{name: "findPw"}' style='text-decoration: none; font-size: 1em;'> 비밀번호 찾기 </router-link>
										</div> 
									</div>

									<!-- 로그인 버튼 -->
									<q-btn @click='clickSignIn' color='black' label='로그인' type='submit' style='border-radius:10px' />

									<!-- 회원가입 이동 버튼 -->
									<div style='margin-top: 1.5rem' class='text-center'>
										<span>회원이 아니신가요?  </span>
										<router-link :to='{name: "signup"}' style='text-decoration: none; font-size: 1em;'> 회원가입 </router-link>
									</div>

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

// import quasarUserOptions from '../../quasar-user-options'
import { ref } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { useQuasar } from 'quasar'

export default {
    name: 'login',

    setup(){
        // app.use(quasarUserOptions);
        const loginForm = ref(null)
        const store = useStore()
        const router = useRouter()
        const $q = useQuasar()
        const isPwd = ref(true)
        // 특수문자 체크 정규 표현식
        const idChecker = /[{}[\]/?.,;:|)*~`!^\-_+<>@#$%&\\=('"]/g

        const state = ref({
            form: {
                userId: '',
                password: ''
            },
						
            rules: {
                userId: [
                    val => val.length >= 3 && val.length <= 5 || '3자 이상 5자 이하로 입력해주세요.',
                    val => !idChecker.test(val) || '특수문자는 입력이 불가능합니다.'                    
                ],
                password: [
                    val => val != '' || '비밀번호를 입력해주세요.' 
                ]
            }
        })

        // 로그인
        const clickSignIn = function(event){
            event.preventDefault()
            loginForm.value.validate().then(success => {
                if (success){
                    // 유효성 통과
                    store.dispatch('getToken', {...state.value.form})
                    .then((response)=>{
                        localStorage.setItem('accessToken', response.data.accessToken)
                    })
                    .then(()=>{
                        store.dispatch('getUserInfo')
                        .then((response)=>{
                            localStorage.setItem('userInfo', JSON.stringify(response.data))
                            // console.log(JSON.parse(localStorage.getItem('userInfo')).userId)
                        })
                        .then(()=>{
                            router.push({name: 'conferenceList'})
                        })
                    })
                    .catch(()=>{
                        $q.notify({
                            type: 'negative',
                            message: '아이디 또는 비밀번호를 잘못 입력하였습니다.'
                        })
                    })  
                    
                } 
            })
        }


        return {loginForm, state, clickSignIn, isPwd}
    }
}
</script>

<style scoped>
/* @import 'quasar/dist/quasar.css'; */
/* @import 'quasar/dist/quasar.css';
@import '@quasar/extras/roboto-font/roboto-font.css';
@import '@quasar/extras/material-icons/material-icons.css';
@import '@quasar/extras/material-icons-outlined/material-icons-outlined.css';
@import '@quasar/extras/material-icons-round/material-icons-round.css';
@import '@quasar/extras/material-icons-sharp/material-icons-sharp.css';
@import '@quasar/extras/fontawesome-v5/fontawesome-v5.css'; */

.login-form{
    width: 20rem;
    
}

.logo{
    width: 120px;
    height: 100px;
}

.backimg {
	background-image: url("../../assets/signin.svg");
	
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