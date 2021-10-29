const booksList = document.getElementById('booksList')
const searchForm = document.getElementById('searchForm')
const sidebar = document.getElementById('categoriesSubmenu')

searchForm.addEventListener('submit', (e) => {
    e.preventDefault();

    booksList.innerHTML = '';
    const searchingCharacters = document
        .getElementById('searchBar').value.toLowerCase();

    fetch("http://localhost:8080/books/api")
        .then(response => response.json())
        .then(books => {
                for (let book of books) {
                    if (book.title.toLowerCase().includes(searchingCharacters)
                        || book.author.toLowerCase().includes(searchingCharacters)) {
                        booksList.appendChild(createBookArticle(book));
                    }
                }
            }
        );
});

sidebar.addEventListener('click', (e) => {
    e.preventDefault();

    booksList.innerHTML = '';
    const searchCategory = e.target.getAttribute('value');

    fetch("http://localhost:8080/books/api")
        .then(response => response.json())
        .then(books => {
                for (const book of books) {
                    for (const category of book.categories) {
                        if (category === searchCategory) {
                            booksList.appendChild(createBookArticle(book));
                            break;
                        }
                    }
                }
            }
        );
});

// TODO - make book-card div the same as in home
function createBookArticle(b) {
    let article = document.createElement('article');
    article.classList.add('card', 'rounded', 'p-2', 'm-3', 'col-sm-6', 'col-md-3');
    article.innerHTML = `<a href="/books/${b.id}">
                        <img class="card-image" src="${b.imageUrl}" alt="Thumbnail [100%x225]"
                             data-holder-rendered="true">
                        <div class="btn-group d-flex justify-content-end">
                            <a href="/books/${b.id}"
                               type="button" class="btn btn-md">
                                <i class="far fa-heart"></i>
                            </a>
                            <a href="/books/${b.id}"
                               type="button" class="btn btn-md">
                                <i class="fas fa-shopping-cart"></i>
                            </a>
                        </div>
                        <div class="card-body pt-1">
                            <div class="text-center">
                                <h5 class="card-title">${b.title}</h5>
                                <p class="card-text text-secondary">${b.author}</p>
                                <p class="card-text">${b.price.toFixed(2)} BGN</p>
                            </div>
                        </div>
                    </a>`;
    return article;
}
