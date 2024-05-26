//导入axios库
import axios from 'axios';

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
        data: data
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