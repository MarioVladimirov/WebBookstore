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
//
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
                let bookId = $(this).attr('bookId');
                fetch("http://localhost:8080/books/api")
                    .then(response => response.json())
                    .then(books => {
                        let book = books.filter(b => b.id === bookId);
                        if (book) {
                            let quantityInput = $('#quantity' + bookId);
                            let newQuantity = parseInt(quantityInput.val()) - 1;
                            if (newQuantity > 0) {
                                quantityInput.val(newQuantity);
                            }
                        }
                    });
            })
        );
});

window.addEventListener('load', function () {
    document.querySelectorAll('a[id="plusButton"]')
        .forEach(a =>
            a.addEventListener('click', function () {
                let bookId = $(this).attr('bookId');
                fetch("http://localhost:8080/books/api")
                    .then(response => response.json())
                    .then(books => {
                        let book = books.filter(b => b.id === bookId);
                        if (book) {
                            let quantityInput = $('#quantity' + bookId);
                            let newQuantity = parseInt(quantityInput.val()) + 1;
                            quantityInput.val(newQuantity);
                        }
                    });
            })
        );
});











