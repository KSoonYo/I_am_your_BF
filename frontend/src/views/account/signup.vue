<template>
    <div class='column items-center bg-grey-3 flex justify-center' style='min-height:100vh'>
		<div class='container'>
			<div class='row justify-center backimg'>
				<div class='col-12 flex items-center justify-center'>
					<div class='bg-white shadow-3' style='max-width: 500px; width: 100%; padding: 3rem; border: 0; border-radius: 0.5rem'>
						<div>
							<!-- 메인 상단 글씨 -->
							<div class='text-center q-mb-4'>
								<h3 style='margin-bottom: 0px'> 회원가입 </h3>
								<br>
								<p style='font-size: 1rem; font-weight: 400; line-height: 1.6; margin-top: 0; margin-bottom: 1rem;'>베프에 오신 걸 환영합니다.</p>
							</div>

							<div class='flex justify-center'>
								<q-form ref='signUpForm' class='column sign-up-form'>
									
									<!-- 이름(별명) -->
									<label for='Name'>이름(별명)</label>
									<q-input outlined v-model="state.form.userName" label='이름(별명)'
									:rules='state.rules.userName'
									>
										<template v-slot:prepend>
											<i class='fas fa-portrait'></i>
										</template>
									</q-input>

									<!-- 아이디 -->
									<label for='Id'>아이디</label>
									<q-input outlined v-model="state.form.userId" label='아이디'
									:rules='state.rules.userId'
									ref='idInput' 
									>
										<template v-slot:prepend>
											<i class='fas fa-user-circle'></i>
										</template>
										<q-btn rounded @click="clickIdCheck" label='중복검사' style='background-color: #929291; color: #fff; margin-top: 10px; margin-bottom: 10px; margin-right: 5px; width: 50%'></q-btn>
									</q-input>

									<!-- 이메일 -->
									<label for='Email'>이메일</label>
									<q-input outlined v-model="state.form.userEmail" label='이메일'
									ref='emailInput'
									:rules='state.rules.userEmail'
									>
										<template v-slot:prepend>
											<i class='far fa-envelope'></i>
										</template>
										<q-btn rounded @click="clickEmailCheck" label='중복검사' style='background-color: #929291; color: #fff; margin-top: 10px; margin-bottom: 10px; margin-right: 5px; width: 50%'></q-btn>
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

									<!-- 비밀번호 확인 -->
									<label for='PasswordChk'>비밀번호 확인</label>
									<q-input outlined v-model="state.form.passwordChk" label='비밀번호 확인'
									:rules='state.rules.password'
									type='password'
									>
										<template v-slot:prepend>
											<i class='fas fa-unlock-alt'></i>
										</template>
									</q-input>

									<!-- 아이디, 비밀번호 찾기 버튼 -->
									<div class='row justify-end'>
										<div style='margin-bottom: 1.5rem;'>
											<router-link :to='{name: "findId"}' style='text-decoration: none; font-size: 1em;'>아이디 찾기 /</router-link>
											<router-link :to='{name: "findPw"}' style='text-decoration: none; font-size: 1em;'> 비밀번호 찾기 </router-link>
										</div> 
									</div>

									<!-- 회원가입 버튼 -->
									<q-btn @click='clickSignUp' color='black' label='회원 가입' type='submit' style='border-radius:10px' />
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
import { ref } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import { useQuasar } from 'quasar'


export default {
    name: 'signup',

    setup(){
        const signUpForm = ref(null)
        const idInput = ref(null)
        const emailInput = ref(null)


        const store = useStore()
        const router = useRouter()
        const $q = useQuasar()


         // 아이디 형식 체크 정규 표현식
        const idChecker = /[{}[\]/?.,;:|)*~`!^\-_+<>@#$%&\\=('"]/g

        // 이메일 형식 체크 정규 표현식
        const emailChecker = /^([\w._-])*[a-zA-Z0-9]+([\w._-])*([a-zA-Z0-9])+([\w._-])+@([a-zA-Z0-9]+\.)+[a-zA-Z0-9]{2,}$/

        // 영어, 숫자, 특수문자 조합 9자리 이상
        const passwordChecker =  /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{9,}$/

        const state = ref({
     
            form: {
                userId : '',
                userName : '',
                userEmail : '',
                password : '',
                passwordChk : ''
            },

            rules: {
                userName: [
                    val => val.trim() !== '' || '필수 입력입니다.'
                ],

                userId: [
                    val => val.length >= 3 && val.length <= 5 || '3자 이상 5자 이하로 입력해주세요.',
                    val => !idChecker.test(val) || '특수문자는 입력이 불가능합니다.',

                ],
                userEmail : [
                    val => emailChecker.test(val) || '이메일 형식을 지켜주세요.',
                ],
                password : [
                    val => val.trim() !== '' || '비밀번호를 입력해주세요.' ,
                    val => val.length >= 9 && val.length <= 16 || '비밀번호는 9자 이상 16자 이하입니다.',
                    val => passwordChecker.test(val) || '영어 대소문자, 숫자, 특수문자를 포함해야 합니다.'
                ],
                passwordChk : [
                    val => val.trim() !== '' || '비밀번호를 입력해주세요.', 
                    val => val === state.value.form.password || '비밀번호가 일치하지 않습니다.'
                ],
            }
        })


        // 아이디 중복 검사
        const checkedId = ref('')

        const clickIdCheck = function(event){
            event.preventDefault()
            idInput.value.validate()

            if(!idInput.value.hasError){
                // 유효성 통과
                 store.dispatch('requestGetUser', state.value.form.userId)
                    .then(()=>{
                        checkedId.value = state.value.form.userId
                        console.log('아이디 확인')
                        $q.notify({
                            type: 'positive',
                            message: '가능한 아이디 입니다.'
                        })

                    })
                    .catch((err)=>{
                        checkedId.value = ''
                        if(err.statusCode === 500){
                            alert('서버 오류')
                            return
                        }

                        $q.notify({
                            type: 'negative',
                            message: '이미 존재하는 회원입니다.'
                        })

                    })

            } 
        }

        // 이메일 중복 검사
        const checkedEmail = ref('')


        const clickEmailCheck = function(event){
            event.preventDefault()
            emailInput.value.validate()


            if(!emailInput.value.hasError){
                store.dispatch('requestGetUser', state.value.form.userEmail)
                .then(()=>{
                    console.log('이메일 확인')
                    checkedEmail.value = state.value.form.userEmail
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

        // 회원가입
        const clickSignUp = function(event){
            event.preventDefault()
            
            
            signUpForm.value.validate().then(success => {
                if ( success ){
                    if(state.value.form.userId !== checkedId.value){
                        $q.notify({
                            type: 'info',
                            message: '아이디 중복 검사가 필요합니다.'
                        })
                        return 
                    } else if(state.value.form.userEmail !== checkedEmail.value){
                        $q.notify({
                            type: 'info',
                            message: '이메일 중복 검사가 필요합니다.'
                        })
                        return
                    }

                    store.dispatch('signUp', {
                        userId : state.value.form.userId,
                        userName : state.value.form.userName,
                        userEmail : state.value.form.userEmail,
                        password : state.value.form.password,
                    })
                    .then(()=>{
                        router.push({name: 'login'})
                    })  
                    .catch(()=>{
                        $q.notify({
                            type: 'negative',
                            message: '회원가입 실패'
                        })
                    })
                } 
            })
        }    



        return { signUpForm, idInput, emailInput, state, clickIdCheck, clickEmailCheck, clickSignUp, isPwd: ref(true) }
    }
}
</script>

<style>



/* .sign-up{
    margin: 0px 300px;
    border: 1px black solid;
} */

.logo{
    width: 120px;
    height: 100px;
}

.sign-up-form{
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