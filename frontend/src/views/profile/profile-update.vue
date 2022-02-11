<template>
    <q-page>
        <div>
            <q-form class='update-form'>
                <div class='image-box'>
                    <div class='box'>
                        <img
                        :src='src'
                        @click="trigger"
                        class='preview'
                        >
                    </div>
                    <q-icon name='settings' class='absolute-bottom-sm upload' style="font-size: 2em;"></q-icon>
                    <input ref='fileInput' @change='readImage' id='profile-upload' type='file' accept='image/*' style="display:none;">
                </div>

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

                <q-input
                filled
                type='textarea'
                label='한 줄 소개'
                />

                <q-btn>
                    수정하기
                </q-btn>
            </q-form>
        </div>
    </q-page>

</template>

<script>
import profileDefault from '@/assets/profile-default.png' 

export default {
    name: 'profileUpdate',
    data(){
        return {
            src : profileDefault
        }
    },

    methods:{
        // click 이벤트 발생시키기
        trigger(){
            this.$refs.fileInput.click()
        },

        // 이미지 미리 보기
        readImage(event){
            const input = event.target
            if(input.files && input.files[0]){
                const reader = new FileReader()

                reader.onload = (e) => {
                    this.src = e.target.result
                }
                reader.readAsDataURL(input.files[0])
            }
        }
    }
}
</script>

<style scoped>
.update-form{
    width: 50%;
    margin: 0 auto;
    margin-top: 30px;
    text-align: center;

}

.upload{
    margin: 0 auto;
}

.image-box{
    position: relative;
    margin: 0 auto;
    width: 20rem;
    height: 20rem;
    
}

.box{
    margin: 0 auto;
    border: 1px solid black;
    border-radius: 70%;
    width: 80%;
    height: 80%;
    overflow: hidden;
}

.preview{
    width: 100%;
    height: 100%;
    object-fit: cover;
}

</style>