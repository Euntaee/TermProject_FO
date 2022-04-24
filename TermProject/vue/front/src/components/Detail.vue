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
            <!-- <v-card-actions class="pa-0">
              <p class="headline font-weight-light pt-3">$65.00 <del style="" class="subtitle-1 font-weight-thin">$80.00</del></p>
              <v-spacer></v-spacer>
              <v-rating v-model="rating" class="" background-color="warning lighten-3"
                        color="warning" dense></v-rating>
              <span class="body-2	font-weight-thin"> 25 REVIEWS</span>
            </v-card-actions> -->
             <h3>책 소개</h3>
             <br>
             
             <p class="subtitle-1 font-weight-thin">
              {{Book.book_content}}
            </p>
          <!--  <p class="title">SIZE</p>
            <v-radio-group v-model="row" row>
              <v-radio label="XS" value="XS"></v-radio>
              <v-radio label="S" value="s"></v-radio>
              <v-radio label="M" value="m"></v-radio>
              <v-radio label="L" value="l"></v-radio>
              <v-radio label="XL" value="xl"></v-radio>
            </v-radio-group>
            <p class="title">ITEMS</p> -->

            <!-- <v-text-field
                outlined
                style="width:100px"
                :value="1"
                dense
            ></v-text-field> -->
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <v-btn class="primary white--text" outlined tile dense><v-icon>mdi-book</v-icon> 대여하기</v-btn>
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
            <!-- <v-tab-item>
              <v-list
                three-line="true"
                avatar="true"
              >
                <v-list-item-group v-model="item" color="primary">
                  <v-list-item
                    v-for="(item, i) in items"
                    :key="i"
                    inactive="true"
                  >
                    <v-list-item-avatar>
                      <v-img :src="item.avatar"></v-img>
                    </v-list-item-avatar>
                    <v-list-item-content>
                      <v-list-item-title v-html="item.title"></v-list-item-title><v-rating v-model="rating" class="" background-color="warning lighten-3"
                                                                                           color="warning" dense></v-rating>
                      <v-list-item-subtitle v-html="item.subtitle"></v-list-item-subtitle>
                    </v-list-item-content>
                  </v-list-item>
                </v-list-item-group>
              </v-list>
            </v-tab-item> -->
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
//  const params = (new URL(document.location)).searchParams;
//  const book_no = params.get('book_no');
    export default {      
    props:{
            book_no: Object
          },
        data () {
          return {
            Book:{},        
            book_no:this.book_no   
          }
        }, 
        // created() {
        //   this.book_no
        // },
        // created() {
        //    this.$axios.get('http://localhost:8080/detail_ok',{params:{book_no:this.book_no}})
        //     .then(response =>{
        //         console.log(response.data);
        //         this.Book=response.data;
        //     })
        // },      
        mounted:function(){
          this.getData();
        },
        methods:{
            getData:function(props){           
           this.$axios.get('http://localhost:8080/detail_ok',{params:{book_no:this.book_no}})
            .then(response =>{
                console.log(response.data);
                this.Book=response.data;
            })
            }
        }                            
    }
</script>
