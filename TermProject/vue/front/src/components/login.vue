<!--eslint-disable-->
<template>
  <v-app id="app">
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
                          :rules="user_id_rule"
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
                          :rules="user_pwd_rule"
                          @keyup.enter="login"
                          required
                        ></v-text-field>
                      </div>
                    </div>
                    <div>
                      <!-- &emsp;  <router-link to="/signup">회원가입</router-link>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; -->
                      
                      <v-row justify-center style="margin-top:10px">
                         <v-btn
                               text
                               router-link to="/signup"
                               style="margin-left:20px; font-weight:bold"
                             >
                             회원가입
                         </v-btn>

                         <v-dialog
                           v-model="dialog"
                           persistent
                           max-width="600px"
                         >
                           <template v-slot:activator="{ on, attrs }">
                             <v-btn
                               text
                               v-bind="attrs"
                               v-on="on"
                               style="margin-left:150px; font-weight:bold"
                             >
                               비밀번호 찾기
                             </v-btn>
                           </template>
                           <v-card>
                             <v-card-title>
                               <span class="text-h5">User Profile</span>
                             </v-card-title>
                             <v-card-text>
                               <v-container>
                                 <v-row>
                                   <v-col cols="12">
                                     <v-text-field
                                       v-model="userId"
                                       label="아이디*"
                                       required
                                     ></v-text-field>
                                   </v-col>
                                   <v-col
                                     cols="12"
                                   >
                                     <v-select
                                       :items="['나의 별명은?', '가장 좋아하는 색상은', '가장 좋아하는 음식은?', '가장 좋아하는 영화는?','가장 좋아하는 노래는?']"
                                       label="비밀번호 힌트 질문*"
                                       required
                                     ></v-select>
                                   </v-col>     
                                   <v-col cols="12">
                                     <v-text-field
                                       v-model="userAnswer"
                                       label="비밀번호 힌트 답*"                                       
                                     ></v-text-field>
                                   </v-col>                            
                                 </v-row>
                               </v-container>
                               <small>*indicates required field</small>
                             </v-card-text>
                             <v-card-actions>
                               <v-spacer></v-spacer>
                               <v-btn
                                 color="blue darken-1"
                                 text
                                 @click="dialog = false"
                               >
                                 Close
                               </v-btn>

                               <v-btn
                                 color="blue darken-1"
                                 text
                                 @click="pwdFind"
                               >
                                 찾기
                               </v-btn>
                             </v-card-actions>
                           </v-card>
                         </v-dialog>
                       </v-row>
                    </div>
                      

                       


                    <v-card-actions style="margin-top:30px">
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
          user_pwd: '',
          dialog: false,
          userId: '',
          userAnswer: ''
      }
  },
  methods: {
     login:function(){       
      const params = new URLSearchParams();
      params.append('user_id',this.user_id);
      params.append('user_pwd',this.user_pwd);
			  this.$axios.post("http://localhost:8080/login_ok", params               
			  ).then(response=>{
				  console.log(response)              
           let result=response.data 
         if(result=='NOID'){
            alert("아이디를 다시 입력해주세요.")
          }				  		
          else if(result=='NOPWD'){
            alert("비밀번호가 틀립니다.")
          }		
          else{
            this.$router.replace("/")
             alert("로그인 됐습니다!.")
            sessionStorage.setItem('user_id', this.user_id)
          }
			  })
        
		  },

      pwdFind:function(){
        this.$axios.post("http://localhost:8080/userFind", null,{
          params:{
            userId:this.userId, userAnswer:this.userAnswer
          }
        }).then(response => {
          console.log(response.data)
          let result1 = response.data
          if(result1==true)  {
              alert("임시 비밀번호는 12345678 입니다!! \n바로 비밀번호를 변경해주세요!!!")
              this.dialog = false;
          } else {
              alert("잘못된 정보 입니다! 다시 확인해주세요")
          }
        }).catch(err => {     
          console.log(err) 
        })
      },    
  }
}
</script>
