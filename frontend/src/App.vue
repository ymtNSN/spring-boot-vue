<template>
  <div id="app">
    <img src="./assets/logo.png">
    <!--<router-view/>-->
    <hello-world></hello-world>
    <span v-bind:title="message">
      ymtsss {{message}}
    </span>
    <div id="app-2">
    <span v-bind:title="message">
    Hover your mouse over me for a few seconds to see my dynamically bound title!
  </span>
    </div>
  </div>

</template>

<script>

  import HelloWorld from './components/HelloWorld'

  export default {
    name: 'App',
    components: {
      HelloWorld
    },
    data() {
      return {
        message: "page on " + new Date()
      }
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
