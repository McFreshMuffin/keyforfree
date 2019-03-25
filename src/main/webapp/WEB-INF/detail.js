function changePrice() {
    var selection = document.getElementById('selection');
    selection.onchange = function () {
        var input = document.getElementById('PriceOnly').innerHTML;
        var output = document.getElementById('PriceTotal');
        output.innerHTML = input;
    };
}