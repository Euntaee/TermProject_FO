<!--연습 페이지-->
<template>
          <v-form ref="form" lazy-validation>
            <v-row>
              <v-col cols="12">
                <v-text-field v-model="user_id" label="아이디*" :rules="user_id_rule" required></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field v-model="user_nm" label="이름*" :rules="user_nm_rule" required></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field v-model="user_pw" label="비밀번호*" type="password" :rules="user_pw_rule"></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field v-model="user_pw_chk" label="비밀번호 확인*" type="password" :rules="user_pw_rule2"></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-select
                v-model="user_auth"
                label="권한*"
                :items="authList"
                item-text="name"
                item-value="value"
                return-object
                :rules="user_auth_rule"
                >
                </v-select>
              </v-col>
              <v-col cols="12">
                <v-text-field v-model="user_desc" label="설명" :rules="user_desc_rule"></v-text-field>
              </v-col>
            </v-row>
                      <small class="red--text">*표시는 반드시 입력해야하는 항목입니다.</small>
          <v-spacer></v-spacer>
          <v-btn class="ma-2" raised depressed color="primary">
            <v-icon left>mdi-check</v-icon> 저장
          </v-btn>
          <v-btn class="ma-2" raised depressed @click="close">
            <v-icon left>mdi-close</v-icon> 닫기
          </v-btn>
          </v-form>

</template>
<script>
/* eslint-disable */
export default {
 
  data () {
    return {
      authList: [
        { name: '관리자', value: 'A'},
        { name: '일반 사용자', value: 'M'}
      ],
      user_id: '',
      user_id_rule: [
        v => !!v || '아이디는 필수 입력사항입니다.',
        v => /^[a-zA-Z0-9]*$/.test(v) || '아이디는 영문+숫자만 입력 가능합니다.',
        v => !( v && v.length >= 15) || '아이디는 15자 이상 입력할 수 없습니다.',      
      ],
      user_nm: '',
      user_nm_rule: [
        v => !!v || '이름은 필수 입력사항입니다.',
        v => !(v && v.length >= 30) || '이름은 30자 이상 입력할 수 없습니다.',
        v => !/[~!@#$%^&*()_+|<>?:{}]/.test(v) || '이름에는 특수문자를 사용할 수 없습니다.'
      ],
      user_pw: '',
      user_pw_chk: '',
      user_pw_rule: [
        v => this.state === 'ins' ? !!v || '패스워드는 필수 입력사항입니다.' : true,
        v => !(v && v.length >= 30) || '패스워드는 30자 이상 입력할 수 없습니다.',
      ],
      user_pw_rule2: [
        v => this.state === 'ins' ? !!v || '패스워드는 필수 입력사항입니다.' : true,
        v => !(v && v.length >= 30) || '패스워드는 30자 이상 입력할 수 없습니다.',
        v => v === this.user_pw || '패스워드가 일치하지 않습니다.'
      ],
      user_auth: '',
      user_auth_rule: [
        v => !!v || '권한은 필수 선택 사항입니다.'
      ],
      user_desc: '',
      user_desc_rule: [
        v => !(v && v.length >= 100) || '설명은 100자 이상 입력할 수 업습니다.'
      ]
    }
  },
  methods: {   
    // close() {
    //   this.dialog = false;
    //   this.state = 'ins';
    //   this.$refs.form.reset();
    //   this.$emit('set_info', null);
    // }
  }
}
</script>

<style>
</style>