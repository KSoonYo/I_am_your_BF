<template>
  <nav class="navbar navbar-expand-lg navbar-light fixed py-3 d-block"
        data-navbar-on-scroll="data-navbar-on-scroll" style="font-size: 1rem; font-weight: 400; background-color:rgba(255,248,232,0.2);">
        <div class="container"><a class="navbar-brand" href="#" @click="movehome"><img src="assets/img/gallery/logo_bf2.png"
              height="45" alt="logo" /></a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
              class="navbar-toggler-icon"> </span></button>
          <div class="collapse navbar-collapse border-top border-lg-0 mt-4 mt-lg-0" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto pt-2 pt-lg-0 font-base">
              <li class="nav-item px-2" id="btn-outline-dark"><a class="nav-link" aria-current="page" @click='howtouse'>HowToUse</a></li>
              <li class="nav-item px-2" id="btn-outline-dark"><a class="nav-link" aria-current="page" @click='editProfile'>Profile</a></li>
              <li class="nav-item px-2" id="btn-outline-dark"><a class="nav-link" aria-current="page" @click='logout'>Logout</a></li>
            </ul>
          </div>
        </div>
  </nav>
  
</template>

<script>
import { useRouter } from 'vue-router'
import { useQuasar } from 'quasar'

export default {
  name: 'navbarConference',
  props: {
  
  },
  
  setup() {
    // router 불러오기
    const router = useRouter();
    const $q = useQuasar()
    // 프로필 수정 페이지 이동
    const editProfile = function () {
      router.push({ name: 'profile' })
    }

    // 로그아웃
    const logout = function () {
      localStorage.removeItem('accessToken')
      localStorage.removeItem('userInfo')
      router.push({ name : 'home' })
    }

    // howtouse
    const howtouse = function () {
      $q.dialog({
        title: '베프 사이트 이용가이드',
        message: '<p style="font-family: GangwonEdu_OTFBoldA; font-size:1.5rem;">BF는 학생들의 온라인 수업권을 보장하기 위해 만들어진 화상 플랫폼 입니다.</p> <p style="font-family: GangwonEdu_OTFBoldA; font-size:1.5rem;">현재는 한정된 개발시간에 의해 수어 모델링 데이터가 부족합니다.</p> <p style="font-family: GangwonEdu_OTFBoldA; font-size:1.5rem;">데이터는 추후 계속 추가될 예정입니다.</p> <p style="font-family: GangwonEdu_OTFBoldA; font-size:1.5rem;">다음 제시된 예시로 저희 플랫폼을 즐겨보세요</p> <p style="font-family: GangwonEdu_OTFBoldA; font-size:1.5rem;">(크롬환경에서 이용하시는걸 권장합니다.)</p>',
        html: true,
        style: 'max-width:800px; width:100%; font-family: GangwonEdu_OTFBoldA; font-size:1.5rem;',
        ok: '다음',
      }).onOk(() => {
        $q.dialog({
          title: '베프 사이트 이용가이드',
          message: '<i class="fas fa-desktop fa-2x" style="font-size:1.5rem;"></i> <span style="font-family: GangwonEdu_OTFBoldA; margin-left: 10px; font-size:1.5rem;">화면공유 버튼입니다. 호스트만 화면을 온오프할 수 있습니다.</span> <hr> <i class="fas fa-microphone fa-2x" style="font-size:1.5rem;"></i><span style="font-family: GangwonEdu_OTFBoldA; margin-left: 10px; font-size:1.5rem">마이크를 온오프 할 수 있습니다. 참가자들과 편하게 대화하세요!</span> <hr> <i class="fas fa-volume-up fa-2x" style="font-size:1.5rem;"></i><span style="font-family: GangwonEdu_OTFBoldA; margin-left: 10px; font-size:1.5rem">소리를 온오프 할 수 있습니다. 집중하고 싶을때 사용하세요!</span> <hr> <i class="fas fa-closed-captioning fa-2x" style="font-size:1.5rem;"></i><span style="font-family: GangwonEdu_OTFBoldA; margin-left: 10px; font-size:1.5rem">자막을 킬 수 있습니다. 자막을 자유롭게 껐다켜보세요!</span> <hr> <i class="fas fa-american-sign-language-interpreting fa-2x" style="font-size:1.5rem;"></i><span style="font-family: GangwonEdu_OTFBoldA; margin-left: 10px; font-size:1.5rem">수어 영상을 볼 수 있습니다. 다음버튼을 눌러 예시를 확인하세요!</span> <hr> <i class="fas fa-comment fa-2x" style="font-size:1.5rem"></i><span style="font-family: GangwonEdu_OTFBoldA; margin-left: 10px; font-size:1.5rem">댓글을 볼 수 있습니다. 참가자들과 대화를 나누세요!</span> <hr> <i class="fas fa-clipboard fa-2x" style="font-size:1.5rem;"></i><span style="font-family: GangwonEdu_OTFBoldA; margin-left: 10px; font-size:1.5rem">수업기록을 볼 수 있습니다. 호스트가 말하는 자막을 받아보세요!</span> <hr> <i class="fas fa-times-circle fa-2x" style="font-size:1.5rem;"></i><span style="font-family: GangwonEdu_OTFBoldA; margin-left: 10px; font-size:1.5rem;">강의실을 나갈 수 있습니다. 나가시면 사용자 이메일로 수업기록이 전송됩니다.</span>',
          html: true,
          style: 'max-width:800px; width:100%; font-family: GangwonEdu_OTFBoldA; font-size:1.5rem;',
          ok: '다음',
        }).onOk(() => {
          $q.dialog({
          title: '베프 사이트 이용가이드',
          message: '<div class="flex column justify-center items-center"><p>수어 영상 출력 예시입니다. 마이크, 수어 영상을 키고 말해보세요!</p> <p>[예시]</p> <p>안녕하세요.</p> <p>우리는 당신의 친구 베프입니다.</p> <p>저희 시스템은 수어를 통역합니다.</p> <p>또, 자막을 실시간처리 합니다.</p> <p>당신의 권리를 지키겠습니다.</p></div>',
          html: true,
          style: 'max-width:800px; width:100%; font-family: GangwonEdu_OTFBoldA; font-size:1.5rem;',
          ok: '마침',
        })
          
        })
      })
    }

    //홈으로
    const movehome = function () {
      router.push({ name : 'home' })
    }
    return { editProfile, logout, movehome, howtouse }
  }
}
</script>

<style scoped src='../../../../public/assets/css/theme.css'>
.fixed {
  position: fixed;
  top: 0;
  right: 0;
  left: 0;
  z-index: 1030;
}

</style>