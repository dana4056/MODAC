<template>
	<div>
		<h1 class="temp1">Hi my Name is</h1>
		{{ article }}
	</div>
</template>

<script setup>
import "./ArticleListItem.css"

const article = defineProps(['article'])

</script>

<style>

</style>