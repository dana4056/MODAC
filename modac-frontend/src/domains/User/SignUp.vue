<template>
  <div id="signup-body">
    <div id="signup-wrapper">
      <div id="signup-container">
        <h1>회원 가입</h1>
        
        <form id="signup">
          <input type="text" placeholder="ID">
          <input type="text" placeholder="Nickname">
          <input type="text" placeholder="E-mail">
          <input type="password" placeholder="Password">
          <button type="submit" id="signup--button">Sign Up</button>
        </form>
      </div>
      
      <ul class="bg-bubbles">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
        <li></li>
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
/* @import './SignUp.css' */
</style>