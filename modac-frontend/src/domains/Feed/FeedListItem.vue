<template>
  <div>
    <Card :class="$style.card_div" >
      <div :class="$style.card_img">
        <!-- <img src="https://picsum.photos/640/480/?random" :class="$style.img_class"/> -->
        <img :src="categoryImg" :class="$style.img_class"/>
      </div>

      <div :class="$style.card_description">
        <div :class="$style.card_title" >
          {{ feed.title }}
        </div>

        <div>
          <span v-if="feed.categoryName === '알고리즘'" :class="$style.article_table_td_category_algorithm">#알고리즘</span>
          <span v-if="feed.categoryName === 'CS'" :class="$style.article_table_td_category_cs">#CS</span>
          <span v-if="feed.categoryName === '개발'" :class="$style.article_table_td_category_programming">#개발</span>
          <span v-if="feed.categoryName === '면접'" :class="$style.article_table_td_category_planning">#면접</span>
          <span v-if="feed.categoryName === '공통'" :class="$style.article_table_td_category_ect">#공통</span>
        </div>
        <div>
        
        </div>
        <div :class="$style.card_date_and_info">
          <div :class="$style.card_date">
            {{ feed.registeredTime.substr(0,10) }}
          </div>
          <div class="flex gap-3">
            <div class="flex items-center gap-1">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-4 h-4">
                <path d="M10 12.5a2.5 2.5 0 100-5 2.5 2.5 0 000 5z" />
                <path fill-rule="evenodd" d="M.664 10.59a1.651 1.651 0 010-1.186A10.004 10.004 0 0110 3c4.257 0 7.893 2.66 9.336 6.41.147.381.146.804 0 1.186A10.004 10.004 0 0110 17c-4.257 0-7.893-2.66-9.336-6.41zM14 10a4 4 0 11-8 0 4 4 0 018 0z" clip-rule="evenodd" />
              </svg>
              {{ feed.viewCount }}
            </div>
            <div class="flex items-center gap-1">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-4 h-4">
                <path d="M9.653 16.915l-.005-.003-.019-.01a20.759 20.759 0 01-1.162-.682 22.045 22.045 0 01-2.582-1.9C4.045 12.733 2 10.352 2 7.5a4.5 4.5 0 018-2.828A4.5 4.5 0 0118 7.5c0 2.852-2.044 5.233-3.885 6.82a22.049 22.049 0 01-3.744 2.582l-.019.01-.005.003h-.002a.739.739 0 01-.69.001l-.002-.001z" />
              </svg>
              {{ feed.likeCount }}
            </div>
            <div class="flex items-center gap-1">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-4 h-4">
                <path fill-rule="evenodd" d="M10 2c-2.236 0-4.43.18-6.57.524C1.993 2.755 1 4.014 1 5.426v5.148c0 1.413.993 2.67 2.43 2.902 1.168.188 2.352.327 3.55.414.28.02.521.18.642.413l1.713 3.293a.75.75 0 001.33 0l1.713-3.293a.783.783 0 01.642-.413 41.102 41.102 0 003.55-.414c1.437-.231 2.43-1.49 2.43-2.902V5.426c0-1.413-.993-2.67-2.43-2.902A41.289 41.289 0 0010 2zM6.75 6a.75.75 0 000 1.5h6.5a.75.75 0 000-1.5h-6.5zm0 2.5a.75.75 0 000 1.5h3.5a.75.75 0 000-1.5h-3.5z" clip-rule="evenodd" />
              </svg>
              {{ feed.commentCount }}
            </div>
          </div>
        </div>
      </div>
      
      <div :class="$style.card_footer">
        <div class="flex gap-2 items-center flex-wrap">
          <div class="flex gap-1 font-bold"><div class="text-gray-400">By</div>{{ feed.user.nickname }}</div>
          <div :class="$style.user_membership">
            {{ feed.user.membershipLevel }}
          </div>
        </div>
      </div>

      <!-- <CardContent>{{ feed }}</CardContent> -->
    </Card>
  </div>

</template>

<script setup>
import Card from "@/components/Card.vue";
import csImg from "@/assets/feed/CS.png"
import developImg from "@/assets/feed/개발.png"
import etcImg from "@/assets/feed/공통.png"
import interviewImg from "@/assets/feed/면접.png"
import algorithmImg from "@/assets/feed/알고리즘.png"
import { defineProps, computed } from 'vue';

// import { ref } from "vue";
// import { useFeedStore } from "../../stores/feed";
const props = defineProps({
  feed: Object,
});

// defineProps(['feed'])
// const store = useFeedStore()
// let getFeeds = ref([]);
// getFeeds.value = store.getFeeds;

// const test = () => {
//   console.log(feed);
// }
const categoryImg = computed(() => {
  if( props.feed.categoryName === 'CS'){
    return csImg
  }
  else if(props.feed.categoryName === '개발'){
    return developImg
  }
  else if(props.feed.categoryName === '면접'){
    return interviewImg
  }
  else if(props.feed.categoryName === '알고리즘'){
    return algorithmImg
  }
  else if(props.feed.categoryName === '공통'){
    return etcImg
  }
});


</script>

<style lang="css" module>
@import './FeedListItem.module.css';
</style>