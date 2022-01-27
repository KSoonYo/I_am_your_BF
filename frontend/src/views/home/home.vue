<template>
  <q-page class="main" center>
    <img class="main-image" alt="사람 둘이 손을 잡고 있는 그림" src="../../assets/bg-image-people.svg" style="width: 50%; height: 50%">
      <div class="intro">
    <h3>
      I’m Your  Best Friend
      <br>
      (Barrier Free)
    </h3>
  </div>
  <div class="conference-button">
  <div class="q-pa-md row items-start q-gutter-md">
    <q-btn push style="background: #E6A377; color: #FFFFFF" label="수업 참여하기"/>
    <!-- <q-btn flat style="color: #000000" label="수업 생성하기"/> -->
  </div>
  </div>
  </q-page>
  <div class="q-pa-md row items-start q-gutter-md justify-center">
    <q-card class="my-card-1 col-3 relative">
    <img src="https://cdn.quasar.dev/img/mountains.jpg" class="image-1">
    <div class="overlay"></div>
    <div class="text">Hello World</div> 
    <q-card-section>
    <div class="text-h6">서비스 요약</div>
    <div class="text-subtitle2">서비스 설명</div>
    </q-card-section> 
    <q-card-section class="q-pt-none">
    {{ lorem }}
    </q-card-section>
    </q-card>
    <q-card class="my-card-2 col-3">
    <img src="https://cdn.quasar.dev/img/mountains.jpg" >
    <div class="overlay"></div>
    <div class="text">Hello World</div> 
    <q-card-section>
    <div class="text-h6">서비스 요약</div>
    <div class="text-subtitle2">서비스 설명</div>
    </q-card-section> 
    <q-card-section class="q-pt-none">
    {{ lorem }}
    </q-card-section>
    </q-card>
    <q-card class="my-card-3 col-3">
    <img src="https://cdn.quasar.dev/img/mountains.jpg">
    <div class="overlay"></div>
      <div class="text">Hello World</div> 
    <q-card-section>
    <div class="text-h6">서비스 요약</div>
    <div class="text-subtitle2">서비스 설명</div>
    </q-card-section> 
    <q-card-section class="q-pt-none">
    {{ lorem }}
    </q-card-section>
    </q-card>
  </div>
</template>


<script>
export default {
  name: 'home'
}
</script>
<style scoped>
.main {
  margin-left: 100px;
  display: flex;
}
.main-image {
  margin-top: -100px;
}
.intro {
  margin-top: 200px;
}
.conference-button {
  margin-top: 400px;
  margin-left: -400px;
}

.my-card-1:hover .overlay {
  opacity: 0.5;
}
.my-card-2:hover .overlay {
  opacity: 0.5;
}
.my-card-3:hover .overlay {
  opacity: 0.5;
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
  background-color: #484d4e;
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