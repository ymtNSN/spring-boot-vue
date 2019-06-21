// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuex from 'vuex'
import {mapState} from 'vuex'
import {mapGetters} from 'vuex'
import {mapMutations} from 'vuex'
import {mapActions} from 'vuex'
// import {name1,name2,add} from './router/a.js'
import a from './a.js'
import {ADD_MUTATION} from './mutation-types.js'

Vue.use(Vuex);

Vue.config.productionTip = false;

/* eslint-disable no-new */

const store = new Vuex.Store({
  state: {
    count: 0,
    num: 1,
    todos: [
      {id: 1, text: '...', done: true},
      {id: 2, text: '...', done: false}
    ]
  },
  getters: {
    doneTodos: state => {
      return state.todos.filter(todo => todo.done)
    },
    doneTodosCount: (state, getters) => {
      return getters.doneTodos.length
    },
    getTodoById: (state) => (id) => {
      return state.todos.find(todo => todo.id === id)
    }
  },
  mutations: {
    // 我们可以使用 ES2015 风格的计算属性命名功能来使用一个常量作为函数名
    [ADD_MUTATION](state) {
      state.count++;
    },
    // increment(state, payload) {
    //   state.count += payload.amount;
    // },
    increment(state, payload) {
      console.log(payload);
      console.log(state.count);
      state.count += payload.amount;
      console.log(state.count)
    },
    // increment(state, payload) {
    //   console.log(payload);
    //   console.log(state.count)
    //   state.count += payload;
    //   console.log(state.count)
    // },
  },
  actions: {
    // increment(context){
    //   context.commit('increment')
    // }
    increment({commit}) {
      commit('ADD_MUTATION')
    },
    incrementAsync({commit}) {
      setTimeout(() => {
        commit('increment', payload)
      }, 1000)
    },
    actionA({commit}) {
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          commit('ADD_MUTATION');
          resolve()
        }, 1000)
      })
    },
    actionB({dispatch, commit}) {
      return dispatch('actionA').then(() => {
        commit('ADD_MUTATION')
      })
    }
    // 假设 getData() 和 getOtherData() 返回的是 Promise
    // actions: {
    //   async actionA ({ commit }) {
    //     commit('gotData', await getData())
    //   },
    //   async actionB ({ dispatch, commit }) {
    //     await dispatch('actionA') // 等待 actionA 完成
    //     commit('gotOtherData', await getOtherData())
    //   }
  }
});

const payload = {
  amount: 6,
};

// console.log(store.getters.doneTodos);
// store.commit('increment',payload.amount);
// store.commit({
//   type: 'increment',
//   amount: 7
// });
// store.commit('ADD_MUTATION');
// console.log(store.state.count);
// console.log(store.getters.getTodoById(1));
// store.dispatch('incrementAsync', payload);
// console.log()
store.dispatch('actionB').then(() => {
  console.log("dd")
});
const Counter = {
  template: `<div>{{count}}<button v-on:click="increment(payload)">hhh</button></div>`,
  data() {
    return {
      payload: {
        amount: 7
      }
    }
  },
  // computed: {
  //   //   count(){
  //   //     return this.$store.state.count
  //   doneTodosCount() {
  //     return this.$store.getters.doneTodosCount;
  //   }
  // }
  // }
  // computed: mapState({
  //   count: state => state.count,
  //   num: state => state.num,
  //   countAlias: 'count',
  //   // 为了能够使用 `this` 获取局部状态，必须使用常规函数
  //   countPlusLocalState (state) {
  //     return state.count + this.localCount
  //   }
  // }),
// computed: mapState([
//   'count'
// ])
  computed: {
    count() {
      return this.$store.state.count;
      // ...mapState([
      //   'count'
      // ]),
    },
    ...mapGetters({
      doneTodosCount: 'doneTodosCount',
    })
  },
  methods: {
    // increment(n) {
    //   console.log(n);
    //   this.$store.commit('increment',6)
    // },
    // ...mapMutations([
    //   'increment'
    // ]),
    ...mapActions([
      'increment'
    ])
  }
};

new Vue({
  el: "#app",
  store,
  components: {Counter},
  template: `
    <div class="app">
        <counter></counter>
    </div>
  `

})

// store.commit('increment');
//
// console.log(store.state.count);

// new Vue({
//   el: '#app',
//   router,
//   components: {App},
//   template: '<App/>'
// });

// console.log(name1);
// console.log(name2);
// console.log(add(4,6));

// console.log(a.name1);
// console.log(a.name2);
// console.log(a.add(4, 6));

