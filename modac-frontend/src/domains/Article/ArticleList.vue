<script setup>
import ArticleListItem from "./ArticleListItem.vue";
import ArticleEditor2 from "./ArticleEditor2.vue";
import OverflowDiv from "@/components/OverflowDiv.vue";
import { useTodoStore } from "@/stores/todo";
import { useArticleStore } from "@/stores/article";
import { ref, toRefs, computed } from "vue";
import { storeToRefs } from "pinia";
import articleAPI from "@/api/article";
import article from "@/api/article";
import todoAPI from "@/api/todo";

const todoStore = useTodoStore();
const todayTodos = ref([...todoStore.todos]);

const getCompleteTodos = () => {
  const filterTodos = computed(() => {
    return todayTodos.value.filter((elem) => {
      return elem.status === 2;
    });
  });

  const getTemplateByCategories = async (todosSeq) => {
    const todoItem = await todoAPI.findTodo(todosSeq);
    return todoItem.templateContent;
  };

  const articlesFromCompleteTodos = computed(() => {
    return filterTodos.value.map((elem) => {
      elem.public_type = 0;
      elem.isSubmit = false;
      // article의 content도 추가해줘야 할 것 같은데?
      elem.content = getTemplateByCategories(elem.seq); // 템플릿 가져오기
      return elem;
    });
  });

  return articlesFromCompleteTodos.value;
};

const articleStore = useArticleStore();

const {
  articles,
  selectedArticleItemSeq,
  completeWriteArticleState,
  changeCompleteWriteArticleState,
} = toRefs(articleStore);

articles.value = getCompleteTodos();

selectedArticleItemSeq.value =
  articles.value.length !== 0 ? articles.value[0].seq : null;

const handleClickArticleItem = (seq) => {
  selectedArticleItemSeq.value = seq;
  console.log(completeWriteArticleState.value);
  if (completeWriteArticleState.value) {
    changeCompleteWriteArticleState.value(false);
  }
};
</script>

<template>
  <!-- <div v-if="articles.length !== 0"> -->
    <div :class="$style.flex_row_div_edit">
      <div :class="$style.article_container_below">
        <OverflowDiv class="overflow-x-hidden">
          <div :class="$style.article_list">
            <ArticleListItem
              :class="$style.article_item"
              v-for="articleItem in articles"
              :key="articleItem.seq"
              :articleItem="articleItem"
              :handleClickArticleItem="handleClickArticleItem"
            />
          </div>
        </OverflowDiv>
        <div v-if="!completeWriteArticleState" :class="$style.editor">
          <ArticleEditor2
            v-for="articleItem in articles"
            :key="articleItem.seq"
            :articleItem="articleItem"
          />
        </div>
        <div :class="$style.complete_box" v-else>
          <p>작성이 완료되었습니다 :-></p>
          <small>우측 상단의 원하시는 버튼을 눌러 md 파일을 활용하세요!</small>
        </div>
      </div>
    </div>
  <!-- </div> -->
  <!-- <div :class="$style.no_item" v-else>완료한 할 일이 없습니다!</div> -->
</template>

<style lang="css" module>
@import "./ArticleList.module.css";

</style>
