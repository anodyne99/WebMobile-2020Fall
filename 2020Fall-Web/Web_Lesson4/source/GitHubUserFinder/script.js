function getGithubInfo(user) {
    //1. Create an instance of XMLHttpRequest class and send a GET request using it.
    // The function should finally return the object(it now contains the response!)
    let xhttp = new XMLHttpRequest();
    xhttp.open("GET",'https://api.github.com/users/' + user, false) // I REALLY don't like using synchronus, but it was the only way I could make it behave
    xhttp.send();
    return xhttp; // Fun thing I learned, returning does not work with asynchronus pulls
}

function showUser(user) {
    //2. set the contents of the h2 and the two div elements in the div '#profile' with the user content
    document.getElementById('userHeader').innerText = "Username";
    document.getElementById('header2').innerText = user["login"]; // Changes the display username to the correct one
    document.getElementById('idNum').innerText = "ID";
    document.getElementById('id').innerText = user["id"];
    document.getElementById('profilePic').innerText = "Click the profile image to be taken to their github page";
    document.getElementById('avatar').src = user["avatar_url"]; // Changes the source of the image to the new one
    document.getElementById('avatar').addEventListener('click', function() { // This adds the ability to click the user image to go to the user's github page
        location.href = user["html_url"]
    })
}

function noSuchUser(username) {
    document.getElementById("header2").innerHTML = username + " does not exist!" // Outputs if the user doesn't exist
}

$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        //check if the enter(i.e return) key is pressed
        if (e.which == 13) {
            //get what the user enters
            let username = $(this).val();
            //reset the text typed in the input
            $(this).val("");
            //get the user's information and store the response
            let response = getGithubInfo(username);
            //if the response is successful show the user's details
            if (response.status == 200) {
                showUser(JSON.parse(response.responseText));
                //else display suitable message
            } else {
                noSuchUser(username);
            }
        }
    })
});
