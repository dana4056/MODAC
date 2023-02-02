<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import UserStatusIndicator from "@/components/UserStatusIndicator.vue";
import { defineProps, useCssModule } from "vue";
import { useRoomStore } from "@/stores/room";

const $style = useCssModule();

const props = defineProps({
  userItem: Object,
});

console.log(props.userItem);

const store = useRoomStore();
</script>

<template>
  <div :class="$style.user_section">
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
