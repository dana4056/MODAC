<script setup>
import Modal from "@/components/Modal.vue";
import GithubIcon from "./GithubIcon.vue";
import GithubTest from "@/views/GithubTest.vue";
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

// const modalState = ref(false);
// const closeGithubModal = () => {
//   modalState.value = false;
//   router.push({ name: "before" });
// };

// const handleClickGithubButton = () => {
//   modalState.value = true;
// };

// Modal 코드
const openModal = () => {
  const dialog = document.querySelector("dialog");
  dialog.showModal();
  router.push({ name: "before" });
};
</script>

<template>
  <button :class="$style.button_next" @click="openModal">
    <GithubIcon />
    <span>&nbsp;Github 올리기</span>
  </button>
  <Teleport to="body">
    <dialog :class="$style.modal">
      <form method="dialog">
        <!-- <GithubTest /> -->
        <RouterView />
        <button :class="$style.modal_button">Close</button>
      </form>
    </dialog>

    <!-- <Modal :closeModal="closeGithubModal" v-if="modalState">
      <RouterView />
    </Modal> -->
  </Teleport>
</template>

<style lang="css" module>
@import "GithubButton.module.css";
</style>
