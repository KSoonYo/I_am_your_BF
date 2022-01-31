<template>
       <div class='column items-center sign-up'>
        <img src='@/assets/logo.png' alt='로고' class='logo'>
        <h3> 회원가입 </h3>
        <q-form ref='signUpForm' class='column sign-up-form'>
            <q-input v-model="state.form.userName" class='q-mb-sm' type='text' label='이름(별명)'
            :rules='state.rules.userName'
            />

            <q-input 
            v-model='state.form.userId'
            :rules='state.rules.userId'
            ref='idInput' 
            class='q-mb-sm' 
            type='text' 
            label='아이디'>  <button @click="clickIdCheck" class='btn-check'> check </button></q-input>

            <q-input 
            v-model='state.form.userEmail' 
            :rules='state.rules.userEmail'
            ref='emailInput'
            class='id q-mb-sm' 
            type='email' 
            label='이메일'
            > <button class='btn-check' @click="clickEmailCheck" > check </button>  </q-input>
    
            <q-input 
            v-model="state.form.password" 
            :rules='state.rules.password'
            class='q-mb-sm' 
            :type="isPwd ? 'password' : 'text'"
            label='비밀번호'>
                <template v-slot:append>
                    <q-icon
                        :name="isPwd ? 'visibility_off' : 'visibility'"
                        class="cursor-pointer"
                        @click="isPwd = !isPwd"
                    />
                </template>
 
            </q-input>

            <q-input 
            v-model="state.form.passwordChk" 
            :rules='state.rules.passwordChk'
            class='q-mb-sm' 
            type='password' 
            label='비밀번호 확인'
            />
  
        <button class='btn-signup' type='submit' @click="clickSignUp"> 회원 가입 </button>
        </q-form>
    </div>

</template>

<script>
import { ref } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'


export default {
    name: 'signup',

    setup(){
        const signUpForm = ref(null)
        const idInput = ref(null)
        const emailInput = ref(null)

        const userIdCheck = ref(false)
        const userEmailCheck = ref(false)

        const store = useStore()
        const router = useRouter()

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
                    val => val === userIdCheck.value || '아이디 중복 검사 미완료'                    
                ],
                userEmail : [
                    val => emailChecker.test(val) || '이메일 형식을 지켜주세요.',
                    val => val === userEmailCheck.value || '이메일 중복 검사 미완료'                    
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
        const clickIdCheck = function(event){
            event.preventDefault()
            idInput.value.validate()
            state.value.userIdCheck = true

            if(idInput.value.hasError){
                // 유효성 미통과

            } else {
                store.dispatch('requestGetUser', state.value.form.userId)
                    .then(()=>{
                        // state.value.userIdCheck = true
                    })
            }
        }

        // 이메일 중복 검사
        const clickEmailCheck = function(event){
            event.preventDefault()
            emailInput.value.validate()
            
            if(emailInput.value.hasError){
                // 유효성 미통과

            } else {
                store.dispatch('requestGetUser', state.value.form.userEmail)
                    .then(()=>{
                        // state.value.userEmailCheck = true
                    })
            }
        }

        const clickSignUp = function(event){
            event.preventDefault()
            
            signUpForm.value.validate().then(success => {
                if ( success ){
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
                        alert('회원가입 실패')
                    })
                } else {
                    console.log('실패')
                }
            })
        }    



        return { signUpForm, idInput, emailInput, state, clickIdCheck, clickEmailCheck, clickSignUp, isPwd: ref(true) }
    }
}
</script>

<style>
.btn-signup{
    color: #FFFFFF;
    background-color: #477B72;
}


/* .sign-up{
    margin: 0px 300px;
    border: 1px black solid;
} */

.logo{
    width: 120px;
    height: 100px;
}

.sign-up-form{
    width: 20rem;
}

.btn-check{
    margin: auto 0;
    height: 50%;
    color: white;
    background-color: #6C757D;
}

</style>