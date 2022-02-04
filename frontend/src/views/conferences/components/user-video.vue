<template>
    <div v-if="streamManager">
        <ov-video :stream-manager="streamManager"/>
        <div><p>{{ clientData }}</p></div>
		<button v-if='!onSpeech' @click='toggleOnSpeech'> 말하기 </button>
		<button v-if='onSpeech' @click='toggleOnSpeech'> 말하기 중지 </button>
		<div class='words'>
		</div>
    </div>
</template>

<script>
import OvVideo from './ov-video';

export default {
	name: 'userVideo',
	components: {
		OvVideo,
	},
	props: {
		streamManager: Object,
	},
	data(){
		return {
			runtimeTranscription_: "",
			transcription_: [],
			lang_: "ko-KR",
			onSpeech : false
		}
	},
	methods: {
		startSpeechToTxt() {
			window.SpeechRecognition =
			window.SpeechRecognition || 
			window.webkitSpeechRecognition

			const recognition = new window.SpeechRecognition()
			recognition.lang = this.lang_
			recognition.interimResults = true
			const p = document.createElement('p')
			document.querySelector('.words').appendChild(p)

			if(this.onSpeech === true){

				recognition.start()
	
				// event current voice reco word
				recognition.addEventListener("result", event => {      
					var text = Array.from(event.results)
						.map(result => result[0])
						.map(result => result.transcript)
						.join("");
					this.runtimeTranscription_ = text
					p.textContent = this.runtimeTranscription_
				})
	
				// end of transcription
				recognition.addEventListener("end", () => {	
					if( this.onSpeech === true){
						recognition.start()
					} else{
						this.runtimeTranscription_ = ''
						p.textContent = ''
						recognition.stop()
					}
					
				})
			}
		},

		toggleOnSpeech(){
			this.onSpeech = !this.onSpeech
		},

		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
	},

	computed: {
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},
	},

	watch: {
		onSpeech: function(){
			this.startSpeechToTxt()
		}
	}

};
</script>

<style>

</style>