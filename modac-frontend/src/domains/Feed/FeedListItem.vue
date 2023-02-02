<template>
	<div>
		<h1 class="temp1">게시글 {{ feed.feed_seq }} 번</h1>
		{{ feed }}
		<button
		id="delete"
		>X</button>
	</div>
</template>

<script setup>
import { useFeedStore } from "../../stores/feed";
defineProps(['feed'])
const store = useFeedStore()


</script>

<style>
@import './FeedListItem.module.css'
</style>