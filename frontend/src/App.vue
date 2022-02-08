<template>
  <div :style="{'background-image':'url('+ require('./assets/bg-image2.svg')+ ')'}" view="hHr lpr fFr">
    <div reveal class='nav-bar'>
      <div class="row justify-between">
        <q-btn flat round style="color: white">
          <router-link to="/">
            <q-avatar size="85px">
              <img alt="배프 로고" src="./assets/bf-logo.svg">
            </q-avatar>
          </router-link>
         </q-btn>
        <!-- toekn 유무에 다른 버튼 -->
        <!-- v-if="!this.$sotre.state.jwtIsValid" -->
      <div class="navbar">
        <router-link class="account" :to='{name:"signup"}'>
        <q-btn flat style="color: #000000" label="회원 가입">
        </q-btn>
        </router-link>
        <router-link class="account" :to='{name:"profile"}'>
          <q-avatar size="30px"  class="q-mb-sm image-box">
            <img src="@/assets/profile-default.png">
          </q-avatar>
        <q-btn flat style="color: #000000" label="마이 페이지">
        </q-btn>
        </router-link>
        <router-link class="account" :to='{name:"login"}'>
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
      </div>
    </div>
    <div class="wrapper">
      <router-view />
      <div :style="image" class="image"></div>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
// import Home from './views/home/home.vue'

export default {
  name: 'LayoutDefault',

  components: {
    // Home,
  },

  setup () {
    return {
      leftDrawerOpen: ref(false)
    }
  },
  data() {
    return {
      image: {logoImage: "./assets/bf-logo.svg"}
    }
  }
}
</script>
<style scoped>

.account {
  text-decoration: none;
} 

.nav-bar {
  /* background-color: #fcfbf9 !important; */
  background-color: rgba(252,251,249,0) !important;
  color: black;
}

.image-box {
  padding: 3.5px;
}

.image {
    height: 150px;
    width: 150px;
    background-repeat: no-repeat;
}
</style>