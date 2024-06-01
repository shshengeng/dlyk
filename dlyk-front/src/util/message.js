import {ElMessage, ElMessageBox} from "element-plus";


export function messageTip(msg, type){
    ElMessage({
        showClose: true, //display close button or not
        center: true, //center
        duration: 3000, //lasts 3s
        message: msg, //message content
        type: type, //message type
    })
}

export function getTokenName(){
    return "dlyk token"
}

export function removeStorageToken(){
    window.localStorage.removeItem(getTokenName());
    window.sessionStorage.removeItem(getTokenName());
}

export function messageReLogin(msg){
    ElMessageBox.confirm(
        msg + ', 是否重新登录?',
        'Warning',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    ).then(() => {
        //既然未通过就清除token，并且跳到登录页面
        removeStorageToken();
        window.location.href = '/';
    }).catch(() => {
        messageTip('取消登录', 'warning')
    })
}