document.getElementById("author").onchange = function () {
    let selectedOption = this.options(this.selectedIndex);
    if (selectedOption.value === "http://localhost:8080/authors/add"
        || selectedOption.value === "http://localhost:8080/publishinghouses/add") {
        window.open(selectedOption.value);
    }
}