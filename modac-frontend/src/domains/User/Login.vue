<template>
<div id="loginbody">
  <div id="loginwrapper">
    <div id="logincontainer">
      <h1>Welcome</h1>
      
      <form id="loginform">
        <div class="input-form">
          <input type="text" placeholder="Username">
        </div>
        <div class="input-form">
          <input type="password" placeholder="Password">
        </div>
        <button type="submit" id="login-button">Login</button>
      </form>
    </div>
    <div class="sns-body">
      <div class="options">
        <RouterLink id="routerlink" to="/signup">회원가입</RouterLink>
        <p>|</p>
        <p>아이디 찾기</p>
        <p>|</p>
        <p>비밀번호찾기</p>
      </div>
    
        <div class="text-center">
          <p>SNS 간편 로그인</p>  
          <div class="bar"></div>
        </div>
        <div id="social-login">
          <a href="www.naver.com">
            <img :src="NaverLogo" alt="">
          </a>
          <a href="www.naver.com">
            <img :src="KakaoLogo" alt="">
          </a>
          <a href="www.google.com">
            <img :src="GoogleLogo" alt="">
          </a>
        </div>
  
    </div>
  </div>
</div>

    <ul class="bg-bubbles">
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
    </ul>

<div class="z-3">
  <div class="tile top-left animate-opacity freq-5"></div>
  <div class="tile top-right animate-opacity freq-9"></div>
  <div class="tile bottom-left animate-opacity freq-7"></div>
  <div class="tile bottom-right animate-opacity freq-10"></div>
</div>
<div class="z-2">
  <div class="tile top-left animate-opacity freq-9 delay-2"></div>
  <div class="tile top-right animate-opacity freq-5 delay-2"></div>
  <div class="tile bottom-left animate-opacity freq-6 delay-4"></div>
  <div class="tile bottom-right animate-opacity freq-10 delay-4"></div>
</div>
<div class="z-1">
  <div class="tile top-left animate-opacity freq-7 delay-2"></div>
  <div class="tile top-right animate-opacity freq-5 delay-4"></div>
  <div class="tile bottom-left animate-opacity freq-9 delay-2"></div>
  <div class="tile bottom-right animate-opacity freq-5 delay"></div>
</div>

</template>

<script setup>
import KakaoLogo from '../../assets/sns/Logo-Kakao.svg'
import NaverLogo from '../../assets/sns/Logo-Naver.svg'
import GoogleLogo from '../../assets/sns/Logo-Google.svg'
</script>

<style>
@import './Login.css'
</style>