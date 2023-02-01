<script setup>
import Card from "@/components/Card.vue";
import CardTitle from "@/components/CardTitle.vue";
import CardContent from "@/components/CardContent.vue";
import { defineProps } from "vue";

const props = defineProps({
  seq: Number,
  name: String,
  status: Number,
  categoryName: String,
});
</script>

<template>
  <Card>
    <CardTitle>{{ props.name }}</CardTitle>
    <CardContent>{{ props.status }}</CardContent>
    <CardContent>{{ props.categoryName }}</CardContent>
    <slot></slot>
  </Card>
</template>

<style lang="css" module>
@import "UserCard.module.css";
</style>
