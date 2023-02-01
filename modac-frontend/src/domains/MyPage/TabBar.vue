<template lang="">
  <div :class="$style.tabs_flex">
    <div :class="$style.tabs_width">
      <ul :class="$style.tabs_ul">
        <li v-for="tab in tabs" :key="tab" :class="$style.tabs_li">
          <button @click="currentTab(tab)" :class="tabName === tab ? `${$style.tab_menu} ${$style.active}` : `${$style.tab_menu}`">
            {{ tab }}
          </button>
        </li>
      </ul>
      <div :class="$style.tab_content">
        <TabArticle v-if="tabName === 'TIL'" />
        <TabStatistics v-if="tabName === '통계'" />
        <TabUserInfoUpdate v-if="tabName === '회원정보 수정'" />
        <TabUserPasswordUpdate v-if="tabName === '비밀번호 변경'" />
        <TabUserDelete v-if="tabName === '회원 탈퇴'" />
      </div>
    </div>
  </div>

</template>

<script setup>

import { ref } from "vue";
import TabArticle from "@/domains/MyPage/Tab/TabArticle.vue";
import TabStatistics from "@/domains/MyPage/Tab/TabStatistics.vue";
import TabUserInfoUpdate from "@/domains/MyPage/Tab/TabUserInfoUpdate.vue";
import TabUserPasswordUpdate from "@/domains/MyPage/Tab/TabUserPasswordUpdate.vue";
import TabUserDelete from "@/domains/MyPage/Tab/TabUserDelete.vue";

let tabName = ref("TIL");

const currentTab = (tab) => {
  tabName.value = tab;
}

var tabs = [ "TIL", "통계", "회원정보 수정", "비밀번호 변경", "회원 탈퇴" ]

// const show = 0;

// const navigate = (e) => {
//   if (e.target.dataset.show) {
//     e.preventDefault();
//     this.show = e.target.dataset.show;
//   }
// }
// const alerts = (e) => {
//   if (e.target.dataset.alert) {
//     e.preventDefault();
//     alert(e.target.dataset.alert);
//   }
// }




// let classIndex = 0;

// const list = [{
//       id: 1,
//       nav: "Messages",
//       con: "Lorem ipsum dolor sit amet,consectetuer edipiscing elit,sed diam nonummy nibh euismod tinciduntut laoreet doloremagna aliquam erat volutpat."
//     }, {
//       id: 2,
//       nav: "Settings",
//       con: "It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged."
//     }, {
//       id: 3,
//       nav: "Logs",
//       con: "We provide our service as is, and we make no promises or guarantees about this service. Please read this section carefully; you should understand what to expect."
//     }]
  
//   const changeClass = (index) => {
//       classIndex = index;
//     }



</script>

<style lang="css" module>
  @import "./TabBar.module.css";
</style>