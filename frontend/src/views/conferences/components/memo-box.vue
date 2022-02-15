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

}
</script>

<style scoped>
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