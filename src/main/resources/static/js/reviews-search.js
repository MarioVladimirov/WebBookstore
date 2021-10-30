let reviewsBtn = document.querySelector('#user-reviews a');
let userReviewsContainer = document.getElementById('user-reviews-container');
let bookReviewsContainer = document.getElementById('book-reviews-container');

document.addEventListener('load', showBookReviews);

reviewsBtn.addEventListener('click', toggle);

function toggle(e) {
    e.preventDefault();

    if (this.textContent === 'Show My Reviews') {
        showUserReviews();
    } else {
        userReviewsContainer.innerHTML = '';
    }

    this.textContent = this.textContent === 'Show My Reviews'
        ? 'Hide My Reviews'
        : 'Show My Reviews';
}

function showBookReviews() {
    bookReviewsContainer.innerHTML = '';

    fetch("http://localhost:8080/reviews/api")
        .then(response => response.json())
        .then(reviews => {
                if (reviews.length === 0) {
                    bookReviewsContainer.innerHTML =
                        `<div class="mb-3">
                            <h5 class="text-left text-secondary">Be the first one to write a review</h5>
                            <h6 class="text-left text-secondary">There are no reviews yet</h6>
                         </div>`;
                    return;
                }

                displayReviews(bookReviewsContainer, createBookReviewElement, ...reviews);
            }
        );
}

function showUserReviews() {
    userReviewsContainer.innerHTML = '';

    fetch("http://localhost:8080/reviews/api/user")
        .then(response => response.json())
        .then(reviews => {
                if (reviews.length === 0) {
                    userReviewsContainer.innerHTML =
                        `<div class="mt-3">
                            <h5 class="text-left text-secondary">You have no reviews yet.</h5>
                        </div>`;
                    return;
                }

                displayReviews(userReviewsContainer, createUserReviewElement, ...reviews);
            }
        );
}

function displayReviews(container, func, ...reviews) {
    for (const review of reviews) {
        container.appendChild(func(review));
    }
}

function createBookReviewElement(review) {
    let article = document.createElement('article');
    article.classList.add('card', 'row', 'mt-2', 'w-100');
    article.innerHTML = `<div class="card-body">
                            <h5 class="card-title">${review.title}</h5>
                            <p class="card-text"><strong>Rating: </strong>${review.rating}</p>
                            <p class="card-text">${review.textContent}</p>
                            <footer class="blockquote-footer">Added by ${review.nickname} at ${review.addedOn}</footer>
                        </div>`;

    return article;
}

function createUserReviewElement(review) {
    let article = document.createElement('article');
    article.classList.add('card', 'row', 'mt-2', 'w-100');
    article.innerHTML = `<h5 class="card-header">${review.bookTitle}</h5>
                        <div class="card-body">
                            <h5 class="card-title">${review.title}</h5>
                            <p class="card-text"><strong>Rating: </strong>${review.rating}</p>
                            <p class="card-text">${review.textContent}</p>
                            <footer class="blockquote-footer">Added by ${review.nickname} at ${review.addedOn}</footer>
                        </div>`;

    return article;
}