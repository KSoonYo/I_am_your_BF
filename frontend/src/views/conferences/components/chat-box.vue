<template>
  <div 
  class='chatBox column'
  :class='{"show-chat" : showChat}'
  >
    <div id='chatLog' class='box col-11 shadow-3' style="margin-right: 12%;">
    </div>  
    <div id='textBox' class='col' style='margin-right: 12%;'>
      <q-input borderless :input-style='{backgroundColor : "rgba(91,94,109, 1)", color : "white" , fontWeight : "bold", fontSize : "1.5rem"}' class="shadow-3" @keyup=' (e) => { e.keyCode === 13 ? sendMessage() : ""} ' type='text' v-model='message'>
        <q-btn flat @click='sendMessage' style="backgroundColor : rgba(91,94,109, 1)">
          <i class="fas fa-paper-plane fa-2x" style="color:white;"></i>
        </q-btn>
      </q-input>
    </div>
    
  </div>
</template>

<script>
export default {
  name: 'chatBox',
  props:{
    session: Object,
    showChat: Boolean
  },

  data () {
    return{
      message : ''
    }
  },

  methods: {
    sendMessage(){
      // this.session.on('signal:chat', (event)=>{
      //   console.log('메시지 보낸 사람 object: ', event.from)
        
      //   const messageBox = document.createElement('div')
      //   const p = document.createElement('p')
      //   const nameSpan = document.createElement('span')
      //   nameSpan.style.display = 'block'
      //   nameSpan.textContent = '보낸사람: ' + JSON.parse(event.from.data).clientData
      //   p.appendChild(nameSpan)
      //   p.innerText = event.data

      //   messageBox.appendChild(p)
      //   document.querySelector('#chatLog').appendChild(messageBox)

      // })

      if (this.message.trim()) {
        this.session.signal({
          data: this.message,
          to: [],
          type: 'chat'
        })
        .then(() => {
          this.message = ''

          // 스크롤 하단 고정
          const chatDiv = document.querySelector('#chatLog')
          chatDiv.scrollTop = chatDiv.scrollHeight
        })
      }
    }
  },

}
</script>

<style scoped>
.chatBox{
  position: absolute;
  width: 20%;
  height: 100%;
  right: -30%;
  z-index: 3;
  transition: all .35s;
}

.show-chat{
  right: 0px;
}

#chatLog{
  position: relative;
  background-color: rgba(47,50,59,0.8);
  overflow: auto;  
  border-radius:20px 20px 0px 0px;
  
}
.box {
    -ms-overflow-style: none; /* IE and Edge */
    scrollbar-width: none; /* Firefox */
}
.box::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
}

</style>