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
              <v-treeview :items="items" :open="[1]" :active="[5]" :selected-color="'#fff'" activatable open-on-click dense></v-treeview>
            </template>
          </v-card>
        </div>
        <div
          class="col-md-9 col-sm-9 col-xs-12"
        >

          <v-breadcrumbs class="pb-0" :items="breadcrums"></v-breadcrumbs>

          <v-row dense>
            <v-col cols="12" sm="8" class="pl-6 pt-6">
              <small>검색결과</small>
            </v-col>
            <v-col cols="12" sm="4">
              <v-select class="pa-0" v-model="select" :items="options" style="margin-bottom: -20px;" outlined dense></v-select>
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
                        <v-btn v-if="hover" href="/detail"  outlined>대여하러 가기</v-btn>
                 </div>
                         </v-expand-transition>
                  </v-img>
                  <v-card-text class="text--primary">
                    <div class="txt_line"><router-link :to="'/book/${book_no}'" style="text-decoration: none">{{pro.book_title}}</router-link></div>
                    <div class="txt_line">{{pro.book_author}}</div>
                  </v-card-text>
                </v-card>
              </v-hover>
            </div>
          </div>
          <div class="text-center mt-12">
            <v-pagination
              v-model="page"
              :length="6"
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
        data () {
          return {
            range: [0, 10000],
            select:'인기순',
            options: [
                '인기순',
                '최신순',                
            ],
            page:1,           
            min:0,
            max:10000,
            items: [
                {
                    id: 2,
                    name: '만화',
                    // children: [
                    //     { id: 2, name: 'Casuals' },
                    //     { id: 3, name: 'Formals' },
                    //     { id: 4, name: 'Sneakers' },
                    // ],
                },                
                {
                    id: 3,
                    name: '에세이',                
                },
                 {
                    id: 4,
                    name: '문제집',                
                },
                 {
                    id: 5,
                    name: '잡지',                
                },
                 {
                    id: 6,
                    name: '인문학',                
                }
            ],
            Book:[]
          }
        },
         mounted:function(){
            this.getData();
        },
        methods:{
            getData:function(){
            this.$axios.get("http://localhost:8080/find_ok")      
            .then(response =>{
                console.log(response.data);
                this.Book=response.data;
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