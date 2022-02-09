<template>
  <div class='concard my-card shadow-5' style="">
    <q-img class='my-card' :src="state.thumbnail" style="max-height: 242px; max-width: 486px">
      <q-card  no-body style="width:100%; height:100%;">
          <div class='text-h3'>
            <p>{{ conference.id }}</p>
          </div>
          <div class='text-h5'>
            <p>{{ conference.title }}</p>
          </div>
          {{ conference.userName }}
      </q-card>
    </q-img>
    <div class="container">
      <div class="overlay">
        <div class="text">{{ conference.description }}</div>
        <q-btn flat color='primary' @click='openConference()'>입장하기</q-btn>
      </div>
    </div>
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
  margin: 20px;
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
  background-color: white;
  /* background-color: #484d4e; */
}

.container:hover .overlay {
  opacity: 0.8;
}
</style>