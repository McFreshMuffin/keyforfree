function generateLinkGenre(elm) {

    var url = window.location.href;
    urlParams = url.replace("http://localhost:8080/WebProjekt/shop", "");

    if (urlParams.includes("genre")) {
        var indexGenre = urlParams.indexOf("genre");
        var help = urlParams.substring(indexGenre);
        var indexLast = help.indexOf("&");
        
        var zielurl2 = "";
        if (indexLast > 0) {
            indexLast = indexLast + indexGenre;
            zielurl2 = urlParams.substring(indexLast);
        }
        
        indexGenre = indexGenre - 1;
        var zielurl1 = urlParams.substring(0, indexGenre);
       
        window.location = zielurl1 + zielurl2 + "&genre=" + elm;

    } else {
        window.location = urlParams + "&genre=" + elm;
    }
}

function generateLinkCategory(elm) {

    var url = window.location.href;
    urlParams = url.replace("http://localhost:8080/WebProjekt/shop", "");

    if (urlParams.includes("category")) {
        var indexCat = urlParams.indexOf("category");
        var help = urlParams.substring(indexCat);
        var indexLast = help.indexOf("&");
        
        var zielurl2 = "";
        if (indexLast > 0) {
            indexLast = indexLast + indexCat;
            zielurl2 = urlParams.substring(indexLast);
        }
        
        indexCat = indexCat - 1;
        var zielurl1 = urlParams.substring(0, indexCat);
       
        window.location = zielurl1 + zielurl2 + "&category=" + elm;

    } else {
        window.location = urlParams + "&category=" + elm;
    }
}

function generateLinkPrice(elm) {

    var url = window.location.href;
    urlParams = url.replace("http://localhost:8080/WebProjekt/shop", "");

    if (urlParams.includes("price")) {
        var indexPrice = urlParams.indexOf("price");
        var help = urlParams.substring(indexPrice);
        var indexLast = help.indexOf("&");
        
        var zielurl2 = "";
        if (indexLast > 0) {
            indexLast = indexLast + indexPrice;
            zielurl2 = urlParams.substring(indexLast);
        }
        
        indexPrice = indexPrice - 1;
        var zielurl1 = urlParams.substring(0, indexPrice);
       
        window.location = zielurl1 + zielurl2 + "&price=" + elm;

    } else {
        window.location = urlParams + "&price=" + elm;
    }
}