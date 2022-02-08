<template>
<!-- 반응형 처리 해주기 -->
<div class='main-container row'>
  <div class="main-page row" center> 
    <img class="main-image col-xs-12 col-sm-7 col-md-8" alt="사람 둘이 손을 잡고 있는 그림" src="../../assets/bg-image-people.svg">
      <div class="intro col-xs-12 col-sm-5 col-md-4">
        <!-- <h4 class="catchphrase">I’m Your Best Friend
          <br>
          (Barrier Free)
        </h4> -->
        <h1>
          <span class="word0">I'm Your</span>
            <div class="message"> 
              <div class="word1">Best Friend</div>
              <div class="word2">Barrier Free</div>
              <div class="word3">Break fence </div>
          </div>
        </h1> 
          <!-- jwt token 유무에 따라 다른 버튼 -->
          <!-- v-if="!this.$store.state.jwtIsValid" -->
          <div>
            <div class="q-pa-md row items-start q-gutter-md">
              <router-link class="conference-button" :to='{name:"conferenceRoom"}'>
                <q-btn push style="background: #E6A377; color: #FFFFFF" label="수업 참여하기">
                </q-btn>
              </router-link>
              <!-- <q-btn push style="background: #E6A377; color: #FFFFFF" label="수업 참여하기"/> -->
              <!-- <q-btn flat style="color: #000000" label="수업 생성하기"/> -->
            </div>
          </div>
      </div>    
  </div>
  <div class="main-page row" center> 
      <div class="intro col-xs-12 col-sm-5 col-md-4">
        <h1>
          <span class="word0">I'm Your</span>
            <div class="message"> 
              <div class="word1">Best Friend</div>
              <div class="word2">Barrier Free</div>
              <div class="word3">Break fence </div>
          </div>
        </h1> 
          <!-- jwt token 유무에 따라 다른 버튼 -->
          <!-- v-if="!this.$store.state.jwtIsValid" -->
          <div>
            <div class="q-pa-md row items-start q-gutter-md">
              <router-link class="conference-button" :to='{name:"conferenceRoom"}'>
                <q-btn push style="background: #E6A377; color: #FFFFFF" label="수업 참여하기">
                </q-btn>
              </router-link>
              <!-- <q-btn push style="background: #E6A377; color: #FFFFFF" label="수업 참여하기"/> -->
              <!-- <q-btn flat style="color: #000000" label="수업 생성하기"/> -->
            </div>
          </div>
      </div>    
    <img class="main-image col-xs-12 col-sm-7 col-md-8" alt="사람 둘이 손을 잡고 있는 그림" src="../../assets/bg-image-people.svg">
  </div>
  <div class="main-page row" center> 
      <div class="intro col-xs-12 col-sm-5 col-md-4">
        <h1>
          <span class="word0">I'm Your</span>
            <div class="message"> 
              <div class="word1">Best Friend</div>
              <div class="word2">Barrier Free</div>
              <div class="word3">Break fence </div>
          </div>
        </h1> 
          <!-- jwt token 유무에 따라 다른 버튼 -->
          <!-- v-if="!this.$store.state.jwtIsValid" -->
          <div>
            <div class="q-pa-md row items-start q-gutter-md">
              <router-link class="conference-button" :to='{name:"conferenceRoom"}'>
                <q-btn push style="background: #E6A377; color: #FFFFFF" label="수업 참여하기">
                </q-btn>
              </router-link>
              <!-- <q-btn push style="background: #E6A377; color: #FFFFFF" label="수업 참여하기"/> -->
              <!-- <q-btn flat style="color: #000000" label="수업 생성하기"/> -->
            </div>
          </div>
      </div>    
    <img class="main-image col-xs-12 col-sm-7 col-md-8" alt="사람 둘이 손을 잡고 있는 그림" src="../../assets/bg-image-people.svg">
  </div>
  <div class="q-pa-md row items-start q-gutter-md justify-evenly">
  <div class="container col-xs-12 col-sm-6 col-md-3">
    <img src="https://cdn.quasar.dev/img/mountains.jpg" alt="Avatar" class="image">
    <div class="overlay">
      <div class="text">저희 서비스의 핵심 가치들을 소개합니다 첫번째로 워라벨입니다.</div>
    </div>
  </div>
  <div class="container col-xs-12 col-sm-6 col-md-3">
    <img src="https://cdn.quasar.dev/img/mountains.jpg" alt="Avatar" class="image">
    <div class="overlay">
      <div class="text">두번째 핵심 가치는 건강과 행복입니다.</div>
    </div>
  </div>
  <div class="container col-xs-12 col-sm-6 col-md-3">
    <img src="https://cdn.quasar.dev/img/mountains.jpg" alt="Avatar" class="image">
    <div class="overlay">
      <div class="text">세번째 핵심 가치는 돈과 명예,지위 그리고 학습 입니다.</div>
    </div>
  </div>
  </div>
</div>
</template>


<script>
export default {
  name: 'home'
}
</script>
<style scoped>

.main-container {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

.main-page {
  display: flex;
  bottom: 5rem;
}

.main-image {
  height: auto;
  width: auto;
  display: flex;

}

.intro {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.catchphrase {
  justify-content: center;
}

.conference-button {
  position: relative;
  text-decoration: none;
}

.container {
  position: relative;
  width: 50%;
}

.image {
  display: block;
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
  background-color: white;
  /* background-color: #484d4e; */
}

.container:hover .overlay {
  opacity: 0.8;
}

.text {
  color: black;
  font-size: 20px;
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  text-align: center;
}

* {
  box-sizing: border-box;
}

/* 변화하는 텍스트 */

/* body {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  width: 100vw;
} */
h1 {
  color: #333;
  font-family: tahoma;
  font-weight: 900;
  font-size: 3rem;
  line-height: 1.5;
  text-transform: uppercase;
  white-space: nowrap;
  overflow: hidden;
  position: relative;
  width: 650px;
}

h1 span {
  font-size: 40px;
  margin-left: 40px;
}

.message {
  background-color: rgba(253,137,0,0.35);
  /* opacity: 0.75; */
  color: #333;
  display: block;
  font-weight: 900;
  overflow: hidden;
  position: absolute;
  padding-left: 0.5rem;
  top: 0.2rem;
  left: 250px;
  animation: openclose 9s ease-in-out infinite;
}

.word1, .word2, .word3 {
  font-family: tahoma;
}

@keyframes openclose {
  0% {
    top: 0.05rem;
    width: 0;
  }
  5% {
    width: 0;
  }
  15% {
    width: 330px;
  }
  30% {
    top: 0.05rem;
    width: 330px;
  }
  33% {
    top: 0.05rem;
    width: 0;
  }
  35% {
    top: 0.05rem;
    width: 0;
  }
  37% {
    top: -4.5rem;
    
  }
  48% {
    top: -4.5rem;
    width: 370px;
  }
  62% {
    top: -4.5rem;
    width: 370px;
  }
  66% {
    top: -4.5rem;
    width: 0;
    text-indent: 0;
  }
  71% {
    top: -9rem;
    width: 0;
    /* text-indent: 5px; */
  }
  86% {
    top: -9rem;
    width: 340px;
  }
  95% {
    top: -9rem;
    width: 340px;
  }
  98% {
    top: -9rem;
    width: 0;
    /* text-indent: 5px; */
  }
  100% {
    top: 0;
    width: 0;
    text-indent: 0;
  }
}
</style>