<!--eslint-disable-->
<template>
  <v-form ref="form" lazy-validation>
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
            :rules="user_name_rule"            
            required
          ></v-text-field>
           <v-text-field
            label="아이디"
            placeholder="아이디"
            v-model="user_id"
            :rules="user_id_rule"
            required
          ></v-text-field>
           <v-text-field
            label="비밀번호"
            placeholder="비밀번호"
            type="password"
            v-model="user_pwd"
            :rules="user_pwd_rule1"
          ></v-text-field>
           <v-text-field
            label="비밀번호 확인"            
            placeholder="비밀번호 확인"
            type="password"
            v-model="pwd_check"
            :rules="user_pwd_rule2"
          ></v-text-field>
           <v-text-field
            label="이메일"
            placeholder="이메일"
            type="email"
            v-model="user_email"
            :rules="user_email_rule"
          ></v-text-field>
           <v-text-field
            label="생년월일"
            placeholder="생년월일"
            v-model="user_date"
            :rules="user_date_rule"
          ></v-text-field>           
           <v-text-field
            label="주소"
            placeholder="주소"
            v-model="user_addr1"
            :rules="user_addr1_rule"
          ></v-text-field>
            <v-text-field
            label="전화번호"
            placeholder="전화번호"
            v-model="user_phone"
            :rules="user_phone_rule"
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
data() {
  return{
    dialog: false,
    state: 'ins',    
    user_name:'',
    user_name_rule: [
      v => !!v || '이름은 필수 입력사항입니다.',
      v => !(v && v.length >= 30) || '이름은 30자 이상 입력할 수 없습니다.',
      v => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || '이름에는 특수문자를 사용할 수 없습니다.'
    ],    
    user_id: '',
    user_id_rule: [
      v => !!v || '아이디는 필수 입력사항입니다.',
      v => /^[a-zA-Z0-9]*$/.test(v) || '아이디는 영문+숫자만 입력 가능합니다.',
      v => !( v && v.length >= 15) || '아이디는 15자 이상 입력할 수 없습니다.'
    ],
    user_email: '',
    user_email_rule:[
      v => !!v || '이메일은 필수 입력사항입니다.',
       v => /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/.test(v) || '이메일 형식으로만 입력 가능합니다.',  
    ],
    user_pwd: '',
    pwd_check: '',
    user_pwd_rule1: [
      v => this.state === 'ins' ? !!v || '패스워드는 필수 입력사항입니다.' : true,
      v => /^[a-zA-Z0-9]*$/.test(v) || '패스워드는 영문+숫자만 입력 가능합니다.',
      v => !(v && v.length >= 20) || '패스워드는 20자 이상 입력할 수 없습니다.',
    ],
    user_pwd_rule2: [
      v => this.state === 'ins' ? !!v || '패스워드는 필수 입력사항입니다.' : true,
      v => !(v && v.length >= 20) || '패스워드는 20자 이상 입력할 수 없습니다.',
      v => v === this.user_pwd || '패스워드가 일치하지 않습니다.'
    ],
    user_date: '',
    user_date_rule: [
      v => !!v || '생년월일은 필수 입력사항입니다.',
      v => /^(19[0-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/.test(v) || '이메일 형식으로만 입력 가능합니다.', 
      !(v && v.length >= 8) || '생년월일은 8자로 입력해주세요.', 
    ],
    user_addr1:'',
    user_addr1_rule:[
     v => !!v || '주소는 필수 입력사항입니다.',
      
    ],
    user_phone: '',
    user_phone_rule:[
      v => !!v || '전화번호는 필수 입력사항입니다.',
      v => /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/.test(v) || '전화번호를 입력 해주세요.', 
      !(v && v.length >= 14) || '전화번호는 14자까지만 입력해주세요.', 
    ]
  }
},
watch:{

},
methods: {
    userInsert:function() {
      const validate =this.$refs.form.validate();
      if (validate) {
        if (confirm ('회원가입 하시겠습니까?')){
          const params= {
            user_id:this.user_id,
            user_pwd:this.user_pwd,
            user_name:this.user_name,
            user_email:this.user_email,
            user_date:this.user_date,
            user_addr1:this.user_addr1,
            user_phone:this.user_phone
          }
           this.$axios.post("http://localhost:8080/userInsert",null,
             ).then(response => {
               console.log(response)
               window.location.href="http://localhost:8080/"
               alert("회원가입이 되었습니다")
             }).catch(err => {
               console.log(err)
             })
          }
      }
      
      
      // const params = new URLSearchParams();
      // params.append('user_id',this.user_id);
      // params.append('user_pwd',this.user_pwd);
      // params.append('user_name',this.user_name);
      // params.append('user_email',this.user_email);
      // params.append('user_date',this.user_date);
      // params.append('user_addr1',this.user_addr1);
      // params.append('user_phone',this.user_phone);
      }      
  }
}
</script>

<style>

</style>
