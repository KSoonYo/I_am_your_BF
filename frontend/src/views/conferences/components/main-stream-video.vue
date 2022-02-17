<template>
    <div v-if="streamManager && show"
		class='main-stream-wrapper'>
      <main-screen-video :stream-manager="streamManager" style="border-radius: 20px;"/>
      <div class='name-box'><p class='user-name'>{{ clientData }}</p></div>
    </div>
</template>

<script>
import MainScreenVideo from './main-screen-video'


export default {
	name: 'mainStreamVideo',
	components: {
		MainScreenVideo,
	},
	props: {
		streamManager: Object,
    show: Boolean,
	},
	data(){
		return {
			runtimeTranscription_: "",
			transcription_: [],
		}
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
			return clientData[1]
		},
	},

};
</script>

<style>

.main-stream-wrapper{
	display: flex;
	width: 100%;
	height: 100%;
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