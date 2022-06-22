import { defineStore,  acceptHMRUpdate } from 'pinia';
import authService from '@/plugins/authService';
import clinicianService from '@/plugins/clinicianService';
import { SHA1 } from '@/utils/methods.js';

export const useUserStore = defineStore({
    id: 'user',
    state: () => ({
        name: '',
        initials: '',
        qrCode: null,
        login: false,
        loading:{
            data: false
        },
        profilePicture: "",
        tempData: {}
    }),

    actions: {
        fnLogout() {
            this.$patch({
                name: null,
                login: false,
            })
            localStorage.removeItem('token');
        },

        async fnApiLogin(email, password) {
            const payload = {
                user: email,
                password: SHA1(password),
                isPatient: false,
                type: "password"
            };

            this.$patch({
                 tempData: payload
            })

            await authService.post('/auth/login', payload).then(res => {
                if (res.data.verified) {
                    this.$router.push(`/pincode`);
                } else {
                    this.$patch({
                        qrCode: res.data.qrCode,
                    })
                    this.$router.push(`/qrcode`);
                }
            }).catch( error => { 
                notify({
                    type: 'error',
                    title: "Login",
                    text: "Invalid email or password",
                });
            } )
        },

        async fnApiSendPinCode(token) {
            const payload = {
                ...this.tempData,
                token,
            };
            await authService.post('/auth/login', payload).then(res => {
                notify({
                    type: 'success',
                    title: "Login",
                    text: "You have been logged in!",
                });

                this.$patch({ login: true, })
                localStorage.setItem('token', res.data.jwt )
                setTimeout(() => {
                    this.$router.push(`/dashboard`);
                }, 200);
            }).catch( error => { 
                notify({
                    type: 'error',
                    title: "Login",
                    text: "Invalid token, please try again",
                });
            } )
        },
            
        async fnResetPasword(id, password) {
            const payload = {
                password: SHA1(password),
            };

            await clinicianService
                .post(`/password/reset/${id}`, payload)
                .then((response) => {
                    this.$router.push('/');
                })
                .catch((err) => {
                    console.log(err);
                });
        },


        async fnMe() {
            await authService.get('/auth/me').then(res => {
                let nameList = res.data.name.split(' ')
                let initials = ''
                nameList.forEach(name => {
                    initials += name[0]
                });

                imageExists(res.data.profilePicture)
                    .then(ok => {
                        if (ok) this.$patch({ name: res.data.name, profilePicture: res.data.profilePicture, initials: initials.slice(0, 2) })
                        else this.$patch({ name: res.data.name, profilePicture: '', initials: initials.slice(0, 2) })
                    })



            }).catch(error => {
                notify({
                    type: 'error',
                    title: "Obteniendo datos de usuario",
                    text: "Token invalido, Intentalo de nuevo",
                });
            })
        },


    },
})

if (import.meta.hot) {
    import.meta.hot.accept(acceptHMRUpdate(useUserStore, import.meta.hot))
  }
  

function imageExists(url) {
    return new Promise(resolve => {
        let image = new Image()
        image.addEventListener('load', () => resolve(true))
        image.addEventListener('error', () => resolve(false))
        image.src = url
    })
}