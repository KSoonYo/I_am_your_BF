<template>
  <div :style="image" class="image">
  </div>
  <div :style="image1" class="image1">
  </div>

  <h2>Fade in Overlay</h2>
<p>Hover over the image to see the effect.</p>

<div class="container">
  <img src="https://cdn.quasar.dev/img/mountains.jpg" alt="Avatar" class="image">
  <div class="overlay">
  <div class="text">나한테 왜 그래</div>
  </div>
  <!-- <img src="https://cdn.quasar.dev/img/mountains.jpg" alt="Avatar" class="image">
  <div class="overlay">
  <div class="text">World</div>
  </div> -->
  <!-- <img src="https://cdn.quasar.dev/img/mountains.jpg" alt="Avatar" class="image">
  <div class="overlay">
  <div class="text">World</div>
  </div> -->
</div>
</template>

<script>
export default {
    data() {
    return {
      image: {backgroundImage: ('url(https://via.placeholder.com/150)')},
      image1: {backgroundImage: ('url(https://via.placeholder.com/150)')}
    } 
  }
}
</script>

<style scoped>
    .image {
        height: 150px;
        width: 150px;
        background-repeat: no-repeat;
        color: black;
    }
    .image1 {
        height: 150px;
        width: 150px;
        background-repeat: no-repeat;
        color: black;
    }

  .container {
  position: relative;
  width: 50%;
}

.image {
  width: 100%;
  height: auto;
}

.overlay {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  height: 100%;
  width: 100%;
  opacity: 0;
  transition: .5s ease;
  background-color: black;
}

.container:hover .overlay {
  opacity: 0.5;
}

.text {
  color: white;
  font-size: 20px;
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  text-align: center;
}
</style>