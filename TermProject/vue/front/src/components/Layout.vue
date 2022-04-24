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
      router-link to="/login" icon>
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
      <v-btn href="/cart" icon>
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
        <!-- <v-menu open-on-hover offset-y>
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
              v-for="(item, index) in items"
              :key="index"
              @click="z"
              href="/book"
            >
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>

          </v-card>
        </v-menu> -->
        <v-btn
         router-link to="/book">
          <span>도서목록</span>
        </v-btn>       
        <!-- <a href="/detail" class="v-btn">
          <span>상세보기</span>
        </a> -->
        <!-- <v-btn href="/pract">
          <span>공지사항</span>
        </v-btn> -->
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
        <!-- <v-card-text>
          <v-btn
            class="mx-4 white--text"
            icon
          >
            <v-icon size="24px">mdi-home</v-icon>
          </v-btn>
          <v-btn
            class="mx-4 white--text"
            icon
          >
            <v-icon size="24px">mdi-email</v-icon>
          </v-btn>
          <v-btn
            class="mx-4 white--text"
            icon
          >
            <v-icon size="24px">mdi-calendar</v-icon>
          </v-btn>
           <v-btn
            class="mx-4 white--text"
            icon
          >
            <v-icon size="24px">mdi-delete</v-icon>
          </v-btn>

        </v-card-text> -->

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
                Book:[]
                // activeBtn: 1,
            }
        }, 
                  
        methods:{          
          findBtn:function(){
            // const params = new URLSearchParams();
            // params.append('st',this.st);
            // params.append('searchtext',this.searchtext);                        
                   
            this.$axios.post("http://localhost:8080/find_ok", null, {params: {st:this.st, searchtext:this.searchtext}}
            ).then(response =>{
              console.log(response.data)
              this.Book=response.data;
              this.$EventBus.$emit('sentBook',this.Book)
            }).catch(function(ex){
              throw new Error(ex)
            })                  
          }        
        }
    }
</script>

<style  scoped>

</style>