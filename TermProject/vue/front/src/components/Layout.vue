<!-- eslint-disable -->
<template>
  <v-app id="inspire">
    <v-app-bar
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      app
      color="primary"
      dark
    >
<!--      <v-app-bar-nav-icon @click.stop="drawer = !drawer" />-->

      <v-toolbar-title
        style="width: 350px"
      >
         <router-link to="/" class="white--text" style="text-decoration: none"><v-icon>mdi-truck</v-icon>&nbsp;서울 도서대여 사이트</router-link>
      </v-toolbar-title>
        <v-form class="row" style="margin: 0px auto">       
         <v-select
          v-model="st"
          style="margin-top: 30px; width:1px;"
          :items="searchType"
          item-text="name"          
          item-value="value"          
          solo
        ></v-select>
       

        &nbsp;&nbsp;
        
      <v-text-field        
        v-model="searchtext"
        flat
        style="margin-top: 30px;"
        solo-inverted
        hide-details
        prepend-inner-icon="mdi-magnify"
        label="도서검색"        
      />            
      &nbsp;&nbsp;
      <v-btn id="findBtn"               
      router-link :to="'/find'"
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
      <v-btn icon>
        <v-badge
          content="2"
          value="2"
          color="green"
          overlap
        >
          <v-icon>mdi-bell</v-icon>
        </v-badge>
      </v-btn>
      <v-btn 
       icon
       router-link to="/bookrent">
        <v-badge
          content="2"
          value="2"
          color="green"
          overlap
        >
          <v-icon>mdi-cart</v-icon>
        </v-badge>
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
        <v-btn
         router-link to="/book">
          <span>도서목록</span>
        </v-btn>
         <v-btn 
         v-if="user_id!=null"
          router-link to="/bookrent">           
          <span>대여정보</span>
         </v-btn>   
         <v-col
        class="d-flex"
        cols="12"
        sm="1"
      >
       <v-select         
          depressed
           v-model="bt"          
          :items="branchtype"
          item-text="name"          
          item-value="value"          
          solo
        ></v-select>
      </v-col>
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
         <p>NAME: 김은태</p>
         <p>PHONE:000-6685-9812</p>
         <p>E-MAIL: Euntae.Kim@ta9.co.kr</p>
         
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
                searchType: [
                     {name: '전체', value:'TA'} ,
                     {name: '도서명', value:'T'} ,
                     {name: '저자', value:'A'} ,
                ],        
                st: 'TA',       
                searchtext: this.searchtext,
                Book:[],
                user_id:sessionStorage.getItem('user_id')
                
            }
        }, 
                  
        methods:{          
          findBtn:function(){                                      
            this.$axios.post("http://localhost:8080/find_ok", null, {params: {st:this.st, searchtext:this.searchtext}}              
            ).then(response =>{
              console.log(response.data)
              this.Book=response.data;
              this.$EventBus.$emit('sentBook',this.Book)
            }).catch(function(ex){
              throw new Error(ex)
            })                  
          },
          logout:function(){
             sessionStorage.removeItem('user_id')
            location.href="http://localhost:8080/"             
             alert("로그아웃 됐습니다!.")
          }        
        }
    }
</script>

<style  scoped>

</style>