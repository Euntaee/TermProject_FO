<!--eslint-disable-->
<template>
  <v-form >
    <v-container>
      <v-row>      
        <v-col
          cols="12"
          sm="6"
          md="3"
           style="margin:0px auto;"
        >
          <v-text-field
            label="이름"
            placeholder="이름"
            v-model="user_name"
          ></v-text-field>
           <v-text-field
            label="아이디"
            placeholder="아이디"
            v-model="user_id"
          ></v-text-field>
           <v-text-field
            label="비밀번호"
            placeholder="비밀번호"
            type="password"
            v-model="user_pwd"
          ></v-text-field>
           <!-- <v-text-field
            label="비밀번호 확인"
            placeholder="비밀번호 확인"
          ></v-text-field> -->
           <v-text-field
            label="이메일"
            placeholder="이메일"
            v-model="user_email"
          ></v-text-field>
           <v-text-field
            label="생년월일"
            placeholder="생년월일"
            v-model="user_date"
          ></v-text-field>           
           <v-text-field
            label="주소"
            placeholder="주소"
            v-model="user_addr1"
          ></v-text-field>
            <v-text-field
            label="전화번호"
            placeholder="전화번호"
            v-model="user_phone"
          ></v-text-field>
          <v-card-actions>
            <v-btn
              color="#2c4f91"
              dark
              large
              block
              @click="userInsert"
              >회원가입</v-btn
            >
          </v-card-actions>
        </v-col>
      </v-row>      
    </v-container>
  </v-form>
</template>

<script>
/* eslint-disable */
export default {
methods: {
    userInsert:function() {
      const params = new URLSearchParams();
      params.append('user_id',this.user_id);
      params.append('user_pwd',this.user_pwd);
      params.append('user_name',this.user_name);
      params.append('user_email',this.user_email);
      params.append('user_date',this.user_date);
      params.append('user_addr1',this.user_addr1);
      params.append('user_phone',this.user_phone);
      this.$axios.post("http://localhost:8080/userInsert",params
      ).then(response => {
        console.log(response)
        window.location.href="http://localhost:8080/"
        alert("회원가입이 되었습니다")
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style>

</style>
