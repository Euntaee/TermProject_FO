<!-- eslint-disable -->
<template>
          <div>          
           <v-container style="width:400px; margin-bottom:150px;">
              <div style="float:right">
                <v-btn @click="pwdok" style="margin: 100px 0px 0px 400px">
                   비밀번호 확인
                </v-btn>  
              </div>                          
             <v-form style="margin: 0px auto">               
                    <div class="mx-3">                                           
                      <div class>                      
                        <textbox>
                          </textbox>             
                             <v-icon color="black" size="30px">lock</v-icon>

                        <v-text-field
                          v-if="old_pwd!=null"
                          id:old_pwd
                          placeholder="주소"
                          type="textbox"
                          v-model="old_pwd"
                          required
                        >
                        </v-text-field>                        
                      </div>
                    </div>
                    <div class="mx-3">
                       <div class="mx-1">                        
                        <v-text-field                                                
                          id:new_pwd1
                          placeholder="전화번호"
                          type="textbox"
                          v-model="user_pwd"
                          required
                        ></v-text-field>
                      </div>
                       <div class="mx-1">                        
                        <v-text-field                                                
                          id:new_pwd1
                          placeholder="새 비밀번호"
                          type="password"
                          v-model="user_pwd"
                          required
                        ></v-text-field>
                      </div>
                      <div class="mx-1">                        
                        <v-text-field                                                
                          id:new_pwd1
                          placeholder="새 비밀번호"
                          type="password"
                          v-model="user_pwd"
                          required
                        ></v-text-field>
                      </div>
                      <div class="mx-1">
                        <v-text-field
                          id:new_pwd2
                          placeholder="새 비밀번호 확인"
                          type="password"                          
                          required
                        ></v-text-field>
                      </div>
                      <v-btn
                        @click="pwdchange"
                        style="width:250px margin-bottom:200px"
                        color="#2c4f91"
                        dark
                        large
                        block
                        type="button"
                        >변경하기</v-btn>
                    </div>
                    <v-card-actions>
                    </v-card-actions>
                </v-form>
           </v-container>
         </div>
</template>

<script>
export default {
  data () {
    return {
      user_pwd: '',
      user_id: sessionStorage.getItem('user_id')
    }
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
        } else {
          alert('비밀번호가 확인 되었습니다!')
        }
      })
    },
    pwdchange: function () {
      this.$axios.post('http://localhost:8080/pwd_change', null, {
        params: {
          user_pwd: this.user_pwd, user_id: this.user_id
        }
      }).then(response => {
        console.log(response)
        alert('비밀번호 변경이 완료 되었습니다!')
      })
    }
  }
}
</script>

<style>

</style>
