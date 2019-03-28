function changePrice(selectedValue, price) {

        selectedValue = Number(selectedValue);
        var price = Number(price);
        var totalPrice = price * selectedValue;
        totalPrice = totalPrice.toFixed(2);
        totalPrice = totalPrice + " €";
        var output = document.getElementById('PriceTotal');
        output.innerHTML = totalPrice;
}