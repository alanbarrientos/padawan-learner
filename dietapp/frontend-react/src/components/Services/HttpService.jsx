import axios from "axios";
import {getBackendUrlEndPoint} from "./util";

    const API_AUTH = getBackendUrlEndPoint('auth')
    const API_BACKEND = getBackendUrlEndPoint('user')

    export function signup(user){
        return axios.post(API_AUTH + '/signup', user , { withCredentials: true })
    }

    export function login(username, password){
        return axios.post(API_AUTH + '/login', { userName: username, password: password }, { withCredentials: true })
    }

   export function logout(){
        axios.post(API_AUTH + '/logout', null, {withCredentials:true}).then()
        localStorage.clear()
    }

    export function getWeights(){
        return axios.get(API_BACKEND + '/getweighthistory', {withCredentials:true})
    }

    export function addWeight(weight){
        return axios.post(API_BACKEND + '/postweight', weight, { withCredentials: true })
    }

    export function updateWeight(weight){
        return axios.post(API_BACKEND + '/postweight/'+ weight.id, weight, { withCredentials: true })
    }

    export function deleteWeight(id){
        return axios.delete(API_BACKEND + "/" + id,  { withCredentials: true })
    }


    export function getLastWeights(){
        return axios.get(API_BACKEND + '/getlastweight', {withCredentials:true})
    }

    export function getFirstWeights(){
        return axios.get(API_BACKEND + '/getfirstweight', {withCredentials:true})
    }

