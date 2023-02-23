<script setup>
import { ref, computed } from 'vue'


const props = defineProps({
  notiListItem: Object,
});


const notiListItem = ref(props.notiListItem)
console.log(notiListItem.value)

const recievedTime = computed(() => {
  const time = notiListItem.value.registeredTime.substr(-8,5)
  return time;
});

const item = computed(() => {
    if (notiListItem.value.type === "like") {
      const like = {
        icon : `
          <svg
            width="48"
            height="48"
            viewBox="0 0 48 48"
            fill="none"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
          >
            <rect width="48" height="48" rx="8" fill="#ffc0cb" />

            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-5 h-5">
              <path d="M9.653 16.915l-.005-.003-.019-.01a20.759 20.759 0 01-1.162-.682 22.045 22.045 0 01-2.582-1.9C4.045 12.733 2 10.352 2 7.5a4.5 4.5 0 018-2.828A4.5 4.5 0 0118 7.5c0 2.852-2.044 5.233-3.885 6.82a22.049 22.049 0 01-3.744 2.582l-.019.01-.005.003h-.002a.739.739 0 01-.69.001l-.002-.001z" />
            </svg>
          </svg>
        `,
        message: notiListItem.value.fromUser.nickname + '님이 회원님의 게시글을 좋아합니다.',
      }
      return like
    } else if (notiListItem.value.type === "comment") {
      const comment = {
        icon : `
          <svg
            width="48"
            height="48"
            viewBox="0 0 48 48"
            fill="none"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
          >
            <rect width="48" height="48" rx="8" fill="#ffdd72" />

            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-5 h-5">
              <path fill-rule="evenodd" d="M10 3c-4.31 0-8 3.033-8 7 0 2.024.978 3.825 2.499 5.085a3.478 3.478 0 01-.522 1.756.75.75 0 00.584 1.143 5.976 5.976 0 003.936-1.108c.487.082.99.124 1.503.124 4.31 0 8-3.033 8-7s-3.69-7-8-7zm0 8a1 1 0 100-2 1 1 0 000 2zm-2-1a1 1 0 11-2 0 1 1 0 012 0zm5 1a1 1 0 100-2 1 1 0 000 2z" clip-rule="evenodd" />
            </svg>
          </svg>
        `,
        message: notiListItem.value.fromUser.nickname + '님이 회원님의 게시글에 댓글을 달았습니다.',
      }
      return comment
    } else {
      const follow = {
        icon : `
          <svg
            width="48"
            height="48"
            viewBox="0 0 48 48"
            fill="none"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
          >
            <rect width="48" height="48" rx="8" fill="#9bd2ff" />

            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor" class="w-5 h-5">
              <path d="M11 5a3 3 0 11-6 0 3 3 0 016 0zM2.615 16.428a1.224 1.224 0 01-.569-1.175 6.002 6.002 0 0111.908 0c.058.467-.172.92-.57 1.174A9.953 9.953 0 018 18a9.953 9.953 0 01-5.385-1.572zM16.25 5.75a.75.75 0 00-1.5 0v2h-2a.75.75 0 000 1.5h2v2a.75.75 0 001.5 0v-2h2a.75.75 0 000-1.5h-2v-2z" />
            </svg>
          </svg>
        `,
        message: notiListItem.value.fromUser.nickname + '님이 회원님을 팔로우 했습니다.',
      }
      return follow
    }
  });
  const notiStyle = computed(() => {
    if (notiListItem.value.isRead === 1) {
      return "new"
    } else {
      return "already_read"
    }
  });


</script>

<template>
  <div :class="`${$style.item_container} ${$style[notiStyle]}`">
    <div class="flex items-center">
      <div
      class="flex h-10 w-10 shrink-0 items-center justify-center text-white sm:h-12 sm:w-12"
      >
        <div v-html="item.icon"></div>
      </div>
      <div class="ml-4 flex flex-col">
        <p class="text-sm text-gray-500">
          {{ item.message }}
        </p>
        <p class="flex text-gray-300 items-end pr-2 text-sm">
          {{ recievedTime }}
        </p>
      </div>
    </div>
    <!-- <div class="flex">
      <button :class="$style.close" @click="deleteItem">
        <svg height="18px" id="Layer_1" style="enable-background:new 0 0 512 512;" version="1.1" viewBox="0 0 512 512" width="18px" xml:space="preserve" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"><path fill="#69727D" d="M437.5,386.6L306.9,256l130.6-130.6c14.1-14.1,14.1-36.8,0-50.9c-14.1-14.1-36.8-14.1-50.9,0L256,205.1L125.4,74.5  c-14.1-14.1-36.8-14.1-50.9,0c-14.1,14.1-14.1,36.8,0,50.9L205.1,256L74.5,386.6c-14.1,14.1-14.1,36.8,0,50.9  c14.1,14.1,36.8,14.1,50.9,0L256,306.9l130.6,130.6c14.1,14.1,36.8,14.1,50.9,0C451.5,423.4,451.5,400.6,437.5,386.6z"/></svg>
      </button>
    </div> -->
  </div>
</template>

<style lang="css" module>
@import "NotificationItem.module.css"
</style>