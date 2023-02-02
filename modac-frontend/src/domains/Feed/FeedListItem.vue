<template>
	
	<Card :class="$style.quote-container">
		<!-- <i :class="$style.pin"></i> -->
		<!-- <h1 :class="$style.note"> -->
		<!-- </h1> -->
		<CardTitle>게시글 {{ feed.feed_seq }} 번</CardTitle>
		<CardContent>{{ feed }}</CardContent>
		
			
	</Card>
</template>

<script setup>
import Card from "@/components/Card.vue";

import { useFeedStore } from "../../stores/feed";
defineProps(['feed'])
const store = useFeedStore()


</script>

<style lang="css" module>
@import './FeedListItem.module.css'
</style>