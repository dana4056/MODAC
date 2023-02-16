<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import UserStatusIndicator from "@/components/UserStatusIndicator.vue";
import { defineProps, useCssModule } from "vue";

const $style = useCssModule();

const props = defineProps({
  userItem: Object,
});
</script>

<template>
  <div>
    <div :class="$style.card_style">
      <div style="text-align: center">
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
