const booksList = document.getElementById('booksList')
const searchForm = document.getElementById('searchForm')

const allBooks = [];

searchForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const searchingCharacters = document
        .getElementById('searchBar').value.toLowerCase();

    fetch("http://localhost:8080/books/api")
        .then(response => response.json())
        .then(books => {
                for (let book of books) {
                    allBooks.push(book);
                }

                let filteredBooks = allBooks.filter(book => {
                    return book.title.toLowerCase().includes(searchingCharacters)
                        || book.author.toLowerCase().includes(searchingCharacters);
                });
                displayBooks(filteredBooks);
            }
        );


});


const displayBooks = (books) => {
    booksList.innerHTML = books
        .map((b) => {
            return `<div class="col-md-3" >
                <div class="card mb-4 box-shadow">
                <img src="${b.imageUrl}" class="card-img-top" alt="Thumbnail [100%x225]"
                     data-holder-rendered="true"
                     style="height: 225px; width: 100%; display: block;">
                <div class="d-flex justify-content-between align-items-center">
                     <div class="btn-group">
                        <a href="/books/details/${b.id}"  type="button" class="btn btn-sm btn-outline-secondary">Details</a>
                     </div>
                     <div class="btn-group">
                         <a href="/books/delete/${b.id}"  type="button" class="btn btn-sm btn-outline-secondary">Delete</a>
                     </div>
                </div>
                <div class="card-body">
                    <div class="text-center">
                        <p class="card-text border-bottom equal-size">${b.title}</p>
                        <p class="card-text border-bottom text-secondary equal-size">Author: ${b.author}</p>
                        <p class="card-text border-bottom">Price: ${(b.price).toFixed(2)} лв.</p>
                    </div>
                </div>
            </div>
            </div>`
        })
        .join('');

}