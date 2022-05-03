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
          <td style="display:none" id="a">{{item.book_no}}</td>
          <td style="display:none" id="b">{{item.branch_code}}</td>
          <td>{{ item.book_title }}</td>
          <td>{{ item.book_author }}</td>
          <td>{{ item.rent_startdate }}</td>
          <td>{{ item.rent_enddate }}</td> 
          <td>{{ item.branch_name }}</td>         
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
  data () {    
    return {     
      rentdata: [],
       user_id:sessionStorage.getItem('user_id'),
       show: false,            
       rent_no: '',
       book_no: '',
       branch_code: ''
    }
  },
   mounted:function(){
            this.getData();
        },
        methods:{
            getData:function(){                              
            this.$axios.post("http://localhost:8080/rent_select",null,{
              params:{
                  user_id:this.user_id,                                                   
              }
            })      
            .then(response =>{
                console.log(response.data);                
                this.rentdata=response.data;                                                           
            }).catch(function(ex){
              throw new Error(ex)
            })       
            },
            returnBook:function(event) {                           
              const ss=document.getElementById('a')             
              const no=ss.innerText
              this.book_no=no
              const bb=document.getElementById('b')              
              const bran=bb.innerText
              this.branch_code=bran
              this.$axios.post("http://localhost:8080/rent_return",null,{
                params:{
                  user_id: this.user_id,
                  book_no: this.book_no,
                  branch_code: this.branch_code
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
