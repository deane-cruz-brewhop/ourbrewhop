let reviewCards = document.getElementsByClassName('review-card');

for (let card of reviewCards) {

    let ratingNumber = parseInt(card.querySelector(".rating").textContent);
    let ratingStars = card.getElementsByClassName('fa-star');
    let ratingStarsArray = ratingStars.toArray;

    for (let i = 0; i < ratingNumber; i++) {
        ratingStars[i].classList.add('checked');
        console.log('added class to star element' + i);

    }
    // document.addEventListener("DOMContentLoaded", function () {
    //     let reviewCards = document.querySelectorAll('.all-reviews-card');
    //
    //     reviewCards.forEach(function (card) {
    //         let ratingNumber = parseInt(card.querySelector(".rating").getAttribute("data-rating"));
    //         let ratingStars = card.querySelectorAll('.fa-star');
    //
    //         for (let i = 0; i < ratingNumber; i++) {
    //             ratingStars[i].classList.add('checked');
    //         }
    //     });
    // });
}
document.addEventListener("DOMContentLoaded", function () {
    let reviewCards = document.querySelectorAll('.all-reviews-card');

    reviewCards.forEach(function (card) {
        let ratingNumber = parseInt(card.querySelector(".rating").textContent);
        let ratingStars = card.querySelectorAll('.fa-star');

        for (let i = 0; i < ratingNumber; i++) {
            ratingStars[i].classList.add('checked');
        }
    });
});

