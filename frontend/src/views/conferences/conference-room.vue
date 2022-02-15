<template>
  <div id='main-container'>
    <div id="session" v-if="session">
			<div id="session-header" class='row flex justify-between'>
				<!-- tool box -->
				<tool-box 
				:session='session'
				:publisher='myPublisher'
				:subscribers='subscribers'
				@leaveSessionClick='leaveSession'
				@toggleCaption='() => { captionEnabled = !captionEnabled }'
				@toggleSignVideo='() => { videoEnabled = !videoEnabled }'
				@toggleShowMemo='() => { showMemo = !showMemo }'
				@toggleShowChat='() => { showChat = !showChat }'
				@clickOpenScreen='openScreen'
				/>
			</div>			


			<div style='height: 100%; position: relative; overflow: hidden;' class='row justify-evenly q-col-gutter-md'>
					<!-- chat box -->
				<chat-box
				:session='session'
				:showChat='showChat'
				/>
				
					<!-- memo box -->
				<memo-box 
				:session='session'
				:showMemo='showMemo'
				/>

					<!-- 메인 화면  -->
				<div id='main-video' class='col-9'>

					<!-- 자막 -->
					<div v-show='captionEnabled' class='caption'>
					</div>

					<!-- 수화 video -->
					<div v-show='videoEnabled' class='sign-video-container'>
						<!-- <video id='videoPlayer' width='200px' height='150px'  playbackRate=2 @ended='onVideoEnded'>
							<source type='vidoe/mp4'>
							<strong>Your browser does not support the video tag.</strong>
						</video> -->
					</div>

					<user-video :role='"mainStreamer"' id='mainStream' :stream-manager="mainStreamManager"/>
				</div>

				<!-- 참가자 화면 -->
				<div id="video-container" class='col-2 column displaywidth'>
					<div class='guest-box shadow-3 col'>
						<!-- 참가자 -->
						<!-- <Flicking 
						:options='{ horizontal: false,  moveType: "freeScroll", bound: true}'
						style="width: 100%; height: 100%;"
						>
						</Flicking> -->
						<user-video 
						:host='host'
						:role='"subscriber"' v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub"/>
					</div>

					<publish-video class="displaywidth" v-show='!host' id='publisher' :stream-manager='myPublisher'/>	
				</div>
			</div>

		
		</div>
  </div>
</template>

<script>
import axios from 'axios'
import { OpenVidu } from 'openvidu-browser'
import UserVideo from './components/user-video'
import PublishVideo from './components/publisher-video'

import ToolBox from './components/tool-box.vue'
import ChatBox from './components/chat-box.vue'
import MemoBox from './components/memo-box.vue'

axios.defaults.headers.post['Content-Type'] = 'application/json'
// const OPENVIDU_URL = process.env.VUE_APP_OPENVIDU_URL;
const VIDEO_DEFAULT_URL = process.env.VUE_APP_DJANGO_MEDIA_URL;


export default {
	name: 'session-test',
	components: {
		UserVideo,
		ToolBox,
		PublishVideo,
		ChatBox,
		MemoBox
	},
	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			myPublisher: undefined,
			screenPublisher: undefined,
			subscribers: [],
			videoEnabled : false,
			captionEnabled : false,
			captionText : [],
			videoList : [],
			videoIndex : 0,
			videoDefaultUrl : VIDEO_DEFAULT_URL,

			showMemo : false,
			showChat : false,

			host: false,
			mySessionId: null,
			myUserName: '',
		
		}
	},
	methods: {
		joinSession () {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();
			// --- Init a session ---
			this.session = this.OV.initSession();

			// --- Specify the actions when events take place in the session ---
			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream)
				if(stream.typeOfVideo === 'SCREEN'){
					this.mainStreamManager = subscriber
				} 
				this.subscribers.push(subscriber)
			})
			
			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			})

			this.session.on('signal:chat', (event)=>{
        console.log('메시지 보낸 사람 object: ',  JSON.parse(event.from.data).clientData)
        
        const messageBox = document.createElement('div')
        const p = document.createElement('p')
        const nameSpan = document.createElement('span')
        nameSpan.style.display = 'block'
				nameSpan.style.color = 'white'
				nameSpan.style.fontSize = '15px'
				messageBox.style.padding = '0.5rem 0.5rem 0.25rem 0.5rem'
        nameSpan.textContent = '보낸사람: ' + JSON.parse(event.from.data).clientData
        messageBox.appendChild(nameSpan)

        p.innerText = event.data
				p.style.color = 'white'
				p.style.fontSize = '18px'
				p.style.fontWeight = '600'
        messageBox.appendChild(p)
        document.querySelector('#chatLog').appendChild(messageBox)

      })


			this.session.on('signal:memo', (event)=>{        
        const messageBox = document.createElement('div')
        const p = document.createElement('p')
        const nameSpan = document.createElement('span')
        nameSpan.style.display = 'block'
				nameSpan.style.color = 'white'
				nameSpan.style.fontSize = '15px'
				messageBox.style.padding = '0.5rem 0.5rem 0.25rem 0.5rem'
        nameSpan.textContent = JSON.parse(event.from.data).clientData 
        messageBox.appendChild(nameSpan)

        p.innerText = event.data
				p.style.color = 'white'
				p.style.fontSize = '18px'
				p.style.fontWeight = '600'
        messageBox.appendChild(p)
        document.querySelector('#memoLog').appendChild(messageBox)

      })

			this.session.on('signal:caption', ({data})=>{
				const span = document.createElement('span')
				const captionBox = document.querySelector('.caption') 
				span.textContent = data
				if( captionBox.hasChildNodes() ){
					captionBox.removeChild(captionBox.firstChild)
				}
				span.style.backgroundColor='black'
				span.style.color= 'white'
				span.style.fontWeight= 'bold'
				span.style.fontSize = '2em'
				span.style.wordBreak = 'keep-all'
				captionBox.appendChild(span)
			})

			// 비디오 소스 저장
			this.session.on('signal:signVideo', (event)=>{
				// const videoPlayer = document.querySelector('#videoPlayer')

				if(this.videoList.length === 0){
					// 아예 비디오가 없는 경우
					console.log('비디오 삽입')
					const signVideoContainer = document.querySelector('.sign-video-container')

					while( signVideoContainer.hasChildNodes() ){
						signVideoContainer.removeChild(signVideoContainer.firstChild )
					}
	
					const video1 = document.createElement('video')
					video1.defaultPlaybackRate = 2
					video1.muted = true
					video1.width = '200px'
					video1.height = '150px'
	
					const video2 = document.createElement('video')
					video2.defaultPlaybackRate = 2
					video2.muted = true
					video2.width = '200px'
					video2.height = '150px'

					this.videoList = event.data.split(',')

					video1.setAttribute('src', this.videoDefaultUrl + this.videoList[0])
					signVideoContainer.appendChild(video1)
					video1.id = 'videoPlayer'
				

					video1.addEventListener('play', () => { 
						this.videoIndex = this.videoIndex < this.videoList.length - 1 ? this.videoIndex + 1 : 0

						if(this.videoIndex === 0){
							this.videoList = []
							return
						}
						const nextIndex = this.videoIndex
						video2.setAttribute('src', this.videoDefaultUrl + this.videoList[nextIndex])
						video2.load()
					})

					video2.addEventListener('play', () => {
						this.videoIndex = this.videoIndex < this.videoList.length - 1 ? this.videoIndex + 1 : 0
						
						if(this.videoIndex === 0){
							this.videoList = []
							return
						}

						const nextIndex = this.videoIndex
						video1.setAttribute('src', this.videoDefaultUrl + this.videoList[nextIndex]) 
						video1.load()
					})

					video1.addEventListener('ended', () => {
						// this.onVideoEnded(1)
						if(this.videoIndex === 0){
							return
						}
						
						const parentNode = video1.parentNode
						video1.id = ''
						video2.id = 'videoPlayer'
						parentNode.replaceChild(video2, video1)
						video2.play()
					})

					video2.addEventListener('ended', () => {
						//this.onVideoEnded(2)
						if(this.videoIndex === 0){
							return
						}
						
						const parentNode = video2.parentNode
						video2.id = ''
						video1.id = 'videoPlayer'
						parentNode.replaceChild(video1, video2)
						video1.play()
					})

					video1.load()
					video1.play()
					// videoPlayer.defaultPlaybackRate = 2
					// videoPlayer.setAttribute('src',  this.videoDefaultUrl + this.videoList[this.videoIndex])
					// videoPlayer.load()
					// videoPlayer.play()
				}	else {
					// 배열에 기존 비디오가 있는 경우
					console.log('비디오 추가')
					this.videoList.push.apply(this.videoList, event.data.split(','))
				}
			})

			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception)
			})

	
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {
						// --- Get your own camera stream with the desired properties ---
						let publisher = this.OV.initPublisher('#publisher', {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: false,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
							resolution: '640x480',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false       	// Whether to mirror your local video or not
						})
						this.myPublisher = publisher

						// 호스트 일 때, mainStream으로
						if(this.host){
							this.mainStreamManager = publisher
						}
						
						this.screenPublisher = publisher

						// --- Publish your stream ---
						this.session.publish(this.myPublisher)
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					})
			})
			window.addEventListener('beforeunload', this.leaveSession)
		},
		
		openScreen () {
			console.log('화면 공유 시작')	
					let publisher = this.OV.initPublisher('#publisher', { 
								videoSource: 'screen', 
								publishAudio: false,
								})

							publisher.once('accessAllowed', () => {
									publisher.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
											console.log('User pressed the "Stop sharing" button')
												// let publisher = this.OV.initPublisher('#publisher', {
												// 	audioSource: undefined, // The source of audio. If undefined default microphone
												// 	videoSource: undefined, // The source of video. If undefined default webcam
												// 	publishAudio: false,  	// Whether you want to start publishing with your audio unmuted or not
												// 	publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
												// 	resolution: '640x480',  // The resolution of your video
												// 	frameRate: 30,			// The frame rate of your video
												// 	insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
												// 	mirror: false       	// Whether to mirror your local video or not
												// })
											this.session.unpublish(this.screenPublisher)
											this.mainStreamManager = undefined
											this.session.publish(this.myPublisher)
								})

							this.session.unpublish(this.myPublisher)
							this.screenPublisher = publisher
							console.log('화면 공유 시작')
							console.log('myPublisher', this.myPublisher)
							console.log('screenPublisher', this.screenPublisher)
							this.mainStreamManager = publisher
							this.session.publish(this.screenPublisher)
						
						})
							publisher.once('accessDenied', () => {
									console.warn('ScreenShare: Access Denied')
						})
		},

		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session){
				this.session.disconnect()
			}
			this.session = undefined
			this.mainStreamManager = undefined
			this.publisher = undefined
			this.subscribers = []
			this.OV = undefined
			window.removeEventListener('beforeunload', this.leaveSession)

		},

		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},


		// onVideoEnded(index){
		// 	const videoPlayer = document.querySelector('#videoPlayer')
			
		// 	if(this.videoIndex < this.videoList.length - 1){
		// 		this.videoIndex++
		// 	} else{
		// 		this.videoIndex = 0
		// 		this.videoList = []
		// 		return
		// 	}
		// 	videoPlayer.defaultPlaybackRate = 2

		// 	videoPlayer.setAttribute('src', this.videoDefaultUrl + this.videoList[this.videoIndex])
		// 	videoPlayer.load()
		// 	videoPlayer.play()
		// },


		getToken (mySessionId) {
			return new Promise((resolve, reject) => {
					axios.post(`http://localhost:8080/api/openvidu/get-token`, JSON.stringify({
									sessionName: mySessionId,
							}))
							.then(response => response.data)
							.then(data => resolve(data.token))
							.catch(error => reject(error.response));
				})
		},

	},

  created(){
		console.log(this.$route.params.conferenceId)
		this.$store.dispatch('getConferenceDetail', this.$route.params.conferenceId)
		.then((response)=>{
			this.mySessionId = this.$route.params.conferenceId
			this.myUserName = response.data.userName
			if(JSON.parse(localStorage.getItem('userInfo')).userId === response.data.userId){
				this.host = true
			} 
		})
		.then(() => {
			this.joinSession()
		})
		
  }
}
</script>

<style scoped>
@media screen and (max-width: 1600px) 
{ .displaywidth { display: none; } }


#videoPlayer{
	object-fit: cover;
	width: 100%;
	height: 100%;
}


#session{
	height: 100%;
}

#session-header{
	margin-top: 30px;
	margin-bottom: 30px;
}

#main-container{
	width: 100vw;
  height: 90vh;
}

#main-video{
	position: relative;
	height: 100%;
	/* background-color: rgba(255, 235, 205, 0.719); */
}

#video-container{
	height: 100%;
	/* background-color: rgb(221, 182, 124); */
}

.sign-video-container{
	position: absolute;
	width: 30%;
	height: 30%;
	bottom: 0px;
	right: 16px;
	z-index: 2;
}

.caption{
	position: absolute;
	width: 50%;
	height: 30%;
	bottom: 0px;
	left: 50px;
	z-index: 2;			
	overflow: auto;
	word-break: keep-all;
}

.caption-text{
	background-color: black;
	color: white;
	font-weight: bold;
	font-size: 2em;
	word-break: keep-all;
}

.guest-box{
	position: relative;
	border: 1px solid black;
	border-radius: 10px;
	padding: 10px 10px;
	width: 100%;
	overflow: auto;
}

.guest{
	width: 100%;
}

</style>