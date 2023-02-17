<script setup>
import { defineProps } from "vue";

const props = defineProps({
  closeModal: Function,
});

// import { ref } from "vue";

// const isOpenModalState = ref(false);

// const openModal = () => {
//   isOpenModalState.value = true;
// };

// const closeModal = () => {
//   isOpenModalState.value = false;
// };


</script>

<template>
  <div>
    <div :class="$style.backdrop" @click="props.closeModal" id="backdrop">
      <div :class="$style.modal">
        <slot></slot>
      </div>
    </div>
    
  </div>
</template>

<style lang="css" module>
@import "Modal.module.css";
</style>
