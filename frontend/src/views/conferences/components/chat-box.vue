<template>
  <div 
  class='chatBox column'
  :class='{"show-chat" : showChat}'
  >
    <div id='chatLog' class='col-12 shadow-3' style="margin-right: 10%;">
      <div id='textBox' style='position:absolute; bottom:0; width:100%'>
        <q-input class="shadow-3" @keyup=' (e) => { e.keyCode === 13 ? sendMessage() : ""} ' type='text' v-model='message' style="border-top: 1px solid; color:white">
          <q-btn flat rounded @click='sendMessage'>
            <i class="fas fa-paper-plane fa-2x" style="color:white;"></i>
          </q-btn>
        </q-input>
      </div>
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
  position: relative;
  background-color: rgba(49, 49, 49, 0.8);
  overflow: auto;
  border-radius:20px;
  
}


</style>