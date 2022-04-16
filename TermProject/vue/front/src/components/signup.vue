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
          ></v-text-field>
           <v-text-field
            label="아이디"
            placeholder="아이디"
          ></v-text-field>
           <v-text-field
            label="비밀번호"
            placeholder="비밀번호"
          ></v-text-field>
           <v-text-field
            label="비밀번호 확인"
            placeholder="비밀번호 확인"
          ></v-text-field>
           <v-text-field
            label="이메일"
            placeholder="이메일"
          ></v-text-field>
           <v-text-field
            label="생년월일"
            placeholder="생년월일"
          ></v-text-field>
           <v-text-field
            label="성별"
            placeholder="성별"
          ></v-text-field>
           <v-text-field
            label="주소"
            placeholder="주소"
          ></v-text-field>
            <v-text-field
            label="전화번호"
            placeholder="전화번호"
          ></v-text-field>
          <v-card-actions>
            <v-btn
              color="#2c4f91"
              dark
              large
              block
              @click="loginSubmit"
              >회원가입</v-btn
            >
          </v-card-actions>
        </v-col>
      </v-row>      
    </v-container>
  </v-form>
</template>

<script>
export default {

}
</script>

<style>

</style>
