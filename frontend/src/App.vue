<template>
  <q-layout view="hHr lpr fFr">
    <q-header reveal class="bg-orange-2 text-black">
      <q-toolbar class="row justify-between">
        <q-btn flat round style="color: white">
          <router-link to="/">
            <q-avatar size="80px">
              <img alt="배프 로고" src="./assets/bf-logo.svg">
            </q-avatar>
          </router-link>
         </q-btn>
      <div class="account">
        <router-link to="signup">
        <q-btn flat style="color: #000000" label="회원 가입">
        </q-btn>
        </router-link>
        <router-link to="login">
        <q-btn flat style="color: #000000" label="로그인">
        </q-btn>
        </router-link>
      </div>
        <!-- <div>
        <q-btn flat style="color: #000000" label="마이 페이지">
        </q-btn>
        <q-btn flat style="color: #000000" label="로그아웃">
        </q-btn>
       </div> -->
      </q-toolbar>
    </q-header>
    <q-page-container>
      <router-view />
      <div :style="image" class="image"></div>
    </q-page-container>
  </q-layout>
  <q-page-container>
    <Home />
  </q-page-container>
</template>

<script>
import { ref } from 'vue'
import Home from './views/home/home.vue'

export default {
  name: 'LayoutDefault',

  components: {
    Home,
  },

  setup () {
    return {
      leftDrawerOpen: ref(false)
    }
  },
  data() {
    return {
      image: {backgroundImage: "./assets/bf-logo.svg"}
    }
  }
}
</script>
<style>

.account {
  text-decoration: none;
}

.image {
    height: 150px;
    width: 150px;
    background-repeat: no-repeat;
}
</style>