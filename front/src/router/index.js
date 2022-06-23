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
      component: () => view('home/HomeView'),
    },
    // {
    //   path: '/dashboard',
    //   name: 'dashboard',
    //   component: () => view('dashboard/DashboardView'),
    // },

    {
      path: '/dashboard',
      // name: 'dashboard',
      component: () => view('dashboard/DashboardView'),
      meta: {
        auth: true,
      },
      children: [{
          path: '',
          name: 'dashboard',
          component: () => view('dashboard/UsersView')
        },
        {
          path: 'users/:userId',
          name: 'user',
          component: () => view('dashboard/UserView')
        },
      ]
    }

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