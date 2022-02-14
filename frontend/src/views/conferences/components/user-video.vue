<template>
    <div v-if="streamManager"
		:class='[{"main-stream-wrapper": role === "mainStreamer"}, {"subscriber-wrapper" : role === "subscriber"}]'>
        <ov-video :role='role' :stream-manager="streamManager"/>
        <div class='name-box'><p class='user-name'>{{ clientData }}</p></div>
    </div>
</template>

<script>
import OvVideo from './ov-video'


export default {
	name: 'userVideo',
	components: {
		OvVideo,
	},
	props: {
		streamManager: Object,
		role: String
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
			return clientData
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

.subscriber-wrapper{
	position: relative;
  display: flex !important;
  width: 100%;
	height: 45%;
  flex-direction: column;
  align-items: center;
}
</style>