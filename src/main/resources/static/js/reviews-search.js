let reviewsBtn = document.querySelector('#user-reviews a');
let userReviewsContainer = document.getElementById('user-reviews-container');
let bookReviewsContainer = document.getElementById('book-reviews-container');

document.addEventListener('load', () => {
    bookReviewsContainer.innerHTML = '';

    fetch("http://localhost:8080/reviews/api")
        .then(response => response.json())
        .then(reviews => {
                for (const review of reviews) {
                    bookReviewsContainer.appendChild(createReviewElement(review));
                }
            }
        );
});

reviewsBtn.addEventListener('click', toggle);

function toggle(e) {
    e.preventDefault();
    this.textContent = this.textContent === 'Show My Reviews'
        ? 'Hide My Reviews'
        : 'Show My Reviews';

    if (this.textContent === 'Show My Reviews') {
        displayReviewsByUser();
    } else {
        userReviewsContainer.innerHTML = '';
    }
}

const displayReviewsByUser = () => {
    userReviewsContainer.innerHTML = '';

    fetch("http://localhost:8080/reviews/api/user")
        .then(response => response.json())
        .then(reviews => {
                for (const review of reviews) {
                    userReviewsContainer.appendChild(createReviewElement(review));
                }
            }
        );
}

function createReviewElement(review) {
    let div = document.createElement('div');
    div.innerHTML = `<p>I am a review div</p>`;
    return div;
}