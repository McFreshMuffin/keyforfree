function generateLinkGenre(elm) {

    var url = window.location.href;
    urlParams = url.replace("http://localhost:8080/WebProjekt/shop", "");

    if (urlParams.includes("genre")) {
        var indexGenre = urlParams.indexOf("genre");
        var help = urlParams.substring(indexGenre);
        var indexLast = help.indexOf("&");
        indexLast = indexLast + indexGenre;
        indexGenre = indexGenre - 1;
        var zielurl1 = urlParams.substring(0, indexGenre);
        var zielurl2 = "";
        if (indexLast > 0) {
            zielurl2 = urlParams.substring(indexLast);
        }
        window.location = zielurl1 + zielurl2 + "&genre=" + elm;


    } else {
        window.location = urlParams + "&genre=" + elm;
    }
}

function generateLinkCategory(elm) {

    var url = window.location.href;
    urlParams = url.replace("http://localhost:8080/WebProjekt/shop", "");

    if (urlParams.includes("category")) {
        var indexGenre = urlParams.indexOf("category");
        var help = urlParams.substring(indexGenre);
        var indexLast = help.indexOf("&");
        indexLast = indexLast + indexGenre;
        indexGenre = indexGenre - 1;
        var zielurl1 = urlParams.substring(0, indexGenre);
        var zielurl2 = "";
        if (indexLast > 0) {
            zielurl2 = urlParams.substring(indexLast);
        }
        window.location = zielurl1 + zielurl2 + "&category=" + elm;


    } else {
        window.location = urlParams + "&category=" + elm;
    }
}

function generateLinkPrice(elm) {

    var url = window.location.href;
    urlParams = url.replace("http://localhost:8080/WebProjekt/shop", "");

    if (urlParams.includes("price")) {
        var indexGenre = urlParams.indexOf("price");
        var help = urlParams.substring(indexGenre);
        var indexLast = help.indexOf("&");
        indexLast = indexLast + indexGenre;
        indexGenre = indexGenre - 1;
        var zielurl1 = urlParams.substring(0, indexGenre);
        var zielurl2 = "";
        if (indexLast > 0) {
            zielurl2 = urlParams.substring(indexLast);
        }
        window.location = zielurl1 + zielurl2 + "&price=" + elm;


    } else {
        window.location = urlParams + "&price=" + elm;
    }
}