<template>
  <div 
  class='chatBox column'
  :class='{"show-chat" : showChat}'
  >
    <div id='chatLog' class='col-11'>
    </div>
    <div id='textBox' class='col'>
    <input @keyup=' (e) => { e.keyCode === 13 ? sendMessage() : ""} ' type='text' v-model='message'>
    <button @click='sendMessage'>
      send
    </button>
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


      this.session.signal({
        data: this.message,
        to: [],

        type: 'chat'
      })
      .then(() => {
        this.message = ''
      })
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
  background-color: rgb(243, 240, 240);
  overflow: auto;
}


</style>