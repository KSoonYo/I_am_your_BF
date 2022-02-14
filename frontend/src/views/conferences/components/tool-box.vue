<template>
  <div class='button-wrapper' style="width: fit-content;">
		<button @click='$emit("leaveSessionClick")'> Leave Session </button>

    <button v-show='!onSpeech' @click='toggleOnSpeech'> 말하기 </button>
		<button v-show='onSpeech' @click='toggleOnSpeech'> 말하기 중지 </button>

		<button v-show='!captionEnabled'  @click='() => { captionEnabled = !captionEnabled, $emit("toggleCaption") }'> 자막 on </button>
		<button v-show='captionEnabled'  @click='() => { captionEnabled = !captionEnabled, $emit("toggleCaption") }'>  자막 off </button>
  
		<button v-show='!signVideoEnabled'  @click='() => { signVideoEnabled = !signVideoEnabled, $emit("toggleSignVideo") }'> 수화 on </button>
		<button v-show='signVideoEnabled'  @click='() => { signVideoEnabled = !signVideoEnabled, $emit("toggleSignVideo") }'>  수화 off </button>
	
		<button @click='() => { $emit("toggleShowChat")}'> 채팅 </button>

		<button @click='() => { $emit("toggleShowMemo")}' > 기록 </button>
	</div>
</template>

<script>
import axios from 'axios'

const baseUrl = process.env.VUE_APP_DJANGO_URL;

window.SpeechRecognition =
window.SpeechRecognition || 
window.webkitSpeechRecognition


// cons

export default {
  name: 'toolBox',
  props: {
		session: Object,
		publisher: Object
	},

  data(){
    return {
      runtimeTranscription_: '',
      onSpeech : false,
			captionEnabled : false,
			signVideoEnabled : false,
			recognition : null

    }
  },

  methods: {
    // STT
		toggleOnSpeech(){
			this.recognition = this.onSpeech ? null: this.recognition
			setTimeout(()=>{
				this.onSpeech = !this.onSpeech
				this.publisher.publishAudio(this.onSpeech)
			}, 1000)
		},

    startSpeechToTxt() {

			this.recognition = new window.SpeechRecognition()
			this.recognition.lang = 'ko-KR'
			this.recognition.interimResults = true
	

			this.recognition.start()	

			// event current voice reco word
			this.recognition.addEventListener("result", event => {      
				var text = Array.from(event.results)
					.map(result => result[0])
					.map(result => result.transcript)
					.join("");
				this.runtimeTranscription_ = text
			})


			// end of transcription
			this.recognition.addEventListener("end", () => {					
					try{
						this.recognition.start()
					} catch(e){
						console.log(e)
					}

					if(this.runtimeTranscription_ && this.recognition){
						const transcription = this.runtimeTranscription_

						this.session.signal({
								data : this.runtimeTranscription_,
								to: [],
	
								type: 'caption'
							})

						this.session.signal({
								data : this.runtimeTranscription_,
								to: [],
	
								type: 'memo'
						})
						.then(()=>{	
								this.runtimeTranscription_ = ''	
							})

						axios({
							url: baseUrl + 'sslis/',
							method: 'post',
							data: JSON.stringify(transcription)
						})
						.then((res)=>{
								if(res.data.sign[0] !== ''){
									this.session.signal({
											data: res.data.sign.toString(),  
											to: [],                     
											type: 'signVideo'
									})
								}
						})
					}
				})
		},
  },

	watch: {
		onSpeech : function(){
			if(this.onSpeech === true){
				console.log('말하기 시작')
				this.startSpeechToTxt()
			} else {
				console.log('말하기 중단')
				this.recognition = null
			}
		}
	}
  
}
</script>

<style>

</style>