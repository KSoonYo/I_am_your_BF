<template>
  <nav class="navbar navbar-expand-lg navbar-light fixed py-3 d-block"
        data-navbar-on-scroll="data-navbar-on-scroll" style="font-size: 1rem; font-weight: 400; background-color:rgba(255,248,232,0.2);">
        <div class="container"><a class="navbar-brand" href="#" @click="movehome"><img src="assets/img/gallery/logo_bf2.png"
              height="45" alt="logo" /></a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
              class="navbar-toggler-icon"> </span></button>
          <div class="collapse navbar-collapse border-top border-lg-0 mt-4 mt-lg-0" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto pt-2 pt-lg-0 font-base">
              <li class="nav-item px-2" id="btn-outline-dark"><a class="nav-link" aria-current="page" @click='editProfile'>Profile</a></li>
              <li class="nav-item px-2" id="btn-outline-dark"><a class="nav-link" aria-current="page" @click='logout'>Logout</a></li>
            </ul>
          </div>
        </div>
  </nav>
</template>

<script>
import { useRouter } from 'vue-router'

export default {
  name: 'navbarConference',
  props: {
  
  },
  
  setup() {
    // router 불러오기
    const router = useRouter();

    // 프로필 수정 페이지 이동
    const editProfile = function () {
      router.push({ name: 'profile' })
    }

    // 로그아웃
    const logout = function () {
      localStorage.removeItem('accessToken')
      localStorage.removeItem('userInfo')
      router.push({ name : 'home' })
    }

    //홈으로
    const movehome = function () {
      router.push({ name : 'home' })
    }
    return { editProfile, logout, movehome }
  }
}
</script>

<style scoped src='../../../../public/assets/css/theme.css'>
.fixed {
  position: fixed;
  top: 0;
  right: 0;
  left: 0;
  z-index: 1030;
}

</style>