
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import SalesOrderManagementManager from "./components/listers/SalesOrderManagementCards"
import SalesOrderManagementDetail from "./components/listers/SalesOrderManagementDetail"

import PersonInChargePersonInChargeManager from "./components/listers/PersonInChargePersonInChargeCards"
import PersonInChargePersonInChargeDetail from "./components/listers/PersonInChargePersonInChargeDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/sales/orderManagements',
                name: 'SalesOrderManagementManager',
                component: SalesOrderManagementManager
            },
            {
                path: '/sales/orderManagements/:id',
                name: 'SalesOrderManagementDetail',
                component: SalesOrderManagementDetail
            },

            {
                path: '/personInCharges/personInCharges',
                name: 'PersonInChargePersonInChargeManager',
                component: PersonInChargePersonInChargeManager
            },
            {
                path: '/personInCharges/personInCharges/:id',
                name: 'PersonInChargePersonInChargeDetail',
                component: PersonInChargePersonInChargeDetail
            },



    ]
})
