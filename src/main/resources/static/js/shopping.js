// $(document).ready(function () {
//     $('a[id="minusButton"]').on('click', function () {
//         let productId = $(this).attr('pid');
//         let quantityInput = $('#quantity' + productId);
//         let newQuantity = parseInt(quantityInput.val()) - 1;
//         if (newQuantity > 0) {
//             quantityInput.val(newQuantity);
//         }
//     });
// });

// $(document).ready(function () {
//     $('a[id="plusButton"]').on('click', function () {
//         let productId = $(this).attr('pid');
//         let quantityInput = $('#quantity' + productId);
//         let newQuantity = parseInt(quantityInput.val()) + 1;
//         if (newQuantity > 0) {
//             quantityInput.val(newQuantity);
//         }
//     });
// });


window.addEventListener('load', function () {
    document.querySelectorAll('a[id="minusButton"]')
        .forEach(a =>
            a.addEventListener('click', function () {
                let productId = $(this).attr('pid');
                let quantityInput = $('#quantity' + productId);
                let newQuantity = parseInt(quantityInput.val()) - 1;
                if (newQuantity > 0) {
                    quantityInput.val(newQuantity);
                }
            })
        );
});

window.addEventListener('load', function () {
    document.querySelectorAll('a[id="plusButton"]')
        .forEach(a =>
            a.addEventListener('click', function () {
                let productId = $(this).attr('pid');
                let quantityInput = $('#quantity' + productId);
                let newQuantity = parseInt(quantityInput.val()) + 1;
                quantityInput.val(newQuantity);
            })
        );
});











