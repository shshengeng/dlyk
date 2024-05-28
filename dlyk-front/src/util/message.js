import {ElMessage} from "element-plus";


export function messageTip(msg, type){
    ElMessage({
        showClose: true, //display close button or not
        center: true, //center
        duration: 3000, //lasts 3s
        message: msg, //message content
        type: type, //message type
    })
}

