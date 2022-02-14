<template>
  <router-view />
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

<style>
body { 
  background-image: -webkit-gradient(linear, left top, left bottom, from(#FFF7E7), to(#FFF1DC));
  background-image: -o-linear-gradient(top, #FFF7E7 0%, #FFF1DC 100%);
  background-image: linear-gradient(180deg, #FFF7E7 0%, #FFF1DC 100%);
  }
</style>