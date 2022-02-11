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
