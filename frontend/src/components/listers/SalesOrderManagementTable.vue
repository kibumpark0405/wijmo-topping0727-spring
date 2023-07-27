<template>
    <div>
        <v-data-table
                :headers="headers"
                :items="values"
                :items-per-page="5"
                class="elevation-1"
        ></v-data-table>

        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                        v-model="openDialog"
                        width="332.5"
                        fullscreen
                        hide-overlay
                        transition="dialog-bottom-transition"
                >
                    <template v-slot:activator="{ on, attrs }">
                        <v-fab-transition>
                            <v-btn
                                    color="primary"
                                    fab
                                    dark
                                    large
                                    style="position:absolute; bottom: 5%; right: 2%; z-index:99"
                                    @click="openDialog=true;"
                            >
                                <v-icon v-bind="attrs" v-on="on">mdi-plus</v-icon>
                            </v-btn>
                        </v-fab-transition>
                    </template>

                    <SalesOrderManagement :offline="offline" class="video-card" :isNew="true" :editMode="true" v-model="newValue" @add="append" v-if="tick"/>
                
                    <v-btn
                            style="postition:absolute; top:2%; right:2%"
                            @click="closeDialog()"
                            depressed 
                            icon 
                            absolute
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-dialog>
            </div>
        </v-col>
    </div>
</template>

<script>
    const axios = require('axios').default;
    import SalesOrderManagement from './../SalesOrderManagement.vue';

    export default {
        name: 'SalesOrderManagementManager',
        components: {
            SalesOrderManagement,
        },
        props: {
            offline: Boolean,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            values: [],
            headers: 
                [
                    { text: "id", value: "id" },
                    { text: "accountingYearAndMonth", value: "accountingYearAndMonth" },
                    { text: "orderType", value: "orderType" },
                    { text: "orderNumber", value: "orderNumber" },
                    { text: "placeOfDelivery", value: "placeOfDelivery" },
                    { text: "fewWeeks", value: "fewWeeks" },
                    { text: "dueDate", value: "dueDate" },
                    { text: "numberOfOrderItems", value: "numberOfOrderItems" },
                    { text: "totalDrinkingCapacity", value: "totalDrinkingCapacity" },
                    { text: "totalOrderAmount", value: "totalOrderAmount" },
                    { text: "personInChargeId", value: "personInChargeId" },
                    { text: "registeredPerson", value: "registeredPerson" },
                    { text: "orderManagementDetails", value: "orderManagementDetails" },
                ],
            orderManagement : [],
            newValue: {},
            tick : true,
            openDialog : false,
        }),
        async created() {
            if(this.offline){
                if(!this.values) this.values = [];
                return;
            }

            var temp = await axios.get(axios.fixUrl('/ordermanagements'))
            temp.data._embedded.ordermanagements.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])
            this.values = temp.data._embedded.ordermanagements;

            this.newValue = {
                'accountingYearAndMonth': '2023-07-27',
                'orderType': '',
                'orderNumber': '',
                'placeOfDelivery': '',
                'fewWeeks': '2023-07-27',
                'dueDate': '2023-07-27',
                'numberOfOrderItems': '',
                'totalDrinkingCapacity': '',
                'totalOrderAmount': '',
                'personInChargeId': {},
                'registeredPerson': '',
                'orderManagementDetails': [],
            }
        },
        methods: {
            closeDialog(){
                this.openDialog = false
            },
            append(value){
                this.tick = false
                this.newValue = {}
                this.values.push(value)
                
                this.$emit('input', this.values);

                this.$nextTick(function(){
                    this.tick=true
                })
            },
        }
    }
</script>

