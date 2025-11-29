import AuthLayout from 'layouts/AuthLayout.vue'
import MainLayout from 'layouts/MainLayout.vue'

import LoginPage from 'pages/Login.vue'
import RegisterPage from 'pages/RegisterPage.vue'
import HomePage from 'pages/HomePage.vue'

import Courses from 'components/Courses.vue'
import CourseInfo from 'components/CourseInfo.vue'

const routes = [
  // Редирект
  {
    path: '/',
    redirect: '/login'
  },

  // Авторизация
  {
    path: '/',
    component: AuthLayout,
    children: [
      { path: 'login', component: LoginPage },
      { path: 'register', component: RegisterPage }
    ]
  },

  // Основной интерфейс
  {
    path: '/app',
    component: MainLayout,
    children: [
      { path: 'home', component: HomePage },
      { path: 'courses', component: Courses },
      { path: 'courses/:course_id', component: CourseInfo }
    ]
  },

  // 404
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
]

export default routes
