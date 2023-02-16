<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
// import UserStatusIndicator from "@/components/UserStatusIndicator.vue";
import { ref, defineProps, useCssModule } from "vue";

const $style = useCssModule();

const props = defineProps({
  userItem: Object,
});

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
</script>

<template>
  <div>
    <div :class="$style.card_style">
      <div>
        <div
          v-if="isDropdownOpenState"
          id="dropdown"
          :class="$style.dropdown"
          role="menu"
          aria-orientation="vertical"
          aria-labelledby="dropdown_button"
          tabindex="-1"
        >
          <!-- 여기에 내용을 넣으세요  -->
        </div>
      </div>
      <div
        :class="$style.user_card_button"
        @click="clickDropdownButtonHandler"
        @blur="blurDropdownButtonHandler"
      >
        <CardTitle :class="$style.display_inline_block">{{
          props.userItem.nickname
        }}</CardTitle>
        <!-- <UserStatusIndicator
          :status="props.userItem.status"
        ></UserStatusIndicator> -->
      </div>
      <div>
        <img
          :src="`src/assets/user_cats/cat${props.userItem.catSkin}.png`"
          alt=""
        />
      </div>
    </div>
  </div>
</template>

<style lang="css" module>
@import "UserListItem.module.css";
</style>
