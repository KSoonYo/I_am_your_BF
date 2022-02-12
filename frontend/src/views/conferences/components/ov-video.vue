<template>
	<video  :class='[{"publish-video" : role === "publisher"}, {"main-stream" : role === "mainStreamer"}]' autoplay/>
</template>

<script>
export default {
	name: 'OvVideo',
	props: {
		streamManager: Object,
		role: String,
	},
	mounted () {
		this.streamManager.addVideoElement(this.$el)
		console.log('role: ', this.role)
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

.main-stream{
	width: 100%;
	height: 100%;
	object-fit: cover;
}
</style>

