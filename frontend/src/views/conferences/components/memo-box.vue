<template>
  <div 
  class='memoBox column'
  :class='{"show-memo" : showMemo}'
  >
    <div id='memoLog' class='col'>
      <h1> 수업 기록 </h1>
      <div id='memoContent'>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'memoBox',
  props:{
    showMemo : Boolean,
    session : Object
  },

  mounted(){
    this.$nextTick(function () {
      // 전체 화면내용이 렌더링된 후에 아래의 코드 실행
       this.session.on('signal:memo', (event)=>{        
        const messageBox = document.createElement('div')
        const p = document.createElement('p')
        const nameSpan = document.createElement('span')
        nameSpan.style.display = 'block'
        nameSpan.textContent = JSON.parse(event.from.data).clientData 
        messageBox.appendChild(nameSpan)

        p.innerText = event.data
        messageBox.appendChild(p)
        document.querySelector('#memoLog').appendChild(messageBox)

      })

    })
  }
}
</script>

<style>
.memoBox{
  position: absolute;
  width: 20%;
  height: 100%;
  left: -20%;
  z-index: 3;
  transition: all .35s;
}

.show-memo{
  left: 0px;
}

#memoLog{
  background-color: rgb(243, 240, 240);
  overflow: auto;
}
</style>