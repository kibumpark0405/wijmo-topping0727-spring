<template>

    <v-card outlined>
        <v-card-title>
            OrderManagementDetails
        </v-card-title>

        <v-card-text>
            <String label="UniqueNumber" v-model="value.uniqueNumber" :editMode="editMode"/>
            <String label="ItemCode" v-model="value.itemCode" :editMode="editMode"/>
            <String label="ItemName" v-model="value.itemName" :editMode="editMode"/>
            <String label="ItemSpecification" v-model="value.itemSpecification" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions v-if="inList">
            <slot name="actions"></slot>
        </v-card-actions>
    </v-card>
</template>

<script>

    export default {
        name: 'OrderManagementDetails',
        components:{},
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
            inList: Boolean,
            label: String,
        },
        data: () => ({
        }),
        async created() {
            if(!Object.values(this.value)[0]) {
                this.$emit('input', {});
                this.newValue = {
                    'id': '',
                    'uniqueNumber': '',
                    'itemCode': '',
                    'itemName': '',
                    'itemSpecification': '',
                }
            }
            if(typeof this.value === 'object') {
                if(!('uniqueNumber' in this.value)) {
                    this.value.uniqueNumber = '';
                }
                if(!('itemCode' in this.value)) {
                    this.value.itemCode = '';
                }
                if(!('itemName' in this.value)) {
                    this.value.itemName = '';
                }
                if(!('itemSpecification' in this.value)) {
                    this.value.itemSpecification = '';
                }
            }
        },
        watch: {
            value(val) {
                this.$emit('input', val);
            },
            newValue(val) {
                this.$emit('input', val);
            },
        },

        methods: {
            edit() {
                this.editMode = true;
            },
            async add() {
                this.editMode = false;
                this.$emit('input', this.value);

                if(this.isNew){
                    this.$emit('add', this.value);
                } else {
                    this.$emit('edit', this.value);
                }
            },
            async remove(){
                this.editMode = false;
                this.isDeleted = true;

                this.$emit('input', this.value);
                this.$emit('delete', this.value);
            },
            change(){
                this.$emit('change', this.value);
            },
        }
    }
</script>

