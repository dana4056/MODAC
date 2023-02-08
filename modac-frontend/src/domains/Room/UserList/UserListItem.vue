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
    <Card :class="$style.card_style">
      <CardTitle :class="$style.display_inline_block">{{
        props.userItem.nickname
      }}</CardTitle>
      <UserStatusIndicator
        :status="props.userItem.status"
      ></UserStatusIndicator>
    </Card>
  </div>
</template>

<style lang="css" module>
@import "UserListItem.module.css";
</style>
