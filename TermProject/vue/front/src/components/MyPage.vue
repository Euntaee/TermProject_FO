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
        <td>{{ User.actv_yn }}</td>                    
        </tr>   
      </tbody>      
  </v-simple-table>
     <div class="container" style="text-align:center">     
         <v-btn  router-link to="/changepwd" style="width:130px;">정보수정</v-btn>
         <v-btn @click="userDelete" style="width:130px;" v-if="result">회원탈퇴</v-btn>
         <v-btn @click="userDelete2" style="width:130px;" v-if="!result">회원탈퇴</v-btn>
     </div>
  </div>  
</template>
<script>
export default {
  data () {
    return {
      User: {},
      user_id: sessionStorage.getItem('user_id'),
      result: ''
    }
  },
  mounted: function () {
    this.userDeleteRules()
    this.getData()
  },
  methods: {
    getData: function () {
      this.$axios.get('http://localhost:8080/userInfo', {params: {user_id: this.user_id}})
        .then(response => {
          console.log(response.data)
          this.User = response.data
        })
    },
    userDelete: function () {
      this.$axios.post('http://localhost:8080/user_delete', null, {
        params: {
          user_id: this.user_id
        }
      }).then(response => {
        console.log(response.data)
        if (confirm('정말 회원 탈퇴를 하시겠습니까?')) {
          sessionStorage.removeItem('user_id')
          window.location.href = '/'
        }
      })
    },
    userDelete2: function () {
      alert('도서를 대여중이면 회원탈퇴가 불가능합니다.')
      window.location.href = '/rent'
    },
    userDeleteRules: function () {
      this.$axios.post('http://localhost:8080/userDeleteRules', null, {
        params: {
          user_id: this.user_id
        }
      }).then(response => {
        console.log(response.data)
        this.result = response.data
      })
    }
  }
}
</script>

<style>

</style>
