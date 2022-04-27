<!-- eslint-disable -->
<template>
<div>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">
            도서
          </th>
          <th class="text-left">
            저자
          </th>
          <th class="text-left">
            대여일
          </th>
          <th class="text-left">
            반납일
          </th>         
          <th class="text-left">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;상태
          </th>         
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="item in rentdata"
          :key="item.name"
        >
          <td>{{ item.book_title }}</td>
          <td>{{ item.book_author }}</td>
          <td>{{ item.rent_startdate }}</td>
          <td>{{ item.rent_enddate }}</td>
          <td><v-btn color="success">대여하기</v-btn></td>
        </tr>
      </tbody>
    </template>         
  </v-simple-table>    
  <br><br><br><br><br>
  </div>
</template>

<script>
/* eslint-disable */
export default {
  data () {
    return {
      desserts: [
        {
          name: 'Frozen Yogurt',
          calories: 159
        },
        {
          name: 'Ice cream sandwich',
          calories: 237
        },
        {
          name: 'Eclair',
          calories: 262
        },
        {
          name: 'Cupcake',
          calories: 305
        },
        {
          name: 'Gingerbread',
          calories: 356
        },
        {
          name: 'Jelly bean',
          calories: 375
        },
        {
          name: 'Lollipop',
          calories: 392
        },
        {
          name: 'Honeycomb',
          calories: 408
        },
        {
          name: 'Donut',
          calories: 452
        },
        {
          name: 'KitKat',
          calories: 518
        }
      ],
      rentdata: [],
       user_id:sessionStorage.getItem('user_id')
    }
  },
   mounted:function(){
            this.getData();
        },
        methods:{
            getData:function(){
            this.$axios.post("http://localhost:8080/rent_select",null,{
              params:{
                  user_id:this.user_id
              }
            })      
            .then(response =>{
                console.log(response.data);
                this.rentdata=response.data;                 
                  // this.pagination.totalpage=this.Book[0].totalpage;
            }).catch(function(ex){
              throw new Error(ex)
            })       
            }           
        }
}
</script>

<style>
</style>
