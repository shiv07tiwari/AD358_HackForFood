import Vue from 'vue'
import VueRouter from 'vue-router'
import MaintenancePortal from '@/views/MaintenancePortal.vue'
import RoadArchive from '@/views/RoadArchive.vue'
import Road from '@/views/Road.vue'
import Complaint from '@/views/Complaint.vue'

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
  {
    path: '/road/:id',
    name: 'road',
    component: Road
  },
  {
    path: '/complaint/:id',
    name: 'complaint',
    component: Complaint
  },
]

const router = new VueRouter({
  routes
})

export default router
