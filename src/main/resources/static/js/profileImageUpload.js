import { client } from "./keys.js";

let imgUploadBtn = document.getElementById('uploadImageBtn');
imgUploadBtn.addEventListener('click', function (e) {
    e.preventDefault();
    const options = {
        onUploadDone: async (file, options) => {
            document.getElementById('profileImageURL').value = file.filesUploaded[0].url;
        }
    }
    client.picker(options).open();
});
