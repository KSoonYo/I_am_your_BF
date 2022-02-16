<template>
	
	<div class='flex' style="border:1px solid; padding: 5px 20px 5px 20px; border-radius:20px">
		<q-btn v-show='host' flat rounded @click='() => { $emit("clickOpenScreen")}' ><i class="fas fa-desktop fa-2x"></i></q-btn>
		<q-btn flat rounded v-show='!onSpeech' @click='toggleOnSpeech'><i class="fas fa-microphone-slash fa-2x"></i></q-btn>
		<q-btn flat rounded v-show='onSpeech' @click='toggleOnSpeech' color='orange-7'> <i class="fas fa-microphone fa-2x"></i></q-btn>

		<q-btn flat rounded v-show='!onMute' @click='toggleOnMute'> <i class="fas fa-volume-up fa-2x"></i></q-btn>
		<q-btn flat rounded v-show='onMute'  @click='toggleOnMute' color='orange-7'> <i class="fas fa-volume-mute fa-2x"></i></q-btn>

		<q-btn flat rounded v-show='!captionEnabled'  @click='() => { captionEnabled = !captionEnabled, $emit("toggleCaption") }'> <i class="fas fa-closed-captioning fa-2x"></i></q-btn>
		<q-btn flat rounded v-show='captionEnabled'  @click='() => { captionEnabled = !captionEnabled, $emit("toggleCaption") }' color='orange-7'>  <i class="far fa-closed-captioning fa-2x"></i></q-btn>
	
		<q-btn flat rounded v-show='!signVideoEnabled'  @click='() => { signVideoEnabled = !signVideoEnabled, $emit("toggleSignVideo") }'><i class="fas fa-american-sign-language-interpreting fa-2x"></i></q-btn>
		<q-btn flat rounded v-show='signVideoEnabled'  @click='() => { signVideoEnabled = !signVideoEnabled, $emit("toggleSignVideo") }' color='orange-7'><i class="fas fa-american-sign-language-interpreting fa-2x"></i></q-btn>
		<q-btn flat rounded @click='() => { $emit("toggleShowChat")}'><i class="fas fa-comment fa-2x"></i></q-btn>
		<q-btn flat rounded @click='() => { $emit("toggleShowMemo")}' ><i class="fas fa-clipboard fa-2x"></i></q-btn>
		<q-btn flat rounded @click='$emit("leaveSessionClick")'><i class="fas fa-times-circle fa-2x"></i></q-btn>
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
		publisher: Object,
		hostPublisher: Object,
		subscribers: Array,
		host: Boolean
	},

  data(){
    return {
      runtimeTranscription_: '',
      onSpeech : false,
			onMute : false,
			captionEnabled : false,
			signVideoEnabled : false,
			recognition : null

    }
  },

  methods: {
    // STT
		toggleOnSpeech(){
			this.recognition = this.onSpeech ? null : this.recognition
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

			// onMute
			toggleOnMute(){
				this.onMute = !this.onMute
				this.subscribers.forEach((subscriber) => {
					subscriber.subscribeToAudio(!this.onMute)
				})

				// 내가 호스트가 아니고 host가 있는 상태라면 hostPublisher를 음소거
				if(!this.host && this.hostPublisher){
					this.hostPublisher.subscribeToAudio(!this.onMute)
				}
				
			}
		
  },

	watch: {
		onSpeech : function(){
			if(this.onSpeech === true){
				console.log('말하기 시작')
				if(this.host){
					this.startSpeechToTxt()
				}
			} else {
				console.log('말하기 중단')
				this.recognition = null
			}
		}
	}
  
}
</script>

<style>


#session-header{
	margin-top: 30px;
	margin-bottom: 30px;
}

</style>