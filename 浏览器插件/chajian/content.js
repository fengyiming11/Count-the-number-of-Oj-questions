window.addEventListener("load", myMain, false);

function getCookies(url) {
    chrome.runtime.sendMessage({url: url},  function (response) {
        console.log(response);
    });
}

function myMain(evt) {
    getCookies(document.URL)
}
