<template>
  <v-app id="app">
    <!-- <div class="protected" v-if="loginsuccess">
      <v-alert
        type="success"
    >로그인 성공</v-alert>
    </div>
     <div class="unprotected" v-else-if="loginError">
      <v-alert
        type="success"
    >로그인 실패</v-alert>
    </div> -->
    <v-main style="margin:0px auto">
      <h1 style="text-align:center;margin-top:20%">로그인</h1>
      <br>
      <v-container
        style="position: relative;"
        class="text-xs-center"
      >
        <v-layout row wrap class="text-xs-center">
          <v-flex>
            <v-card flat class="mx-auto" max-width="800">
              <v-row>
                <v-col>
                  <v-form  style="width: 400px; height: 300px; margin-top:20%">
                    <div class="mx-3">
                      <v-icon color="black" size="30px">person</v-icon>
                      <div class="mx-1">
                        <v-text-field
                          id:login_id
                          placeholder="userId"
                          v-model="user_id"
                          required
                        ></v-text-field>
                      </div>
                    </div>
                    <div class="mx-3">
                      <v-icon color="black" size="30px">lock</v-icon>
                      <div class="mx-1">
                        <v-text-field
                          id:login_pwd
                          placeholder="userPassword"
                          type="password"
                          v-model="user_pwd"
                          required
                        ></v-text-field>
                      </div>
                    </div>
                    <div>
                      &emsp; <a href="/signup">회원가입</a>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<a>비밀번호 찾기</a>
                    </div>
                    <v-card-actions>
                      <v-btn
                       @click="login"              
                        color="#2c4f91"
                        dark
                        large
                        block
                        type="button"
                        >Login</v-btn
                      >                     
                    </v-card-actions>
                  </v-form>
                </v-col>
              </v-row>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
/* eslint-disable */
export default { 
  data () {
   return {          
          user_id: '',
          user_pwd: ''          
      }
  },
  methods: {
     login:function(){       
       const logindata ={user_id:this.user_id, user_pwd:this.user_pwd}
       console.log(this.user_id,this.user_pwd)              
			  this.$axios.post("http://localhost:8080/login_ok", logindata                  
			  ).then(response=>{
				  console.log(response.data);        
          let result=response;
          if(result='NOID'){
            alert("아이디가 존재하지 않습다.")
          }				  		
          else if(result=='NOPWD'){
            alert("비밀번호가 틀립니다.")
          }		
          else{
            //location.href="http://localhost:8080/"
             alert("로그인 됐습니다!.")
          }
			  })
		  }
  }
}
</script>
