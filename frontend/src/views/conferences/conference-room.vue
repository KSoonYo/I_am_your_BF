<template>
  <div id='main-container'>
    <div id="session" v-if="session">
			<div id="session-header" class='row flex justify-between'>
				<!-- tool box -->
				<tool-box 
				:session='session'
				:publisher='myPublisher'
				:subscribers='subscribers'
				:host='host'
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
				<div class='main-video col-9'>

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

					<main-stream-video :show='!shareScreenEnabled' id='mainStream' :stream-manager="mainStreamManager"/>
					<div v-show='shareScreenEnabled' class='share-screen'>
						<!-- 화면 공유용 비디오 공간 -->

					</div>
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
import MainStreamVideo from './components/main-stream-video'
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
		MemoBox,
		MainStreamVideo
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
			shareScreenEnabled : false,
			captionText : [],
			videoList : [],
			videoIndex : 0,
			videoDefaultUrl : VIDEO_DEFAULT_URL,

			showMemo : false,
			showChat : false,

			host: false,
			hostId : '',
			hostName : '',

			hostPublisher : undefined,
			mySessionId: null,
			myUserName: '',
		

			// 드래그 이벤트용
			isDragging : null,
			originLeft : null,
			originTop : null,
			originX : null,
			originY : null,

			containerWidth: null,
			containerHeight: null,

			signVideoBoxWidth: null,
			signVideoBoxHeight: null


		}
	},
	methods: {
		joinSession () {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();
			// --- Init a session ---
			this.session = this.OV.initSession();

			this.session.on('connectionCreated', ()=>{
				console.log('세션 연결!')
			})

			this.session.on('connectionDestroyed', ()=>{
				console.log('세션 연결 해제!')
			})

			// --- Specify the actions when events take place in the session ---
			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				if(stream.typeOfVideo === 'SCREEN'){
					const shareScreen = document.querySelector('.share-screen')
					const subscriber = this.session.subscribe(stream, shareScreen, {insertMode: 'APPEND'})
					subscriber.createVideoElement(shareScreen, 'APPEND')

					subscriber.on('videoElementCreated', event => {
						this.initMainVideo(event.element)
						event.element['muted'] = true
					})
					this.shareScreenEnabled = true
					return
				}

				const subscriber = this.session.subscribe(stream, undefined)
				const { connection } = subscriber.stream
				const { clientData } = JSON.parse(connection.data)
				console.log('clientData[0]: ', clientData[0], 'hostId: ', this.hostId)
				
				
				if( stream.typeOfVideo === 'CAMERA' &&  clientData[0] === this.hostId ){
					this.shareScreenEnabled = false

					this.mainStreamManager = subscriber
					this.hostPublisher = subscriber
				} else{
					this.subscribers.push(subscriber)
				}
				
			})
			
			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			})

			// 채팅
			this.session.on('signal:chat', (event)=>{        
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

			// 수업 기록
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

			// 자막
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
					video1.setAttribute('width', '100%')
					video1.setAttribute('height', '100%')
					video1.style.objectFit = 'cover'
					// video1.width = '200px'
					// video1.height = '150px'
	
					const video2 = document.createElement('video')
					video2.defaultPlaybackRate = 2
					video2.muted = true
					// video2.width = '200px'
					// video2.height = '150px'
					video2.setAttribute('width', '100%')
					video2.setAttribute('height', '100%')
					video2.style.objectFit = 'cover'

					this.videoList = event.data.split(',')

					video1.setAttribute('src', this.videoDefaultUrl + this.videoList[0])
					signVideoContainer.appendChild(video1)
					video1.className = 'video-player'
				

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
						video1.className = ''
						video2.className = 'video-player'
						parentNode.replaceChild(video2, video1)
						video2.play()
					})

					video2.addEventListener('ended', () => {
						//this.onVideoEnded(2)
						if(this.videoIndex === 0){
							return
						}
						
						const parentNode = video2.parentNode
						video2.className = ''
						video1.className = 'video-player'
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
				this.session.connect(token, { clientData: [this.myUserId, this.myUserName] })
					.then(() => {
						// --- Get your own camera stream with the desired properties ---
						let publisher = this.OV.initPublisher('undefined', {
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
						if(this.hostId === this.myUserId ){
							this.hostPublisher = publisher
							this.mainStreamManager = publisher
						}
						// --- Publish your stream ---
						this.session.publish(this.myPublisher)
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					})
			})

			// 새로고침하면 leaveSession 메소드가 실행되는 이벤트 리스너 등록
			window.addEventListener('beforeunload', this.leaveSession)
		},
		
		initMainVideo(videoElement){
			document.querySelector('.share-screen video').srcObject = videoElement.srcObject
			document.querySelector('.share-screen video')['muted'] = true
		},

		openScreen () {
					const shareScreen = document.querySelector('.share-screen')	
					let newPublisher = this.OV.initPublisher(shareScreen, { 
								videoSource: 'screen', 
								publishAudio: false,
								insertMode: 'APPEND'
							})
							newPublisher.on('videoElementCreated', (event) => {
								console.log('화면 공유 video 생성')
								this.initMainVideo(event.element)
								event.element['muted'] = true
							})

							newPublisher.once('accessAllowed', () => {
								console.log('화면 공유 시작')
									newPublisher.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
											console.log('User pressed the "Stop sharing" button')
							
											this.session.unpublish(newPublisher)
											this.myPublisher = this.hostPublisher
											this.shareScreenEnabled = false
											this.session.publish(this.myPublisher)
								})

							this.session.unpublish(this.myPublisher)
							newPublisher.createVideoElement(shareScreen, 'APPEND')
							this.shareScreenEnabled = true
							this.session.publish(newPublisher)
						
						})
							newPublisher.once('accessDenied', () => {
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
			this.myPublisher = undefined
			this.subscribers = []
			this.OV = undefined
			window.removeEventListener('beforeunload', this.leaveSession)
			
			if (this.host){
				this.$store.dispatch('deleteConference', this.mySessionId)
				.then(()=>{
					this.$router.push({name: 'conferenceList'})
				})
			} else{
				this.$router.push({name: 'conferenceList'})
			}

		},


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
		console.log('현재 session', this.session)
		console.log(this.$route.params.conferenceId)
		this.$store.dispatch('getConferenceDetail', this.$route.params.conferenceId)
		.then((response)=>{
			console.log(response.data.userId)
			this.hostId = response.data.userId
			this.hostName = response.data.userName

			this.mySessionId = this.$route.params.conferenceId

			this.myUserId = JSON.parse(localStorage.getItem('userInfo')).userId
			this.myUserName = JSON.parse(localStorage.getItem('userInfo')).userName
			if(JSON.parse(localStorage.getItem('userInfo')).userId === this.hostId){
				this.host = true
			} 
		})
		.then(() => {
			this.joinSession()
		})
		
  },

	// mounted(){
	// 	this.$nextTick(function () {
	// 		// 전체 화면내용이 렌더링된 후에 아래의 코드 실행
	// 			const container = document.querySelector('.main-video')
	// 			const signVideoBox = document.querySelector('.sign-video-container')
	// 			this.containerWidth = container.getBoundingClientRect().width
	// 			this.containerHeight = container.getBoundingClientRect().height

	// 			this.signVideoBoxWidth = signVideoBox.getBoundingClientRect().width 
	// 			this.signVideoBoxHeight = signVideoBox.getBoundingClientRect().height

	// 			window.addEventListener('resize', ()=>{
	// 			const container = document.querySelector('.main-video')
	// 			const signVideoBox = document.querySelector('.sign-video-container')

	// 			this.containerWidth = container.getBoundingClientRect().width
	// 			this.containerHeight = container.getBoundingClientRect().height

	// 			this.signVideoBoxWidth = signVideoBox.getBoundingClientRect().width 
	// 			this.signVideoBoxHeight = signVideoBox.getBoundingClientRect().height
				
	// 			})

	// 			signVideoBox.addEventListener('mousedown', (e)=>{
	// 				const signVideoBox = document.querySelector('.sign-video-container')

	// 				this.isDragging = true
	// 				this.originX = e.clientX
	// 				this.originY = e.clientY
	// 				this.originLeft = signVideoBox.offsetLeft
	// 				this.originTop = signVideoBox.offsetTop
	// 			})

	// 			document.addEventListener('mousemove', (e)=>{
	// 				if(this.isDragging){
	// 					const signVideoBox = document.querySelector('.sign-video-container')

	// 					const diffX = e.clientX - this.originX
	// 					const diffY = e.clientY - this.originY

	// 					const endOfXPoint = this.containerWidth - this.signVideoBoxWidth
	// 					const endOfYPoint = this.containerHeight - this.signVideoBoxHeight

	// 					signVideoBox.style.left = `${Math.min(Math.max(0, this.originLeft + diffX), endOfXPoint)}px`
	// 					signVideoBox.style.top = `${Math.min(Math.max(0, this.originTop + diffY), endOfYPoint)}px`
	// 				}
	// 			})

	// 			document.addEventListener('mouseup', ()=>{
	// 				this.isDragging = false
	// 			})
			
	// 		})

	// }
}
</script>

<style scoped>
@media screen and (max-width: 1600px) 
{ .displaywidth { display: none; } }


.video-player{
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

.main-video{
	position: relative;
	height: 100%;
	/* background-color: rgba(255, 235, 205, 0.719); */
}

#video-container{
	height: 100%;
	/* background-color: rgb(221, 182, 124); */
}

.share-screen{
	display: flex;
	width: 100%;
	height: 100%;
	flex-direction: column;
  align-items: center;
}

.share-screen > video:nth-child(1){
	width: 100%;
	height: 100%;
	object-fit: cover;
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