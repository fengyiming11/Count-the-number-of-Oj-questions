function ajaxObject() {
    var xmlHttp;
    try {
        xmlHttp = new XMLHttpRequest();
    }
    catch (e) {
        try {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
                alert("您的浏览器不支持AJAX！");
                return false;
            }
        }
    }
    return xmlHttp;
}

let ajax = ajaxObject();
let username,password;
document.getElementById('button').onclick=bindbt;
document.getElementById('jinggao').innerText=null;
function init(){
    chrome.storage.local.get(['username','password'],function (items){
        username=items.username;
        password=items.password;
        if(username===undefined){
            document.getElementById('status').innerText="未绑定";
            document.getElementById('button').innerText="绑定";
        }
        else {
            document.getElementById('button').innerText="解绑";
            document.getElementById('status').innerText="已绑定";
            document.getElementById('xuehao').innerText="学号: "+username;
            document.getElementById('account').value=username;
            document.getElementById('password').value=password;
            ajax.open("GET", "http://101.43.175.73:8080/api/User/"+username);
            ajax.onreadystatechange = function() {
                let idx = this.responseText.indexOf('"name":"');
                let name = "";
                for(let i=idx+8;i<this.responseText.length;i++){
                    if(this.responseText.charAt(i)==='"') {
                        break;
                    }
                    name+=this.responseText.charAt(i);
                }
                document.getElementById('xingming').innerText="姓名: "+name;
            };
            ajax.send();
        }
    })

}
init();

function bindbt(){
    if(username===undefined) {
        username=document.getElementById('account').value;
        password=document.getElementById('password').value;
        let param ={
            uname:username,
            password:password
        }
        ajax.open("post", "http://101.43.175.73:8080/api/login");
        ajax.setRequestHeader('content-type','application/json;charset=UTF-8');
        ajax.onreadystatechange = function() {
            if(this.readyState === 4 && this.status === 200) {
                if(this.responseText==='error'){
                    document.getElementById('jinggao').innerText="账号或密码错误";
                }
                else if(this.responseText==='ok') {
                    document.getElementById('jinggao').innerText="绑定成功";
                    chrome.storage.local.set({'username': username});
                    chrome.storage.local.set({'password': password});
                    init();
                }
            }
        };
        ajax.send(JSON.stringify(param));
    }
    else{
        document.getElementById('jinggao').innerText="解绑成功";
        username=undefined;
        password=undefined;
        chrome.storage.local.remove(['username','password']);
        init();
    }
}
