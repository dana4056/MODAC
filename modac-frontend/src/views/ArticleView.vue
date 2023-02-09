<script setup>
import ArticleList from "../domains/Article/ArticleList.vue";
import ArticleEditor from "@/domains/Article/ArticleEditor.vue";
import emojiSmilingCatWithHeartEyes from '../assets/emojis/emoji-smiling-cat-with-heart-eyes.png';

import { ref, onMounted } from "vue";
import { useArticleStore } from "../stores/article";

import CommonButton from "@/components/CommonButton.vue";
import OverflowDiv from '@/components/OverflowDiv.vue';
const store = useArticleStore();

const copyText = () => {
  // navigator.clipboard.writeText(ArticleEditor.editorContent)
  // console.log(ArticleEditor.editorContent);
  navigator.clipboard.writeText(store.tempArticle);
  console.log(store.tempArticle);
  alert('복사 완료')
}

const publicType = ref(1);
const buttonState = ref(false);
const nextButtonState = () => {
  buttonState.value = true;
};


const setTemplete = ref("");
function setTempleteClick() {
  // 내보낸 자식 컴포넌트 함수를 호출한다.
  setTemplete.value.setTemplete();
}

const studyFinish = () => {
  console.log("study fin");
  console.log(store.getArticles);
  console.tempArticle = store.getArticles;
  console.log(store.tempArticle);
}


// import confetti from "https://cdn.skypack.dev/canvas-confetti";
// confetti();

// window.addEventListener("click", () => {
  // document.getElementById("confetti").addEventListener("click", confetti);

  // onMounted(() => {
  //   function randomInRange(min, max) {
  //     return Math.random() * (max - min) + min;
  //   }
  //   var duration = 15 * 1000;
  //   var animationEnd = Date.now() + duration;
  //   var defaults = { startVelocity: 30, spread: 360, ticks: 60, zIndex: 0 };
    
  //   var interval = setInterval(function() {
  //     var timeLeft = animationEnd - Date.now();
    
  //     if (timeLeft <= 0) {
  //       return clearInterval(interval);
  //     }
    
  //     var particleCount = 50 * (timeLeft / duration);
  //     if (store.articles.length == 0) {
  //     // since particles fall down, start a bit higher than random
  //     confetti(Object.assign({}, defaults, { particleCount, origin: { x: randomInRange(0.1, 0.3), y: Math.random() - 0.2 } }));
  //     confetti(Object.assign({}, defaults, { particleCount, origin: { x: randomInRange(0.7, 0.9), y: Math.random() - 0.2 } }));
  //     }
  //   }, 250);
    
  //   const el = document.getElementById("confetti");
  //   el.addEventListener("click", () => { confetti(); }, false);
    
  // })

// })

</script>

<template>
        <!-- v-if="store.tempArticle.length !== 0" -->
  <div :class="$style.article_container_above"
        >
    <div :class="$style.flex_row_div">
      <div :class="$style.flex_row">
        <h1 :class="$style.page_title">TIL 작성하기</h1>
      </div>

      <div v-if="!buttonState" :class="$style.buttons">
        <div :class="$style.add_room_div">
          <select :class="$style.add_room_input" 
                  v-model="publicType"
                  id="publicType">
            <option value="1" selected="selected">전체 피드에 업로드</option>
            <option value="0">내 피드에만 업로드</option>
            <option value="2">피드에 업로드하지 않기</option>
          </select>
        </div>

        <button @click="nextButtonState" type="button" :class="$style.button_next">
            작성 완료하기 →
        </button>
      </div>
      
      <div v-if="buttonState" :class="$style.buttons">
        <button @click="copyText" :class="$style.button_next">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-5 h-5">
            <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 17.25v3.375c0 .621-.504 1.125-1.125 1.125h-9.75a1.125 1.125 0 01-1.125-1.125V7.875c0-.621.504-1.125 1.125-1.125H6.75a9.06 9.06 0 011.5.124m7.5 10.376h3.375c.621 0 1.125-.504 1.125-1.125V11.25c0-4.46-3.243-8.161-7.5-8.876a9.06 9.06 0 00-1.5-.124H9.375c-.621 0-1.125.504-1.125 1.125v3.5m7.5 10.375H9.375a1.125 1.125 0 01-1.125-1.125v-9.25m12 6.625v-1.875a3.375 3.375 0 00-3.375-3.375h-1.5a1.125 1.125 0 01-1.125-1.125v-1.5a3.375 3.375 0 00-3.375-3.375H9.75" />
          </svg>
          &nbsp;복사하기
        </button>

        <button @click="githubCommit" :class="$style.button_next">
          <svg class="w-5 h-5" aria-hidden="true" focusable="false" data-prefix="fab" data-icon="github" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 496 512"><path fill="currentColor" d="M165.9 397.4c0 2-2.3 3.6-5.2 3.6-3.3 .3-5.6-1.3-5.6-3.6 0-2 2.3-3.6 5.2-3.6 3-.3 5.6 1.3 5.6 3.6zm-31.1-4.5c-.7 2 1.3 4.3 4.3 4.9 2.6 1 5.6 0 6.2-2s-1.3-4.3-4.3-5.2c-2.6-.7-5.5 .3-6.2 2.3zm44.2-1.7c-2.9 .7-4.9 2.6-4.6 4.9 .3 2 2.9 3.3 5.9 2.6 2.9-.7 4.9-2.6 4.6-4.6-.3-1.9-3-3.2-5.9-2.9zM244.8 8C106.1 8 0 113.3 0 252c0 110.9 69.8 205.8 169.5 239.2 12.8 2.3 17.3-5.6 17.3-12.1 0-6.2-.3-40.4-.3-61.4 0 0-70 15-84.7-29.8 0 0-11.4-29.1-27.8-36.6 0 0-22.9-15.7 1.6-15.4 0 0 24.9 2 38.6 25.8 21.9 38.6 58.6 27.5 72.9 20.9 2.3-16 8.8-27.1 16-33.7-55.9-6.2-112.3-14.3-112.3-110.5 0-27.5 7.6-41.3 23.6-58.9-2.6-6.5-11.1-33.3 2.6-67.9 20.9-6.5 69 27 69 27 20-5.6 41.5-8.5 62.8-8.5s42.8 2.9 62.8 8.5c0 0 48.1-33.6 69-27 13.7 34.7 5.2 61.4 2.6 67.9 16 17.7 25.8 31.5 25.8 58.9 0 96.5-58.9 104.2-114.8 110.5 9.2 7.9 17 22.9 17 46.4 0 33.7-.3 75.4-.3 83.6 0 6.5 4.6 14.4 17.3 12.1C428.2 457.8 496 362.9 496 252 496 113.3 383.5 8 244.8 8zM97.2 352.9c-1.3 1-1 3.3 .7 5.2 1.6 1.6 3.9 2.3 5.2 1 1.3-1 1-3.3-.7-5.2-1.6-1.6-3.9-2.3-5.2-1zm-10.8-8.1c-.7 1.3 .3 2.9 2.3 3.9 1.6 1 3.6 .7 4.3-.7 .7-1.3-.3-2.9-2.3-3.9-2-.6-3.6-.3-4.3 .7zm32.4 35.6c-1.6 1.3-1 4.3 1.3 6.2 2.3 2.3 5.2 2.6 6.5 1 1.3-1.3 .7-4.3-1.3-6.2-2.2-2.3-5.2-2.6-6.5-1zm-11.4-14.7c-1.6 1-1.6 3.6 0 5.9 1.6 2.3 4.3 3.3 5.6 2.3 1.6-1.3 1.6-3.9 0-6.2-1.4-2.3-4-3.3-5.6-2z"></path></svg>
          &nbsp;Github 올리기
        </button>

        <button :class="$style.button_next">
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-5 h-5">
            <path stroke-linecap="round" stroke-linejoin="round" d="M3 16.5v2.25A2.25 2.25 0 005.25 21h13.5A2.25 2.25 0 0021 18.75V16.5M16.5 12L12 16.5m0 0L7.5 12m4.5 4.5V3" />
          </svg>
          &nbsp;다운로드
        </button>
      </div>

    </div>

    <div :class="$style.flex_row_div_edit">
      <div :class="$style.article_container_below">
        <OverflowDiv>
          <div :class="$style.article_list">
            <ArticleList :setTempleteClick="setTempleteClick" />
          </div>
        </OverflowDiv>
        <div :class="$style.editor">
          <ArticleEditor ref="setTemplete" />
        </div>
      </div>
    </div>

  <!-- <div :class="$style.article_container_below">
    <div :class="$style.article_list">
      <ArticleList />
    </div>
    <div :class="$style.editor">
      <ArticleEditor />
    </div>
  </div> -->

        <!-- <img :src="emojiSmilingCatWithHeartEyes" class="w-50 h-50 inline" id="confetti"> -->
    <!-- <div :class="$style.article_container_above"
        v-if="store.tempArticle.length === 0"
    >
      <div class="flex flex-col items-center gap-2">
        <h1 :class="$style.confetti_text">오늘도 열심히 공부한 당신을 응원해요!</h1>
        <h2 :class="$style.confetti_text2">지금처럼만 하면 좋은 결과가 있을 거예요.</h2>
        <br>
        <RouterLink id="routerlink" to="/room">
          <button type="button" @click="studyFinish" :class="$style.room_add_button">
            스터디룸 리스트로 돌아가기 →
          </button>
        </RouterLink>
      </div>
    </div> -->
</template>

<style lang="css" module>
@import './ArticleView.module.css';

</style>
