<template>
    <q-page>
        <div>
            <q-form class='update-form'>

                <q-img
                src='@/assets/profile-default.png'
                spinner-color="white"
                style='max-width: 200px'
                fit='cover'
                @click="trigger"
                >
                    <q-icon name='settings' class='absolute-bottom upload' style="font-size: 2em;"></q-icon>
                    <input ref='fileInput' id='profile-upload' type="file" style="display:none;">
                </q-img>

                <q-input 
                class='q-mb-sm' 
                type='text' 
                label='이름(별명)'
                />

                <q-input
                class='q-mb-sm' 
                type='text'
                label='이메일'
                >

                </q-input>

                <q-btn>
                    수정하기
                </q-btn>
            </q-form>
        </div>
    </q-page>

</template>

<script>
export default {
    name: 'profileUpdate',
    methods:{
        // click 이벤트 발생시키기
        trigger(){
            this.$refs.fileInput.click()
        }
    }
}
</script>

<style>
.update-form{
    width: 50%;
    margin: 0 auto;
    margin-top: 30px;
    text-align: center;

}

.upload{
    margin: 0 auto;
}

</style>