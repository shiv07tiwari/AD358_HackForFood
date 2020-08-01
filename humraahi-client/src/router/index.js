import Vue from 'vue'
import VueRouter from 'vue-router'
import MaintenancePortal from '@/views/MaintenancePortal.vue'
import RoadArchive from '@/views/RoadArchive.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: {name: 'roads'}
  },
  {
    path: '/maintenance',
    name: 'maintenance',
    component: MaintenancePortal
  },
  {
    path: '/roads',
    name: 'roads',
    component: RoadArchive
  },
]

const router = new VueRouter({
  routes
})

export default router
