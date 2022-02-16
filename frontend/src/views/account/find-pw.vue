<template>
	<div class="home-btn"><a class="navbar-brand" href="/"><img src="assets/img/gallery/logo_bf2.png"
        height="45" alt="logo" /></a>
  </div>
	<div class='column items-center flex justify-center' style='min-height:100vh; background-image:url(../../../public/assets/img/illustrations/big-main.png);'>
		<div class='container'>
			<div class='row justify-center backimg'>
				<div class='col-12 flex items-center justify-center'>
					<div class='bg-white shadow-3' style='max-width: 500px; width: 100%; padding: 3rem; border: 0; border-radius: 0.5rem'>
						<div>
							<!-- 메인 상단 글씨 -->
							<div class='text-center q-mb-4'>
								<h3 style='margin-bottom: 0px'> 비밀번호 찾기 </h3>
								<br>
								<p style='font-size: 1rem; font-weight: 400; line-height: 1.6; margin-top: 0; margin-bottom: 1rem;'></p>
							</div>

							<div class='flex justify-center'>
								<q-form ref='find-pw-form' class='column find-pw-form'>
									<!-- 아이디 -->
									<label for='Id'>아이디</label>
									<q-input outlined v-model="state.form.userId" label='아이디'>
										<template v-slot:prepend>
											<i class='fas fa-user-circle'></i>
										</template>
									</q-input>

									<!-- 이름(별명) -->
									<label for='Name'>이름</label>
									<q-input outlined v-model="state.form.userName" label='이름'>
										<template v-slot:prepend>
											<i class='fas fa-portrait'></i>
										</template>
									</q-input>

									<!-- 이메일 -->
									<label for='Email'>이메일</label>
									<q-input outlined v-model="state.form.userEmail" label='이메일'>
										<template v-slot:prepend>
											<i class='far fa-envelope'></i>
										</template>
									</q-input>

									<!-- 아이디 찾기 버튼 -->
									<div class='row justify-end'>
										<div style='margin-bottom: 1.5rem; margin-top: 1rem'>
											<router-link :to='{name: "findId"}' style='text-decoration: none; font-size: 1em;'>아이디 찾기</router-link>
										</div> 
									</div>

									<!-- 비밀번호 찾기 버튼 -->
									<q-btn @click='findPw' color='black' label='비밀번호 찾기' type='submit' style='border-radius:10px' />
									

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
import { useQuasar } from 'quasar'
import { useRouter } from 'vue-router'

export default {
    name: 'findPw',
		

		setup() {
			const $q = useQuasar()
			const store = useStore()
			const router = useRouter()
			const state = ref({
				form: {
					userId: '',
					userName: '',
					userEmail: '',
				},
			})
			const findPw = function(event){
				event.preventDefault()
				store.dispatch('findPassword', state.value.form)
            .then(() => {
              $q.dialog({
                message: '이메일로 임시 비밀번호를 보냈습니다.'
              })
							.onOk(() => {
								router.push({name:'login'})
							})
						})
            .catch(() => {
              $q.notify({
                type: 'negative',
                message: '아이디, 이름, 이메일을 확인해주세요.'
              })
            })
			}
			
			return {store, state, findPw}
		}

}
</script>

<style scoped>

.logo{
    width: 120px;
    height: 100px;
}

.find-pw-form{
    width: 20rem;
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