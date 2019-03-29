function changeSelector(selectedValue, price) {
    selectedValue = Number(selectedValue);
    price = Number(price);
    var totalPrice = price * selectedValue;
    totalPrice = totalPrice.toFixed(2);
    totalPrice = totalPrice + " €";
    var menge = document.getElementById('inputMengeId');
    menge.value = selectedValue;
    var output = document.getElementById('PriceTotal');
    output.innerHTML = totalPrice;
}

function roundPrice() {
    var element = document.getElementsByClassName('roundPriceId');
    var laenge = element.length;
    var i;
    for (i = 0; i < laenge; i++) {
        var price = element[i].innerHTML;
        price = Number(price);
        price = price.toFixed(2);
        price = price + " €";
        element[i].innerHTML = price;
    }
}

function btnKaufen(sessionScope) {
    var b1 = Boolean(sessionScope);
    if (!b1) {
        var element = document.getElementsByClassName('btnKaufenId');
        var laenge = element.length;
        var i;
        for (i = 0; i < laenge; i++) {
            element[i].disabled = true;
            element[i].style.backgroundColor = '#848484';
            element[i].setAttribute('title', 'Bitte loggen Sie sich zuerst ein!');
            element[i].style.border = '0px';
        }
    }

}