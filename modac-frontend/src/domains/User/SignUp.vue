<template>
  <div id="signup-body">
    <div id="signup-wrapper">
      <div id="signup-container">
        <h1>회원 가입</h1>

        <form id="signup-form">
          <span class="input-with-doublecheck">
            <input type="text" placeholder="ID">
          </span>
          <span class="input-with-doublecheck">
            <input type="text" placeholder="Nickname">
          </span>
          <span class="input-with-doublecheck">
            <input type="text" placeholder="E-mail">
          </span>
          <input type="password" placeholder="Password">
          <button type="submit" id="submit-button">Sign Up</button>
        </form>
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
    </div>
  </div>
  
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


</script>

<style>
@import './SignUp.css'
</style>