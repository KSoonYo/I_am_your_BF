<template>
	<video  
	:class='{"subscribe-video" : role === "subscriber"}' 
	autoplay
	/>
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
	},
}
</script>
<style>

.subscribe-video{
	position: absolute;
	bottom: 0px;
	width: 100%;
	object-fit: cover;
}

</style>

