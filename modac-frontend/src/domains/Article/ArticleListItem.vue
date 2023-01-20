<template>
	<div>
		<h1 class="temp1">게시글 {{ article.todos_seq }} 번</h1>
		{{ article }}
		<button
		id="delete"
		@click="deleteArticleItem"
		>X</button>
	</div>
</template>

<script setup>
import { useArticleStore } from "../../stores/article";
defineProps(['article'])
const store = useArticleStore()
// 함수 불러오기
const deleteArticleItem = store.deleteArticleItem


</script>

<style>
@import './ArticleListItem.css'
</style>