import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Book from '@/components/Book'
import Detail from '@/components/Detail'
import Pract from '@/components/Pract'
import login from '@/components/login'
import signup from '@/components/signup'
import find from '@/components/find'
import BookRent from '@/components/BookRent'
import Layout from '@/components/Layout'
import MyPage from '@/components/MyPage'
import ChangePwd from '@/components/ChangePwd'

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
                    path: '/detail',
                    component: Detail,
                    name: 'Detail',
                    props: true
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
                },
                {
                    path: '/signup',
                    component: signup,
                    name: 'signup'
                },
                {


                    path: '/find',
                    component: find,
                    name: 'find'
                },
                {
                    path: '/bookrent',
                    component: BookRent,
                    name: 'BookRent'
                },
                {
                    path: '/mypage',
                    component: MyPage,
                    name: 'MyPage'
                },
                {
                    path: '/changepwd',
                    component: ChangePwd,
                    name: 'ChangePwd'
                },
            ]
        }],
        mode: 'history'
    },

)