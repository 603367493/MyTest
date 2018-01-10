<template>
  <div id="app">
    <label>账号</label><input v-model="name" placeholder="loginHolder"/>
    <label>密码</label><input v-model="password" placeholder="passwordHolder" type="password" />
    <button v-on:click="login">登陆</button>


    <button v-on:click="getData">获取书籍数据</button>
    <li v-for="art in artData">
      <ul>{{art.title}}</ul>
    </li>
  </div>
</template>

<script>
    export default {
      data () {
        return {
          name: '',
          password: '',
          artData: []
        }
      },
      methods: {
        login: function () {
          var that = this
          if (that.name === 'shidi' && that.password === '123') {
            alert('登陆成功')
          } else {
            alert('登陆失败')
          }
        },
        getData: function () {
          this.$axios.get('http://192.168.1.212:8081/web-springboot-mybatis/test/getData.do').then((response) => {
            console.log(response)
            this.artData = response.data.subjects
          }, (error) => {
            console.log(error)
          })
        }
      }
    }
</script>

<style scoped>

</style>
