const booksList = document.getElementById('booksList')
const sidebar = document.getElementById('sidebar')

const allBooks = [];

fetch("http://localhost:8080/books/api")
    .then(response => response.json())
    .then(books => {
        for (let book of books) {
            allBooks.push(book);
        }
    });


sidebar.addEventListener('click', (e) => {
    e.preventDefault();

    const category = e.target.value.toUpperCase();
    let filteredBooks = allBooks.filter(book =>
        book.categories.contains(category));

    displayBooks(filteredBooks);
})


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
                        <p class="card-text border-bottom ">${b.title}</p>
                        <p class="card-text border-bottom text-secondary">Author: ${b.author}</p>
                        <p class="card-text border-bottom ">Price: ${b.price}</p>
                    </div>
                </div>
            </div>
            </div>`
        })
        .join('');

}