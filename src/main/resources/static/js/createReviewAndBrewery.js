import {
    client
} from "./keys.js"

const urlParams = new URLSearchParams(window.location.search);
const breweryID = urlParams.get('brewery');
document.getElementById('brewFormID').value = breweryID
console.log(breweryID);

fetch('https://api.openbrewerydb.org/v1/breweries/' + breweryID)
    .then(response => response.json())
    .then(brewery => {
        // Store the brewery name in a variable
        const breweryName = brewery.name;

        // Set the brewery name in the hidden input field
        document.getElementById('breweryName').value = breweryName;

        const formattedPhone = brewery.phone ? formatPhoneNumber(brewery.phone) : 'N/A';
        const breweryInfoDiv = document.getElementById('breweryInfo');
        breweryInfoDiv.innerHTML = `<h2>${brewery.name}</h2>`;
    }).catch(error => console.error('Error:', error));


let imgUploadBtn = document.getElementById('uploadImageBtn');
imgUploadBtn.addEventListener('click', function (e) {
    e.preventDefault();
    const options = {
        onUploadDone: async (file, options) => {
            let fileURL = "";
            console.log(file.filesUploaded[0].url);
            document.getElementById('imgURL').value = file.filesUploaded[0].url;
        }
    }
    client.picker(options).open();
})

function formatPhoneNumber(phoneNumber) {
    const cleaned = ('' + phoneNumber).replace(/\D/g, '');
    const match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
    if (match) {
        return `(${match[1]})${match[2]}-${match[3]}`;
    }
    return null;
}