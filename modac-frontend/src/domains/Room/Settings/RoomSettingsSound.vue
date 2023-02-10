<script setup></script>

<template>
  <div>
    <div :class="$style.기본_음악">
      <label for="small-range" :class="$style.sound_bar_label">음악 볼륨</label>
      <input
        id="small-range"
        type="range"
        value="50"
        :class="$style.sound_bar_range"
      />
    </div>

    <div :class="$style.ASMR">
      <label for="small-range" :class="$style.sound_bar_label">ASMR 볼륨</label>
      <input
        id="small-range"
        type="range"
        value="50"
        :class="$style.sound_bar_range"
      />
    </div>
  </div>
</template>

<style lang="css" module>
@import "RoomSettingsSound.module.css";
</style>
