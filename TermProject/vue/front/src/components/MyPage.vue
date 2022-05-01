<!-- eslint-disable -->
<template>
<div class="container" style="margin-top:100px">
  <h1 style="text-align:center">회원정보</h1>
  <br>
  <v-simple-table style="margin-bottom:100px">
      <tbody>
        <tr>
          <th class="text-left">
            아이디
          </th>
          <td>{{ User.user_id }}</td>
        </tr>
        <tr>  
           <th class="text-left">
            이름
          </th>
          <td>{{ User.user_name }}</td>
        </tr>
        <tr>  
           <th class="text-left">
            생년월일
          </th>
          <td>{{ User.user_date }}</td>
        </tr>
        <tr>  
          <th class="text-left">
            주소
          </th>
          <td>{{ User.user_addr1 }}</td>
        </tr>
        <tr>
           <th class="text-left">
            이메일
          </th>  
          <td>{{ User.user_email }}</td>
        </tr>
        <tr>
          <th class="text-left">
            전화번호
          </th>  
          <td>{{ User.user_phone }}</td>                    
        </tr>      
        <tr>
        <th class="text-left">
           상태
          </th>
        </tr>   
      </tbody>      
  </v-simple-table>
     <div class="container" style="text-align:center">     
         <v-btn  router-link to="/changepwd" style="width:130px;">정보수정</v-btn>
     </div>
  </div>  
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
