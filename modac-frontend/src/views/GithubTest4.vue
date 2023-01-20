<template>
    <div id="wrap">
        <div v-for="file, index in files" :key="index">
            <a :href="file.html_url">{{ file.name }}</a>
        </div>
    </div>
</template>
    
<script setup>
import axios from "axios";
import {useTodoStore} from "../stores/todo.js"
import {ref} from "vue";
import { useRoute } from 'vue-router'

let files = ref([]);

const store = useTodoStore();
const route = useRoute();

const http = axios.create({
    headers: {
        "Content-Type": "application/json",
        "Accept": "application/vnd.github+json"
    },
});


function fetchRepo(){
    const GITHUB_API_SERVER = "https://api.github.com"
    const owner = route.params.user;
    const repo = route.params.repo;

    const headers={
        "Authorization" : "Bearer "+ store.access_token
    }

    http.get(GITHUB_API_SERVER + `/repos/${owner}/${repo}/contents`, {headers})
    .then((response) => {
        response.data.forEach(element => {
            let el = {
                name: element.name,
                html_url: element.html_url
            };

            files.value.push(el);
        });

        console.log(files.value)
    })
    .catch((err) => console.log(err));
}

fetchRepo();

</script>

<style scoped>
#wrap{
    text-align: center;
}
input, textarea{
    margin: 0 auto;
    display: block;
    border: 1px gray solid;
}
button{
    background-color: rgb(82, 156, 134);
}
</style>