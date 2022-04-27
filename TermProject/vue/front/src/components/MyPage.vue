<!-- eslint-disable -->
<template v-slot:default>
  <v-simple-table style="margin: 0px auto">
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
      <v-btn  router-link to="/changepwd">비밀번호 바꾸기</v-btn>  <v-btn>정보수정</v-btn>     
  </v-simple-table>
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
