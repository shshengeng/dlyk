//导入axios库
import axios from 'axios';
import {ElMessageBox} from "element-plus";
import {getTokenName, messageReLogin, messageTip, removeStorageToken} from "../util/message.js";

axios.defaults.baseURL = 'http://localhost:8088'

export function doGet(url, params) {
    return axios({
        method: 'GET',
        url: url,
        params: params
    })
}

export function doPost(url, data) {
    return axios({
        method: 'POST',
        url: url,
        data: data,
    })
}

export function doPut(url, data) {
    return axios({
        method: 'PUT',
        url: url,
        data: data
    })
}

export function doDelete(url, params) {
    return axios({
        method: 'DELETE',
        url: url,
        params: params
    })
}


// Add a request interceptor
axios.interceptors.request.use(function (config) {
    let token = localStorage.getItem(getTokenName())
    if (!token) {
        token = sessionStorage.getItem(getTokenName())
    }
    if(token){
        // 在每个请求的头部添加 Authorization 头
        config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});

// Add a response interceptor
axios.interceptors.response.use(function (response) {
    if (response.data.code >= 900) { //token validation failed
        //message user 并且清除token跳到登录页面
        messageReLogin(response.data.msg) //个人认为不应该是选项框，而是直接到登录页面
    }
    return response;
}, function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    return Promise.reject(error);
});






// export const doGet = (url, params = {}, config = {}) => {
//     return axios.get(url, { params, ...config });
// };
//
// export const doPost = (url, data = {}, config = {}) => {
//     return axios.post(url, data, config);
// };
//
// export const doPut = (url, data = {}, config = {}) => {
//     return axios.put(url, data, config);
// };
//
// export const doDelete = (url, params = {}, config = {}) => {
//     return axios.delete(url, { params, ...config });
// };