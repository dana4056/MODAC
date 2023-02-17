<script setup>
import { computed } from 'vue';
import OverflowDiv from '@/components/OverflowDiv.vue';
import { useNotiStore } from '@/stores/notification'
import { storeToRefs } from 'pinia';
import { useUserStore } from '@/stores/user';
import NotificationItem from '@/components/NotificationItem.vue';

const notiStore = useNotiStore();
const { notiList } = storeToRefs(notiStore);
const userStore = useUserStore()
const { loginUser } = storeToRefs(userStore)

const payload = {
  usersSeq : loginUser.value.seq
}

// const recievedTime = computed(() => {
//   return notiList.value.registeredTime.substr(-8);
// });

notiStore.api.getNoti(payload)

</script>

<template>
  <div
  :class="$style.container"
  >
      <div :class="$style.inner_container">
        <NotificationItem
          v-for="(notiListItem, index) in notiList"
          :key="index"
          :notiListItem="notiListItem"
          :class="$style.item_container"
        />
      </div>
    </div>
</template>

<style lang="css" module>
@import "Notification.module.css"
</style>