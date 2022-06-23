import { createApp, markRaw } from 'vue'
import { createPinia } from 'pinia'
import router from './router'
import i18n from './plugins/i18n'
import App from './App.vue'
import Notifications from '@kyvg/vue3-notification'

import "./assets/scss/index.scss";

const app = createApp(App);
const pinia = createPinia();

pinia.use(({ store }) => {
    store['$router'] = markRaw(router)
});

app.use(router)
app.use(pinia)
app.use(i18n)
app.use(Notifications)

app.mount('#app')

