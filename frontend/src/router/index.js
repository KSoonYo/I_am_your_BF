import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/home/home.vue'
import Test from '../views/account/test.vue'
import login from './login'
import signup from './signup'
// import findId from './find-id'
// import findPw from './find-pw'

// conferenceRoom
import conferenceRoom from './conference-room'

import ConferenceList from './conference-list'
import CreateConference from './create-conference'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/test',
    name: 'Test',
    component: Test
  },
  ...login,
  ...signup,
  // ...findId,
  // ...findPw,
  ...conferenceRoom,

  ...ConferenceList,
  ...CreateConference,
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
