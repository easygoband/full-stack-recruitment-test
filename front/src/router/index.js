import {
  createRouter,
  createWebHistory
} from 'vue-router'

const modules = import.meta.glob('../views/**/*.vue');

const view = async (viewName) => {
  return await modules[`../views/${viewName}.vue`]();
}

const router = createRouter({
  history: createWebHistory(
    import.meta.env.BASE_URL),
  routes: [{
      path: '/',
      name: 'home',
      component: () => view('HomeView'),
    },
    // {
    //   path: '/pincode',
    //   name: 'pincode',
    //   component:() => view('PinCodeView'),
    // },
    // {
    //   path: '/qrcode',
    //   name: 'qrcode',
    //   component:() => view('QRCodeView'),
    // },
    // {
    //   path: '/reset-pwd/:id',
    //   name: 'ResetPassword',
    //   component: () => view('ResetPasswordView')
    // },
    // {
    //   path: '/styles',
    //   name: 'styles',
    //   component: () => view('stylesView'),
    // },
    // {
    //   path: '/dashboard',
    //   // name: 'dashboard',
    //   component: () => view('dashboard/IndexView'),
    //   meta: {
    //     auth: true,
    //   },
    //   children: [{
    //       path: '',
    //       name: 'dashboard',
    //       component: () => view('dashboard/DashboardView')
    //     },
    //     {
    //       path: 'patients',
    //       name: 'patients',
    //       component: () => view('dashboard/PatientsView')
    //     },
    //     {
    //       path: 'patients/:id',
    //       name: 'patientInformation',
    //       component: () => view('dashboard/patient/PatientView')
    //     },
    //     {
    //       path: 'configuration',
    //       name: 'configuration',
    //       component: () => view('dashboard/ConfigurationView')
    //     },
    //   ]
    // }
  ]
})

// router.beforeEach((to, from) => {
//   if (to.meta.auth && localStorage.getItem('token')) {
//     return {
//       name: 'home',
//     }
//   }
// })

export default router