<template>
	<video  
  class='main-stream'
  autoplay/>
</template>

<script>
export default {
	name: 'mainStream',
	props: {
		streamManager: Object,
	},
	mounted () {
		this.streamManager.addVideoElement(this.$el)
	},
}
</script>
<style>
.main-stream{
	width: 100%;
	height: 100%;
	object-fit: cover;
}
</style>

