function generateLinkGenre(elm) {

    var url = window.location.href;
    urlParams = url.replace("http://localhost:8080/WebProjekt/shop", "");

    if (urlParams.includes("genre")) {
        var indexGenre = urlParams.indexOf("genre") - 1;
        var help = urlParams.substring(indexGenre);
        var indexLast = help.indexOf("&");
        var zielurl1 = urlParams.slice(0, indexGenre);
        var zielurl2 = "";
        if (indexLast > 0) {
            zielurl2 = urlParams.slice(indexLast);
        }
        window.location = zielurl1 + zielurl2 + "&genre=" + elm;


    } else {
        window.location = urlParams +"&genre="+ elm;
    }

}

