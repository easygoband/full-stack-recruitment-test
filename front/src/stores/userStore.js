import {
    defineStore,
    acceptHMRUpdate
} from 'pinia';
import userService from '@/plugins/userService';
import { _UserModel, _ReportModel } from '@/utils/models.js'

 

export const useUserStore = defineStore({
    id: 'user',
    state: () => ({
        userInfo: _UserModel(),
        survivorInfo: _UserModel(),
        report: _ReportModel(),
        loading: {
            data: false,
            survivorInfo: false,
            infected: false,
            report: false,
        },
        userList: []
    }),

    actions: {
        fnLogout() {
            this.$patch({
                userInfo: _UserModel(),
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
            })
            setTimeout(() => {
                this.fnLoading({location:false});
            }, 200);
        },

        async fnApiUserInfected(userId) {
            this.fnLoading({infected:true});
            await userService.post(`/user/infected/${userId}`).then(res => {
                if (res.data.success == true) {
                    this.$patch( {
                        survivorInfo: { ...this.survivorInfo, ...{ infected: true }}
                    })
                }
            }).catch(error => {
            })
            setTimeout(() => {
                this.fnLoading({infected:false});
            }, 200);
        },

        async fnApiGetReport() {
            this.fnLoading({report:true});
            await userService.get(`/users/report`).then(res => {
                 this.$patch( {
                    report: res.data,
                 })
            }).catch(error => { })
            setTimeout(() => {
                this.fnLoading({report:false});
            }, 200);
        },
    },
})

if (
    import.meta.hot) {
    import.meta.hot.accept(acceptHMRUpdate(useUserStore,
        import.meta.hot))
}