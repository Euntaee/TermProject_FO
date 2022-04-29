<!-- eslint-disable -->
<template>
<!-- <div class="container" style="">
  <v-simple-table style="margin-bottom:500px">
      <thead>
        <tr>
          <th class="text-left">
            아이디
          </th>
          <th class="text-left">
            이름
          </th>
          <th class="text-left">
            생년월일
          </th>
          <th class="text-left">
            주소
          </th> 
          <th class="text-left">
            이메일
          </th>   
          <th class="text-left">
            전화번호
          </th>     
          <th class="text-left">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;상태
          </th>         
        </tr>
      </thead>
      <tbody>
        <tr
        >
          <td>{{ User.user_id }}</td>
          <td>{{ User.user_name }}</td>
          <td>{{ Useruser_date }}</td>
          <td>{{ User.user_addr1 }}</td>
          <td>{{ User.user_email }}</td>
          <td>{{ User.user_phone }}</td>          
        </tr>        
      </tbody>      
  </v-simple-table>
     <div class="container" style="text-align:center">     
         <v-btn  router-link to="/changepwd" style="width:130px;">정보수정</v-btn>              
     </div>
  </div> -->
   <v-card
    max-width="500"
    style="margin-bottom:230px"
    class="mx-auto"
  > 
      <v-row class="fill-height">      

        <v-spacer></v-spacer>   
       <v-input 
       type="text">
        {{User.user_id}}
       </v-input>
    <v-list two-line>
      <v-list-item>
       <v-list-item-action></v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>{{ User.user_id }}</v-list-item-title>
          <v-list-item-subtitle>아이디</v-list-item-subtitle>
        </v-list-item-content>      
      </v-list-item>
        <v-divider inset></v-divider>
      <v-list-item>
        <v-list-item-action></v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>{{ User.user_name }}</v-list-item-title>
          <v-list-item-subtitle>이름</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>    
        <v-divider inset></v-divider>
      <v-list-item>
        <v-list-item-action></v-list-item-action>
        <v-list-item-content>
          <v-list-item-title><td>{{ Useruser_date }}</td></v-list-item-title>
          <v-list-item-subtitle>생년월일</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>  
        <v-divider inset></v-divider>
      <v-list-item>
        <v-list-item-action></v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>{{ User.user_addr1 }}</v-list-item-title>
          <v-list-item-subtitle>주소</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>  
        <v-divider inset></v-divider>
      <v-list-item>
        <v-list-item-action></v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>{{ User.user_email }}</v-list-item-title>
          <v-list-item-subtitle>이메일</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>  
        <v-divider inset></v-divider>
      <v-list-item>
        <v-list-item-action></v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>{{ User.user_phone }}</v-list-item-title>
          <v-list-item-subtitle>전화번호</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>   
    </v-list>
     <div class="container" style="text-align:center">     
         <v-btn  router-link to="/changepwd" style="width:130px;">정보수정</v-btn>              
     </div>
      </v-row>
  </v-card>
</template>
<script>
export default {
  data () {
    return {
      User: {},
      user_id: sessionStorage.getItem('user_id')
    }
  },
  mounted: function () {
    this.getData()
  },
  methods: {
    getData: function () {
      this.$axios.get('http://localhost:8080/userInfo', {params: {user_id: this.user_id}})
        .then(response => {
          console.log(response.data)
          this.User = response.data
        })
    }
  }
}
</script>

<style>

</style>
