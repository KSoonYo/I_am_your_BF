<template>
  <div class="q-ma-md">
    <q-img class='my-card concard shadow-5 no-border'  :fit="scale-down" :src="state.thumbnail">
      <q-card class="no-border" no-body style="width:100%; height:100%;">
          <div class='text-h3' style="opacity:0.7">
            <p>{{ conference.id }}</p>
          </div>
          <div class='text-h5' style="opacity:0.7">
            <p>{{ conference.title }}</p>
          </div>
          <p style="opacity:0.7">{{ conference.userName }}</p>
      </q-card>
      <div class="overlay flex column justify-center items-center">
        <div class="text-h6">{{ conference.description }}</div>
        <br>
        <br>
        <q-btn label="입장하기" rounded color="positive" @click='openConference(conference.id)'></q-btn>
      </div>
    </q-img>
    
  </div>
</template>

<script>
import axios from 'axios'
// import { useRouter } from 'vue-router'
import { ref, onMounted } from 'vue'

export default {
  name: 'Conference',

  props: {
    conference: Object,
  },

  
  setup (props) {
    onMounted(() => {
      axios({
        method: 'get',
        url: 'http://localhost:8080/api/image/' + props.conference.thumbnail,
      })
        .then((res) => {
          state.value.thumbnail = res.config.url
        })
        .catch(() => {
          console.log('실패')
        })
    })
    const state = ref({
      thumbnail: ''
    });

    // const router = useRouter()
    // const openConference = function (id) {
    //   router.push({
    //     name: 'ConferenceRoom',
    //     params: {
    //       conferenceId : id
    //     }
    //   })
    // }
    
    return { state }//openConference, }
  }
}
</script>

<style>
.my-card {
  width: 100%;
  max-width: 486px;
  border-radius: 20px;
}
.concard {
  min-width: 243px; 
  min-height: 121px; 
  max-width: 486px;
  max-height: 242px;
  margin: 10px;
}

.overlay {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  height: 100%;
  width: 100%;
  opacity: 0;
  transition: .5s ease;
  /* background-color: white; */
  /* background-color: #484d4e; */
}

.my-card:hover .overlay {
  opacity: 1;
  font-weight: bolder;
}
</style>