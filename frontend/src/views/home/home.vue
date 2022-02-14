<template>
  <!-- ===============================================-->
  <!--    Main Content-->
  <!-- ===============================================-->
  <main class="main" id="top">
    <div id="mainbig"> 
    <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3 d-block"
      data-navbar-on-scroll="data-navbar-on-scroll" style="background-color:rgba(255,248,232,0.5)">
      <div class="container"><a class="navbar-brand" href="#home"><img src="assets/img/gallery/logo_bf2.png"
            height="45" alt="logo" /></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
            class="navbar-toggler-icon"> </span></button>
        <div class="collapse navbar-collapse border-top border-lg-0 mt-4 mt-lg-0" id="navbarSupportedContent">
          <ul class="navbar-nav ms-auto pt-2 pt-lg-0 font-base">
            <li class="nav-item px-2" style="cursor:pointer;"><a class="nav-link" aria-current="page" href="#home">Home</a></li>
            <li class="nav-item px-2"><a class="nav-link" aria-current="page" href="#service">Service</a></li>
            <li class="nav-item px-2"><a class="nav-link" aria-current="page" href="#demonstration">Demonstaration</a></li>
            <li class="nav-item px-2"><a class="nav-link" aria-current="page" href="#testimonial">Testimonial</a></li>
            <li v-if="!state.isLogin" class="nav-item px-2" id="btn-outline-dark"><a class="nav-link" aria-current="page" @click='clickLogin' href="#">Login</a></li>
            <li v-if="!state.isLogin" class="nav-item px-2" id="btn-outline-dark"><a class="nav-link" aria-current="page" @click='clickSignUp' href="#">SignUp</a></li>
            <li v-if="state.isLogin" class="nav-item px-2" id="btn-outline-dark"><a class="nav-link" aria-current="page" @click='editProfile' href="#">Edit</a></li>
            <li v-if="state.isLogin" class="nav-item px-2" id="btn-outline-dark"><a class="nav-link" aria-current="page" @click='logout' href="#">Logout</a></li>
            <!-- <li class="nav-item px-2"><a class="nav-link" href="#services">Our Services</a></li>
            <li class="nav-item px-2"><a class="nav-link" href="#findUs">Find Us</a></li> -->
          </ul>
          <!-- <div class="dropdown d-none d-lg-block">
            <button class="btn bg-soft-warning ms-2" id="dropdownMenuButton1" type="submit" data-bs-toggle="dropdown"
              aria-expanded="false"><i class="fas fa-search text-warning"></i></button>
            <div class="dropdown-menu dropdown-menu-lg-end p-0 rounded" aria-labelledby="dropdownMenuButton1"
              style="top:55px">
              <form>
                <input class="form-control border-200" type="search" placeholder="Search" aria-label="Search"
                  style="background:#FDF1DF;" />
              </form>
            </div>
          </div> -->
          
          <!-- <a class="btn btn-primary order-1 order-lg-0 ms-lg-3" href="#!">Contact Us</a>
          <form class="d-flex my-3 d-block d-lg-none">
            <input class="form-control me-2 border-200 bg-light" type="search" placeholder="Search"
              aria-label="Search" />
            <button class="btn btn-outline-primary" type="submit">Search</button>
          </form> -->
        </div>
      </div>
    </nav>
    <section class="py-xxl-10 pb-0" id="home">
      <div class="bg-holder bg-size"
        style="background-image:url(assets/img/gallery/hero-header-bg.png);background-position:top center;background-size:cover;">
      </div>
      <!--/.bg-holder-->

      <div class="container">
        <div class="row align-items-center">
          <div class="col-md-5 col-xl-6 col-xxl-7 order-0 order-md-1 text-end"><img class="pt-7 pt-md-0 w-110"
              src="assets/img/illustrations/big-main.png" alt="hero-header" /></div>
          <div class="col-md-75 col-xl-6 col-xxl-5 text-md-start text-center py-8">
            <!-- <h1 class="fw-normal fs-6 fs-xxl-7">I am Your </h1>
            <h1 class="fw-bolder fs-6 fs-xxl-7 mb-2">Best Friend</h1> -->
            <div class="content">
              <div class="content__container">
                <p class="content__container__text">
                  I'm Your
                </p>
              </div>
              <div class="content__container">
                <ul class="content__container__list">
                  <li class="content__container__list__item">BEST FRIEND</li>
                  <li class="content__container__list__item">BARRIER FREE</li>
                  <li class="content__container__list__item">BREAK FENCE</li>
                  <li class="content__container__list__item">BLUE FLOWER</li>
                </ul>
              </div>
            </div>
            <p class="main-msg fs-1 mb-5">당신의 수업권을<br />지키며 싸우는 BF가 되겠습니다. </p><a
              class="btn btn-primary me-2" role="button" @click="moveConferenceList">수업실 입장<i
                class="fas fa-arrow-right ms-2"></i></a>
          </div>
        </div>
      </div>
    </section>


    <!-- ============================================-->
    <!-- <section> begin ============================-->
    <section class="pt-5 pt-md-9" id="service">

      <div class="container">
        <div class="row justify-content-center">
          <div class="col-md-8 col-lg-5 text-center mb-3">
            <h5 class="text-danger">SERVICES</h5>
            <h2>Our services for you</h2>
          </div>
        </div>
        
        
        <div class="row">
          <div class="col-lg-3 col-sm-6 mb-6">
            <div class="card service-card shadow-hover rounded-3 text-center align-items-center">
              <div class="card-body p-xxl-5 p-4"> <img src="assets/img/category/icon1.png" width="75" alt="Service" />
                <h4 class="mb-3">Enter <br>Classroom</h4>
                <p class="mb-0 fw-medium">수업실로 <br>입장하세요</p>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-sm-6 mb-6">
            <div class="card service-card shadow-hover rounded-3 text-center align-items-center">
              <div class="card-body p-xxl-5 p-4"> <img src="assets/img/category/icon2.png" width="75" alt="Service" />
                <h4 class="mb-3">Turn On <br>Subtitle </h4>
                <p class="mb-0 fw-medium">자막을 <br>켜보세요.</p>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-sm-6 mb-6">
            <div class="card service-card shadow-hover rounded-3 text-center align-items-center">
              <div class="card-body p-xxl-5 p-4"> <img src="assets/img/category/icon3.png" width="75" alt="Service" />
                <h4 class="mb-3">Turn On <br>SL</h4>
                <p class="mb-0 fw-medium">수어를 <br>켜보세요.</p>
              </div>
            </div>
          </div>
          <div class="col-lg-3 col-sm-6 mb-6">
            <div class="card service-card shadow-hover rounded-3 text-center align-items-center">
              <div class="card-body p-xxl-5 p-4"> <img src="assets/img/category/icon4.png" width="75" alt="Service" />
                <h4 class="mb-3">Enjoy <br>Study</h4>
                <p class="mb-0 fw-medium">신나게 <br>공부하세요.</p>
              </div>
            </div>
          </div>
        </div>
      </div><!-- end of .container-->

    </section>
    <!-- <section> close ============================-->
    <!-- ============================================-->

    
    <!-- ============================================-->
    <!-- <section> begin ============================-->
    <section id="demonstration">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-md-8 col-lg-5 text-center mb-3">
            <h5 class="text-danger">Demonstration</h5>
            <h2>How to use BF</h2>
          </div>
        </div>
        <div class="row">
          <div class="col-12">
            <div class="card bg-dark text-white py-4 py-sm-0"><img class="w-100" src="assets/img/gallery/imyourbf.png"
                alt="video" />
              <div class="card-img-overlay bg-dark-gradient d-flex flex-column flex-center"><img
                  src="assets/img/icons/play.png" width="80" alt="play" />
                <h5 class="text-primary">BF</h5>
                <p class="text-center">당신의 수업권을 보장하는 방법, BF<br
                    class="d-none d-sm-block" />어떻게 사용하면 좋을까요?<br
                    class="d-none d-sm-block" /></p><a class="stretched-link" href="#"
                  data-bs-toggle="modal" data-bs-target="#exampleModal"></a>
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                  aria-hidden="true">
                  <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content overflow-hidden">
                      <div class="modal-header p-0">
                        <div class="ratio ratio-16x9" id="exampleModalLabel">
                          <iframe src="https://www.youtube.com/embed/TlcP2aTOp-Q" title="YouTube video"
                            allowfullscreen="allowfullscreen"></iframe>
                        </div>
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-primary" type="button" data-bs-dismiss="modal">Close</button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- end of .container-->

    </section>
    <!-- <section> close ============================-->
    <!-- ============================================-->




    <!-- ============================================-->
    <!-- <section> begin ============================-->
    <section class="py-7" id="testimonial">

      <div class="container-fluid">
        <div class="row flex-center">
          <div class="bg-holder bg-size"
            style="background-image:url(assets/img/gallery/quote.png);background-position:top;background-size:auto;margin-left:-270px;margin-top:-45px;">
          </div>
          <!--/.bg-holder-->

          <div class="col-md-8 col-lg-5 text-center">
            <h5 class="text-danger">TESTIMONIAL</h5>
            <h2>Our Awesome Clients</h2>
          </div>
        </div>
        <div class="carousel slide pt-6" id="carouselExampleDark" data-bs-ride="carousel">
          <div class="carousel-inner">
            <div class="carousel-item active" data-bs-interval="10000">
              <div class="row h-100">
                <div class="col-md-4 mb-3 mb-md-0">
                  <div class="card h-100 card-span p-3">
                    <div class="card-body">
                      <h5 class="mb-0 text-primary">Fantastic service!</h5>
                      <p class="card-text pt-3">I purchased a phone from an e-commerce site, and this courier service
                        provider assisted me in getting it delivered to my home. I received my phone within one day, and
                        I was really satisfied with their service when I received it. </p>
                      <div class="d-xl-flex justify-content-between align-items-center">
                        <div class="d-flex align-items-center mb-3"><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i>
                        </div>
                        <div class="d-flex align-items-center"><img class="img-fluid" src="assets/img/icons/avatar.png"
                            alt="" />
                          <div class="flex-1 ms-3">
                            <h6 class="mb-0 fs--1 text-1000 fw-medium">Yves Tanguy</h6>
                            <p class="fs--2 fw-normal mb-0">Chief Executive, DLF</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-4 mb-3 mb-md-0">
                  <div class="card h-100 card-span p-3">
                    <div class="card-body">
                      <h5 class="mb-0 text-primary">Fantastic service!</h5>
                      <p class="card-text pt-3">I purchased a phone from an e-commerce site, and this courier service
                        provider assisted me in getting it delivered to my home. I received my phone within one day, and
                        I was really satisfied with their service when I received it.</p>
                      <div class="d-xl-flex justify-content-between align-items-center">
                        <div class="d-flex align-items-center mb-3"><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i>
                        </div>
                        <div class="d-flex align-items-center"><img class="img-fluid" src="assets/img/icons/avatar.png"
                            alt="" />
                          <div class="flex-1 ms-3">
                            <h6 class="mb-0 fs--1 text-1000 fw-medium">Kim Young Jou</h6>
                            <p class="fs--2 fw-normal mb-0">Chief Executive, DLF</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-4 mb-3 mb-md-0">
                  <div class="card h-100 card-span p-3">
                    <div class="card-body">
                      <h5 class="mb-0 text-primary">Fantastic service!</h5>
                      <p class="card-text pt-3">I purchased a phone from an e-commerce site, and this courier service
                        provider assisted me in getting it delivered to my home. I received my phone within one day, and
                        I was really satisfied with their service when I received it. .</p>
                      <div class="d-xl-flex justify-content-between align-items-center">
                        <div class="d-flex align-items-center mb-3"><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i>
                        </div>
                        <div class="d-flex align-items-center"><img class="img-fluid" src="assets/img/icons/avatar.png"
                            alt="" />
                          <div class="flex-1 ms-3">
                            <h6 class="mb-0 fs--1 text-1000 fw-medium">Yves Tanguy</h6>
                            <p class="fs--2 fw-normal mb-0">Chief Executive, DLF</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="carousel-item" data-bs-interval="2000">
              <div class="row h-100">
                <div class="col-md-4 mb-3 mb-md-0">
                  <div class="card h-100 card-span p-3">
                    <div class="card-body">
                      <h5 class="mb-0 text-primary">Fantastic service!</h5>
                      <p class="card-text pt-3">I purchased a phone from an e-commerce site, and this courier service
                        provider assisted me in getting it delivered to my home. I received my phone within one day, and
                        I was really satisfied with their service when I received it. </p>
                      <div class="d-xl-flex justify-content-between align-items-center">
                        <div class="d-flex align-items-center mb-3"><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i>
                        </div>
                        <div class="d-flex align-items-center"><img class="img-fluid" src="assets/img/icons/avatar.png"
                            alt="" />
                          <div class="flex-1 ms-3">
                            <h6 class="mb-0 fs--1 text-1000 fw-medium">Yves Tanguy</h6>
                            <p class="fs--2 fw-normal mb-0">Chief Executive, DLF</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-4 mb-3 mb-md-0">
                  <div class="card h-100 card-span p-3">
                    <div class="card-body">
                      <h5 class="mb-0 text-primary">Fantastic service!</h5>
                      <p class="card-text pt-3">I purchased a phone from an e-commerce site, and this courier service
                        provider assisted me in getting it delivered to my home. I received my phone within one day, and
                        I was really satisfied with their service when I received it. </p>
                      <div class="d-xl-flex justify-content-between align-items-center">
                        <div class="d-flex align-items-center mb-3"><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i>
                        </div>
                        <div class="d-flex align-items-center"><img class="img-fluid" src="assets/img/icons/avatar.png"
                            alt="" />
                          <div class="flex-1 ms-3">
                            <h6 class="mb-0 fs--1 text-1000 fw-medium">Kim Young Jou</h6>
                            <p class="fs--2 fw-normal mb-0">Chief Executive, DLF</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-4 mb-3 mb-md-0">
                  <div class="card h-100 card-span p-3">
                    <div class="card-body">
                      <h5 class="mb-0 text-primary">Fantastic service!</h5>
                      <p class="card-text pt-3">I purchased a phone from an e-commerce site, and this courier service
                        provider assisted me in getting it delivered to my home. I received my phone within one day, and
                        I was really satisfied with their service when I received it. .</p>
                      <div class="d-xl-flex justify-content-between align-items-center">
                        <div class="d-flex align-items-center mb-3"><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i>
                        </div>
                        <div class="d-flex align-items-center"><img class="img-fluid" src="assets/img/icons/avatar.png"
                            alt="" />
                          <div class="flex-1 ms-3">
                            <h6 class="mb-0 fs--1 text-1000 fw-medium">Yves Tanguy</h6>
                            <p class="fs--2 fw-normal mb-0">Chief Executive, DLF</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="carousel-item">
              <div class="row h-100">
                <div class="col-md-4 mb-3 mb-md-0">
                  <div class="card h-100 card-span p-3">
                    <div class="card-body">
                      <h5 class="mb-0 text-primary">Fantastic service!</h5>
                      <p class="card-text pt-3">I purchased a phone from an e-commerce site, and this courier service
                        provider assisted me in getting it delivered to my home. I received my phone within one day, and
                        I was really satisfied with their service when I received it. </p>
                      <div class="d-xl-flex justify-content-between align-items-center">
                        <div class="d-flex align-items-center mb-3"><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i>
                        </div>
                        <div class="d-flex align-items-center"><img class="img-fluid" src="assets/img/icons/avatar.png"
                            alt="" />
                          <div class="flex-1 ms-3">
                            <h6 class="mb-0 fs--1 text-1000 fw-medium">Yves Tanguy</h6>
                            <p class="fs--2 fw-normal mb-0">Chief Executive, DLF</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-4 mb-3 mb-md-0">
                  <div class="card h-100 card-span p-3">
                    <div class="card-body">
                      <h5 class="mb-0 text-primary">Fantastic service!</h5>
                      <p class="card-text pt-3">“I purchased a phone from an e-commerce site, and this courier service
                        provider assisted me in getting it delivered to my home. I received my phone within one day, and
                        I was really satisfied with their service when I received it. </p>
                      <div class="d-xl-flex justify-content-between align-items-center">
                        <div class="d-flex align-items-center mb-3"><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i>
                        </div>
                        <div class="d-flex align-items-center"><img class="img-fluid" src="assets/img/icons/avatar.png"
                            alt="" />
                          <div class="flex-1 ms-3">
                            <h6 class="mb-0 fs--1 text-1000 fw-medium">Kim Young Jou</h6>
                            <p class="fs--2 fw-normal mb-0">Chief Executive, DLF</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-4 mb-3 mb-md-0">
                  <div class="card h-100 card-span p-3">
                    <div class="card-body">
                      <h5 class="mb-0 text-primary">Fantastic service!</h5>
                      <p class="card-text pt-3">I purchased a phone from an e-commerce site, and this courier service
                        provider assisted me in getting it delivered to my home. I received my phone within one day, and
                        I was really satisfied with their service when I received it. .</p>
                      <div class="d-xl-flex justify-content-between align-items-center">
                        <div class="d-flex align-items-center mb-3"><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i><i
                            class="fas fa-star text-primary me-1"></i><i class="fas fa-star text-primary me-1"></i>
                        </div>
                        <div class="d-flex align-items-center"><img class="img-fluid" src="assets/img/icons/avatar.png"
                            alt="" />
                          <div class="flex-1 ms-3">
                            <h6 class="mb-0 fs--1 text-1000 fw-medium">Yves Tanguy</h6>
                            <p class="fs--2 fw-normal mb-0">Chief Executive, DLF</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row px-3 px-md-0 mt-6">
            <div class="col-12 position-relative">
              <ol class="carousel-indicators">
                <li class="active" data-bs-target="#carouselExampleDark" data-bs-slide-to="0"></li>
                <li data-bs-target="#carouselExampleDark" data-bs-slide-to="1"></li>
                <li data-bs-target="#carouselExampleDark" data-bs-slide-to="2"></li>
              </ol>
            </div>
          </div>
        </div>
      </div>
      <!-- end of .container-->

    </section>
    <!-- <section> close ============================-->
    <!-- ============================================-->

    <!-- ============================================-->
    <!-- <section> begin ============================-->
    <section>

      <div class="container">
        <div class="row justify-content-center">
          <div class="col-md-6 col-lg-5 col-xl-4"><img src="assets/img/illustrations/callback.png" alt="..." />
            <h5 class="text-danger">REQUEST A CALLBACK</h5>
            <h2>We will contact in the shortest time.</h2>
            <p class="text-muted">Monday to Friday, 9am-5pm.</p>
          </div>
          <div class="col-md-6 col-lg-5 col-xl-4">
            <form class="row">
              <div class="mb-3">
                <label class="form-label visually-hidden" for="inputName">Name</label>
                <input class="form-control form-quriar-control" id="inputName" type="text" placeholder="Name" />
              </div>
              <div class="mb-3">
                <label class="form-label visually-hidden" for="inputEmail">Another label</label>
                <input class="form-control form-quriar-control" id="inputEmail" type="email" placeholder="Email" />
              </div>
              <div class="mb-5">
                <label class="form-label visually-hidden" for="validationTextarea">Message</label>
                <textarea class="form-control form-quriar-control is-invalid border-400" id="validationTextarea"
                  placeholder="Message" style="height: 150px" required="required"></textarea>
              </div>
              <div class="d-grid">
                <button class="btn btn-primary" type="submit">Send Message<i
                    class="fas fa-paper-plane ms-2"></i></button>
              </div>
            </form>
          </div>
        </div>
      </div>
      <!-- end of .container-->

      <div class="position-relative pt-9 pt-lg-8 pb-6 pb-lg-8">
        <div class="container">
          <div class="row row-cols-lg-5 row-cols-md-3 row-cols-2 flex-center">
            <div class="col">
              <div class="partner card shadow-hover mb-4" style="border-radius:10px;">
                <div class="card-body text-center"> <img class="img-fluid" style="min-width: 200px; min-height: 125px; max-width:200px; max-height:125px" src="assets/img/partner/samsung_logo_1.svg" alt="" /></div>
              </div>
            </div>
            <div class="col">
              <div class="partner card shadow-hover mb-4" style="border-radius:10px;">
                <div class="card-body text-center"> <img class="img-fluid" style="min-width: 200px; min-height: 125px; max-width:200px; max-height:125px" src="assets/img/partner/ssafy_1.svg" alt="" /></div>
              </div>
            </div>
            <div class="col">
              <div class="partner card shadow-hover mb-4" style="border-radius:10px;">
                <div class="card-body text-center"> <img class="img-fluid" style="min-width: 200px; min-height: 125px; max-width:200px; max-height:125px" src="assets/img/partner/ministry_1.svg" alt="" /></div>
              </div>
            </div>
          </div>
        </div>
      </div>

    </section>
    <!-- <section> close ============================-->
    <!-- ============================================-->




   






    <!-- ============================================-->
    <!-- <section> begin ============================-->
    <section class="py-0 bg-1000">

      <div class="container">
        <div class="row justify-content-md-between justify-content-evenly py-4">
          <div class="col-12 col-sm-8 col-md-6 col-lg-auto text-center text-md-start">
            <p class="fs--1 my-2 fw-bold text-200">All rights Reserved &copy; BF, 2022</p>
          </div>
          <div class="col-12 col-sm-8 col-md-6">
            <p class="fs--1 my-2 text-center text-md-end text-200"> Made with&nbsp;
              <svg class="bi bi-suit-heart-fill" xmlns="http://www.w3.org/2000/svg" width="12" height="12"
                fill="#F95C19" viewBox="0 0 16 16">
                <path
                  d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z">
                </path>
              </svg>&nbsp;by&nbsp;<a class="fw-bold text-primary" href="https://themewagon.com/"
                target="_blank">ThemeWagon </a>
            </p>
          </div>
        </div>
      </div>
      <!-- end of .container-->

    </section>
    <!-- <section> close ============================-->
    <!-- ============================================-->

  </div>
  </main>
  <!-- ===============================================-->
  <!--    End of Main Content-->
  <!-- ===============================================-->
</template>

<script>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
export default {
  name: 'home',
  setup () {
    onMounted (() => {
      const token = localStorage.getItem('accessToken')
      if (token) {
        state.value.isLogin = true
      } else {
        state.value.isLogin = false
      }
    })
    const router = useRouter()
    const clickLogin = function() {
        router.push({name: 'login'})
    }
    const clickSignUp = function() {
        router.push({name: 'signup'})
    }
    const state = ref({
      isLogin: false,
    })
    const editProfile = function() {
      router.push({name: 'profile'})
    }
    const logout = function () {
      localStorage.removeItem('accessToken')
      localStorage.removeItem('userInfo')
      router.go({ name : 'home'})
    }
    const moveConferenceList = function () {
      const token = localStorage.getItem('accessToken')
      if (token) {
        router.push({ name : 'conferenceList'})
      } else {
        router.push({ name : 'login'})
      }
    }
    return {
      state, clickLogin,clickSignUp, editProfile, logout, moveConferenceList
    }
  },
}
</script>
<style scoped src='../../../public/assets/css/theme.css'>
 /* @import '../../../public/assets/css/theme.css' */


</style>
