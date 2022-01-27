<template>
  <div class='q-pa-md row items-start q-gutter-md'>
    <q-card class='my-card'>

      <q-img :src='reference.thumbnail'/>

      <q-card-section>
        <div class='text-h5'>
          <p>{{ reference.title }}</p>
        </div>
      </q-card-section>

      <q-card-section class='q-pt-none'>
        {{ reference.datetime }}
      </q-card-section>

      <q-separator />

      <q-card-actions>
        <q-btn flat color='primary' @click='openReference(conference.id)'>입장하기</q-btn>
      </q-card-actions>
    </q-card>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'

export default {
  name: 'Conference',

  props: {
    conference: Object,
  },


  setup () {
    const router = useRouter()
    const openReference = function (id) {
      router.push({
        name: 'ConferenceRoom',
        params: {
          conferenceId : id
        }
      })
    }
    
    return { openReference }
  }
}
</script>

<style>
.my-card {
  width: 100%;
  max-width: 300px;
}
</style>