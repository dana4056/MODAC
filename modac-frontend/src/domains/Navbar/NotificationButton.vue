<script setup>
import Notification from "@/components/Notification.vue";
import OverflowDiv from "@/components/OverflowDiv.vue";
import { ref, computed, useCssModule } from "vue";


const $style = useCssModule();

const isDropdownOpenState = ref(false);

const openDropdown = () => {
  isDropdownOpenState.value = true;
};

const closeDropdown = () => {
  isDropdownOpenState.value = false;
};

const clickDropdownButtonHandler = () => {
  isDropdownOpenState.value ? closeDropdown() : openDropdown();
};

const blurDropdownButtonHandler = () => {
  if (isDropdownOpenState.value === true) {
    closeDropdown();
  }
};

// const dropdownStyleState = computed(() => {
//   return isDropdownOpenState.value
//     ? `${$style.dropdown} ${$style.dropdown_block}`
//     : `${$style.dropdown} ${$style.dropdown_none}`;
// });
</script>

<template>
  <div class="relative ml-3">
    <div>
      <button
        @click="clickDropdownButtonHandler"
        @blur="blurDropdownButtonHandler"
        type="button"
        :class="$style.notification_button"
      >
        <span class="sr-only">View notifications</span>
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 24 24"
          fill="currentColor"
          class="w-6 h-6"
        >
          <path
            fill-rule="evenodd"
            d="M5.25 9a6.75 6.75 0 0113.5 0v.75c0 2.123.8 4.057 2.118 5.52a.75.75 0 01-.297 1.206c-1.544.57-3.16.99-4.831 1.243a3.75 3.75 0 11-7.48 0 24.585 24.585 0 01-4.831-1.244.75.75 0 01-.298-1.205A8.217 8.217 0 005.25 9.75V9zm4.502 8.9a2.25 2.25 0 104.496 0 25.057 25.057 0 01-4.496 0z"
            clip-rule="evenodd"
          />
        </svg>
      </button>
    </div>
    <div
    v-if="isDropdownOpenState"
    id="dropdown"
    :class="$style.dropdown"
    role="menu"
    aria-orientation="vertical"
    aria-labelledby="dropdown_button"
    tabindex="-1"
    >
      <OverflowDiv>
        <Notification />
      </OverflowDiv>
      </div>
  </div>
</template>

<style lang="css" module>
@import "NotificationButton.module.css";
</style>
