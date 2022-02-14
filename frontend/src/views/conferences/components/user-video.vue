<template>
    <div v-if="streamManager"
		:class='[
		{"main-stream-wrapper": role === "mainStreamer"}, 
		{"subscriber-wrapper-host" : host === true && role === "subscriber"}, 
		{"subscriber-wrapper-guest" : host === false && role === "subscriber"}
		]'>
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
		role: String,
		host: Boolean,
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

.subscriber-wrapper-host{
	position: relative;
  display: flex !important;
  width: 100%;
	height: 30%;
  flex-direction: column;
  align-items: center;
}

.subscriber-wrapper-guest{
	position: relative;
  display: flex !important;
  width: 100%;
	height: 45%;
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