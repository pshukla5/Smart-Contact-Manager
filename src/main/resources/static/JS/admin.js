console.log("admin script")

document.querySelector("#image_file_input").addEventListener('change', function() {

    let file = this.files[0];
    let reader = new FileReader();
    reader.onload = function(e) {

        document.getElementById("upload_image_preview").src = reader.result;
    }
    reader.readAsDataURL(file);
})
