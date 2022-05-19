<!-- eslint-disable -->
<template>
  <v-app id="inspire">
    <v-app-bar
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      app
      color="primary"
      dark
    >
      <v-toolbar-title
        style="width: 350px"
      >
         <router-link to="/" class="white--text" style="text-decoration: none"><v-icon>mdi-truck</v-icon>&nbsp;서울 도서대여 사이트</router-link>
      </v-toolbar-title>
        <v-form class="row" style="margin: 0px auto">    
         
        <v-select
          v-model="bt"
          :items="branchType"
          selected="종로도서관"
          item-text="name"          
          item-value="value"
          style="margin-top: 50px; width:50px;"
        ></v-select>
    

         <v-select
          v-model="st"
          style="margin-top: 30px; width:1px;"
          selected="TA"
          :items="searchType"
          item-text="name"          
          item-value="value"          
          solo
        ></v-select>
               
        &nbsp;&nbsp;
        
      <v-text-field        
        v-model.trim="searchtext"
        flat
        style="margin-top: 30px;"
        solo-inverted
        hide-details      
        prepend-inner-icon="mdi-magnify"
        label="도서검색"        
        focusable
        @keyup.enter.prevent="findBtn"
      />            
      &nbsp;&nbsp;
      <v-btn id="findBtn"                    
      @click="findBtn"      
      style="margin-top: 35px;">
        검색
      </v-btn>
      </v-form>      
      <v-spacer />
      
      <v-btn                 
      v-if="user_id==null"
      router-link to="/login" icon>
        <v-icon>mdi-account-circle</v-icon>
      </v-btn>
      <v-btn v-if="user_id!=null"
      @click="logout"
      >
      <span>로그아웃</span>
      </v-btn>
      <v-btn v-if="user_id!=null"
       router-link to="/mypage" icon
      >
      <v-icon>mdi-account-circle</v-icon>
      </v-btn>                   
      <v-btn 
       icon
       router-link to="/rent">        
          <v-icon>mdi-cart</v-icon>
      </v-btn>
    </v-app-bar>
    <v-main>
      <v-bottom-navigation
       
        color="#2d3753"
        horizontal
      >
       <v-btn
         router-link to="/">
          <span>Home</span>
        </v-btn>                           
          <v-menu open-on-hover offset-y>
            <template v-slot:activator="{ on }">
              <v-btn v-on="on">
                <span>도서목록</span>
              </v-btn>
            </template>
            <v-card
              class="mx-auto"
              max-width="344"
              outlined
            >  
              <v-list-item
                v-for="(item, index) in branchData"
                :key="index"               
                router-link :to="{name:'Book', query:{branch_code: item.branch_code, branch_name: item.branch_name, branch_addr: item.branch_addr}}"               
              >
                <v-list-item-title>{{ item.branch_name }}</v-list-item-title>                       
              </v-list-item>  
            </v-card>
        </v-menu>
        <v-btn 
          v-if="user_id!=null"
          router-link to="/rent">           
          <span>대여정보</span>
        </v-btn>   
      </v-bottom-navigation>
    </v-main>
      <router-view/>
    <v-footer
      :padless="true"
    >
      <v-card
        flat
        tile
        width="100%"
        class="secondary white--text text-center"
      >  
        <v-card-text class="white--text pt-0">
         <p>NAME: </p>
         <p>PHONE:000-6666-6666</p>
         <p>E-MAIL: ket@naver.co</p>
         
        </v-card-text>

        <v-divider></v-divider>

        <v-card-text class="white--text">
          {{ new Date().getFullYear() }} <strong>OJT TERMPROJECT</strong>
        </v-card-text>
      </v-card>
    </v-footer>
  </v-app>
</template>

<script>
/*eslint-disable*/

export default {
          data () {
            return {        
                branchType: [
                     {name: '종로도서관', value:'종로도서관'} ,
                     {name: '정독도서관', value:'정독도서관'} ,
                     {name: '강남도서관', value:'강남도서관'} ,
                     {name: '강서도서관', value:'강서도서관'} ,
                     {name: '강동도서관', value:'강동도서관'} ,
                ],                                     
                searchType: [
                     {name: '전체', value:'TA'} ,
                     {name: '도서명', value:'T'} ,
                     {name: '저자', value:'A'} ,
                ],        
                st: 'TA',
                bt: '종로도서관' ,
                searchtext: '',
                Book:[],
                user_id:sessionStorage.getItem('user_id'),
                branchData: [],
                branch: ''                    
            }
        },
        mounted: function(){
          this.getBranchData();
        },
        methods:{          
          findBtn:function(){  
            this.$router.push({name:'find', params:{searchtext:this.searchtext}}  )
            this.$axios.post("http://localhost:8080/find_ok", null, {params: {bt:this.bt, st:this.st, searchtext:this.searchtext }}              
            ).then(response =>{
              console.log(response.data)
              this.Book=response.data;
              this.$EventBus.$emit('sentBook',this.Book)
              this.st=this.st
              this.bt=this.bt
              this.searchtext=this.searchtext
            }).catch(function(ex){                    
            })                        
          },
          logout:function(){
            sessionStorage.removeItem('user_id')
            location.href="http://localhost:8080/"             
             alert("로그아웃 됐습니다!.")
          },
          getBranchData:function(){
            this.$axios.get("http://localhost:8080/branchData")
            .then(response =>{
                console.log(response.data);
                this.branchData=response.data;                 
              })            
          },
          refresh: function() {
            this.$router.go();
          }
        }
    }
</script>
<style  scoped>

</style>