<template>
  <div id='chatBox'>
    <div id='chatLog'>
    </div>
    <input type='text'>
    <button>
      send
    </button>
  </div>
</template>

<script>
export default {
  name: 'chatBox',

}
</script>

<style>
#chatBox{
  position: absolute;
  width: 30%;
  height: 100%;
  
  left: 0px;
}


</style>