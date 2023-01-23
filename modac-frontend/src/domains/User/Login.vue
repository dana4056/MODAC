<template>
<div id="loginbody">
  <div id="loginwrapper">
    <div id="logincontainer">
      <h1>Welcome</h1>
      
      <form id="loginform">
        <input type="text" placeholder="Username">
        <input type="password" placeholder="Password">
        <button type="submit" id="login-button">Login</button>
      </form>
      <button id="signupButton">Signup</button>
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

<script>
export default {

}
</script>

<style>
@import './Login.css'
</style>