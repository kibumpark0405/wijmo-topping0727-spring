<template>
    <v-card outlined>
        <v-card-title>
            OrderManagement # {{item._links.self.href.split("/")[item._links.self.href.split("/").length - 1]}}
        </v-card-title>

        <v-card-text>
            <div>
                <Date label="AccountingYearAndMonth" v-model="item.accountingYearAndMonth" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="OrderType" v-model="item.orderType" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="OrderNumber" v-model="item.orderNumber" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="PlaceOfDelivery" v-model="item.placeOfDelivery" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="FewWeeks" v-model="item.fewWeeks" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="DueDate" v-model="item.dueDate" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="NumberOfOrderItems" v-model="item.numberOfOrderItems" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="TotalDrinkingCapacity" v-model="item.totalDrinkingCapacity" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="TotalOrderAmount" v-model="item.totalOrderAmount" :editMode="editMode" @change="change" />
            </div>
            <PersonInChargeId offline label="PersonInChargeId" v-model="item.personInChargeId" :editMode="false" :key="false" @change="change" />
            <RegisteredPerson offline label="RegisteredPerson" v-model="item.registeredPerson" :editMode="false" :key="false" @change="change" />
            <OrderManagementDetailsManager offline label="OrderManagementDetails" v-model="item.orderManagementDetails" :editMode="false" @change="change" />
        </v-card-text>

        <v-card-actions>
            <v-btn text color="deep-purple lighten-2" large @click="goList">List</v-btn>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="edit"
                    v-if="!editMode"
            >
                Edit
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="save"
                    v-else
            >
                Save
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="remove"
                    v-if="!editMode"
            >
                Delete
            </v-btn>
            <v-btn
                    color="primary"
                    text
                    @click="editMode = false"
                    v-if="editMode"
            >
                Cancel
            </v-btn>
        </v-card-actions>
    </v-card>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'SalesOrderManagementDetail',
        components:{},
        props: {
        },
        data: () => ({
            item: null,
            editMode: false,
        }),
        async created() {
            var me = this;
            var params = this.$route.params;
            var temp = await axios.get(axios.fixUrl('/orderManagements/' + params.id))
            if(temp.data) {
                me.item = temp.data
            }
        },
        methods: {
            goList() {
                var path = window.location.href.slice(window.location.href.indexOf("/#/"), window.location.href.lastIndexOf("/#"));
                path = path.replace("/#/", "/");
                this.$router.push(path);
            },
            edit() {
                this.editMode = true;
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.item._links.self.href))
                    }

                    this.editMode = false;

                    this.$emit('input', this.item);
                    this.$emit('delete', this.item);

                } catch(e) {
                    console.log(e)
                }
            },
        },
    };
</script>
