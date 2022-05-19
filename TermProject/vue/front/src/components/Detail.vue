<!-- eslint-disable -->
<template>
  <div>
    <v-container>
      <div class="row">      
        <div class="col-md-3 col-sm-3 col-xs-12" style="width:400px; height:780px;">
          <v-img
              :src="Book.book_img"
              style="width:400px"
            >
          </v-img>
        </div>
        <div class="col-md-9 col-sm-9 col-xs-12">
          <v-breadcrumbs class="pb-0" :items="breadcrums"></v-breadcrumbs>
          <div class="pl-6">
            <p class="display-1 mb-0">제목:{{Book.book_title}}</p>             
             <h3>책 소개</h3>
             <br>             
             <p class="subtitle-1 font-weight-thin">
             {{Book.book_content}}
            </p>
            <br><br><br>
            <br><br><br>
            <br><br><br>
            <br><br>
            <br><br>            
            <!-- :disabled="!result" -->
            <br>
            <v-btn class="primary white--text" outlined tile dense 
            @click="rentData" v-if="result && stock && rented"
            >            
            <v-icon>mdi-book</v-icon> 
            대여예약
            </v-btn>

            <v-btn class="primary white--text" outlined tile dense
            @click="rentData2" v-else-if="!result && stock && rented"
            >            
            <v-icon>mdi-book</v-icon> 
            대여예약
            </v-btn>

             <v-btn class="primary white--text" outlined tile dense
            @click="rentData3" v-else-if="!stock && result && rented"
            >   
             <v-icon>mdi-book</v-icon> 
            대여예약
            </v-btn>
            
            <v-btn class="primary white--text" outlined tile dense
            @click="rentData4" v-else-if="!rented && stock && result"
            >         
            <v-icon>mdi-book</v-icon> 
            대여예약
            </v-btn>                         
            <v-btn class="ml-4" outlined tile @click="$router.go(-1)">뒤로가기</v-btn>

          </div>
      </div>
      </div>
      <div class="row">
        <div class="col-sm-12 col-xs-12 col-md-12">
          <v-tabs>
            <v-tab >작가</v-tab>
            <v-tab >장르</v-tab>
            <v-tab>출판사</v-tab>
            <v-tab-item>
              <p class="pt-10 subtitle-1 font-weight-thin">
               작가: {{Book.book_author}}
              </p>
            </v-tab-item>
            <v-tab-item>
              <p class="pt-10 subtitle-1 font-weight-thin">
               장르: {{Book.book_genre}}
              </p>
            </v-tab-item>
            <v-tab-item>
              <p class="pt-10 subtitle-1 font-weight-thin">
               출판사: {{Book.book_publish}}
              </p>
            </v-tab-item>          
          </v-tabs>
          <v-card-text
            class="pa-0 pt-4"
            tile
            outlined
          >
            <p class="subtitle-1 font-weight-light pt-3 text-center">비슷한 책 추천</p>
            <v-divider></v-divider>
            <div class="row text-center" >
              <div class="col-md-2 col-sm-4 col-xs-12 text-center" v-for="vo in randData" v-bind:key="vo.id">
                <v-hover
                  v-slot:default="{ hover }"
                  open-delay="200"
                >
                  <v-card
                    :elevation="hover ? 16 : 2"
                  >
                    <v-img
                      class="white--text align-end"
                      height="200px"
                      v-bind:src="vo.book_img"
                    >
                    </v-img>

                    <v-card-text class="text--primary text-center">
                      <div class="txt_line" style="font-weight:bold">{{vo.book_title}}</div>
                      <div class="txt_line">{{vo.book_author}}</div>
                    </v-card-text>

                    <div class="text-center">
                      <v-btn
                        class="ma-2"
                        outlined
                        color="info"
                        router-link :to="{name:'Detail',query:{book_no: vo.book_no, branch_code: vo.branch_code, book_genre: vo.book_genre}}"
                      >대여하러 가기
                      </v-btn>
                    </div>
                  </v-card>
                </v-hover>
              </div>                                        
           </div>
          </v-card-text>          
        </div>        
      </div>
    </v-container>    
  </div>
</template>
<script>
/* eslint-disable */

    export default {         
    props:{
            book_no: Object,
            branch_code: Object,
            book_genre: Object
          },
        data () {
          return {
            Book:{},                    
            user_id:sessionStorage.getItem('user_id'),
            result:'',
            stock: '',
            rented: '',
            suspen: '',
            book_genre: '',
            randData: [],
          }
        },         
        mounted:function(){
          this.getData();
          this.rentRule(); 
          this.rentRule2(); 
          this.duplicateBook();  
          this.bookRandData();    
          this.suspensionUser(); 
        },
        methods:{
            getData:function(props){           
           this.$axios.get('http://localhost:8080/detail_ok',{params:{book_no: this.$route.query.book_no}})
            .then(response =>{
                console.log(response.data);
                this.Book=response.data;
            })
            },
            rentData:function(props){
              this.$axios.post('http://localhost:8080/rent_info',null,{params:{book_no:this.$route.query.book_no ,user_id:this.user_id, branch_code: this.$route.query.branch_code }})
              .then(response => {
              console.log(response)              
                alert('대여가 완료 되었습니다!!') 
                window.location.href="/rent"                
            })            
        },
        rentData2:function(){
          if(this.user_id==null){
            alert('로그인 이후 이용이 가능합니다!')
            window.location.href="/login"
          }else{
                alert('도서는 총 3권만 대여할 수 있습니다!!')                
          } 
        },
        rentData3:function(){
          if(this.user_id==null){
            alert('로그인 이후 이용이 가능합니다!')
            window.location.href="/login"
          }else{
                alert('도서 재고가 없습니다!!')                
          } 
        },
        rentData4:function(){
          if(this.user_id==null){
            alert('로그인 이후 이용이 가능합니다!')
            window.location.href="/login"
          }else{
                alert('이미 대여하신 도서입니다!!')                
          } 
        },
        rentRule:function(){
              this.$axios.post('http://localhost:8080/rent_rule',null,{params:{user_id:this.user_id }})
              .then(response =>{
                console.log(response)
                this.result=response.data
              })
            },
        rentRule2:function (props) {
            this.$axios.post('http://localhost:8080/bookStock', null, {
              params: {
                  book_no: this.$route.query.book_no, branch_code: this.$route.query.branch_code
              }
            }).then(response => {
              console.log(response.data)
              this.stock=response.data
            })
        },
        duplicateBook:function (props) {
            this.$axios.post('http://localhost:8080/duplicateBook', null, {
              params: {
                  book_no: this.$route.query.book_no, branch_code: this.$route.query.branch_code, user_id: this.user_id
              }
            }).then(response => {
              console.log(response.data)
              this.rented=response.data
            })
        },
        bookRandData:function(props) {          
          this.$axios.post('http://localhost:8080/book/selectRandData', null, {
            params: {
                branch_code: this.$route.query.branch_code, book_genre: this.$route.query.book_genre
            }
          }).then(response => {
             console.log(response.data)
             this.randData=response.data
          })
        },
    }
  }
</script>
<style>
  .txt_line {
   width: 180px;
   padding: 0 5px;
   overflow: hidden;
   text-overflow: ellipsis;
   white-space: nowrap;
 }
</style>