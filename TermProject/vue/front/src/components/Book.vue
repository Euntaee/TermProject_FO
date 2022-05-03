<!-- eslint-disable -->
<template>
<div>
    <v-container>
      <div class="row">
        <div
         class="col-md-3 col-sm-3 col-xs-12"
        >
          <v-card outlined style="height:965px; width:300px;">
            <v-card-title>카테고리</v-card-title>
            <v-divider></v-divider>
            <template>
             
            <div v-bind:key="vo.id" v-for="vo in genre">
               <a class="v-list-item--default v-list-item v-list-item--link theme--light">
              <div class="v-list-item__content">                                          
                <div @click="genredata">{{vo.book_genre}}</div>
              </div>
              <div>                                
              </div>
               </a>
            </div>
            </template>

          </v-card>
        </div>
        <div
          class="col-md-9 col-sm-9 col-xs-12"
        >

          <v-breadcrumbs class="pb-0" :items="breadcrums"></v-breadcrumbs>

          <v-row dense>
            <v-col cols="12" sm="8" class="pl-6 pt-6">
              <small>Showing 1-12 of 200 products</small>
            </v-col>
            <v-col cols="12" sm="4">
              <v-select 
              class="pa-0" 
              v-model="sort" 
              :items="options" 
              item-text="name"
              item-value="value"
              style="margin-bottom: -20px;" 
              @change="change"
              outlined dense>             
              </v-select>
            </v-col>
          </v-row>

          <v-divider></v-divider>

          <div class="row text-center">
            <div class="col-md-3 col-sm-6 col-xs-12" v-bind:key="pro.id" v-for="pro in Book">              
              <v-hover v-slot:default="{ hover }">
                <v-card
                  class="mx"
                  color="grey lighten-4"
                  max-width="200"
                >
                  <v-img
                    class="white--text align-end"
                    :aspect-ratio="16/9"
                    height="200px"
                    :src="pro.book_img"
                  >
                    <!-- <v-card-title>{{pro.type}} </v-card-title> -->
                    <v-expand-transition>
                      <div
                        v-if="hover"
                        class="d-flex transition-fast-in-fast-out white darken-2 v-card--reveal display-3 white--text"
                        style="height: 100%;"
                      >
                        <v-btn v-if="hover" router-link :to="{name:'Detail',params:{book_no: pro.book_no, branch_code: pro.branch_code}}"  outlined>대여하러 가기</v-btn>
                 </div>
                         </v-expand-transition>
                  </v-img>
                  <v-card-text class="text--primary">
                    <div class="txt_line"><router-link :to="{name:'Detail',params:{book_no: pro.book_no, branch_code: pro.branch_code}}" style="text-decoration: none">{{pro.book_title}}</router-link></div>
                    <div class="txt_line">{{pro.book_author}}</div>
                  </v-card-text>
                </v-card>
              </v-hover>
            </div>
          </div>
          <div class="text-center mt-12">
            <v-pagination 
              v-model="pagination.curpage"
              :length="pagination.totalpage"
              :total-visible="pagination.totalpage"
              @input="getData"
            ></v-pagination>          
          </div>
        </div>
      </div>
    </v-container>
  </div>
</template>

<script>
/* eslint-disable */
export default {         
        props:{
            mainsort: Object,
            branch_code: Object
          },   
        data () {
          return {
            range: [0, 10000],
            options: [
                     {name: '인기순', value:'1'} ,
                     {name: '최신순', value:'2'} ,                     
            ],                              
            pagination: {                             
            curpage:1,
            totalpage: '',     
            visible:7
            },          
            Book:[],       
            genre:[],
            sort: '1',             
            cate: '문학'
          }
        },                         
         mounted:function(){
            this.getData();
            this.getGenre();
            this.getTotalPage();
        },
        methods:{
            getData:function(branch_code){
            this.$axios.post("http://localhost:8080/rest_prac",null,{
              params:{
                  page:this.pagination.curpage,  
                  sort:this.sort,
                  cate:this.cate,
                  branchCode:this.branch_code           
              }
            })      
            .then(response =>{
                console.log(response.data);
                this.Book=response.data;                               
                  // this.pagination.totalpage=this.Book[0].totalpage;
            }).catch(function(ex){
              throw new Error(ex)
            })       
            },
            getTotalPage:function(){
              this.$axios.post("http://localhost:8080/totalpage",null,{
                params: {
                  sort:this.sort,
                  cate:this.cate 
                }
              }).then(response =>{
                console.log(response.data);
                this.pagination.totalpage=response.data;
              })
            },
            getGenre:function(){
              this.$axios.get("http://localhost:8080/genre")              
            .then(response =>{
                console.log(response.data);
                this.genre=response.data;
            })
            },
            change:function(branch_code){
              this.$axios.post("http://localhost:8080/rest_prac",null,{params:{
                sort:this.sort,
                page:this.pagination.curpage,
                cate:this.cate,
                branchCode:this.branch_code                  
                }
              }).then(response =>{
                console.log(response.data);
                this.Book=response.data;
                sort=this.sort                          
            })              
            },
             genredata:function(event){    
              const cate2= event.currentTarget.textContent                        
              this.$axios.post("http://localhost:8080/rest_prac",null,{
                params:{
                sort:this.sort,
                page:this.pagination.curpage,
                cate: cate2,
                branchCode:this.branch_code                
                }
              }).then(response =>{
                console.log(response.data);
                this.Book=response.data;
                this.cate=cate2
                                                        
            })              
            }             
        }
    }
</script>
<style>
 .v-card--reveal {
    align-items: center;
    bottom: 0;
    justify-content: center;
    opacity: .8;
    position: absolute;
    width: 100%;
  }
  
 .txt_line {
   width: 180px;
   padding: 0 5px;
   overflow: hidden;
   text-overflow: ellipsis;
   white-space: nowrap;
 }
</style>