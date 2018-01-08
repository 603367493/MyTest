import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'

Vue.config.debug = true;

Vue.use(VueRouter);
Vue.use(VueResource);

//定义组件
const First = {template: '<div><h2>第一个子页面</h2></div>'}

import secondcomponent from './component/secondcomponent.vue'

//创建一个路由器实例
//并且配置路由器规则
const router = new VueRouter({
  mode: 'history',
  base: __dirname,
  routes: [
    {
      path: '/first',
      component: First
    },
    {
      path: '/second',
      component: secondcomponent
    }
  ]
})


const app = new Vue({
  router: router,
  render: h => h(App)
}).$mount('#app')


