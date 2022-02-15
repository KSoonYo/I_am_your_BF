<template>
  <div 
  class='chatBox column'
  :class='{"show-chat" : showChat}'
  >
    <div id='chatLog' class='col-11 shadow-3'>
    </div>
    <div id='textBox' class='col'>
      <q-input class="shadow-3" @keyup=' (e) => { e.keyCode === 13 ? sendMessage() : ""} ' type='text' v-model='message' style="border: 1px solid;">
        <q-btn flat rounded color="primary" @click='sendMessage'>
          <i class="fas fa-paper-plane fa-2x" style="color:black;"></i>
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
  right: -20%;
  z-index: 3;
  transition: all .35s;
}

.show-chat{
  right: 0px;
}

#chatLog{
  background-color: rgba(49, 49, 49, 0.7);
  overflow: auto;
  border-radius:10px;
}


</style>