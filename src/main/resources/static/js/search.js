const booksList = document.getElementById('booksList')
const searchForm = document.getElementById('searchForm')
const sidebar = document.getElementById('categoriesSubmenu')

searchForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const allBooks = [];
    const searchingCharacters = document
        .getElementById('searchBar').value.toLowerCase();

    fetch("http://localhost:8080/books/api")
        .then(response => response.json())
        .then(books => {
                for (let book of books) {
                    if (book.title.toLowerCase().includes(searchingCharacters)
                        || book.author.toLowerCase().includes(searchingCharacters)) {

                        allBooks.push(book);
                    }
                }
                displayBooks(allBooks);
            }
        );
});

sidebar.addEventListener('click', (e) => {
    e.preventDefault();

    const allBooks = [];
    const searchCategory = e.target.getAttribute('value')
        .toUpperCase()
        .replaceAll(' ', '_');

    fetch("http://localhost:8080/books/api")
        .then(response => response.json())
        .then(books => {
                for (const book of books) {
                    for (const category of book.categories) {
                        if (category === searchCategory) {
                            allBooks.push(book);
                            break;
                        }
                    }
                }
                displayBooks(allBooks);
            }
        );
});

// TODO - make book-card div the same as in home
const displayBooks = (books) => {
    booksList.innerHTML = books
        .map((b) => {
            return `<div class="book-card col-md-3">
                    <a class="product-box" href="/books/details/${b.id}">
                            <span class="image-wrapper">
                                <img class="first" src="${b.imageUrl}" alt="Thumbnail [100%x225]"
                                     data-holder-rendered="true">
                            </span>
                        <div class="d-flex">
                            <div class="btn-group">
                                <a href="/books/details/${b.id}"
                                   type="button" class="btn btn-sm">
                                    <i class="far fa-heart"></i>
                                </a>
                            </div>
                            <div class="btn-group">
                                <a href="/books/details/${b.id}"
                                   type="button" class="btn btn-sm">
                                    <i class="fas fa-shopping-cart"></i>
                                </a>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="text-center">
                                <p class="card-text equal-size">${b.title}</p>
                                <p class="card-text text-secondary equal-size">Author: 
                                    <span>${b.author}</span>
                                </p>
                                <p class="card-text">Price:
                                    <span>${b.price.toFixed(2)}</span> lv.
                                </p>
                            </div>
                        </div>
                    </a>
                </div>`
        })
        .join('');

}


// return `<div class="col-md-3" >
//     <div class="card mb-4 box-shadow">
//     <img src="${b.imageUrl}" class="card-img-top" alt="Thumbnail [100%x225]"
//          data-holder-rendered="true"
//          style="height: 225px; width: 100%; display: block;">
//     <div class="d-flex justify-content-between align-items-center">
//          <div class="btn-group">
//             <a href="/books/details/${b.id}"  type="button" class="btn btn-sm btn-outline-secondary">Details</a>
//          </div>
//          <div class="btn-group">
//              <a href="/books/delete/${b.id}"  type="button" class="btn btn-sm btn-outline-secondary">Delete</a>
//          </div>
//     </div>
//     <div class="card-body">
//         <div class="text-center">
//             <p class="card-text border-bottom equal-size">${b.title}</p>
//             <p class="card-text border-bottom text-secondary equal-size">Author: ${b.author}</p>
//             <p class="card-text border-bottom">Price: ${(b.price).toFixed(2)} лв.</p>
//         </div>
//     </div>
// </div>
// </div>`