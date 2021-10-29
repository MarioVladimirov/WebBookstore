document.getElementById('author-added-alert')
    .addEventListener('click', toggleAlert);

document.getElementById('book-added-alert')
    .addEventListener('click', toggleAlert);

document.getElementById('book-updated-alert')
    .addEventListener('click', toggleAlert);

let formElements = [...document.getElementsByTagName('form')];
formElements
    .forEach(form => form
        .addEventListener('mouseover', setBorderStyle));
formElements
    .forEach(form => form
        .addEventListener('mouseout', removeBorderStyle));


function toggleAlert() {
    this.parentElement.classList.toggle('visible');
}

function setBorderStyle() {
    if (this.tagName === 'INPUT') {
        this.style.border = '1px solid #ced4da;';
    }
}

function removeBorderStyle() {
    if (this.tagName === 'INPUT') {
        this.style.border = '';
    }
}