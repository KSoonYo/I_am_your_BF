<template>
    <div class='column items-center'>
        <img src='@/assets/logo.png' alt='로고' class='logo'>
        <h3> 비밀번호 찾기 </h3>
        <q-form class='column find-pw-form'>
            <q-input  
            class='q-mb-sm' 
            type='text' 
            label='아이디'
            />
            <q-input  
            class='q-mb-sm' 
            type='text' 
            label='이름'
            />
            <q-input  
            class='q-mb-sm' 
            type='email' 
            label='이메일'
            />
        
     
        <button class='btn-find-pw' type='submit'> 비밀번호 찾기 </button>
        </q-form>
    </div>

</template>

<script>
export default {
    name: 'findPw',

}
</script>

<style>
.btn-find-pw{
    color: #FFFFFF;
    background-color: #477B72;
}

.logo{
    width: 120px;
    height: 100px;
}

.find-pw-form{
    width: 20rem;
}

</style>