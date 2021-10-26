document.getElementById('author-added-alert')
    .addEventListener('click', toggleAlert);

document.getElementById('book-added-alert')
    .addEventListener('click', toggleAlert);

document.getElementById('book-updated-alert')
    .addEventListener('click', toggleAlert);

function toggleAlert(e) {
    e.target().parentElement.classList.toggle('visible');
}