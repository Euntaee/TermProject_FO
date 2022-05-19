<!-- eslint-disable -->
<template>
  <div>     
    <v-carousel hide-delimiters>
      <v-carousel-item
       :src="require('@/assets/bookmain.jpg')"
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
          <div class="display-2 white--text pl-5 pr-5 hidden-sm-only"><strong></strong></div><br/>
        </v-row>
      </v-carousel-item>     
    </v-carousel>
    
    <br>

    <div class="pl-4 pr-4 row">
      <div
       class="col-md-6 col-sm-6 col-xs-12"
      >
        <v-card>

          <v-img
            :src="require('@/assets/book3.jpg')"
            class="white--text align-center"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            height="400px"
          >
           <h1 class="text-center font-size">Top Picks</h1>
            <div class="text-center">
              <v-btn 
               router-link :to="{name:'Book',params:{mainsort: sort2}}"
              class="white--text " 
              outlined>book NOW
              </v-btn>              
            </div>
          </v-img>
        </v-card>
      </div>
      <div
        class="col-md-6 col-sm-6 col-xs-12"
      >
        <v-card>
          <v-img
            :src="require('@/assets/book3.jpg')"
            class="white--text align-center"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            height="400px"    
          >
            <h1 class="text-center font-size">New Arrivals</h1>
            <div class="text-center">
              <v-btn
                router-link :to="{name:'Book',params:{mainsort: sort}}"
                class="white--text" outlined
                >book NOW
                </v-btn>
            </div>
          </v-img>
        </v-card>
      </div>
    </div>
    <div class="pl-4 pr-4 row">
      <v-container>
      <v-row no-gutters>
        <v-col :cols="12">
          <v-card-text
            class=""
            tile
            outlined
          >
            <v-card-title class="subheading ">인기도서</v-card-title>
            <v-divider></v-divider>
            <div class="row">
              <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center"
               v-bind:key="h.id" v-for="h in hitBook"
              >
                <v-hover
                  v-slot:default="{ hover }"
                  open-delay="200"
                >
                  <v-card class="text-center"
                    :elevation="hover ? 16 : 2"
                     max-width="250"
                >
                  <v-img
                    class="white--text align-center"
                    height="300px"
                    width="250px"
                    :src="h.book_img"
                  >                    
                  </v-img>

                  <v-card-text class="text--primary text-center">
                    <div class="txt_line">{{h.book_title}}</div>
                    <div class="txt_line">{{h.book_author}}</div>
                  </v-card-text>

                  <div class="text-center">
                    <div>
                      총대여된 횟수: {{h.book_hit}}
                    </div>                   
                  </div>
                </v-card>
                </v-hover>
              </div>                          
            </div>
          </v-card-text>
        </v-col>
      </v-row>
    </v-container>           
    </div>
    <v-container>
      <v-row no-gutters>
        <v-col :cols="12">
          <v-card-text
            class=""
            tile
            outlined
          >
            <v-card-title class="subheading ">신간도서</v-card-title>
            <v-divider></v-divider>
            <div class="row">
              <div class="col-12 col-md-3 col-sm-6 col-xs-6 text-center"
              v-bind:key="n.id" v-for="n in newBook"
              >
                <v-hover
                  v-slot:default="{ hover }"
                  open-delay="200"
                >
                  <v-card class="text-center"
                    :elevation="hover ? 16 : 2"
                     max-width="250"
                >
                  <v-img
                    class="white--text align-end margin:0px auto"
                    height="300px"
                    width="250px"
                    object-fit: cover
                    :src="n.book_img"
                  >                    
                  </v-img>

                  <v-card-text class="text--primary text-center">
                    <div class="txt_line">{{n.book_title}}</div>
                    <div class="txt_line">{{n.book_author}}</div>
                  </v-card-text>

                  <div class="text-center">
                    <div>
                      등록일: {{n.book_regdate}}
                    </div>
                    <!-- <v-btn              
                      router-link :to="{name:'Detail',params:{book_no: n.book_no}}"        
                      class="ma-2"
                      outlined
                      color="info"
                    >
                      Book NOW
                    </v-btn> -->
                  </div>
                </v-card>
                </v-hover>
              </div>              
            </div>
          </v-card-text>
        </v-col>
      </v-row>
    </v-container>
    <br>
  </div>
</template>

<script>
/*eslint-disable*/
   export default {
        data () {
            return {             
                activeBtn: 1,             
                sort: '2',
                sort2:'1',
                newBook:[],
                hitBook:[]                
            }
        },
        mounted:function(){
          this.getData();
          this.getData2();
        },
        methods:{
          getData:function(){
            this.$axios.post("http://localhost:8080/bookmain1",null,{
              params:{
                sort:this.sort
              }
            }).then(response =>{
              console.log(response.data);
              this.hitBook=response.data;
            })
          },
           getData2:function(){
            this.$axios.post("http://localhost:8080/bookmain2",null,{
               params:{
                sort:this.sort2
              }
            }).then(response =>{
              console.log(response.data);
              this.newBook=response.data;
            })
          }
        }
    }
</script>

<style scoped>
.txt_line {
   width: 210px;
   padding: 0 5px;
   overflow: hidden;
   text-overflow: ellipsis;
   white-space: nowrap;
 }
</style>