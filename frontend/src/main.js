// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import App from './App'
import router from './router'

process.env.NODE_E

Vue.use(Vuex);

Vue.config.productionTip = false;

/* eslint-disable no-new */

const moduleA = {
  state: {
    num: 1
  },
  mutations: {},
  actions: {},
  getters: {}
};
const moduleB = {
  state: {
    num: 2
  },
  mutations: {},
  actions: {},
  getters: {}
};

const store = new Vuex.Store({
  state: {
    message: 'dd',
    num: 0
  },
  mutations: {
    updateMessage(state,message) {
      state.message = message;
    }
  },
  modules: {
    a: moduleA,
    b: moduleB,
    foo: {
      namespaced: true,
      actions: {
        someAction: {
          // 在带命名空间的模块注册全局 action
          root: true,
          handler(namespacedContext, payload) {

          }
        }
      }
    }
  }
});
console.log(store.state.num);

const Counter = {
  template: `<div>{{message}}<input v-model="message">hhh</input></div>`,
  computed: {
    message: {
      get() {
        return this.$store.state.message
      },
      set(value) {
        this.$store.commit('updateMessage', value)
      }
    }
  }
  // co
}


new Vue({
  el: '#app',
  router,
  store,
  components: {Counter},
  template: `
    <div class="app">
        <counter></counter>
    </div>
  `
})
