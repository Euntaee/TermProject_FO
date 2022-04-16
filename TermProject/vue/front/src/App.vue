<!-- eslint-disable-->
<!-- <template>
  <div id="app">
    <TodoHeader></TodoHeader>
    <TodoInput v-on:addTodo="addTodo"></TodoInput>
    <TodoList v-bind:propsdata="todoItems" @removeTodo="removeTodo"></TodoList>
    <TodoFooter v-on:removeAll="clearAll"></TodoFooter>    
  </div>
</template>

<script>
/* eslint-disable */
import TodoHeader from './components/TodoHeader.vue'
import TodoInput from './components/TodoInput.vue'
import TodoList from './components/TodoList.vue'
import TodoFooter from './components/TodoFooter.vue'

export default {
  data() {
    return{
      todoItems: []
    }
  },
     created() {
        if(localStorage.length>0){
            for(var i=0; i<localStorage.length; i++){
              if (localStorage.key(i) != "loglevel:webpack-dev-server") {
                this.todoItems.push(localStorage.key(i));
              }
            }
        }
  },
  methods: {
    addTodo(todoItem){
      localStorage.setItem(todoItem, todoItem);
      this.todoItems.push(todoItem);
    },
    clearAll() {
      localStorage.clear();
      this.todoItems=[];
    },
    removeTodo(todoItem,index){
      localStorage.removeItem(todoItem);
      this.todoItems.splice(index,1);
    }
  },
  components: {
    'TodoHeader': TodoHeader,
    'TodoInput': TodoInput,
    'TodoList': TodoList,
    'TodoFooter': TodoFooter
  }

}
</script>

<style>

</style> -->
<template>
  <div id="app">
      <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>
