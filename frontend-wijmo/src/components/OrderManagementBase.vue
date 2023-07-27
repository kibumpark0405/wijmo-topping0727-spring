<template>
    <div></div>
</template>

<script>
    import PersonInChargeBase from '../components/PersonInChargeBase.vue'
    const axios = require('axios').default;

    export default{
        name: 'orderManagement-base',
        props: {
            offline: Boolean
        },
        computed: {},
        data: () => ({
            values: [],
            newValue: {},
            isUpdating: false,
        }),
        methods:{
            closeDialog(){
                this.openDialog = false
                location.reload()
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
            remove(value){
                var where = -1;
                for(var i=0; i<this.values.length; i++){
                    if(this.values[i]._links.self.href == value._links.self.href){
                        where = i;
                        break;
                    }
                }
                if(where > -1){
                    this.values.splice(i, 1);
                    this.$emit('input', this.values);
                }
            },
            async getRealEntity(id){
               try{
                    let result = await axios.get(axios.fixUrl(`/orderManagements/${id}`))
                    result.data.id = id;
                    return result.data;
                }catch(e){
                    return null;
                }
            },
            async processData(data){
                let PersonInChargeClass = this.$Vue.extend(PersonInChargeBase);
                this.personInChargeId = new PersonInChargeClass();
                

                let Promises = data.map(async (value) => {
                    if(value == null) return
                    if (value.personInChargeId && value.personInChargeId.id){
                        value.personInChargeId = await this.personInChargeId.getRealEntity(value.personInChargeId.id);
                    }
                });
                await Promise.all(Promises);
                for(var i = 0; i < data.length ; i++ ) {
                    data[i].index = i;
                }
                return data;

            },
            async search(query) {
                var me = this;
                if(me.offline){
                    if(!me.values) me.values = [];
                    return;
                } 
                var temp = null;
                if(query!=null){
                    temp = await axios.put(axios.fixUrl('/orderManagements/search/findByNumberOfOrderItems'), { numberOfOrderItems: query.parameters.numberOfOrderItems });
                    me.values = await me.processData(temp.data);
                }else{
                    temp = await axios.get(axios.fixUrl('/orderManagements'))
                    me.values = await me.processData(temp.data._embedded.orderManagements);
                }
                return me.values;
            },
        },

    }

</script>
