import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Book from '@/components/Book'
import Detail from '@/components/Detail'
import Pract from '@/components/Pract'
import login from '@/components/login'
import signup from '@/components/signup'
// import Post from '@/components/Post'
// import Cart from '@/components/Cart'
import Layout from '@/components/Layout'

/* eslint-disable */
Vue.use(Router)

export default new Router({
        routes: [{
            path: '/',
            component: Layout,
            children: [{
                    path: '/',
                    component: Home,
                    name: 'Home',

                },
                {
                    path: '/book',
                    component: Book,
                    name: 'Book'
                },
                {
                    path: '/book/:book_no',
                    component: Detail,
                    name: 'Detail',
                    props: route => ({
                        book_no: Number(route.params.book_no)
                    })
                },
                {
                    path: '/pract',
                    component: Pract,
                    name: 'Pract'
                },
                {
                    path: '/login',
                    component: login,
                    name: 'login'
                }, {
                    path: '/signup',
                    component: signup,
                    name: 'signup'
                },
            ]
        }],
        mode: 'history'
    },

)