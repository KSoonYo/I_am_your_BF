<template>
  <div id='chatBox' class='column'>
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
    session: Object
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

  mounted(){
    this.$nextTick(function () {
      // 전체 화면내용이 렌더링된 후에 아래의 코드 실행
       this.session.on('signal:chat', (event)=>{
        console.log('메시지 보낸 사람 object: ',  JSON.parse(event.from.data).clientData)
        
        const messageBox = document.createElement('div')
        const p = document.createElement('p')
        const nameSpan = document.createElement('span')
        nameSpan.style.display = 'block'
        nameSpan.textContent = '보낸사람: ' + JSON.parse(event.from.data).clientData
        messageBox.appendChild(nameSpan)

        p.innerText = event.data
        messageBox.appendChild(p)
        document.querySelector('#chatLog').appendChild(messageBox)

      })

  })
  }

}
</script>

<style>
#chatBox{
  position: absolute;
  width: 20%;
  height: 100%;
  padding-left: 30px;
  right: 0px;
  z-index: 3;
  overflow: auto;
}

#chatLog{
  background-color: rgb(243, 240, 240);
}


</style>