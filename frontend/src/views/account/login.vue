<template>
       <div class='column items-center'>
        <img src='@/assets/logo.png' alt='로고' class='logo'>
        <h3> 로그인 </h3>
        <q-form ref='loginForm' class='column login-form'>
            <q-input 
            v-model='state.form.userId' 
            class='id q-mb-sm' 
            type='text' 
            label='아이디'
            :rules='state.rules.userId'
            />
            <q-input 
            v-model="state.form.password" 
            class='password q-mb-sm' 
            type='password' 
            label='비밀번호'
            :rules='state.rules.password'
            />
        
            <!-- <input class="id q-mb-sm" type="text" placeholder="아이디">
            <input class="password q-mb-sm" type="password" placeholder="비밀번호"> -->

        <div class='row justify-between'>
            <div>
                <router-link :to='{name: "findId"}' >아이디 찾기 /</router-link>
                <router-link :to='{name: "findPw"}' > 비밀번호 찾기 </router-link>
            </div>
            <router-link :to='{name: "signup"}'> 회원가입 </router-link>
        </div>

        <button @click='clickSignIn' class='btn-login' type='submit'> 로그인 </button>
        </q-form>
    </div>

</template>

<script>
import { ref } from 'vue'
import { useStore } from 'vuex'

export default {
    name: 'login',

    setup(){
        const loginForm = ref(null)
        const store = useStore()

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

        const clickSignIn = function(event){
            event.preventDefault()
            loginForm.value.validate().then(success => {
                if (success){
                    // 유효성 통과
                    store.dispatch('signIn', {...state.value.form})
                 
                } else {
                    // 유효성 실패
                    alert('로그인에 실패하였습니다.')
                }
            })
        }


        return {loginForm, state, clickSignIn}
    }
}
</script>

<style>


.btn-login{
    margin: 100px;
    color: #FFFFFF;
    background-color: #477B72;
}


.login-form{
    width: 20rem;
}

.logo{
    width: 120px;
    height: 100px;
}

</style>