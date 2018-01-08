<template>
  <div id="secondcomponent">

    <ul>
      <li v-for="article in articles">
        {{article.title}}
      </li>
    </ul>
  </div>
</template>

<script>
    export default {
        data () {

        },
        mounted:function(){
          this.$http.jsonp('https://api.douban.com/v2/movie/top250?count=10',{},{
            headers:{

            },
            emulateJSON:true
          }).then(function(response){
            this.articles = response.data.subjects
          },function(response){
            console.log(response);
          })
        }
    }
</script>

<style scoped>

</style>
