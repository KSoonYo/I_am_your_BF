<template>
  <div class='wrapper'>
    <q-layout view="lHh Lpr lff" style="height: 650px" container>

        <!-- 프로필 -->
      <q-drawer
        v-model="drawer"
        show-if-above
        :width="300"
        :breakpoint="600"    
        bordered
        class='side-bar'
      >

        <div class='profile'>
          <q-avatar size="200px"  class="q-mb-sm image-box">
            <img src="@/assets/profile-default.png">
          </q-avatar>
          <div class="text-weight-bold">Razvan Stoenescu</div>
          <div>@rstoenescu</div>
        </div>
      
        <q-list padding>
            <q-item :to='{ name: "conferenceList"}' clickable v-ripple>
              수업 이력
            </q-item>
          
            <q-item :to='{ name: "profileUpdate"}' clickable v-ripple>
              프로필 수정
            </q-item>

            <q-item :to='{ name: "conferenceList"}' clickable v-ripple>
              비밀번호 변경
            </q-item>
        </q-list>


      </q-drawer>

      <q-page-container>
          <router-view></router-view>
      </q-page-container>

    
    </q-layout>
  </div>
</template>

<script>

export default {
  name: 'profile',
}
</script>

<style scoped>

.profile{
  margin-top: 30px;
  text-align: center;
}

.image-box{
  border-radius: 70% !important;
}

.side-bar{
  padding: 0 30px;
}

.wrapper{
  padding-right: 30px;
}


</style>