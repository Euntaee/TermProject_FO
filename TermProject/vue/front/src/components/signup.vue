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
            v-model.trim="userName"
            label="이름"
            :rules="user_name_rule"
            :counter="30"
            maxlength="30"
            required
          ></v-text-field>
           <v-text-field
            id='user_id'
            v-model.trim="userId"
            label="아이디"
            :counter="15"
            maxlength="15"
            :rules="user_id_rule"
            required
          ></v-text-field>
          <v-btn
           @click="checkId">
            중복체크
           </v-btn>
           <v-text-field
            v-model.trim="userPwd"
            label="비밀번호"
            type="password"
            :rules="user_pwd_rule1"
            :counter="20"
            maxlength="20"
          ></v-text-field>
           <v-text-field
            v-model.trim="pwd_check"
            label="비밀번호 확인"
            type="password"
            :counter="20"
            maxlength="20"
             :rules="[rules.required, rules.counter, rules.userPwd, passwordConfirmationRule]"
            @click="test()"
          ></v-text-field>
           <v-text-field
            v-model.trim="userEmail"
            label="이메일"
            type="email"
            :counter="60"
            maxlength="60"
            :rules="user_email_rule"
          ></v-text-field>
           <v-text-field
            v-model.trim="userDate"
            label="생년월일  ex)19961010"
            :counter="8"
            maxlength="8"
            :rules="user_date_rule"
          ></v-text-field>
           <v-text-field
            v-model.trim="userAddr1"
            label="주소"
             :counter="60"
            maxlength="60"
            :rules="user_addr1rule"
          ></v-text-field>
            <v-text-field
            v-model.trim="userPhone"
            label="전화번호"
            :counter="13"
            maxlength="13"
            @keyup="getPhoneMask(userPhone)"
            :rules="user_phone_rule"
          ></v-text-field>
           <v-select
           v-model.trim="userQuestion"
          :items="questionType"
           item-text="name"
           item-value="value"
          label="비밀번호 찾기 힌트"
        ></v-select>
         <v-text-field
            v-model.trim="userAnswer"
            :counter="30"
            maxlength="30"
            :rules=" user_answer_rule"
            label="비밀번호 찾기 힌트 답"
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
    userQuestion:'1',
    questionType: [
                     {name: '나의 별명은?', value:'1'} ,
                     {name: '가장 좋아하는 색상은?', value:'2'} ,
                     {name: '가장 좋아하는 음식은?', value:'3'} ,
                     {name: '가장 좋아하는 영화는?', value:'4'} ,
                     {name: '가장 좋아하는 노래는?', value:'5'}
                  ],
   
    valid:false,
    userName:'',
    user_name_rule: [
      v => !!v || '이름은 필수 입력사항입니다.',
      v => !(v && v.length >= 31) || '이름은 30자 이내로 입력해주세요.',
      v => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || '이름에는 특수문자를 사용할 수 없습니다.',
      v => /^[ㄱ-ㅎ가-힣a-zA-Z]*$/.test(v) || '이름은 한글과 영문만 가능합니다.',
      v => !/[/\s/]/.test(v) || '중간에 공백이 있으면 안됩니다.'
    ],    
    userId: '',
    user_id_rule: [
      v => !!v || '아이디는 필수 입력사항입니다.',
      v => /^[a-zA-Z0-9]*$/.test(v) || '아이디는 영문+숫자만 입력 가능합니다.',
      v => !( v && v.length >= 21) || '아이디는 21자 이내로 입력해주세요.',
      v => !/[/\s/]/.test(v) || '중간에 공백이 있으면 안됩니다.'
    ],
    userEmail: '',
    user_email_rule:[
      v => !!v || '이메일은 필수 입력사항입니다.',
      v => /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/.test(v) || '이메일 형식으로만 입력 가능합니다.',
      v => !(v && v.length >= 61) || '이메일은 60자 이내로 입력해주세요',
      v => !/[/\s/]/.test(v) || '중간에 공백이 있으면 안됩니다.'
    ],
    userPwd: '',
    pwd_check: '',
    user_pwd_rule1: [
      v => !!v || '패스워드는 필수 입력사항입니다.',
      v => /^[a-zA-Z0-9]*$/.test(v) || '패스워드는 영문+숫자만 입력 가능합니다.',
      v => !(v && v.length >= 21) || '패스워드는 20자 이내로 입력해주세요.',
      v => !/[/\s/]/.test(v) || '중간에 공백이 있으면 안됩니다.'
    ],
     rules: {
          required: value => !!value || '패스워드는 필수 입력사항입니다.',
          counter: value => value.length <= 21 || '패스워드는 20자 이내로 입력해주세요',
          userPwd: value => {
            const pattern =/^[a-zA-Z0-9]*$/
            return pattern.test(value) || '패스워드는 영문+숫자만 입력 가능합니다.'
          }
    },
    user_pwd_rule2: [
      v => !!v || '패스워드는 필수 입력사항입니다.',
      v => !(v && v.length >= 21) || '패스워드는 20자 이내로 입력해주세요.',
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
     v => !!v || '주소는 필수 입력사항입니다.',
     v => !(v && v.length >= 61) || '주소는 60자 이내로 입력해주세요'       
    ],
    userPhone: '',
    user_phone_rule:[
      v => !!v || '전화번호는 필수 입력사항입니다.',
      v => /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/.test(v) || '전화번호를 입력 해주세요.', 
      v => !(v && v.length >= 14) || '전화번호는 13자까지만 입력해주세요.'
    ],
    userAnswer:'',
    user_answer_rule:[
     v => !!v || '답변은 필수 입력사항입니다..',
     v => !(v && v.length >= 31) || '주소는 30자 이내로 입력해주세요'       
    ],
  }
},
computed: {
    passwordConfirmationRule() {
      return this.userPwd === this.pwd_check || '비밀번호가 일치하지 않습니다!!'
    }
},
methods: {
    test : () =>  console.log(`this.userPwd = ${this.userPwd}`),
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
              userPhone:this.userPhone, userQuestion:this.userQuestion, userAnswer:this.userAnswer
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
          if(this.userId.trim().length==0){
            alert("아이디를 입력해주세요!!")
          } else{
          if(result=='NO'){
            alert("아이디가 중복되었습니다!!")
            this.userId=""
          } else{
            alert("아이디 사용이 가능합니다!!")
            document.getElementById("user_id").readOnly = true;
            }
          }
        })
      },
       getPhoneMask(val) {
        let res = this.getMask(val)
        this.userPhone = res        
    },
    
    getMask( phoneNumber ) {
        if(!phoneNumber) return phoneNumber
        phoneNumber = phoneNumber.replace(/[^0-9]/g, '')    
        let res = ''
        if(phoneNumber.length < 3) {
            res = phoneNumber
        }
        else {
            if(phoneNumber.substr(0, 2) =='02') {
        
                if(phoneNumber.length <= 5) {//02-123-5678
                    res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 3)
                }
                else if(phoneNumber.length > 5 && phoneNumber.length <= 9) {//02-123-5678
                    res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 3) + '-' + phoneNumber.substr(5)
                }
                else if(phoneNumber.length > 9) {//02-1234-5678
                    res = phoneNumber.substr(0, 2) + '-' + phoneNumber.substr(2, 4) + '-' + phoneNumber.substr(6)
                }        
            } else {
                if(phoneNumber.length < 8) {
                    res = phoneNumber
                }
                else if(phoneNumber.length == 8)
                {
                    res = phoneNumber.substr(0, 4) + '-' + phoneNumber.substr(4)
                }
                else if(phoneNumber.length == 9)
                {
                    res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 3) + '-' + phoneNumber.substr(6)
                }
                else if(phoneNumber.length == 10)
                {
                    res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 3) + '-' + phoneNumber.substr(6)
                }
                else if(phoneNumber.length > 10) { //010-1234-5678
                    res = phoneNumber.substr(0, 3) + '-' + phoneNumber.substr(3, 4) + '-' + phoneNumber.substr(7)
                }
            }
        }                  
        return res
        }
  }
}
</script>

<style>

</style>
