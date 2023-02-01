<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import CardContent from "@/components/CardContent.vue";
import CommonButton from "@/components/CommonButton.vue";
import Wrapper from "@/components/Wrapper.vue";

const props = defineProps({
  favoritesRoom: Object,
});
</script>

<template>
  <Card :class="$style.flex_wrapper">
    <Wrapper :class="$style.flex_left">
      <CardTitle>
        {{ props.favoritesRoom.name }}
      </CardTitle>
      <CardContent>
        {{ props.favoritesRoom.description }}
      </CardContent>
      <CardContent>방장 {{ props.favoritesRoom.host }}</CardContent>
      <CardContent> 테마 {{ props.favoritesRoom.theme }}</CardContent>
    </Wrapper>
    <Wrapper :class="$style.flex_right">
      <CommonButton>자물쇠</CommonButton>
      <CommonButton>쓰레기통</CommonButton>
    </Wrapper>
  </Card>
</template>

<style lang="css" module>
@import "./FavoritesRoomListItem.module.css";
</style>
