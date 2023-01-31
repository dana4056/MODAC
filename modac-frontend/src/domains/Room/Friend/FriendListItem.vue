<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import CardContent from "@/components/CardContent.vue";

const props = defineProps({
  friendItem: Object,
});
</script>

<template lang="">
  <Card>
    <CardTitle>
      {{ props.friendItem.name }}
    </CardTitle>
    <CardContent>
      {{ props.friendItem.status }}
    </CardContent>
  </Card>
</template>

<style lang="css" module>
@import "./FriendListItem.module.css";
</style>
