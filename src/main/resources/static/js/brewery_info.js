import {
    client, yelp_api
} from "./keys.js"

const defaultId = "default_id";
const urlParams = new URLSearchParams(window.location.search);
const breweryID = urlParams.get('brewery') || defaultId;

document.getElementById('brewFormID').value = breweryID;

console.log('Brewery ID:', breweryID); // Debugging log

function getBreweryImagePath(breweryId) {
    const imagePath = `/img/${breweryId}.jpeg`;
    console.log('Image path:', imagePath); // Debugging log
    return imagePath;
}
window.handleImageError = function(imageElement) {
    imageElement.src = '/img/default.jpeg';
};




function formatPhoneNumber(phoneNumber) {
    const cleaned = ('' + phoneNumber).replace(/\D/g, '');
    const match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
    if (match) {
        return `(${match[1]})${match[2]}-${match[3]}`;
    }
    return null;
}

if (!breweryID) {
    console.error('Error: Brewery ID is null or undefined');
} else {
    fetch('https://api.openbrewerydb.org/v1/breweries/' + breweryID)
        .then(response => response.json())
        .then(brewery => {
            const formattedPhone = brewery.phone ? formatPhoneNumber(brewery.phone) : 'N/A';
            const breweryImagePath = getBreweryImagePath(brewery.id);
             const breweryImageHTML = `<img src="${breweryImagePath}" alt="Brewery Image" onerror="handleImageError(this)" style="width: 250px; height: 300px;">`;

            const breweryInfoDiv = document.getElementById('breweryInfo');
            breweryInfoDiv.innerHTML = `
                ${breweryImageHTML}
                <h2>${brewery.name}</h2>
               
                <p>
                    Type: ${brewery.brewery_type}<br>
                     ${brewery.street ? brewery.street : 'N/A'},
                             ${brewery.city},
                             ${brewery.state},
                             ${brewery.postal_code}<br>
                   ${brewery.website_url ? `<a href="${brewery.website_url}" target="_blank" class="blue-link">${brewery.website_url}</a>` : 'N/A'}<br>
                    Phone: ${formattedPhone}
                </p>`;
        })
        .catch(error => console.error('Error:', error));
}
let reviewCards = document.getElementsByClassName('review-card');

for (let card of reviewCards) {

    let ratingNumber = parseInt(card.querySelector(".rating").textContent);
    let ratingStars = card.getElementsByClassName('fa-star');
    let ratingStarsArray = ratingStars.toArray;

    for (let i = 0; i < ratingNumber; i++) {
        ratingStars[i].classList.add('checked');
        console.log('added class to star element' + i);

    }

}