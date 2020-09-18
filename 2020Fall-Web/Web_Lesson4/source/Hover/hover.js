function upDate(previewPic) {
    // Got the answer for the below with some help from group-mates.
    document.getElementById('image').style.backgroundImage // pulls the image of the passed previewpic variable as a background formatting.
        = "url(" + previewPic.getAttribute('src');  // gets the appropriate url for the picture from the source previewPic.
    // This gets around the issue of having different urls for each picture.
    document.getElementById('image').innerText = previewPic.alt; // pulls the alt text and updates the innerText attribute.
}

function unDo() {
    document.getElementById('image').style.backgroundImage = "url()" ; // Clears out the background image by pulling a blank url.
    document.getElementById('image').innerText = "Hover over an image below to display here"; // Returns the text to the original.
}
