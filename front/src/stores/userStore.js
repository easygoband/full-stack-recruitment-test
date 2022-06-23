import {
    defineStore,
    acceptHMRUpdate
} from 'pinia';
import userService from '@/plugins/userService';

const userModel =  {
    _id: null,
    location:{
        latitude: null,
        longitude: null,
    },
    items:{
        water: 0,
        food: 0,
        medication: 0,
        ammunition: 0,
    }
}

export const useUserStore = defineStore({
    id: 'user',
    state: () => ({
        userInfo: {...userModel},
        survivorInfo: {...userModel},
        loading: {
            data: false,
            survivorInfo: false,
        },
        userList: []
    }),

    actions: {
        fnLogout() {
            this.$patch({
                userInfo: {...userModel},
            })
            localStorage.removeItem('token');
            localStorage.removeItem('user');
            this.$router.push(`/`);
        },
        fnLoading(loading){
            this.$patch({
                loading: { ...this.loading, ...loading }
            })
        },

        fnSaveUserData(data){
            localStorage.setItem('token', data.token);
            localStorage.setItem('user', JSON.stringify(data.user));
            this.$router.push(`/dashboard`);
        },

        fnGetUserData(){
            const data = localStorage.getItem('user');
            if(data){
                this.userInfo = JSON.parse(data) ;
            }
        },

        async fnApiLogin(name) {
            const payload = {
                name
            };

            this.fnLoading({data:true});

            await userService.post('/login', payload).then(res => {
                if (res.data.token) {
                    this.fnSaveUserData(res.data);
                }
            }).catch(error => {
                // notify({
                //     type: 'error',
                //     title: "Login",
                //     text: "Invalid email or password",
                // });
            })
            setTimeout(() => {
                this.fnLoading({data:false});
            }, 200);
        },

        async fnApiSignup(payload) {
            await userService.post('/signup', payload).then(res => {
                if (res.data.token) {
                    this.fnSaveUserData(res.data);
                }
            }).catch(error => {
                // notify({
                //     type: 'error',
                //     title: "Login",
                //     text: "Invalid email or password",
                // });
            })
        },


        async fnApiGetUserList(isTrue) {
            this.fnLoading({data:true});
            let params = {};
            if( isTrue ){
                params = {
                    userId: this.userInfo._id
                };
            }
            await userService.get('/users',{params}).then(res => {
                 this.$patch( {
                    userList: res.data,
                 })
            }).catch(error => { })
            setTimeout(() => {
                this.fnLoading({data:false});
            }, 200);
        },

        async fnApiGetUserInfo(userId) {
            this.fnLoading({survivorInfo:true});
            await userService.get(`/users/${userId}`).then(res => {
                 this.$patch( {
                    survivorInfo: res.data,
                 })
            }).catch(error => { })
            setTimeout(() => {
                this.fnLoading({survivorInfo:false});
            }, 200);
        },


        

        async fnApiUpdateLocation(payload) {
            this.fnLoading({location:true});
            await userService.put('/user/location', payload).then(res => {
               this.$patch( {
                   userInfo: { ...this.userinfo, ...{location: payload }}
               })
            }).catch(error => {
                // notify({
                //     type: 'error',
                //     title: "Login",
                //     text: "Invalid email or password",
                // });
            })
            setTimeout(() => {
                this.fnLoading({location:false});
            }, 200);
        },

        


    },
})

if (
    import.meta.hot) {
    import.meta.hot.accept(acceptHMRUpdate(useUserStore,
        import.meta.hot))
}