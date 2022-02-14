<template>
    <div v-if="streamManager" class='video-wrapper col-4'>
        <my-video :stream-manager="streamManager"/>
        <div class='name-box'><p class='user-name'>{{ clientData }}</p></div>
    </div>
</template>

<script>
import MyVideo from './my-video'

export default {
  name: 'publishVideo',
  components: {
		MyVideo,
	},
  props: {
    streamManager: Object,
  },

  methods: {
		getConnectionData () {
      console.log(this.streamManager)
			const { connection } = this.streamManager.stream
			return JSON.parse(connection.data)
		},
	},

	computed: {
		clientData () {
			const { clientData } = this.getConnectionData()
			return clientData
		},
	},
}
</script>
<style scoped>
.video-wrapper{
  position: relative;
  display: flex;
  width: 100%;
  flex-direction: column;
  align-items: center;
}

.name-box{
  position: absolute;
  bottom: 20px;
  color: white;
}

.user-name{
  margin: 0;
}

</style>