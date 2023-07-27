
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import OrderManagementManager from "./grid/OrderManagementGrid"
import OrderManagementDetail from "./components/listers/OrderManagementDetail"

import PersonInChargeManager from "./grid/PersonInChargeGrid"
import PersonInChargeDetail from "./components/listers/PersonInChargeDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/orderManagements',
                name: 'OrderManagementManager',
                component: OrderManagementManager
            },
            {
                path: '/orderManagements/:id',
                name: 'OrderManagementDetail',
                component: OrderManagementDetail
            },

            {
                path: '/personInCharges',
                name: 'PersonInChargeManager',
                component: PersonInChargeManager
            },
            {
                path: '/personInCharges/:id',
                name: 'PersonInChargeDetail',
                component: PersonInChargeDetail
            },



    ]
})
