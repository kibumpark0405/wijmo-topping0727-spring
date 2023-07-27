<template>

    <v-data-table
        :headers="headers"
        :items="orderManagment"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'OrderManagmentView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            orderManagment : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/orderManagments'))

            temp.data._embedded.orderManagments.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.orderManagment = temp.data._embedded.orderManagments;
        },
        methods: {
        }
    }
</script>

