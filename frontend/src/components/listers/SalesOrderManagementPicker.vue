<template>
    <div>
        <v-list two-line v-if="list.length > 0">
            <v-list-item-group 
                    v-model="selected" 
                    color="primary"
                    @change="select"
            >
                <v-list-item v-for="(item, idx) in list" :key="idx">
                    <template v-slot:default="{ active }">
                        <v-list-item-avatar color="primary-darker-1">
                        </v-list-item-avatar>
                        
                        <v-list-item-content>
                            <v-list-item-title>
                                {{item.personInChargeId }}
                            </v-list-item-title>
                            <v-list-item-subtitle>
                                AccountingYearAndMonth :  {{item.accountingYearAndMonth }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                OrderType :  {{item.orderType }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                OrderNumber :  {{item.orderNumber }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                PlaceOfDelivery :  {{item.placeOfDelivery }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                FewWeeks :  {{item.fewWeeks }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                DueDate :  {{item.dueDate }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                NumberOfOrderItems :  {{item.numberOfOrderItems }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                TotalDrinkingCapacity :  {{item.totalDrinkingCapacity }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                TotalOrderAmount :  {{item.totalOrderAmount }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                PersonInChargeId :  {{item.personInChargeId }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                RegisteredPerson :  {{item.registeredPerson }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                OrderManagementDetails :  {{item.orderManagementDetails }}
                            </v-list-item-subtitle>
                        </v-list-item-content>

                        <v-list-item-action>
                            <v-checkbox :input-value="active" color="primary-darker-1"></v-checkbox>
                        </v-list-item-action>
                    </template>
                </v-list-item>
            </v-list-item-group>
        </v-list>
    </div>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'SalesOrderManagementPicker',
        props: {
            value: [String, Object, Array, Number, Boolean],
        },
        data: () => ({
            list: [],
            selected: null,
        }),
        async created() {
            var me = this;
            var temp = await axios.get(axios.fixUrl('/orderManagements'))
            if(temp.data) {
                me.list = temp.data._embedded.orderManagements;
            }

            if(me.value && typeof me.value == "object" && Object.values(me.value)[0]) {
                var id = Object.values(me.value)[0];
                var tmpValue = await axios.get(axios.fixUrl('/orderManagements/' + id))
                if(tmpValue.data) {
                    var val = tmpValue.data
                    me.list.forEach(function(item, idx) {
                        if(item.name == val.name) {
                            me.selected = idx
                        }
                    })
                }
            }
        },
        methods: {
            select(val) {
                var obj = {}
                if(val != undefined) {
                    var arr = this.list[val]._links.self.href.split('/');
                    obj['id'] = arr[4]; 
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    obj['nameField'] = this.list[val].personInChargeId; 
                    
                    
                    
                    
                    this.$emit('selected', obj);
                }
            },
        },
    };
</script>

