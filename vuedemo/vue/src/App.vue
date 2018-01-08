<template>
  <div id="app">
    <img src="./assets/logo.png"/>
    <h1>{{message}}</h1>
    <router-view>
    </router-view>
  </div>
</template>

<script>
  import firstcomponent from './component/firstcomponent.vue'
  import secondcomponent from './component/secondcomponent.vue'
  export default {
    data () {
      return {
        message: "hello vue!"
      }
    },
    components: {
      firstcomponent,
      secondcomponent
    }
  }
</script>
