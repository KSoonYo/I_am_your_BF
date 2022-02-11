import profile from '@/views/profile/profile'
import profileUpdate from '@/views/profile/profile-update'


export default [
  {
    path: '/profile',
    name: 'profile',
    component: profile,
    children: [
      {
        path: '/lobby/profile-update/',
        name: 'profileUpdate',
        component: profileUpdate
      }
    ]
  },
]