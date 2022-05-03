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
            지점
          </th>       
          <th class="text-left">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;상태
          </th>         
        </tr>
      </thead>
      <tbody>        
        <tr
          v-for="(item, index) in rentdata"
          :key="item.name"
        > 
          <td style="display:none" id="b">{{item.rent_no}}</td>
          <td style="display:none" id="a">{{item.book_no}}</td>
          <td>{{ item.book_title }}</td>
          <td>{{ item.book_author }}</td>
          <td>{{ item.rent_startdate }}</td>
          <td>{{ item.rent_enddate }}</td> 
          <td>{{ item.branch_code }}</td>         
          <td><v-btn color="danger" @click="returnBook" :index="index">반납하기</v-btn></td>
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
  props:{
            book_no: Object,
            branch_code: Object
          },
  data () {    
    return {     
      rentdata: [],
       user_id:sessionStorage.getItem('user_id'),
       show: false,     
       book_no: '',
       rent_no: ''
    }
  },
   mounted:function(){
            this.getData();
        },
        methods:{
            getData:function(){
            const rn=document.getElementById('b'); 
            const rno=rn.innerText
            this.rent_no=rno
            this.$axios.post("http://localhost:8080/rent_select",null,{
              params:{
                  user_id:this.user_id,
                  rent_no: this.rent_no
              }
            })      
            .then(response =>{
                console.log(response.data);                
                this.rentdata=response.data;                                         
                  // this.pagination.totalpage=this.Book[0].totalpage;
            }).catch(function(ex){
              throw new Error(ex)
            })       
            },
            returnBook:function(event) {
              const ss=document.getElementById('a');              
              const no=ss.innerText
              const rn=document.getElementById('b'); 
              const rno=rn.innerText
              this.book_no=no
              this.rent_no=rno
              this.$axios.post("http://localhost:8080/rent_return",null,{
                params:{
                  user_id: this.user_id,
                  book_no: this.book_no,                 
                  rent_no: this.rent_no
                }
              }).then(response => {
                console.log(response.data)    
                window.location.href="/rent"            
              })
            }        
        }
}
</script>

<style>
</style>
