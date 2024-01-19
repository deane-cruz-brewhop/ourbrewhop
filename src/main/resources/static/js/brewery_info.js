    import {
    client, yelp_api
    } from "./keys.js"

    const defaultId = "default_id";
    const urlParams = new URLSearchParams(window.location.search);
    const breweryID = urlParams.get('brewery') || defaultId;
    // const button = document.getElementById("submit-button");

    // document.getElementById('breweryIDfromJS').value = breweryID;
    document.getElementById('brewFormID').value = breweryID


    if (!breweryID) {
        console.error('Error: Brewery ID is null or undefined');
    }else {
       
    fetch('https://api.openbrewerydb.org/v1/breweries/' + breweryID)
        .then(response => response.json())
        .then(brewery => {
            const formattedPhone = brewery.phone ? formatPhoneNumber(brewery.phone) : 'N/A';


            const breweryInfoDiv = document.getElementById('breweryInfo');
            breweryInfoDiv.innerHTML = `
                    <h2>${brewery.name}</h2>
                    <p>
                        Type: ${brewery.brewery_type}<br>
                        Address: ${brewery.street ? brewery.street : 'N/A'},
                                 ${brewery.city},
                                 ${brewery.state},
                                 ${brewery.postal_code}<br>
                        Website: ${brewery.website_url ? `<a href="${brewery.website_url}" target="_blank">${brewery.website_url}</a>` : 'N/A'}<br>
                        Phone: ${formattedPhone}

                    </p>`;
                console.log(breweryID);
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

    function formatPhoneNumber(phoneNumber) {
    const cleaned = ('' + phoneNumber).replace(/\D/g, '');
    const match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
    if (match) {
        return `(${match[1]})${match[2]}-${match[3]}`;
    }
    return null;
}
