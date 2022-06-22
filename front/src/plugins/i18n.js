import {  createI18n } from 'vue-i18n';
import en from '../locales/en.json'
import es from '../locales/es.json'

// Optimization
// async function loadLocaleMessages() {
//     // const locales = import.meta.glob('./locales', true, /[A-Za-z0-9-_,\s]+\.json$/i)
//     const locales = import.meta.glob('../locales/*.json');
//     const messages = {}
//     for (const path in locales) {
//         await locales[path]().then((mod) => {
//             console.log(path, mod)
//             console.log( path.match( /[A-Za-z0-9-_,\s]+\.json$/i) ); 
//             // messages = mod.default ;
//         })
//       }
//     console.log(locales);
//     // const messages = {}
//     // Object.keys(locales).forEach(key => {
//     //     // console.log(locales[key]());
//     //     const matched = key.match(/([A-Za-z0-9-_]+)\./i)
//     //     if (matched && matched.length > 1) {
//     //         const locale = matched[1]
//     //         messages[locale] = locales(key)
//     //     }
//     // })
//     return messages
// }

const i18n = createI18n({
    locale: 'es', // set locale
    fallbackLocale: 'es', // set fallback locale
    messages: {
        en,
        es,
    }
})

export default i18n;