<!-- eslint-disable -->
<template>
  <div>
    <v-container>
      <div class="row">      
        <div class="col-md-5 col-sm-5 col-xs-12">
          <v-carousel>
            <v-carousel-item
              :src="Book.book_img"
            >
            </v-carousel-item>           
          </v-carousel>
        </div>
        <div class="col-md-7 col-sm-7 col-xs-12">
          <v-breadcrumbs class="pb-0" :items="breadcrums"></v-breadcrumbs>
          <div class="pl-6">
            <p class="display-1 mb-0">제목:{{Book.book_title}}</p> 
            <br>
            <br>
             <h3>책 소개</h3>
             <br>             
             <p class="subtitle-1 font-weight-thin">
              {{Book.book_content}}
            </p>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <!-- :disabled="!result" -->
            <br>
            <v-btn class="primary white--text" outlined tile dense
            @click="rentData" v-if="result"
            >            
            <v-icon>mdi-book</v-icon> 
            대여하기
            </v-btn>

            <v-btn class="primary white--text" outlined tile dense
            @click="rentData2" v-if="!result"
            >            
            <v-icon>mdi-book</v-icon> 
            대여하기
            </v-btn>
            
            <v-btn class="ml-4" outlined tile>책 담기 OR 목록 보러 가기(미정)</v-btn>

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
            <div class="row text-center">
              <div class="col-md-2 col-sm-4 col-xs-12 text-center">
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
                      :src="require('../assets/img/home/deal1.jpg')"
                    >
                      <v-card-title>Bags & Purses </v-card-title>
                    </v-img>

                    <v-card-text class="text--primary text-center">
                      <div>Upto 60% + Extra 10%</div>
                      <div>Baggit, Zara, Fossil</div>
                    </v-card-text>

                    <div class="text-center">
                      <v-btn
                        class="ma-2"
                        outlined
                        color="info"
                      >
                        Explore
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
            book_no: Object
          },
        data () {
          return {
            Book:{},                    
            user_id:sessionStorage.getItem('user_id'),
            result:''
          }
        },         
        mounted:function(){
          this.getData();
          this.rentRule();         
        },
        methods:{
            getData:function(props){           
           this.$axios.get('http://localhost:8080/detail_ok',{params:{book_no:this.book_no}})
            .then(response =>{
                console.log(response.data);
                this.Book=response.data;
            })
            },
            rentData:function(){
              this.$axios.post('http://localhost:8080/rent_info',null,{params:{book_no:this.book_no ,user_id:this.user_id, }})
              .then(response => {
              console.log(response)              
                alert('대여가 완료 되었습니다!!') 
                window.location.href="/bookrent"             
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
        rentRule:function(){
              this.$axios.post('http://localhost:8080/rent_rule',null,{params:{user_id:this.user_id }})
              .then(response =>{
                console.log(response)
                this.result=response.data
              })
            }
    }
  }
</script>