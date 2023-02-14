import { createApp } from "vue";
import { createPinia } from "pinia";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faHatWizard } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

import App from "./App.vue";
import router from "./router";

import "./assets/main.css";
import "./index.css";

const app = createApp(App);
const pinia = createPinia()

pinia.use(piniaPluginPersistedstate)
app.use(pinia);
app.use(router);

library.add(faHatWizard);
app.component("font-awesome-icon", FontAwesomeIcon);

app.mount("#app");
