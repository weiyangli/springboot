import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        activeName: [
            {
                name: '赵本山',
                age: 45,
            },
            {
                name: '佟大为',
                age: 18,
            },
        ],
    },
    mutations: {
        ageAdd: (state, paylocad) => {
            state.activeName.forEach(x => {
                x.age -= paylocad;
            });
        }
    },
    actions: {
        changeAge: (content, paylocad) => {
            content.commit('ageAdd', paylocad);
        }
    },
    getters: {
        addAges: (state => {
            var newAge = state.activeName.map(
                start => {
                    return {
                        age: start.age + 1,
                    };
                }
            );
            return newAge;
        })
    }
});
