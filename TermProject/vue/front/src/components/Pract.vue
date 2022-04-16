<template>
<div id>
      <div class="row">
        <div
          class="col-md-9 col-sm-9 col-xs-12"
        >
          <div class="row text-center">
            <div class="col-md-3 col-sm-6 col-xs-12" v-bind:key="a.id" v-for="a in Book">
              <v-hover v-slot:default="{ hover }">
                <v-card
                  class="mx-auto"
                  color="grey lighten-4"
                  max-width="600"
                >
                  <v-img
                    class="white--text align-end"
                    :aspect-ratio="16/9"
                    height="200px"
                    :src="a.book_img"
                  >
                    <!-- <v-card-title>{{pro.type}} </v-card-title> -->
                    <v-expand-transition>
                      <div
                        v-if="hover"
                        class="d-flex transition-fast-in-fast-out white darken-2 v-card--reveal display-3 white--text"
                        style="height: 100%;"
                      >
                        <v-btn v-if="hover" href="/detail" class="" outlined>VIEW</v-btn>
                      </div>

                    </v-expand-transition>
                  </v-img>
                  <v-card-text class="text--primary">
                    <div><a href="/detail" style="text-decoration: none">{{a.book_title}}</a></div>
                    <div>{{a.book_author}}</div>
                  </v-card-text>
                </v-card>
              </v-hover>
            </div>
          </div>
          <div class="text-center mt-12">
          </div>
        </div>
      </div>
  </div>
</template>

<script>
/* eslint-disable */
export default {         
         data: () => ({            
             Book:[]
         }),       
        mounted:function(){
            this.getData();
        },
        methods:{
            getData:function(){
            this.$axios.get("http://localhost:8080/rest_prac")      
            .then(response =>{
                console.log(response.data);
                this.Book=response.data;
            })      
            }
        }
    }
</script>

<style>
</style>