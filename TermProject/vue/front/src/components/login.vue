<template>
  <v-app id="app">
    <div class="protected" v-if="loginsuccess">
      <v-alert
        type="success"
    >로그인 성공</v-alert>
    </div>
     <div class="unprotected" v-else-if="loginError">
      <v-alert
        type="success"
    >로그인 실패</v-alert>
    </div>
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
                  <v-form @submit.prevent="login()" style="width: 400px; height: 300px; margin-top:20%">
                    <div class="mx-3">
                      <v-icon color="black" size="30px">person</v-icon>
                      <div class="mx-1">
                        <v-text-field
                          placeholder="userId"
                          v-model="user"
                          required
                        ></v-text-field>
                      </div>
                    </div>
                    <div class="mx-3">
                      <v-icon color="black" size="30px">lock</v-icon>
                      <div class="mx-1">
                        <v-text-field
                          placeholder="userPassword"
                          type="password"
                          v-model="password"
                          required
                        ></v-text-field>
                      </div>
                    </div>
                    <div>
                      &emsp; <a href="/signup">회원가입</a>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<a>비밀번호 찾기</a>
                    </div>
                    <v-card-actions>
                      <v-btn
                        color="#2c4f91"
                        dark
                        large
                        block
                        type="submit"
                        >Login</v-btn
                      >
                      <p v-if="error" class="error">bad login information</p>
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
  name: 'login',
  data () {
   return {
          loginSuccess: true,
          loginError: false,
          user: '',
          password: '',
          error: false
      }
  },
  methods: {
      async login() {
          try {
              const result = await this.$axios.get('/login', {
                  auth: {
                      username: this.user,
                      password: this.password
                  }
              });
              if (result.status === 200) {
                  this.loginSuccess = true
              }
        } catch (err) {
              this.loginError = true;
              throw new Error(err)
              }
      }
   }
}
</script>
