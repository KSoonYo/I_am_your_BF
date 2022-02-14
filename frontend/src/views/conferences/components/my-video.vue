<template>
	<video  
  class='publish-video'
  autoplay/>
</template>

<script>
export default {
	name: 'MyVideo',
	props: {
		streamManager: Object,
	},
	mounted () {
		this.streamManager.addVideoElement(this.$el)
	},
}
</script>
<style>
.publish-video{
	position: absolute;
	bottom: 0px;
	width: 100%;
	object-fit: cover;
}
</style>

