<template>
    <div v-if="streamManager" class='video-wrapper col'>
        <ov-video :role='"publisher"' :client-data='clientData' :stream-manager="streamManager"/>
        <div class='name-box'><p class='user-name'>{{ clientData }}</p></div>
    </div>
</template>

<script>
import OvVideo from './ov-video'

export default {
  name: 'publishVideo',
  components: {
		OvVideo,
	},
  props: {
    streamManager: Object,
  },

  methods: {
		getConnectionData () {
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
<style>
.video-wrapper{
  display: flex;
  width: 100%;
  flex-direction: column;
  align-items: center;
}

.name-box{
  position: relative;
  bottom: 20px;
  color: white;
}

.user-name{
  margin: 0;
}

</style>