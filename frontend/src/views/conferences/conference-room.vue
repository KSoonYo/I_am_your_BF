<template>
  <div id='main-container' class="container">
    <div id="session" v-if="session">
			<div id="session-header">
				<h1 id="session-title">{{ mySessionId }}</h1>
				<input type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session">
			</div>

      <div class="row">
        <!-- 메인 화면  -->
        <!-- <div id="main-video col-6">
          <user-video :stream-manager="mainStreamManager"/>
        </div> -->

        <!-- 참가자 화면 -->
        <div id="video-container col-6">
          <!-- host -->
          <user-video id='publisher' :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)"/>
          
          <!-- 참가자 -->
          <!-- <user-video class="participant" v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)"/> -->
        </div>
      </div>

			<!-- 참가자 명단 -->
			<!-- <div class="container">
				<h2> 참가자 명단 </h2>
				<div v-for="subscriber in subscribers" :key="subscriber.stream.connection.connectionId">
					<p>{{ subscriber }}</p>
				</div>
			</div> -->

			<!-- 자막 -->
			<div v-show='captionEnabled' class='caption'>
			</div>

			<!-- 수화 video -->
			<div v-show='videoEnabled' class='sign-video-container'>
				<video id='videoPlayer' width='200' height='150'  muted autoplay playbackRate=3 @ended='onVideoEnded'>
					<source :src='videoList ? videoDefaultUrl + videoList[videoIndex]: null' type='vidoe/mp4'>
					<strong>Your browser does not support the video tag.</strong>
				</video>
			</div>

      <!-- tool box -->
			<tool-box 
			:session='session'
			:publisher='publisher'
			@toggleCaption='() => { captionEnabled = !captionEnabled }'
			@toggleSignVideo='() => { videoEnabled = !videoEnabled }'
			
			>

			</tool-box>



      <div id="tool-bar">
				<!-- 화면공유 -->
				<button @click="openScreen">화면공유</button>

      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { OpenVidu } from 'openvidu-browser'
import UserVideo from './components/user-video'
import ToolBox from './components/tool-box.vue'

axios.defaults.headers.post['Content-Type'] = 'application/json'
const OPENVIDU_URL = "http://" + location.hostname + ":8080";
const VIDEO_DEFAULT_URL = 'http://127.0.0.1:8000/media/'

// const OPENVIDU_SERVER_URL = 'https://' + 'i6b107.p.ssafy.io' + ':6443'
// const OPENVIDU_SERVER_SECRET = 'bfssafy'

export default {
	name: 'session-test',
	components: {
		UserVideo,
		ToolBox
	},
	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			videoEnabled : false,
			captionEnabled : false,
			captionText : [],
			videoList : [],
			videoIndex : 0,
			videoDefaultUrl : VIDEO_DEFAULT_URL,


      // conference-list에서 참여하기 버튼 눌렀을 때, 강의실 제목을 props로 전달받아야 할 듯
			mySessionId: 'SessionA',

      // 헌재 유저의 이름(별명)을 store의 state로 받아야 할 것 같다.
			myUserName: 'Participant' + Math.floor(Math.random() * 100),
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
				// if(stream.connect.data.signVideod){

				// }

				this.subscribers.push(subscriber)
				// console.log('사용자 목록 : ',this.subscribers)
			});
			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			})

			this.session.on('signal:caption', ({data})=>{
				const p = document.createElement('p')
				p.textContent = data
				document.querySelector('.caption').appendChild(p)
			})

			// 비디오 소스 저장
			this.session.on('signal:signVideo', (event)=>{
				const videoPlayer = document.querySelector('#videoPlayer')
				if(this.videoList.length == 0){
					// 아예 비디오가 없는 경우
					this.videoList = event.data.split(',')
					videoPlayer.defaultPlaybackRate = 1
					videoPlayer.setAttribute('src',  this.videoDefaultUrl + this.videoList[this.videoIndex])
					videoPlayer.play()
				}	else {
					// 배열에 기존 비디오가 있는 경우
					this.videoList.push.apply(this.videoList, event.data.split(','))
				}
			})

			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception)
			})

			// --- Connect to the session with a valid user token ---
			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {
						// --- Get your own camera stream with the desired properties ---
						let publisher = this.OV.initPublisher('#publisher', {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
							resolution: '350x240',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false       	// Whether to mirror your local video or not
						})
						this.mainStreamManager = publisher
						this.publisher = publisher

						// --- Publish your stream ---
						this.session.publish(this.publisher)
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					})
			})
			window.addEventListener('beforeunload', this.leaveSession)
		},
		
		openScreen () {
			var OV = new OpenVidu();
			var sessionScreen = OV.initSession();
			this.getToken().then((token) => {
					sessionScreen.connect(token).then(() => {
							var publisher = OV.initPublisher("html-element-id", { videoSource: "screen" });

							publisher.once('accessAllowed', () => {
									publisher.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
											console.log('User pressed the "Stop sharing" button');
									});
									sessionScreen.publish(publisher);

							});

							publisher.once('accessDenied', () => {
									console.warn('ScreenShare: Access Denied');
							})

					}).catch((error => {
							console.warn('There was an error connecting to the session:', error.code, error.message);

					}));
			});
		},

		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) this.session.disconnect()
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


		onVideoEnded(){
			const videoPlayer = document.querySelector('#videoPlayer')
			if(this.videoIndex < this.videoList.length - 1){
				this.videoIndex++
			} else{
				this.videoIndex = 0
				this.videoList = []
				return
			}
			videoPlayer.defaultPlaybackRate = 1
			videoPlayer.setAttribute('src', this.videoDefaultUrl + this.videoList[this.videoIndex])
			videoPlayer.play()
		},


		getToken (mySessionId) {
			return new Promise((resolve, reject) => {
					axios.post(`${OPENVIDU_URL}/api/openvidu/get-token`, JSON.stringify({
									sessionName: mySessionId,
							}))
							.then(response => response.data)
							.then(data => resolve(data.token))
							.catch(error => reject(error.response));
			});
	},

	},

  created(){
    this.joinSession()
  }
}
</script>

<style scoped>
.participant{
  width: 5rem;
}
</style>