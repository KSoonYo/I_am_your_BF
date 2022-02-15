export default [
    {
        path: '/session-test/:conferenceId',
        name: 'session-test',
        component: () => import('@/views/conferences/conference-room')
    }
]