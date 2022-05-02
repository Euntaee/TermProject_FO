<template>
 <v-form ref="form" v-model="valid" lazy-validation>
    <v-container>
      <v-row>
        <v-col
          cols="12"
          sm="6"
          md="3"
           style="margin:0px auto;"
        >
          <v-text-field
            v-model="userName"
            label="이름"
            :rules="user_name_rule"
            required
          ></v-text-field>
           <v-text-field
            id='user_id'
            v-model="userId"
            label="아이디"
            :rules="user_id_rule"
            required
          ></v-text-field>
          <v-btn
           @click="checkId">
            중복체크
           </v-btn>
           <v-text-field
            v-model="userPwd"
            label="비밀번호"
            type="password"
            :rules="user_pwd_rule1"
          ></v-text-field>
           <v-text-field
            v-model="pwd_check"
            label="비밀번호 확인"
            type="password"
            :rules="user_pwd_rule2"
          ></v-text-field>
           <v-text-field
            v-model="userEmail"
            label="이메일"
            type="email"
            :rules="user_email_rule"
          ></v-text-field>
           <v-text-field
            v-model="userDate"
            label="생년월일"
            :rules="user_date_rule"
          ></v-text-field>
           <v-text-field
            v-model="userAddr1"
            label="주소"
            :rules="user_addr1rule"
          ></v-text-field>
            <v-text-field
            v-model="userPhone"
            label="전화번호"
            :rules="user_phone_rule"
          ></v-text-field>
          <v-card-actions>
            <v-btn
              :disabled="!valid"
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
data () {  
  return {       
    valid:false,
    userName:'',
    user_name_rule: [
      v => !!v || '이름은 필수 입력사항입니다.',
      v => !(v && v.length >= 30) || '이름은 30자 이상 입력할 수 없습니다.',
      v => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || '이름에는 특수문자를 사용할 수 없습니다.'
    ],    
    userId: '',
    user_id_rule: [
      v => !!v || '아이디는 필수 입력사항입니다.',
      v => /^[a-zA-Z0-9]*$/.test(v) || '아이디는 영문+숫자만 입력 가능합니다.',
      v => !( v && v.length >= 15) || '아이디는 15자 이상 입력할 수 없습니다.'
    ],
    userEmail: '',
    user_email_rule:[
      v => !!v || '이메일은 필수 입력사항입니다.',
      v => /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/.test(v) || '이메일 형식으로만 입력 가능합니다.'  
    ],
    userPwd: '',
    pwd_check: '',
    user_pwd_rule1: [
      v => !!v || '패스워드는 필수 입력사항입니다.',
      v => /^[a-zA-Z0-9]*$/.test(v) || '패스워드는 영문+숫자만 입력 가능합니다.',
      v => !(v && v.length >= 20) || '패스워드는 20자 이상 입력할 수 없습니다.'
    ],
    user_pwd_rule2: [
      v => !!v || '패스워드는 필수 입력사항입니다.',
      v => !(v && v.length >= 20) || '패스워드는 20자 이상 입력할 수 없습니다.',
      v => v === this.userPwd || '패스워드가 일치하지 않습니다.'
    ],
    userDate: '',
    user_date_rule: [
      v => !!v || '생년월일은 필수 입력사항입니다.',
      v => /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/.test(v) || '8글자로 생년월일을 입력해주세요.', 
      v => !(v && v.length >= 9) || '생년월일은 8자로 입력해주세요.' 
    ],
    userAddr1:'',
    user_addr1_rule:[
     v => !!v || '주소는 필수 입력사항입니다.'      
    ],
    userPhone: '',
    user_phone_rule:[
      v => !!v || '전화번호는 필수 입력사항입니다.',
      v => /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/.test(v) || '전화번호를 입력 해주세요.', 
      v => !(v && v.length >= 14) || '전화번호는 14자까지만 입력해주세요.'
    ]
  }
},
methods: {
    userInsert:function() {
      const validate =this.$refs.form.validate();      
        if (confirm ('회원가입 하시겠습니까?')){
          if(document.getElementById("user_id").readOnly == false){
				      alert("아이디 중복검사를 해주세요");
				      document.getElementById("user_id").focus();
				      return false;
          }
           this.$axios.post("http://localhost:8080/userInsert",null,{
             params:{
              userId:this.userId, userPwd:this.userPwd, userName:this.userName,
              userEmail:this.userEmail, userDate:this.userDate, userAddr1:this.userAddr1,
              userPhone:this.userPhone
            }
           }
             ).then(response => {
               console.log(response)
               window.location.href="http://localhost:8080/"
               alert("회원가입이 되었습니다")
             }).catch(err => {
               console.log(err)
             })
          }
      },
      checkId:function(){
        this.$axios.post("http://localhost:8080/id_check",null,{
          params:{
            userId:this.userId
          }
        }).then(response=>{
          console.log(response.data)

          let result=response.data
          if(result=='NO'){
            alert("아이디가 중복되었습니다!!")
            this.userId=""
          }else{
            alert("아이디 사용이 가능합니다!!")
            document.getElementById("user_id").readOnly = true;
          }
        })
      }
  }
}
</script>

<style>

</style>
