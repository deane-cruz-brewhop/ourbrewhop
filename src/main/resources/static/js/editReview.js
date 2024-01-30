// Example JavaScript for handling image upload
import { client } from "./keys.js";

let imgUploadBtn = document.getElementById('uploadImageBtn');
imgUploadBtn.addEventListener('click', function (e) {
    e.preventDefault();
    const options = {
        onUploadDone: async (file, options) => {
            // Set the value of the hidden input field to the new image URL
            document.getElementById('imgURL').value = file.filesUploaded[0].url;
        }
    }
    client.picker(options).open();
});
