<template>
  <div 
  class='memoBox column'
  :class='{"show-memo" : showMemo}'
  >
    <div id='memoLog' class='col shadow-3' style="margin-left: 13%; margin-bottom: 10%">
      <h5 class="text-center" style="color:white"> 수업 기록 </h5>
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
  left: -30%;
  z-index: 3;
  transition: all .35s;
}

.show-memo{
  left: 0px;
}

#memoLog{
  background-color: rgba(47,50,59,0.8);
  overflow: auto;
  border-radius:20px;
}
</style>