<template>
             <v-form style="margin: 0px auto">
                    <div class="mx-3">
                      <v-icon color="black" size="30px">lock</v-icon>
                      <div class="mx-1">
                        <v-text-field
                          id:old_pwd
                          placeholder="현재 비밀번호"
                          type="password"
                          v-model="old_pwd"
                          required
                        ></v-text-field>
                        <v-btn @click="pwdok">
                            비밀번호 확인
                        </v-btn>
                      </div>
                    </div>
                    <div class="mx-3">
                      <v-icon color="black" size="30px">lock</v-icon>
                      <div class="mx-1">
                        <v-text-field
                          id:new_pwd
                          placeholder="새 비밀번호"
                          type="password"
                          v-model="user_pwd"
                          required
                        ></v-text-field>
                      </div>
                      <div class="mx-1">
                        <v-text-field
                          id:new_pwd
                          placeholder="새 비밀번호 확인"
                          type="password"
                          v-model="user_pwd"
                          required
                        ></v-text-field>
                      </div>
                      <v-btn
                       @click="pwdchange"
                        color="#2c4f91"
                        dark
                        large
                        block
                        type="button"
                        >변경하기</v-btn>
                    </div>
                    <div>
                      &emsp;  <router-link to="/signup">회원가입</router-link>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<router-link to>비밀번호 찾기</router-link>
                    </div>
                    <v-card-actions>
                    </v-card-actions>
                </v-form>
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
