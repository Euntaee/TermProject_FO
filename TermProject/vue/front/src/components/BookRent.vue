<!-- eslint-disable -->
<template>
<v-container>
<v-row>
  
   <v-col
          cols="12"
          sm="12"
          md="12"
           style="margin:0px auto; text-align:center;"
        >        
        <h2>대여리스트</h2>
        <br>
        <br>
  <v-simple-table style="margin-bottom:300px">   
     
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
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;대여요청 상태
          </th>         
        </tr>
      </thead>
      <tbody>        
        <tr
          v-bind:key="item.id" v-for="item in rentdata"          
        >           
          <td style="display:none" id="a">{{item.book_no}}</td>
          <td style="display:none" id="b">{{item.branch_code}}</td>
          <td>{{ item.book_title }}</td>
          <td>{{ item.book_author }}</td>
          <td v-if="item.rent_check=='0'">{{ item.rent_startdate }}</td>
          <td v-if="item.rent_check=='0'">{{ item.rent_enddate }}</td> 
          <td v-if="item.rent_check=='1'"></td>
          <td v-if="item.rent_check=='1'"></td>
          <td>{{ item.branch_name }}</td>         
          <td>
            <v-btn color="danger" v-if="item.rent_check=='1'" @click="returnBook(item)" :index="index">예약 취소하기</v-btn>
            <span v-if="item.rent_check==0">대여중</span>
          </td>
        </tr>      
      </tbody>     
    </template>         
  </v-simple-table>      
  <br><br><br><br><br>
   </v-col>
  </v-row>
  </v-container>
</template>

<script>
/* eslint-disable */
export default {
  data () {    
    return {     
      rentdata: [],
       user_id:sessionStorage.getItem('user_id'),        
       rent_no: '',
       book_no: '',
       branch_code: '',
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
            this.$nextTick(function () {
               console.log(response.data);                
                this.rentdata=response.data;
            })                                                           
            }).catch(function(ex){
              throw new Error(ex)
            })       
            },
            returnBook:function(item) {                                     
              this.book_no = item.book_no
              this.branch_code = item.branch_code
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
