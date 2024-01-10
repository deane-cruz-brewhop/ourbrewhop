function formatPhoneNumber(phoneNumber) {
    const cleaned = ('' + phoneNumber).replace(/\D/g, '');
    const match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
    if (match) {
        return `(${match[1]}) ${match[2]}-${match[3]}`;
    }
    return null;
}

function getBreweryImagePath(breweryId) {
    return `/img/${breweryId}.jpeg`;
}

function handleImageError(imageElement) {
    imageElement.src = '/img/default.jpeg';
}

function searchBreweries() {
    const name = document.getElementById('breweryName').value;
    const city = document.getElementById('city').value;
    const state = document.getElementById('state').value;
    const type = document.getElementById('breweryType').value;

    let url = 'https://api.openbrewerydb.org/v1/breweries?per_page=10&';
    if (name) url += `by_name=${encodeURIComponent(name)}&`;
    if (city) url += `by_city=${encodeURIComponent(city)}&`;
    if (state) url += `by_state=${encodeURIComponent(state)}&`;
    if (type) url += `by_type=${type}&`;

    fetchAndDisplayBreweries(url);
}

function searchByCurrentLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(position => {
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;
            const url = `https://api.openbrewerydb.org/v1/breweries?per_page=10&by_dist=${latitude},${longitude}`;
            fetchAndDisplayBreweries(url);
        }, () => {
            alert('Unable to retrieve your location');
        });
    } else {
        alert('Geolocation is not supported by this browser.');
    }
}

function fetchAndDisplayBreweries(url) {
    document.querySelector('.hero').style.display = 'none';
    fetch(url)
        .then(response => response.json())
        .then(data => {
            const resultsDiv = document.getElementById('results');
            resultsDiv.innerHTML = ''; // Clear previous results
            data.forEach(brewery => {
                let breweryCard = document.createElement('div');
                breweryCard.classList.add('brewery-card');
                const formattedPhone = brewery.phone ? formatPhoneNumber(brewery.phone) : 'N/A';

                // Get the correct image path using the brewery id
                const breweryImagePath = getBreweryImagePath(brewery.id);

                // Define the image HTML using the path
                const breweryImageHTML = `<img src="${breweryImagePath}" alt="Brewery Image" onerror="handleImageError(this)" style="width: 150px; height: 150px;">`;

                breweryCard.innerHTML = `
                    <div class="brewery-card">
                        ${breweryImageHTML}
                        <div class="brewery-details">
                            <h2>${brewery.name}</h2>
                            <p> ${brewery.street ? brewery.street : 'N/A'}, <p>
                            <p>   ${brewery.city}, 
                                ${brewery.state}, 
                                ${brewery.postal_code}</p>
                            <p>Website: ${brewery.website_url ? `<a href="${brewery.website_url}" target="_blank">Visit Website</a>` : 'N/A'}</p>
                            <p>Phone: ${formattedPhone}</p>
                        </div>
                        <button class="view-button"><a href="/view-brewery?brewery=${brewery.id}">View Brewery</a></button>
                    </div>
                `;
                resultsDiv.appendChild(breweryCard);
            });
        })
        .catch(error => console.error('Error:', error));
}
