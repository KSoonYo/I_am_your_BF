import lobby from '@/views/conferences/lobby'
import conferenceList from '@/views/conferences/components/conference-list'
import profileUpdate from '@/views/conferences/components/profile-update'


export default [
  {
    path: '/lobby',
    name: 'lobby',
    component: lobby,
    children: [
      {
        path: '/lobby/conference-list/',
        name: 'conferenceList',
        component: conferenceList
      },

      {
        path: '/lobby/profile-update/',
        name: 'profileUpdate',
        component: profileUpdate
      }
    ]
  },
]