<!-- eslint-disable -->
<template>
          <div>          
           <v-container style="width:400px; margin-bottom:150px;">
              <div style="float:right">
                <v-btn @click="pwdok" style="margin: 100px 0px 0px 400px">
                   비밀번호 확인
                </v-btn>  
              </div>              
             <v-form ref="form" style="margin: 0px auto" v-model="valid" lazy-validation>               
                    <div class="mx-3">                                                                                                                     
                        <v-icon color="black" size="30px">lock</v-icon>                        
                        <v-text-field                                                                             
                          placeholder="주소"                          
                          type="textbox" 
                          v-model="user_addr1"
                          :rules="user_addr1rule"
                          required
                        ></v-text-field>                                              
                     </div>
                    <div class="mx-3">
                       <div class="mx-1">                        
                        <v-text-field                                                                          
                          placeholder="전화번호"
                          type="textbox"
                          v-model="user_phone"  
                          :rules="user_phone_rule"                        
                          required
                        ></v-text-field>
                      </div>
                       <div class="mx-1">                        
                        <v-text-field                                                                          
                          placeholder="이메일"
                          type="textbox"
                          v-model="user_email"
                          :rules="user_email_rule"                          
                          required
                        ></v-text-field>
                      </div>
                       <div class="mx-1">                        
                        <v-text-field                                                
                          id:new_pwd1
                          placeholder="현재 비밀번호"
                          type="password"
                          v-model="user_pwd"
                          :rules="user_pwd_rule"
                          required
                        ></v-text-field>
                      </div>
                      <div class="mx-1">                        
                        <v-text-field                                                
                          id:new_pwd1
                          placeholder="새 비밀번호"
                          :rules="user_newpwd_rule"
                          type="password"
                          v-model="user_pwd2"
                          required
                        ></v-text-field>
                      </div>
                      <div class="mx-1">
                        <v-text-field
                          id:new_pwd2
                          placeholder="새 비밀번호 확인"
                          :rules="user_newpwd2_rule"
                          type="password"                          
                          required
                        ></v-text-field>
                      </div>
                      <v-btn                                                                       
                        :disabled="!valid"
                        style="width:250px; margin-bottom:200px;"
                        color="#2c4f91"
                        dark
                        large
                        block
                        @click="pwdchange"                                                 
                        >변경하기</v-btn>
                    </div>                   
                </v-form>
           </v-container>
         </div>
</template>

<script>
/* eslint-disable */
export default {
  data () {
    return {      
      valid:false,
      user_addr1: '',
      user_email: '',
      user_phone: '',
      user_pwd: '',
      user_pwd2: '',
      user_id: sessionStorage.getItem('user_id'),
      user_email_rule:[
      v => !!v || '이메일은 필수 입력사항입니다.',
      v => /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/.test(v) || '이메일 형식으로만 입력 가능합니다.'  
    ],
     user_pwd_rule: [
      v => !!v || '패스워드는 필수 입력사항입니다.',     
    ],
     user_newpwd_rule: [
      v => !!v || '패스워드는 필수 입력사항입니다.',
      v => /^[a-zA-Z0-9]*$/.test(v) || '패스워드는 영문+숫자만 입력 가능합니다.',
      v => !(v && v.length >= 20) || '패스워드는 20자 이상 입력할 수 없습니다.'
    ],
    user_newpwd2_rule: [
      v => !!v || '패스워드는 필수 입력사항입니다.',
      v => !(v && v.length >= 20) || '패스워드는 20자 이상 입력할 수 없습니다.',
      v => v === this.user_pwd2 || '패스워드가 일치하지 않습니다.'
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
  mounted: function () {
    this.getData()
  },
  methods: {
    pwdok: function () {
      this.$axios.post('http://localhost:8080/pwd_ok', null, {
        params: {
          user_pwd: this.user_pwd, user_id: this.user_id
        }
      }).then(response => {
        console.log(response)
        let result = response.data
        if (result === 'NOPWD') {
          alert('비밀번호가 틀립니다.')
          this.user_pwd=''
        } else {
          alert('비밀번호가 확인 되었습니다!')
        }
      })
    },
    pwdchange: function () {
      const validate =this.$refs.form.validate();
      if (validate) {
      if (confirm ('정보수정을 하시겠습니까?')){
      this.$axios.post('http://localhost:8080/pwd_change', null, {
        params: {
          user_pwd: this.user_pwd2, user_id: this.user_id, user_addr1: this.user_addr1,
          user_email: this.user_email, user_phone: this.user_phone
        }
      }).then(response => {
        console.log(response)
        window.location.href="http://localhost:8080/mypage"
        alert('비밀번호 변경이 완료 되었습니다!')       
      }).catch(err => {
               console.log(err)
         })
      }
      }
    },
    getData: function () {
      this.$axios.get('http://localhost:8080/userInfo', {params: {user_id: this.user_id}})
        .then(response => {
          console.log(response.data)
          this.user_addr1 = response.data.user_addr1
          this.user_phone = response.data.user_phone
          this.user_email = response.data.user_email
        })
    }
  }
}
</script>

<style>
</style>