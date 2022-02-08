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
import OvVideo from './ov-video'
import axios from 'axios'

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
			onSpeech : false,
			baseUrl : 'http://127.0.0.1:8000/'
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
					axios({
						url: this.baseUrl + 'sslis/',
						method: 'post',
						data: JSON.stringify(this.runtimeTranscription_)
					})
					.then((res) => {
						console.log(res.data.sign)
					})
					
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

		// getCookie(name){
		// 	let cookieValue = null;
		// 		if (document.cookie && document.cookie !== '') {
		// 				const cookies = document.cookie.split(';');
		// 				for (let i = 0; i < cookies.length; i++) {
		// 						const cookie = cookies[i].trim();
		// 						// Does this cookie string begin with the name we want?
		// 						if (cookie.substring(0, name.length + 1) === (name + '=')) {
		// 								cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
		// 								break;
		// 						}
		// 				}
		// 		}
		// 		return cookieValue
		// },

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